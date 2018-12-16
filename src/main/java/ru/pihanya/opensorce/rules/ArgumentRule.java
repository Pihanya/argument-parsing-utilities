package ru.pihanya.opensorce.rules;


public interface ArgumentRule {

  boolean check(String signature, String value);

  default boolean check(String signature) {
    return check(signature, null);
  }
}
