package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.management.openmbean.InvalidKeyException;
import org.junit.Before;
import org.junit.Test;

public class FindAndReplaceTest {
  private List<String> failedList = new ArrayList<String>();
  private List<String> correctList = new ArrayList<String>();
  private Map<String, String> values = new HashMap<>();

  private FindAndReplace findAndReplaceA;
  private FindAndReplace findAndReplaceB;
  private FindAndReplace findAndReplaceC;
  private ReadTemplate diffObj;

  @Before
  public void setUp() throws Exception {
    failedList.add("This is the test text file for testing readTemplate.");
    failedList.add("[[This]] is test!");
    failedList.add("I am [[telling]] you this is test!!!");

    correctList.add("This is the [[test]] text file for testing readTemplate.");
    correctList.add("This is [[readTemplate]] test!");
    correctList.add("[[I am]] telling [[you]] this is test!!!");

    values.put("test", "unit test");
    values.put("readTemplate", "replace placeholder");
    values.put("I am", "you are");
    values.put("you", "me");

    findAndReplaceA = new FindAndReplace();
    findAndReplaceB = new FindAndReplace();
    findAndReplaceC = new FindAndReplace();
    diffObj = new ReadTemplate();
  }

  @Test
  public void replace() {
    List<String> expected = new ArrayList<String>();
    expected.add("This is the unit test text file for testing readTemplate.");
    expected.add("This is replace placeholder test!");
    expected.add("you are telling me this is test!!!");

    assertEquals(expected, FindAndReplace.replace(correctList, values));
  }

  @Test(expected = javax.management.openmbean.InvalidKeyException.class)
  public void replaceThrowsInvalidKeyException() throws InvalidKeyException {
    FindAndReplace.replace(failedList, values);
  }

  @Test
  public void testHashCode() {
    assertEquals(findAndReplaceA.hashCode(), findAndReplaceA.hashCode());
    assertEquals(findAndReplaceA.hashCode(), findAndReplaceB.hashCode());
    assertEquals(findAndReplaceB.hashCode(), findAndReplaceC.hashCode());
    assertEquals(findAndReplaceA.hashCode(), findAndReplaceC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(findAndReplaceA, findAndReplaceA);
    assertEquals(findAndReplaceA, findAndReplaceB);
    assertEquals(findAndReplaceB, findAndReplaceC);
    assertEquals(findAndReplaceA, findAndReplaceC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(findAndReplaceA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "FindAndReplace{}";
    assertEquals(expected, findAndReplaceA.toString());

  }
}