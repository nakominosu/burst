/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.felt.model.ginsu.group

import org.burstsys.felt.model.sweep.symbols.sweepRuntimeSym
import org.burstsys.felt.model.tree.code.{FeltCode, FeltCodeCursor, I, I2, I3, T}
import org.burstsys.felt.model.types.FeltType

/**
 * generate the Ginsu enum function.
 * TODO: This should generate an enum signature that does not require the creation of an ARRAY (see [[org.burstsys.ginsu.functions.group.GinsuEnumFunctions]])
 */
trait FeltGinsuGrpSplitFuncExpr extends FeltGinsuGrpFuncExpr {

  final override val nodeName = "ginsu-split-call"

  final override val usage: String =
    s"""
       |usage: $functionName(value_expression*) -> value_expression
       |  bucket/group a value expression into a set of expression defined range boundaries""".stripMargin

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // TYPE INFERENCE
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  final override
  def resolveTypes: this.type = {
    parameters.foreach(_.resolveTypes)
    feltType = FeltType.combine(parameters.map(_.feltType): _*)
    this
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // CODE GENERATION
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////

  final override
  def generateExpression(implicit cursor: FeltCodeCursor): FeltCode = {
    parameterCountAtLeast(2)

    val domain = generatedDomain

    val domainExpressions = domain.map(_._2).mkString

    val domainNullity = domain.map(_._1).map {
      c => c.callScope.scopeNull
    }.mkString(" || ")

    val domainSplits = domain.take(parameters.length - 1).map(_._1).map {
      c => c.callScope.scopeVal
    }.mkString(", ")

    val domainTest = domain.map(_._1).last.callScope.scopeVal

    val functionName = s"${feltType.valueTypeAsFelt}SplitSlice"

    s"""|
        |${T(this)}
        |$domainExpressions
        |${I}if($domainNullity) {
        |$I2${cursor.callScope.scopeNull} = true;
        |$I} else {
        |$I2${cursor.callScope.scopeNull} = false;
        |$I2${cursor.callScope.scopeVal} = $sweepRuntimeSym.$functionName(
        |${I3}Array($domainSplits),
        |${I3}$domainTest
        |$I2);
        |$I}""".stripMargin
  }
}
