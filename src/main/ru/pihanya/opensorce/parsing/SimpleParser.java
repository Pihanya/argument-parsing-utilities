package ru.pihanya.opensorce.parsing;

import com.sun.istack.internal.NotNull;
import java.util.Objects;
import ru.pihanya.opensorce.Argument;
import ru.pihanya.opensorce.ArgumentInfo;

public class SimpleParser implements ArgumentParser {

  private ArgumentInfoProvider provider;

  public SimpleParser(@NotNull ArgumentInfoProvider provider) {
    this.provider = provider;
  }

  @Override
  public ParseResult parse(String argumentsString) {
    Objects.requireNonNull(argumentsString);

    MutableParseResult result = new MutableParseResult();

    String[] arguments = argumentsString.split(" ");
    for (int index = 0; index < arguments.length; ) {
      // --argument=value (--file="/mnt/c/hello.txt"), --verbose
      // -a=value (-n=12), -a value (-n 13), -b "value" (-f "/mnt/c/hello.txt")
      // -v

      String argument = arguments[index++];
      String signature;
      if (argument.startsWith("--")) {
        String[] ar = argument.split("=");
        signature = ar[0].substring(2);

        ArgumentInfo argInfo = getArgumentInfoBySignature(signature);
        if (argInfo == null) {
          result.withSuccess(false);
          continue;
        }

        if (ar.length == 2) {
          result
              .addArgument(argInfo, new Argument(signature, escapeQuotes(ar[1])));
        } else {
          result
              .withSuccess(false)
              .addException(new IllegalArgumentException("Illegal argument format: " + argument));
        }
      } else if (argument.startsWith("-")) {
        signature = argument.substring(1);

        ArgumentInfo argInfo = getArgumentInfoBySignature(signature);
        if (argInfo == null) {
          result.withSuccess(false);
          continue;
        }

        if (index >= arguments.length) {
          result.addArgument(argInfo, null);
        } else if (!isSignatureSequence(arguments[index])) {
          result.addArgument(argInfo, new Argument(signature, escapeQuotes(arguments[index++])));
        } else {
          continue;
        }
      } else {
        result
            .withSuccess(false)
            .addException(new IllegalArgumentException("Incorrect argument format: " + argument));
      }
    }

    return result.getUnmutableResult();
  }

  private ArgumentInfo getArgumentInfoBySignature(String signature) {
    for (ArgumentInfo info : provider.getArgumentInfoList()) {
      if (info.getSignatures().contains(signature)) {
        return info;
      }
    }

    return null;
  }


  private boolean isSignatureSequence(String argumentPart) {
    return argumentPart.charAt(0) == '-';
  }

  private String escapeQuotes(@NotNull String str) {
    int leftIndex = 0;
    int rightIndex = str.length() - 1;

    while (leftIndex < rightIndex) {
      if (isQuote(str.charAt(leftIndex))) {
        ++leftIndex;
      } else {
        break;
      }
    }

    while (rightIndex > leftIndex) {
      if (isQuote(str.charAt(rightIndex))) {
        --rightIndex;
      } else {
        break;
      }
    }

    return str.substring(leftIndex, rightIndex + 1);
  }

  private boolean isQuote(char c) {
    return (c == '"') || (c == '\'') || (c == '`');
  }
}
