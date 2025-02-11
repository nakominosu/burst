/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.fabric.test.mock

import org.burstsys.brio.flurry.provider.quo.BurstQuoMockData
import org.burstsys.fabric.data.model.slice.state.{FabricDataState, FabricDataWarm}
import org.burstsys.fabric.data.model.snap.FabricSnap
import org.burstsys.fabric.data.model.slice.data.FabricSliceData
import org.burstsys.fabric.data.worker.store.FabricWorkerLoader
import org.burstsys.tesla
import org.burstsys.tesla.buffer.mutable.TeslaMutableBuffer
import org.burstsys.tesla.thread.worker.TeslaWorkerCoupler
import org.burstsys.vitals.errors.VitalsException
import org.burstsys.vitals.errors._
import org.burstsys.vitals.logging._

/**
 * initialize a slice on the mock store worker side
 */
trait MockInitializer extends FabricWorkerLoader {

  final override protected
  def initializeSlice(snap: FabricSnap): FabricDataState = {
    try {
      val start = System.nanoTime
      var itemCount = 0
      var byteCount = 0
      snap.data.openForWrites()
      try {
        val result = writeParcels(snap.data, BurstQuoMockData(userCount = itemsPerSlice).pressToBuffers)
        itemCount += result._1
        byteCount += result._2
        snap.data.waitForWritesToComplete()
        snap.metadata.state = FabricDataWarm
        snap.metadata.generationMetrics.recordSliceNormalColdLoad(
          loadTookMs = ((System.nanoTime - start) / 1e6).toLong, snap.data.regionCount,
          itemCount, expectedItemCount = itemCount, potentialItemCount = itemCount, rejectedItemCount = 0,
          byteCount
        )
      } finally snap.data.closeForWrites()
    } catch safely {
      case t: Throwable =>
        val msg = burstStdMsg(t)
        log.error(msg, t)
        throw VitalsException(msg, t)
    }
    snap.metadata.state
  }

  private final val buffersPerParcel = 5

  private
  def writeParcels(slice: FabricSliceData, data: Array[TeslaMutableBuffer]): (Int, Int) = {
    var itemCount = 0
    var bufferTally = 0
    var byteCount = 0
    TeslaWorkerCoupler {
      var inflatedParcel = tesla.parcel.factory.grabParcel(10e6.toInt)
      try {
        inflatedParcel.startWrites()

        def pushOut(): Unit = {
          bufferTally = 0
          val deflatedParcel = tesla.parcel.factory.grabParcel(10e6.toInt)
          deflatedParcel.deflateFrom(inflatedParcel)
          slice queueParcelForWrite deflatedParcel
          tesla.parcel.factory.releaseParcel(inflatedParcel)
          inflatedParcel = tesla.parcel.factory.grabParcel(10e6.toInt)
          inflatedParcel.startWrites()
        }

        data foreach {
          buffer =>
            itemCount += 1
            bufferTally += 1
            if (bufferTally > buffersPerParcel) pushOut()
            inflatedParcel.writeNextBuffer(buffer)
            byteCount += inflatedParcel.currentUsedMemory
            tesla.buffer.factory.releaseBuffer(buffer)
        }

        if (bufferTally > 0) pushOut()

        (itemCount, byteCount)
      } finally tesla.parcel.factory.releaseParcel(inflatedParcel)
    }
  }

}
