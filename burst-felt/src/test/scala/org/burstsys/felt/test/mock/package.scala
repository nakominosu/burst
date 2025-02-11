/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.felt.test

import org.burstsys.brio.model.schema.BrioSchema
import org.burstsys.brio.runtime.BrioThreadRuntime
import org.burstsys.brio.types.BrioPath.BrioPathKey
import org.burstsys.fabric.execution.model.execute.invoke.FabricInvocation
import org.burstsys.felt.binding.FeltBinding
import org.burstsys.felt.model.analysis.decl.FeltAnalysisDecl
import org.burstsys.felt.model.collectors.FeltCollectorProviders
import org.burstsys.felt.model.collectors.cube.FeltCubeProvider
import org.burstsys.felt.model.collectors.route.FeltRouteProvider
import org.burstsys.felt.model.collectors.runtime.FeltCollectorBuilder
import org.burstsys.felt.model.collectors.shrub.FeltShrubProvider
import org.burstsys.felt.model.collectors.tablet.FeltTabletProvider
import org.burstsys.felt.model.frame.FeltFrameDecl
import org.burstsys.felt.model.mutables.FeltMutableProviders
import org.burstsys.felt.model.mutables.valarr.FeltMutableValArrProv
import org.burstsys.felt.model.mutables.valmap.FeltMutableValMapProv
import org.burstsys.felt.model.mutables.valset.FeltMutableValSetProv
import org.burstsys.felt.model.reference.path.{FeltPathExpr, FeltSimplePath}
import org.burstsys.felt.model.runtime.{FeltPlaneRuntimeContext, FeltRuntime}
import org.burstsys.felt.model.schema.decl.{FeltSchemaDecl, FeltSchemaExtension}
import org.burstsys.felt.model.schema.traveler.FeltTraveler
import org.burstsys.felt.model.sweep.FeltSweep
import org.burstsys.felt.model.sweep.splice.FeltPlacementKey
import org.burstsys.felt.model.tree.FeltGlobal
import org.burstsys.felt.model.variables.global.FeltGlobVarDecl
import org.burstsys.felt.model.variables.parameter.FeltParamDecl
import org.burstsys.ginsu.runtime.GinsuRuntime
import org.burstsys.vitals.uid.newBurstUid
import org.joda.time.DateTimeZone

package object mock {
  val mockBrioSchema: BrioSchema = BrioSchema("unity")

  object MockCollectorProviders extends FeltCollectorProviders {
    override val cubes: FeltCubeProvider = null
    override val routes: FeltRouteProvider = null
    override val tablets: FeltTabletProvider = null
    override val shrubs: FeltShrubProvider = null
  }

  object MockMutableProviders extends FeltMutableProviders {
    override val valarr: FeltMutableValArrProv = null
    override val valset: FeltMutableValSetProv = null
    override val valmap: FeltMutableValMapProv = null
  }

  object MockBinding extends FeltBinding {
    override val name: String = "MockBinding"
    override val sweepClass: Class[_ <: FeltSweep] = classOf[MockSweep]
    override val sweepRuntimeClass: Class[_ <: FeltRuntime] = classOf[MockRuntime]
    override val mutables: FeltMutableProviders = MockMutableProviders
    override val collectors: FeltCollectorProviders = MockCollectorProviders
  }

  val theGlobal: FeltGlobal = FeltGlobal(
    brioSchema = mockBrioSchema, source = "mock source sample", travelerClassName = newBurstUid,
    binding = MockBinding
  )

  trait MockTravelerRuntime extends FeltRuntime

  object MockSchema extends FeltSchemaDecl {
    global = theGlobal
    override val schemaName: FeltPathExpr = FeltSimplePath("unity")
    override val schemaExtensions: Array[FeltSchemaExtension] = Array.empty
  }

  object MockAnalysis extends FeltAnalysisDecl {
    global = theGlobal
    override val sourcePrefix: String = "Foo Bar"
    override val analysisName: String = "MyAnalysis"
    override val schemaDecl: FeltSchemaDecl = MockSchema
    override val frames: Array[FeltFrameDecl] = Array.empty
    override val parameters: Array[FeltParamDecl] = Array.empty
    override val variables: Array[FeltGlobVarDecl] = Array.empty

    override def timezone: DateTimeZone =
      ???
  }

  theGlobal.analysis = MockAnalysis


  abstract
  class MockRuntime(invocation: FabricInvocation)
    extends FeltPlaneRuntimeContext(invocation)
      with FeltRuntime with GinsuRuntime {
    @inline override implicit def threadRuntime: BrioThreadRuntime = this

    final val binding: FeltBinding = MockBinding
  }

  class MockTraveler extends FeltTraveler[MockRuntime] {
    override val travelerClassName: String = "MockTravelerClassName"
    override val runtimeClassName: String = "MockRuntimeClassName"
    override val brioSchema: BrioSchema = mockBrioSchema

    override def apply(runtime: MockRuntime, sweep: FeltSweep): Unit = {

    }
  }

  case
  class MockSweep() extends FeltSweep {
    override val sweepName: String = "MockSweep"
    override val sweepClassName: String = "MockSweepClass"
    override val feltTraveler: FeltTraveler[_] = new MockTraveler
    override val feltBinding: FeltBinding = MockBinding

    override def apply(runtime: FeltRuntime): Unit = {

    }

    override def newRuntime(call: FabricInvocation): FeltRuntime = {
      ???
    }

    override def rootSplice(runtime: FeltRuntime, path: BrioPathKey, placement: FeltPlacementKey): Unit = {

    }

    override def collectorBuilders: Array[_ <: FeltCollectorBuilder] = Array.empty

    override def dynamicRelationSplices(runtime: FeltRuntime, pathKey: BrioPathKey, placement: FeltPlacementKey): Unit = {

    }

  }

}
