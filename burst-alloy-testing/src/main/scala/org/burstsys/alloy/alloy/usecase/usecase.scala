/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.alloy.alloy

import org.burstsys.vitals.logging.VitalsLogger
import org.burstsys.vitals.reflection

import scala.collection.JavaConverters._

package object usecase extends VitalsLogger {

  private
  lazy val alloyUseCases: Array[AlloyUseCase] = {
    reflection.getSubTypesOf(
      classOf[AlloyUseCase]
    ).asScala.map(_.newInstance).toArray
  }

}
