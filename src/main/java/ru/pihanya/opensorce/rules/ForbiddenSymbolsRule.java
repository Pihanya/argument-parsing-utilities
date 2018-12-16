package ru.pihanya.opensorce.rules;

public class ForbiddenSymbolsRule implements ArgumentRule {


  private char[] forbiddenChars;

  public ForbiddenSymbolsRule(String forbiddenCharsString) {
    this.forbiddenChars = forbiddenCharsString.toCharArray();
  }

  @Override
  public boolean check(String signature, String value) {
    char[] concatChars = (signature + value).toCharArray();

    for (char argChar : concatChars) {
      for (char forbiddenChar : forbiddenChars) {
        if (argChar == forbiddenChar) {
          return false;
        }
      }
    }

    return true;
  }
}
