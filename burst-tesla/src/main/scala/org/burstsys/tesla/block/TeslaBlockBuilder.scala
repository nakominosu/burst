/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.tesla.block

import org.burstsys.tesla.TeslaTypes.TeslaMemorySize
import org.burstsys.tesla.part.TeslaPartBuilder


final case class TeslaBlockBuilder() extends TeslaPartBuilder {
  override def defaultStartSize: TeslaMemorySize = ???
}
