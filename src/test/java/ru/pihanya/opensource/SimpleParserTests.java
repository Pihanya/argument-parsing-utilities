package ru.pihanya.opensource;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import ru.pihanya.opensorce.Argument;
import ru.pihanya.opensorce.ArgumentInfo;
import ru.pihanya.opensorce.parsing.ParseResult;
import ru.pihanya.opensorce.parsing.SimpleParser;

public class SimpleParserTests {

  private static final ArgumentInfo HELLO_ARG = new ArgumentInfo("hello",
      Arrays.asList("hello", "hell", "h"));

  private SimpleParser parser;

  @Before
  public void init() {
    parser = new SimpleParser(() -> Collections.singletonList(HELLO_ARG));
  }

  @Test
  public void notNullResult() {
    ParseResult result = parser.parse("-t");
    assertNotNull(result);
  }

  @Test
  public void doubleDashTest() {
    ParseResult result = parser.parse("--hello=\"world\"");
    assertNotNull(result);
    assertTrue(result.isSuccess());

    Map<ArgumentInfo, Argument> data = result.getData();
    Argument argument = data.get(HELLO_ARG);

    assertEquals(argument.getSignature(), "hello");
    assertEquals(argument.getValue(), "world");
  }

  @Test
  public void oneDashTest() {
    ParseResult result = parser.parse("-h \"world\"");
    assertNotNull(result);
    assertTrue(result.isSuccess());

    Map<ArgumentInfo, Argument> data = result.getData();
    Argument argument = data.get(HELLO_ARG);

    assertEquals(argument.getSignature(), "h");
    assertEquals(argument.getValue(), "world");
  }
}
