/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.dash.service.execution

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.burstsys.dash.endpoints.ClientJsonObject
import org.burstsys.fabric.execution.model.execute.parameters.FabricCall
import org.burstsys.fabric.execution.model.result.status.{FabricInProgressResultStatus, FabricResultStatus}
import org.burstsys.fabric.metadata.model.over.FabricOver
import org.burstsys.vitals.json.VitalsJsonSanatizers.Values
import org.burstsys.vitals.uid.VitalsUid

import scala.collection.mutable

final case
class RequestState(
                    guid: VitalsUid,
                    over: FabricOver,
                    call: Option[FabricCall],
                    startTime: Long = System.currentTimeMillis(),
                    var state: FabricResultStatus = FabricInProgressResultStatus,
                    var status: String = "Preparing Request",
                    var endTime: Long = 0,
                    var wave: Option[WaveState] = None
                  ) extends ClientJsonObject {
  @JsonSerialize(contentUsing = classOf[Values])
  val source: mutable.ArrayBuffer[String] = mutable.ArrayBuffer[String]()
}

