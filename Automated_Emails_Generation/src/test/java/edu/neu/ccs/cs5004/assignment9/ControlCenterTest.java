package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import org.junit.Before;
import org.junit.Test;

public class ControlCenterTest {
  private String addr1;
  private String addr2;
  private String addr3;
  private String addr4;
  private String addr5;
  private ParserResult test;

  @Before
  public void setUp() throws Exception {
    addr1 = "lettertemplate.txt";
    addr2 = "insurance_company_members.csv";
    addr3 = "insurance_company_members";
    addr4 = System.getProperty("user.dir") + File.separator + "lettertemplate.txt";
    addr5 = System.getProperty("user.dir") + File.separator + "test";
  }

  @Test
  public void dirValidator() {
    test = new ParserResult(addr1,addr2,addr5);
    ControlCenter.dirValidator(test);
    assertTrue(Files.exists(Paths.get(addr5)));
  }

  @Test
  public void fullPathHelper() {
    assertEquals(addr4,ControlCenter.fullPathHelper(addr1));
    assertEquals(addr4,ControlCenter.fullPathHelper(addr4));

  }

  @Test
  public void csvValidator(){
    try {
      test = new ParserResult(addr1, addr3, addr5);
      ControlCenter.csvValidator(test);
      fail();
    } catch (NoSuchFileException e) {
      System.out.println("csvValidator passed");
    }
  }

  @Test
  public void templateValidator() {
    try {
      test = new ParserResult("emailtemplate", addr2, addr5);
      ControlCenter.templateValidator(test);
      fail();
    } catch (NoSuchFileException e) {
      System.out.println("templateValidator passed");
    }
  }

  @Test
  public void columnCounter() {
    String[] test = {"test1","2","3"};
    assertEquals(3, ControlCenter.columnCounter(test));
  }

  @Test
  public void controller() {
    // tryout the custom email template
    ParserResult test = new ParserResult(
        "custom_email_template.txt",
        "insurance_company_members.csv",
        System.getProperty("user.dir") + File.separator + "customEmail");
    try {
      ControlCenter.controller(test);
    } catch (IOException io) {
      System.out.println("IOException in main");
    }

    // tryout the custom letter template
    ParserResult test2 = new ParserResult(
        "custom_letter_template.txt",
        "insurance_company_members.csv",
        System.getProperty("user.dir") + File.separator + "customLetter");
    try {
      ControlCenter.controller(test2);
    } catch (IOException io) {
      System.out.println("IOException in main");
    }
  }

  @Test
  public void toString1() {
    ControlCenter text = new ControlCenter();
    assertEquals("ControlCenter{}",text.toString());
  }

  @Test
  public void hashCode1() {
    ControlCenter text = new ControlCenter();
    assertEquals(42,text.hashCode());
  }

  @Test
  public void equals1() {
    ControlCenter text = new ControlCenter();
    ControlCenter test = new ControlCenter();
    assertEquals(text,text);
    assertNotEquals("Dude",text);
    assertEquals(test,text);
  }
}