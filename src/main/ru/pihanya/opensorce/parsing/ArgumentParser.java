package ru.pihanya.opensorce.parsing;

import com.sun.istack.internal.NotNull;

public interface ArgumentParser {

  ParseResult parse(@NotNull String arguments);
}
