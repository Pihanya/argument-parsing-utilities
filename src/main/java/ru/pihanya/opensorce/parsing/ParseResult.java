package ru.pihanya.opensorce.parsing;

import java.util.List;
import java.util.Map;
import ru.pihanya.opensorce.Argument;
import ru.pihanya.opensorce.ArgumentInfo;

public class ParseResult {
  private boolean success;
  private Map<ArgumentInfo, Argument> data;
  private List<Exception> exceptions;

  ParseResult(MutableParseResult parseResult) {
    this.success = parseResult.isSuccess();
    this.data = parseResult.getData();
    this.exceptions = parseResult.getExceptions();
  }

  public boolean isSuccess() {
    return success;
  }

  public Map<ArgumentInfo, Argument> getData() {
    return data;
  }

  public List<Exception> getExceptions() {
    return exceptions;
  }
}
