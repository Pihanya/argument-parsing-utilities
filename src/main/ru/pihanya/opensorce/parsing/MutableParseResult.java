package ru.pihanya.opensorce.parsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import ru.pihanya.opensorce.Argument;
import ru.pihanya.opensorce.ArgumentInfo;

class MutableParseResult {

  private boolean success;
  private Map<ArgumentInfo, Argument> data;
  private List<Exception> exceptions;

  public MutableParseResult() {
    this(new HashMap<>(5));
  }

  public MutableParseResult(
      Map<ArgumentInfo, Argument> data) {
    this(true, data);
  }

  public MutableParseResult(boolean success,
      Map<ArgumentInfo, Argument> data) {
    this.success = success;
    this.data = data;
    this.exceptions = new ArrayList<>(1);
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

  public void addArgument(ArgumentInfo info, Argument argument) {
    data.put(info, argument);
  }

  public MutableParseResult withSuccess(boolean success) {
    this.success = success;
    return this;
  }

  public MutableParseResult addException(Exception exception) {
    Objects.requireNonNull(exception);
    exceptions.add(exception);
    return this;
  }

  public ParseResult getUnmutableResult() {
    return new ParseResult(this);
  }
}
