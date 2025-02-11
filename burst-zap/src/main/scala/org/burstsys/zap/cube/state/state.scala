/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.zap.cube

package object state {

  /**
   * offset from the beginning of off heap memory where the bucket array starts.
   */
  private[state]
  val bucketBlockOffset: ZapMemoryOffset = 0 // at the beginning

}
