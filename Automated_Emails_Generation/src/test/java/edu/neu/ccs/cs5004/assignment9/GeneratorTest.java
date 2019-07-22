package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

public class GeneratorTest {
  private List<String> correctList = new ArrayList<String>();
  private Map<String, String> values = new HashMap<>();

  private Generator generatorA;
  private Generator generatorB;
  private Generator generatorC;
  private ReadTemplate diffObj;

  @Before
  public void setUp() throws Exception {
    correctList.add("This is the [[test]] text file for testing readTemplate.");
    correctList.add("This is [[readTemplate]] test!");
    correctList.add("[[I am]] telling [[you]] this is test!!!");

    values.put("test", "unit test");
    values.put("readTemplate", "replace placeholder");
    values.put("I am", "you are");
    values.put("you", "me");

    generatorA = new Generator();
    generatorB = new Generator();
    generatorC = new Generator();
    diffObj = new ReadTemplate();
  }

  @Test
  public void fileGenerator() throws IOException {
    List<String> expected = new ArrayList<String>();
    expected.add("This is the unit test text file for testing readTemplate.");
    expected.add("This is replace placeholder test!");
    expected.add("you are telling me this is test!!!");
    ParserResult test = new ParserResult("template","csv","emails");
    ControlCenter.dirValidator(test);
    Generator.fileGenerator(correctList, values, "joe", "bob", "emails");
    List<String> actual = ReadTemplate.readTemplate("emails/joebob.txt");

    assertEquals(expected, actual);
  }

  @Test
  public void testHashCode() {
    assertEquals(generatorA.hashCode(), generatorA.hashCode());
    assertEquals(generatorA.hashCode(), generatorB.hashCode());
    assertEquals(generatorB.hashCode(), generatorC.hashCode());
    assertEquals(generatorA.hashCode(), generatorC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(generatorA, generatorA);
    assertEquals(generatorA, generatorB);
    assertEquals(generatorB, generatorC);
    assertEquals(generatorA, generatorC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(generatorA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "Generator{}";
    assertEquals(expected, generatorA.toString());

  }
}