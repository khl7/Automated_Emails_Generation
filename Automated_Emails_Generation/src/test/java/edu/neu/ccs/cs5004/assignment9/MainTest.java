package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MainTest {
  private Main MainA;
  private Main MainB;
  private Main MainC;
  private ReadTemplate diffObj;

  @Before
  public void setUp() throws Exception {
    MainA = new Main();
    MainB = new Main();
    MainC = new Main();
    diffObj = new ReadTemplate();
  }

  @Test
  public void testHashCode() {
    assertEquals(MainA.hashCode(), MainA.hashCode());
    assertEquals(MainA.hashCode(), MainB.hashCode());
    assertEquals(MainB.hashCode(), MainC.hashCode());
    assertEquals(MainA.hashCode(), MainC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(MainA, MainA);
    assertEquals(MainA, MainB);
    assertEquals(MainB, MainC);
    assertEquals(MainA, MainC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(MainA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "Main{}";
    assertEquals(expected, MainA.toString());

  }
}