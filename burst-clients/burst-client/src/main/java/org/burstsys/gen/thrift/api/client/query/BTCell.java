/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.gen.thrift.api.client.query;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2022-03-10")
public class BTCell implements org.apache.thrift.TBase<BTCell, BTCell._Fields>, java.io.Serializable, Cloneable, Comparable<BTCell> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("BTCell");

  private static final org.apache.thrift.protocol.TField C_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("cType", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField D_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("dType", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField DATUM_FIELD_DESC = new org.apache.thrift.protocol.TField("datum", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField IS_NULL_FIELD_DESC = new org.apache.thrift.protocol.TField("isNull", org.apache.thrift.protocol.TType.BOOL, (short)4);
  private static final org.apache.thrift.protocol.TField IS_NA_N_FIELD_DESC = new org.apache.thrift.protocol.TField("isNaN", org.apache.thrift.protocol.TType.BOOL, (short)5);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new BTCellStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new BTCellTupleSchemeFactory();

  /**
   * If the cell is a dimension or an aggregation
   * 
   * @see BTCellType
   */
  public @org.apache.thrift.annotation.Nullable BTCellType cType; // required
  /**
   * The datatype of the cell
   * 
   * @see org.burstsys.gen.thrift.api.client.BTDataType
   */
  public @org.apache.thrift.annotation.Nullable org.burstsys.gen.thrift.api.client.BTDataType dType; // required
  /**
   * The cell's data
   */
  public @org.apache.thrift.annotation.Nullable org.burstsys.gen.thrift.api.client.BTDatum datum; // required
  /**
   * If the cell's data is null
   */
  public boolean isNull; // required
  /**
   * If the cell's data is Not a Number
   */
  public boolean isNaN; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    /**
     * If the cell is a dimension or an aggregation
     * 
     * @see BTCellType
     */
    C_TYPE((short)1, "cType"),
    /**
     * The datatype of the cell
     * 
     * @see org.burstsys.gen.thrift.api.client.BTDataType
     */
    D_TYPE((short)2, "dType"),
    /**
     * The cell's data
     */
    DATUM((short)3, "datum"),
    /**
     * If the cell's data is null
     */
    IS_NULL((short)4, "isNull"),
    /**
     * If the cell's data is Not a Number
     */
    IS_NA_N((short)5, "isNaN");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // C_TYPE
          return C_TYPE;
        case 2: // D_TYPE
          return D_TYPE;
        case 3: // DATUM
          return DATUM;
        case 4: // IS_NULL
          return IS_NULL;
        case 5: // IS_NA_N
          return IS_NA_N;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    @org.apache.thrift.annotation.Nullable
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __ISNULL_ISSET_ID = 0;
  private static final int __ISNAN_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.C_TYPE, new org.apache.thrift.meta_data.FieldMetaData("cType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, BTCellType.class)));
    tmpMap.put(_Fields.D_TYPE, new org.apache.thrift.meta_data.FieldMetaData("dType", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, org.burstsys.gen.thrift.api.client.BTDataType.class)));
    tmpMap.put(_Fields.DATUM, new org.apache.thrift.meta_data.FieldMetaData("datum", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, org.burstsys.gen.thrift.api.client.BTDatum.class)));
    tmpMap.put(_Fields.IS_NULL, new org.apache.thrift.meta_data.FieldMetaData("isNull", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    tmpMap.put(_Fields.IS_NA_N, new org.apache.thrift.meta_data.FieldMetaData("isNaN", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.BOOL)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(BTCell.class, metaDataMap);
  }

  public BTCell() {
  }

  public BTCell(
    BTCellType cType,
    org.burstsys.gen.thrift.api.client.BTDataType dType,
    org.burstsys.gen.thrift.api.client.BTDatum datum,
    boolean isNull,
    boolean isNaN)
  {
    this();
    this.cType = cType;
    this.dType = dType;
    this.datum = datum;
    this.isNull = isNull;
    setIsNullIsSet(true);
    this.isNaN = isNaN;
    setIsNaNIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public BTCell(BTCell other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetCType()) {
      this.cType = other.cType;
    }
    if (other.isSetDType()) {
      this.dType = other.dType;
    }
    if (other.isSetDatum()) {
      this.datum = new org.burstsys.gen.thrift.api.client.BTDatum(other.datum);
    }
    this.isNull = other.isNull;
    this.isNaN = other.isNaN;
  }

  public BTCell deepCopy() {
    return new BTCell(this);
  }

  @Override
  public void clear() {
    this.cType = null;
    this.dType = null;
    this.datum = null;
    setIsNullIsSet(false);
    this.isNull = false;
    setIsNaNIsSet(false);
    this.isNaN = false;
  }

  /**
   * If the cell is a dimension or an aggregation
   * 
   * @see BTCellType
   */
  @org.apache.thrift.annotation.Nullable
  public BTCellType getCType() {
    return this.cType;
  }

  /**
   * If the cell is a dimension or an aggregation
   * 
   * @see BTCellType
   */
  public BTCell setCType(@org.apache.thrift.annotation.Nullable BTCellType cType) {
    this.cType = cType;
    return this;
  }

  public void unsetCType() {
    this.cType = null;
  }

  /** Returns true if field cType is set (has been assigned a value) and false otherwise */
  public boolean isSetCType() {
    return this.cType != null;
  }

  public void setCTypeIsSet(boolean value) {
    if (!value) {
      this.cType = null;
    }
  }

  /**
   * The datatype of the cell
   * 
   * @see org.burstsys.gen.thrift.api.client.BTDataType
   */
  @org.apache.thrift.annotation.Nullable
  public org.burstsys.gen.thrift.api.client.BTDataType getDType() {
    return this.dType;
  }

  /**
   * The datatype of the cell
   * 
   * @see org.burstsys.gen.thrift.api.client.BTDataType
   */
  public BTCell setDType(@org.apache.thrift.annotation.Nullable org.burstsys.gen.thrift.api.client.BTDataType dType) {
    this.dType = dType;
    return this;
  }

  public void unsetDType() {
    this.dType = null;
  }

  /** Returns true if field dType is set (has been assigned a value) and false otherwise */
  public boolean isSetDType() {
    return this.dType != null;
  }

  public void setDTypeIsSet(boolean value) {
    if (!value) {
      this.dType = null;
    }
  }

  /**
   * The cell's data
   */
  @org.apache.thrift.annotation.Nullable
  public org.burstsys.gen.thrift.api.client.BTDatum getDatum() {
    return this.datum;
  }

  /**
   * The cell's data
   */
  public BTCell setDatum(@org.apache.thrift.annotation.Nullable org.burstsys.gen.thrift.api.client.BTDatum datum) {
    this.datum = datum;
    return this;
  }

  public void unsetDatum() {
    this.datum = null;
  }

  /** Returns true if field datum is set (has been assigned a value) and false otherwise */
  public boolean isSetDatum() {
    return this.datum != null;
  }

  public void setDatumIsSet(boolean value) {
    if (!value) {
      this.datum = null;
    }
  }

  /**
   * If the cell's data is null
   */
  public boolean isIsNull() {
    return this.isNull;
  }

  /**
   * If the cell's data is null
   */
  public BTCell setIsNull(boolean isNull) {
    this.isNull = isNull;
    setIsNullIsSet(true);
    return this;
  }

  public void unsetIsNull() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  /** Returns true if field isNull is set (has been assigned a value) and false otherwise */
  public boolean isSetIsNull() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISNULL_ISSET_ID);
  }

  public void setIsNullIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISNULL_ISSET_ID, value);
  }

  /**
   * If the cell's data is Not a Number
   */
  public boolean isIsNaN() {
    return this.isNaN;
  }

  /**
   * If the cell's data is Not a Number
   */
  public BTCell setIsNaN(boolean isNaN) {
    this.isNaN = isNaN;
    setIsNaNIsSet(true);
    return this;
  }

  public void unsetIsNaN() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ISNAN_ISSET_ID);
  }

  /** Returns true if field isNaN is set (has been assigned a value) and false otherwise */
  public boolean isSetIsNaN() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ISNAN_ISSET_ID);
  }

  public void setIsNaNIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ISNAN_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, @org.apache.thrift.annotation.Nullable java.lang.Object value) {
    switch (field) {
    case C_TYPE:
      if (value == null) {
        unsetCType();
      } else {
        setCType((BTCellType)value);
      }
      break;

    case D_TYPE:
      if (value == null) {
        unsetDType();
      } else {
        setDType((org.burstsys.gen.thrift.api.client.BTDataType)value);
      }
      break;

    case DATUM:
      if (value == null) {
        unsetDatum();
      } else {
        setDatum((org.burstsys.gen.thrift.api.client.BTDatum)value);
      }
      break;

    case IS_NULL:
      if (value == null) {
        unsetIsNull();
      } else {
        setIsNull((java.lang.Boolean)value);
      }
      break;

    case IS_NA_N:
      if (value == null) {
        unsetIsNaN();
      } else {
        setIsNaN((java.lang.Boolean)value);
      }
      break;

    }
  }

  @org.apache.thrift.annotation.Nullable
  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case C_TYPE:
      return getCType();

    case D_TYPE:
      return getDType();

    case DATUM:
      return getDatum();

    case IS_NULL:
      return isIsNull();

    case IS_NA_N:
      return isIsNaN();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case C_TYPE:
      return isSetCType();
    case D_TYPE:
      return isSetDType();
    case DATUM:
      return isSetDatum();
    case IS_NULL:
      return isSetIsNull();
    case IS_NA_N:
      return isSetIsNaN();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof BTCell)
      return this.equals((BTCell)that);
    return false;
  }

  public boolean equals(BTCell that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_cType = true && this.isSetCType();
    boolean that_present_cType = true && that.isSetCType();
    if (this_present_cType || that_present_cType) {
      if (!(this_present_cType && that_present_cType))
        return false;
      if (!this.cType.equals(that.cType))
        return false;
    }

    boolean this_present_dType = true && this.isSetDType();
    boolean that_present_dType = true && that.isSetDType();
    if (this_present_dType || that_present_dType) {
      if (!(this_present_dType && that_present_dType))
        return false;
      if (!this.dType.equals(that.dType))
        return false;
    }

    boolean this_present_datum = true && this.isSetDatum();
    boolean that_present_datum = true && that.isSetDatum();
    if (this_present_datum || that_present_datum) {
      if (!(this_present_datum && that_present_datum))
        return false;
      if (!this.datum.equals(that.datum))
        return false;
    }

    boolean this_present_isNull = true;
    boolean that_present_isNull = true;
    if (this_present_isNull || that_present_isNull) {
      if (!(this_present_isNull && that_present_isNull))
        return false;
      if (this.isNull != that.isNull)
        return false;
    }

    boolean this_present_isNaN = true;
    boolean that_present_isNaN = true;
    if (this_present_isNaN || that_present_isNaN) {
      if (!(this_present_isNaN && that_present_isNaN))
        return false;
      if (this.isNaN != that.isNaN)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCType()) ? 131071 : 524287);
    if (isSetCType())
      hashCode = hashCode * 8191 + cType.getValue();

    hashCode = hashCode * 8191 + ((isSetDType()) ? 131071 : 524287);
    if (isSetDType())
      hashCode = hashCode * 8191 + dType.getValue();

    hashCode = hashCode * 8191 + ((isSetDatum()) ? 131071 : 524287);
    if (isSetDatum())
      hashCode = hashCode * 8191 + datum.hashCode();

    hashCode = hashCode * 8191 + ((isNull) ? 131071 : 524287);

    hashCode = hashCode * 8191 + ((isNaN) ? 131071 : 524287);

    return hashCode;
  }

  @Override
  public int compareTo(BTCell other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCType()).compareTo(other.isSetCType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.cType, other.cType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDType()).compareTo(other.isSetDType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dType, other.dType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDatum()).compareTo(other.isSetDatum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDatum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.datum, other.datum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIsNull()).compareTo(other.isSetIsNull());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsNull()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isNull, other.isNull);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetIsNaN()).compareTo(other.isSetIsNaN());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIsNaN()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.isNaN, other.isNaN);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  @org.apache.thrift.annotation.Nullable
  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("BTCell(");
    boolean first = true;

    sb.append("cType:");
    if (this.cType == null) {
      sb.append("null");
    } else {
      sb.append(this.cType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("dType:");
    if (this.dType == null) {
      sb.append("null");
    } else {
      sb.append(this.dType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("datum:");
    if (this.datum == null) {
      sb.append("null");
    } else {
      sb.append(this.datum);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("isNull:");
    sb.append(this.isNull);
    first = false;
    if (!first) sb.append(", ");
    sb.append("isNaN:");
    sb.append(this.isNaN);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (cType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'cType' was not present! Struct: " + toString());
    }
    if (dType == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'dType' was not present! Struct: " + toString());
    }
    if (datum == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'datum' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'isNull' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'isNaN' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class BTCellStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BTCellStandardScheme getScheme() {
      return new BTCellStandardScheme();
    }
  }

  private static class BTCellStandardScheme extends org.apache.thrift.scheme.StandardScheme<BTCell> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, BTCell struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // C_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.cType = org.burstsys.gen.thrift.api.client.query.BTCellType.findByValue(iprot.readI32());
              struct.setCTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // D_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.dType = org.burstsys.gen.thrift.api.client.BTDataType.findByValue(iprot.readI32());
              struct.setDTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATUM
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.datum = new org.burstsys.gen.thrift.api.client.BTDatum();
              struct.datum.read(iprot);
              struct.setDatumIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // IS_NULL
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isNull = iprot.readBool();
              struct.setIsNullIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // IS_NA_N
            if (schemeField.type == org.apache.thrift.protocol.TType.BOOL) {
              struct.isNaN = iprot.readBool();
              struct.setIsNaNIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.isSetIsNull()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'isNull' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.isSetIsNaN()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'isNaN' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, BTCell struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.cType != null) {
        oprot.writeFieldBegin(C_TYPE_FIELD_DESC);
        oprot.writeI32(struct.cType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.dType != null) {
        oprot.writeFieldBegin(D_TYPE_FIELD_DESC);
        oprot.writeI32(struct.dType.getValue());
        oprot.writeFieldEnd();
      }
      if (struct.datum != null) {
        oprot.writeFieldBegin(DATUM_FIELD_DESC);
        struct.datum.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(IS_NULL_FIELD_DESC);
      oprot.writeBool(struct.isNull);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(IS_NA_N_FIELD_DESC);
      oprot.writeBool(struct.isNaN);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class BTCellTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public BTCellTupleScheme getScheme() {
      return new BTCellTupleScheme();
    }
  }

  private static class BTCellTupleScheme extends org.apache.thrift.scheme.TupleScheme<BTCell> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, BTCell struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      oprot.writeI32(struct.cType.getValue());
      oprot.writeI32(struct.dType.getValue());
      struct.datum.write(oprot);
      oprot.writeBool(struct.isNull);
      oprot.writeBool(struct.isNaN);
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, BTCell struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      struct.cType = org.burstsys.gen.thrift.api.client.query.BTCellType.findByValue(iprot.readI32());
      struct.setCTypeIsSet(true);
      struct.dType = org.burstsys.gen.thrift.api.client.BTDataType.findByValue(iprot.readI32());
      struct.setDTypeIsSet(true);
      struct.datum = new org.burstsys.gen.thrift.api.client.BTDatum();
      struct.datum.read(iprot);
      struct.setDatumIsSet(true);
      struct.isNull = iprot.readBool();
      struct.setIsNullIsSet(true);
      struct.isNaN = iprot.readBool();
      struct.setIsNaNIsSet(true);
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
