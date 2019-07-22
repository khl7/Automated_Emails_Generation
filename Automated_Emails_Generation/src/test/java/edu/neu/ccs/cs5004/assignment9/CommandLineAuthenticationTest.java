package edu.neu.ccs.cs5004.assignment9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

public class CommandLineAuthenticationTest {
  private String[] args1;
  private String[] args2;
  private String[] args3;
  private String[] args4;
  private String[] args5;
  private String[] args6;
  private String[] args7;
  private String[] args8;
  private String[] args9;
  private String[] args10;
  private String[] args11;
  private String[] args12;
  private String[] args13;
  private String[] args14;
  private String[] args15;
  private String[] args16;
  private String[] args17;
  private String[] args18;
  private String[] args19;
  private String[] args20;
  private String[] args21;
  private String[] args22;
  private String[] args23;
  private String[] args24;
  private String[] args25;

  private CommandLineAuthentication commandLineAuthenticationA;
  private CommandLineAuthentication commandLineAuthenticationB;
  private CommandLineAuthentication commandLineAuthenticationC;
  private ReadTemplate diffObj;
  
  @Before
  public void setUp() throws Exception {
    args1 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args2 = new String[] {"--email-template", "email-template.txt", "--email", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args3 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv", "abcdef"};
    args4 = new String[] {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args5 = new String[] {"--email", "email-template.txt", "--email-template", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args6 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails", "customer.csv", "--csv-file"};
    args7 = new String[] {"--email", "--email-template", "email-template.abc", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args8 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.abc"};
    args9 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails/abc", "--csv-file", "customer.csv"};
    args10 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails/abc\\abc", "--csv-file", "customer.csv"};
    args11 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails//sadf", "--csv-file", "customer.csv"};
    args12 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "", "--csv-file", "customer.csv"};
    args13 = new String[] {"--email", "--email-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customercsv"};
    args14 = new String[] {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args15 = new String[] {"--email", "--hello-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args16 = new String[] {"--letter", "--letter-template", "letter-template.txt", "--csv-file",
        "customer.csv", "--output-dir", "emails",};
    args17 = new String[] {"--letter", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "abc", "customer.csv"};
    args18 = new String[] {"bcd", "--letter-template", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args19 = new String[] {"--letter", "xyz", "letter-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args20 = new String[] {"--letter", "--letter-template", "letter-template.txt", "hello",
        "emails", "--csv-file", "customer.csv"};
    args21 = new String[] {"abc", "--email-template", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args22 = new String[] {"--email", "abc", "email-template.txt", "--output-dir",
        "emails", "--csv-file", "customer.csv"};
    args23 = new String[] {"--email", "--email-template", "email-template.txt", "abc",
        "emails", "--csv-file", "customer.csv"};

    commandLineAuthenticationA = new CommandLineAuthentication();
    commandLineAuthenticationB = new CommandLineAuthentication();
    commandLineAuthenticationC = new CommandLineAuthentication();
    diffObj = new ReadTemplate();
    
  }
  
  @Test
  public void createParserResult() {
    ParserResult parserResult = CommandLineAuthentication.createParserResult(this.args1);
    ParserResult parserResult1 = new ParserResult("email-template.txt",
        "customer.csv", "emails");
    assertEquals(parserResult1, parserResult);
    ParserResult parserResult2 = CommandLineAuthentication.createParserResult(this.args14);
    ParserResult parserResult3 = new ParserResult("letter-template.txt",
        "customer.csv", "emails");
    assertEquals(parserResult2, parserResult3);
  }
  
  @Test(expected = IllegalArgumentException.class)
  public void testWrongParserResult() {
    ParserResult parserResult2 = CommandLineAuthentication.createParserResult(this.args13);
  }
  
  @Test
  public void isCorrectCommand() {
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args1));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args2));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args3));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args4));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args5));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args6));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args7));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args8));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args9));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args10));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args11));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args12));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args13));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args14));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args15));
    assertEquals(true, CommandLineAuthentication.isCorrectCommand(args16));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args17));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args18));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args19));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args20));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args21));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args22));
    assertEquals(false, CommandLineAuthentication.isCorrectCommand(args23));
  
  }

  @Test
  public void testHashCode() {
    assertEquals(commandLineAuthenticationA.hashCode(), commandLineAuthenticationA.hashCode());
    assertEquals(commandLineAuthenticationA.hashCode(), commandLineAuthenticationB.hashCode());
    assertEquals(commandLineAuthenticationB.hashCode(), commandLineAuthenticationC.hashCode());
    assertEquals(commandLineAuthenticationA.hashCode(), commandLineAuthenticationC.hashCode());
  }

  @Test
  public void testEquals() {
    assertEquals(commandLineAuthenticationA, commandLineAuthenticationA);
    assertEquals(commandLineAuthenticationA, commandLineAuthenticationB);
    assertEquals(commandLineAuthenticationB, commandLineAuthenticationC);
    assertEquals(commandLineAuthenticationA, commandLineAuthenticationC);
  }

  @Test
  public void testEqualsDiffObj() {
    assertNotEquals(commandLineAuthenticationA, diffObj);
  }

  @Test
  public void testToString() {
    String expected = "CommandLineAuthentication{}";
    assertEquals(expected, commandLineAuthenticationA.toString());

  }
}