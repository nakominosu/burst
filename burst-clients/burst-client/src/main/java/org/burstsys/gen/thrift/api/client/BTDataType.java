/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.gen.thrift.api.client;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2022-03-10")
public enum BTDataType implements org.apache.thrift.TEnum {
  BoolType(0),
  ByteType(1),
  ShortType(2),
  IntType(3),
  LongType(4),
  DoubleType(5),
  StringType(6);

  private final int value;

  private BTDataType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  @org.apache.thrift.annotation.Nullable
  public static BTDataType findByValue(int value) { 
    switch (value) {
      case 0:
        return BoolType;
      case 1:
        return ByteType;
      case 2:
        return ShortType;
      case 3:
        return IntType;
      case 4:
        return LongType;
      case 5:
        return DoubleType;
      case 6:
        return StringType;
      default:
        return null;
    }
  }
}
