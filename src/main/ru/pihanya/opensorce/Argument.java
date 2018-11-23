package ru.pihanya.opensorce;

import java.util.Objects;

public class Argument {

  private String signature;
  private String value;

  public Argument(String signature, String value) {
    Objects.requireNonNull(signature);

    this.signature = signature;
    this.value = value;
  }

  public String getSignature() {
    return signature;
  }

  public String getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "{" + signature + "=" + value + "}";
  }
}
