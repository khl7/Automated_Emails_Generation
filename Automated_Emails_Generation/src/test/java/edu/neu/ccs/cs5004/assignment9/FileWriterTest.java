package edu.neu.ccs.cs5004.assignment9;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class FileWriterTest {
  private List<String> correctList = new ArrayList<String>();
  private List<String> expected = new ArrayList<String>();

  private FileWriter fileWriterA;
  private FileWriter fileWriterB;
  private FileWriter fileWriterC;
  private ReadTemplate diffObj;

  @Before
  public void setUp() throws Exception {
    correctList.add("This is the unit test text file for testing readTemplate.");
    correctList.add("This is replace placeholder test!");
    correctList.add("you are telling me this is test!!!");

    expected.add("This is the unit test text file for testing readTemplate.");
    expected.add("This is replace placeholder test!");
    expected.add("you are telling me this is test!!!");

    fileWriterA = new FileWriter();
    fileWriterB = new FileWriter();
    fileWriterC = new FileWriter();
    diffObj = new ReadTemplate();
  }

  @Test
  public void writeToFile() throws IOException {
    ParserResult test = new ParserResult("template","csv","emails");
    ControlCenter.dirValidator(test);
    FileWriter.writeToFile(correctList,"Joe", "bob", "emails");
    List<String> actual = ReadTemplate.readTemplate("emails/joebob.txt");

    assertEquals(expected, actual);
  }

  @Test
  public void writeToFileThrowsIOException() throws NoSuchFileException, IOException {
    try {
      FileWriter.writeToFile(correctList,"Joe", "bob", "../../email");
    } catch (NoSuchFileException e) {
      fail("NoSuchFileException should have been thrown");
    } catch (IOException e) {
      fail("IOException should have been thrown");
    }
  }

  @Test
  public void testHashCode() {
    assertEquals(fileWriterA.hashCode(), fileWriterA.hashCode());
    assertEquals(fileWriterA.hashCode(), fileWriterB.hashCode());
    assertEquals(fileWriterB.hashCode(), fileWriterC.hashCode());
    assertEquals(fileWriterA.hashCode(), fileWriterC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(fileWriterA, fileWriterA);
    assertEquals(fileWriterA, fileWriterB);
    assertEquals(fileWriterB, fileWriterC);
    assertEquals(fileWriterA, fileWriterC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(fileWriterA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "FileWriter{}";
    assertEquals(expected, fileWriterA.toString());

  }
}