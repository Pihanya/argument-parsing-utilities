package ru.pihanya.opensorce;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ArgumentInfo {

  private String name;
  private List<String> signatures;
  private String description;

  public ArgumentInfo(String name, List<String> signatures) {
    this(name, signatures, null);
  }

  public ArgumentInfo(String name, List<String> signatures,
      String description) {
    Objects.requireNonNull(name, "Name of the argument cannot be null");
    Objects.requireNonNull(signatures, "Signatures list of the argument cannot be null");

    if (signatures.size() < 1) {
      throw new IllegalArgumentException("Argument must have at least one signature");
    }

    this.name = name.toLowerCase();
    this.signatures = signatures;
    this.description = description;
  }

  public List<String> getSignatures() {
    return signatures;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ArgumentInfo)) {
      return false;
    }

    return ((ArgumentInfo) o).name.equals(name);
  }

  @Override
  public int hashCode() {
    char[] nameChars = name.toCharArray();
    int hash = 0;
    for (char c : nameChars) {
      hash += (31 * c);
    }
    return hash;
  }

  @Override
  public String toString() {
    return "{"
        + name
        + ": ["
        + String.join("," + Arrays.toString(signatures.toArray(new String[0])))
        + "]"
        + "}";
  }
}
