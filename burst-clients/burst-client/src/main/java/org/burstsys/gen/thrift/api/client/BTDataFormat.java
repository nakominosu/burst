/* Copyright Yahoo, Licensed under the terms of the Apache 2.0 license. See LICENSE file in project root for terms. */
package org.burstsys.gen.thrift.api.client;


@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.12.0)", date = "2022-03-10")
public enum BTDataFormat implements org.apache.thrift.TEnum {
  /**
   * a single primitive datum
   */
  Scalar(0),
  /**
   * a list of data
   */
  Vector(1),
  /**
   * a key-value set of data
   */
  Map(2);

  private final int value;

  private BTDataFormat(int value) {
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
  public static BTDataFormat findByValue(int value) { 
    switch (value) {
      case 0:
        return Scalar;
      case 1:
        return Vector;
      case 2:
        return Map;
      default:
        return null;
    }
  }
}
