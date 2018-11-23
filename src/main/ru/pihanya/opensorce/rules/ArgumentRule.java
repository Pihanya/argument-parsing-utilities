package ru.pihanya.opensorce.rules;

import com.sun.istack.internal.Nullable;

public interface ArgumentRule {

  boolean check(String signature, @Nullable String value);

  default boolean check(String signature) {
    return check(signature, null);
  }
}
