/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.vitals.errors

import com.esotericsoftware.kryo.io.{Input, Output}
import com.esotericsoftware.kryo.{Kryo, KryoSerializable}
import org.burstsys.vitals.logging.VitalsLogger

import scala.concurrent.duration.Duration

/**
 * add 'fault' memory to a type. Record one or more faults that ''go away'' or ''heal'' over time
 * as retries are implemented.
 */
trait VitalsHealingFault {

  /**
   * heal this fault manually
   */
  def manualHeal(): Unit

  /**
   * how long before a recorded fault 'heals'
   *
   * @return
   */
  def healDuration: Duration

  /**
   * record a first or additional fault
   *
   * @param t
   */
  def recordFault(t: Throwable): Unit

  /**
   * current fault count
   *
   * @return
   */
  def faultCount: Int

  /**
   * the last fault if there was one or more or None if there were not
   * or if the failures ''healed''
   *
   * @return
   */
  def fault: Option[Throwable]

}

object VitalsHealingFault {

  def apply(healDuration: Duration): VitalsHealingFault = VitalsHealingFaultContext(healDuration)

}

private[vitals] final case
class VitalsHealingFaultContext(var healDuration: Duration) extends VitalsHealingFault with KryoSerializable with VitalsLogger {

  private[this] var _fault: Option[Throwable] = None

  private[this] var _lastFaultTime: Long = -1

  private[this] var _faultCount: Int = 0

  private def healIfTime(): Unit = if ((System.currentTimeMillis - _lastFaultTime) > healDuration.toMillis) manualHeal()

  override def recordFault(t: Throwable): Unit = {
    healIfTime()
    _lastFaultTime = System.currentTimeMillis()
    _fault = Some(t)
    _faultCount += 1
  }

  override def faultCount: Int = {
    healIfTime()
    _faultCount
  }

  override def fault: Option[Throwable] = _fault

  override def manualHeal(): Unit = {
    log info s"VitalsHealingFault.manualHeal($healDuration)"
    _faultCount = 0
    _fault = None
  }

  ////////////////////////////////////////////////////////////////////////////
  // KRYO SERDE
  ////////////////////////////////////////////////////////////////////////////

  def this() = this(null)

  override def write(kryo: Kryo, output: Output): Unit = {
    output.writeLong(_lastFaultTime)
    output.writeLong(healDuration.toNanos)
    output.writeInt(_faultCount)
    output.writeBoolean(fault.nonEmpty)
    if (fault.nonEmpty) kryo.writeClassAndObject(output, fault)
  }

  override def read(kryo: Kryo, input: Input): Unit = {
    _lastFaultTime = input.readLong
    healDuration = Duration.fromNanos(input.readLong)
    _faultCount = input.readInt
    if (input.readBoolean) _fault = Some(kryo.readClassAndObject(input).asInstanceOf[Throwable]) else _fault = None
  }

}
