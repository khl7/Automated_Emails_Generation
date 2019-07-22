package edu.neu.ccs.cs5004.assignment9;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;


public class ReadTemplateTest {
  private ReadTemplate readTemplateA;
  private ReadTemplate readTemplateB;
  private ReadTemplate readTemplateC;
  private Generator diffObj;

  @Before
  public void setUp() throws Exception {
    readTemplateA = new ReadTemplate();
    readTemplateB = new ReadTemplate();
    readTemplateC = new ReadTemplate();
    diffObj = new Generator();
  }

  @Test
  public void readTemplate() throws IOException {
    List<String> expected = new ArrayList<String>();
    expected.add("This is the test text file for testing readTeamplate.");
    expected.add("This is test!");
    expected.add("I am telling you this is test!!!");

    FileWriter.writeToFile(expected,"te","xt", System.getProperty("user.dir"));
    assertEquals(expected, ReadTemplate.readTemplate("text.txt"));
  }

  @Test
  public void readTemplateThrows() throws IOException {
    try {
      ReadTemplate.readTemplate("test2.txt");
    } catch (NoSuchFileException e) {
      fail("NoSuchFileException should have been thrown");
    } catch (IOException e) {
      fail("IOException should have been thrown");
    }
  }

  @Test
  public void testHashCode() {
    assertEquals(readTemplateA.hashCode(), readTemplateA.hashCode());
    assertEquals(readTemplateA.hashCode(), readTemplateB.hashCode());
    assertEquals(readTemplateB.hashCode(), readTemplateC.hashCode());
    assertEquals(readTemplateA.hashCode(), readTemplateC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(readTemplateA, readTemplateA);
    assertEquals(readTemplateA, readTemplateB);
    assertEquals(readTemplateB, readTemplateC);
    assertEquals(readTemplateA, readTemplateC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(readTemplateA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "ReadTemplate{}";
    assertEquals(expected, readTemplateA.toString());

  }
}