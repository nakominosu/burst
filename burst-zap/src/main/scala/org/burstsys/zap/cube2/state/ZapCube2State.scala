/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.zap.cube2.state

import org.burstsys.brio.dictionary.flex.{BrioFlexDictionary, BrioFlexDictionaryAnyVal}
import org.burstsys.tesla
import org.burstsys.tesla.TeslaTypes.{SizeOfLong, TeslaMemoryOffset, TeslaMemorySize}
import org.burstsys.tesla.offheap
import org.burstsys.tesla.pool.{TeslaPoolId, TeslaPooledResource}
import org.burstsys.zap.cube2.key.{ZapCube2Key, ZapCube2KeyAnyVal}
import org.burstsys.zap.cube2.row.{ZapCube2Row, ZapCube2RowAnyVal, limitExceededMarkerRow}
import org.burstsys.zap.cube2.{ZapCube2, ZapCube2Builder}

/**
 * ==Off Heap G2 Cube State==
 * {{{
 *   FIXED LENGTH HEADER (always the same size structure for all cubes)
 *   [ POOL_ID           | INTEGER ] the runtime identity of the hosting pool
 *   [ DIM_COUNT         | BYTE ] the number of dimensions (max of 64)
 *   [ AGG_COUNT         | BYTE ] the number of aggregations (max of 64)
 *
 *   [ BUCKETS START     | INTEGER ] offset to start of schema defined size bucket block
 *   [ BUCKETS_COUNT     | INTEGER ] the schema defined number of buckets in this cube
 *
 *   [ CURSOR START      | INTEGER ] offset to start of schema defined size current cursor key block
 *   [ CURSOR_ROW        | INTEGER ] offset to row that cursor points to
 *   [ CURSOR_UPDATED    | BOOLEAN ] the cursor has mutated at runtime
 *
 *   [ PIVOT START       | INTEGER ] offset to start of schema defined size current cursor key block
 *
 *   [ ROW_SIZE          | INTEGER ] the schema defined size of each row
 *   [ ROWS START        | INTEGER ] offset to start of dynamically sized row block
 *   [ ROWS END          | INTEGER ] offset to end of dynamically sized row block
 *   [ ROWS COUNT        | INTEGER ] current number of rows
 *   [ RESIZE_COUNT      | INTEGER ] how many times did this cube get resized?
 *   [ ROWS_LIMITED      | BOOLEAN ] too many rows for current chunk of off-heap memory
 *   [ CURSOR_ROW        | INTEGER ] the offset of the  row current cursor points at
 *
 *   [ DICTIONARY        | INTEGER ] the index for the flex dictionary
 *
 * ---------- endOfFixedSizeHeader (static definition)  --------------
 *
 *   SCHEMA DEFINED LENGTH DATA  ( structure size defined by schema )
 *   [ BUCKETS           | ARRAY[LONG] ] the list of buckets
 *   [ CURSOR            | ARRAY[LONG] ] a [[ZapCube2Key]] holding current row key
 *   [ PIVOT             | ARRAY[LONG] ] a [[ZapCube2Key]] holding a tmp join pivot key
 *
 *   DYNAMIC LENGTH DATA (structure size grows at runtime)
 *   [ ROWS              | ARRAY[BYTE] ] the list of keys in the current cursor (this grows over time)
 * }}}
 */
trait ZapCube2State extends Any with TeslaPooledResource with ZapCube2 {

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Pool Id Field
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def poolId: TeslaPoolId = offheap.getInt(basePtr + poolIdFieldOffset)

  @inline final
  def poolId_=(w: TeslaPoolId): Unit = offheap.putInt(basePtr + poolIdFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Dim Count Field
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def dimCount: Int = offheap.getInt(basePtr + dimCountFieldOffset)

  @inline final
  def dimCount_=(w: Int): Unit = offheap.putInt(basePtr + dimCountFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Agg Count Field
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def aggCount: Int = offheap.getByte(basePtr + aggCountFieldOffset)

  @inline final
  def aggCount_=(w: Int): Unit = offheap.putByte(basePtr + aggCountFieldOffset, w.toByte)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // bucket start offset
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def bucketsStart: TeslaMemoryOffset = offheap.getInt(basePtr + bucketsStartFieldOffset)

  @inline final
  def bucketsStart_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + bucketsStartFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Bucket Count Field - how many buckets in this cube
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def bucketsCount: Int = offheap.getInt(basePtr + bucketsCountFieldOffset)

  @inline final
  def bucketsCount_=(w: Int): Unit = offheap.putInt(basePtr + bucketsCountFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // cursor start offset
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def cursorStart: TeslaMemoryOffset = offheap.getInt(basePtr + cursorStartFieldOffset)

  @inline final
  def cursorStart_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + cursorStartFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // was cursor row updated
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def cursorUpdated: Boolean = offheap.getInt(basePtr + cursorUpdatedFieldOffset) != 0

  @inline final
  def cursorUpdated_=(w: Boolean): Unit = offheap.putInt(basePtr + cursorUpdatedFieldOffset, if (w) 1 else 0)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // pivot start offset
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def pivotStart: TeslaMemoryOffset = offheap.getInt(basePtr + pivotStartFieldOffset)

  @inline final
  def pivotStart_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + pivotStartFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Row Size Field - how many bytes in each cube
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def rowSize: Int = offheap.getInt(basePtr + rowSizeFieldOffset)

  @inline final
  def rowSize_=(w: Int): Unit = offheap.putInt(basePtr + rowSizeFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // rows start offset
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def rowsStart: TeslaMemoryOffset = offheap.getInt(basePtr + rowsStartFieldOffset)

  @inline final
  def rowsStart_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + rowsStartFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // rows end offset
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def rowsEnd: TeslaMemoryOffset = offheap.getInt(basePtr + rowsEndFieldOffset)

  @inline final
  def rowsEnd_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + rowsEndFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // row count
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def rowsCount: Int = offheap.getInt(basePtr + rowsCountFieldOffset)

  @inline final
  def rowsCount_=(w: Int): Unit = offheap.putInt(basePtr + rowsCountFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // row count
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def resizeCount: Int = offheap.getInt(basePtr + resizeCountFieldOffset)

  @inline final
  def resizeCount_=(w: Int): Unit = offheap.putInt(basePtr + resizeCountFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // row limited Field
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def rowsLimited: Boolean = offheap.getInt(basePtr + rowsLimitedFieldOffset) != 0

  @inline final
  def rowsLimited_=(w: Boolean): Unit = offheap.putInt(basePtr + rowsLimitedFieldOffset, if (w) 1 else 0)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // cursor row start field
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def cursorRow: TeslaMemoryOffset = offheap.getInt(basePtr + cursorRowFieldOffset)

  @inline final
  def cursorRow_=(w: TeslaMemoryOffset): Unit = offheap.putInt(basePtr + cursorRowFieldOffset, w)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // local flex dictionary
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def dictionary: BrioFlexDictionary = BrioFlexDictionaryAnyVal(offheap.getInt(basePtr + dictionaryFieldOffset))

  @inline final override
  def dictionary_=(d: BrioFlexDictionary): Unit = offheap.putInt(basePtr + dictionaryFieldOffset, d.index)

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Cursors
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def cursor: ZapCube2Key = ZapCube2KeyAnyVal(basePtr + cursorStart)

  @inline final override
  def resetCursor(): Unit = ZapCube2Key(basePtr + cursorStart, dimCount) // TODO rewrite this initialize pivot

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Pivot
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final override
  def pivot: ZapCube2Key = ZapCube2KeyAnyVal(basePtr + pivotStart)

  @inline final override
  def resetPivot(): Unit = ZapCube2Key(basePtr + pivotStart, dimCount) // initialize pivot

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // Buckets
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  @inline final
  def bucketRead(index: Int): TeslaMemoryOffset =
    offheap.getInt(basePtr + bucketsStart + (index * SizeOfLong))

  @inline final
  def bucketWrite(index: Int, offset: TeslaMemoryOffset): Unit =
    offheap.putInt(basePtr + bucketsStart + (index * SizeOfLong), offset)

  @inline final
  def resetBuckets(): Unit = tesla.offheap.setMemory(basePtr + bucketsStart, bucketsCount * SizeOfLong, 0)

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // Row Management
  //////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * truncate row set to k or the row count whichever is smaller
   *
   * @param k
   */
  @inline final
  def truncateRows(k: Int): Unit = {
    if (k > rowsCount) return
    rowsCount = math.min(rowsCount, k)
    rowsEnd = rowsStart + (rowsCount * rowSize)
  }

  @inline final override
  def row(index: Int): ZapCube2Row = ZapCube2RowAnyVal(basePtr + rowOffset(index))

  @inline final
  def rowOffset(row: ZapCube2Row): TeslaMemoryOffset = (row.basePtr - this.basePtr).toInt

  @inline final
  def rowOffset(row: Int): TeslaMemoryOffset = rowsStart + (rowSize * row)

  @inline final override
  def isEmpty: Boolean = rowsCount == 0

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // flex support
  //////////////////////////////////////////////////////////////////////////////////////////////////

  final override def currentMemorySize: TeslaMemorySize = rowsEnd // we keep the current end in an off heap field

  /**
   * import a too small ''source'' cube into the ''local'' cube as a destination
   *
   * @param source
   * @param rows
   */
  @inline final
  def importCube(source: ZapCube2, rows: Int): Unit = {
    val localPoolId = this.poolId
    tesla.offheap.copyMemory(source.basePtr, this.basePtr, source.currentMemorySize)
    this.poolId = localPoolId
    this.rowsCount = rows
    this.resizeCount = source.resizeCount + 1
    this.rowsLimited = false
  }

  //////////////////////////////////////////////////////////////////////////////////////////////////
  // row creation and access
  //////////////////////////////////////////////////////////////////////////////////////////////////


  /**
   *
   * @param key
   * @return
   */
  @inline final
  def createNewRowFromKey(key: ZapCube2Key): ZapCube2Row = {
    val row = newRow
    if (row == limitExceededMarkerRow) row else {
      row.initializeFromKey(key)
      row
    }
  }

  @inline final
  def cloneRowFromRow(thatRow: ZapCube2Row): ZapCube2Row = {
    val row = newRow
    if (row == limitExceededMarkerRow) row else {
      row.initializeFromRow(thatRow)
      row
    }
  }

  @inline final private
  def newRow: ZapCube2Row = {
    val offset = rowOffset(rowsCount)
    if (offset + rowSize > availableMemorySize) {
      rowsLimited = true
      limitExceededMarkerRow
    } else {
      val row = ZapCube2Row(basePtr + offset, dimCount, aggCount)
      rowsEnd += rowSize
      rowsCount += 1
      row
    }
  }

  @inline final override
  def resetDirtyRows(): Unit = {
    var r = 0
    while (r < rowsCount) {
      row(r).dirty = false
      r += 1
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // lifecycle
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

  /**
   * Called when cube is first allocated and when it is re-used
   *
   * @param pId
   * @param builder
   */
  @inline final override
  def initialize(pId: TeslaPoolId, builder: ZapCube2Builder): Unit = {
    poolId = pId
    reset(builder)
  }

  @inline final override
  def reset(builder: ZapCube2Builder): Unit = {
    val keySize = byteSize(builder.dimensionCount)
    dimCount = builder.dimensionCount
    aggCount = builder.aggregationCount
    rowsLimited = false
    rowsCount = 0
    resizeCount = 0
    rowSize = ZapCube2Row.byteSize(builder.dimensionCount, builder.aggregationCount)
    bucketsCount = builder.bucketCount
    bucketsStart = endOfFixedSizeHeader
    resetBuckets()
    cursorUpdated = true
    cursorStart = bucketsStart + (bucketsCount * SizeOfLong)
    resetCursor()
    pivotStart = cursorStart + keySize
    resetPivot()
    rowsStart = pivotStart + keySize
    rowsEnd = rowsStart
    resetDirtyRows()
  }

  @inline final override
  def clear(): Unit = {
    rowsLimited = false
    rowsCount = 0
    resizeCount = 0
    resetBuckets()
    cursorUpdated = true
    resetCursor()
    resetPivot()
    rowsEnd = rowsStart
    resetDirtyRows()
  }

}