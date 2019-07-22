package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import java.io.File;
import org.junit.Before;
import org.junit.Test;

public class ParserResultTest {
  private ParserResult parserResult1;
  private ParserResult parserResult2;
  private ParserResult parserResult3;
  private ParserResult parserResult4;
  
  @Before
  public void setUp() throws Exception {
    parserResult1 = new ParserResult("email-template.txt", "customer.csv",
        "emails");
    parserResult2 = new ParserResult("email-template.txt", "customer.csv",
        "emails\\hello");
    parserResult3 = new ParserResult("email-template.txt", "customer.csv",
        "emails\\hello/there");
    parserResult4 = new ParserResult("email-template.txt", "customer.csv",
        "emails/hello/there");
  }
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    parserResult1 = new ParserResult("", "customer.csv",
        "emails");
    parserResult2 = new ParserResult("email-template.txt", "",
        "emails\\hello");
    parserResult3 = new ParserResult("email-template.txt", "customer.csv",
        "");
    parserResult3 = new ParserResult("email-template.txt", "",
        "");
    parserResult3 = new ParserResult("", "customer.csv",
        "");
  }
  
  @Test
  public void getTemplate() {
    assertEquals("email-template.txt", this.parserResult1.getTemplate());
  }
  
  @Test
  public void getDir() {
    String string1 = "emails";
    String string2 = "emails" + File.separator + "hello" + File.separator;
    String string3 = "emails" + File.separator + "hello" + File.separator + "there" + File.separator;
    
    assertEquals(string1, this.parserResult1.getDir());
    assertEquals(string2, this.parserResult2.getDir());
    assertEquals(string3, this.parserResult3.getDir());
    assertEquals(string3, this.parserResult4.getDir());
  }
  
  @Test
  public void getCsv() {
    assertEquals("customer.csv", this.parserResult1.getCsv());
  }
  
  @Test
  public void testToString() {
    String empty = "ParserResult{template='email-template.txt', dir='emails', csv='customer.csv'}";
    assertEquals(empty, this.parserResult1.toString());
  }
  
  @Test
  public void testEquals() {
    ParserResult parserResult6 = new ParserResult("email-template.txt", "customer.csv",
        "emails");
    ParserResult parserResult7 = new ParserResult("email-template.txt", "customer.csv",
        "ema");
    ParserResult parserResult8 = new ParserResult("email-temte.txt", "customer.csv",
        "emails");
    ParserResult parserResult9 = new ParserResult("email-template.txt", "cusmer.csv",
        "emails");;
    assertTrue(this.parserResult1.equals(parserResult6));
    assertTrue(this.parserResult1.equals((this.parserResult1)));
    assertFalse(this.parserResult1.equals(null));
    assertFalse(this.parserResult1.equals("a"));
    assertFalse(this.parserResult1.equals(parserResult7));
    assertFalse(this.parserResult1.equals(parserResult8));
    assertFalse(this.parserResult1.equals(parserResult9));
    
  }
  
  @Test
  public void testHashCode() {
    ParserResult parserResult6 = new ParserResult("email-template.txt", "customer.csv",
        "emails");
    assertTrue(this.parserResult1.hashCode() == parserResult6.hashCode());
  }
}