package edu.neu.ccs.cs5004.assignment9;

import java.io.IOException;
/**
 * Main class that takes in command line argument, authenticate it, and pass information to file
 * reader.
 */
public class Main {
  
  /**
   * Main that takes in command line and pass in to be processed if authenticated.
   * @param args - command line.
   * @throws IOException when there is IOException invoked by controller
   */
  public static void main(String[] args) throws IOException {
    if (CommandLineAuthentication.isCorrectCommand(args)) {
      ParserResult parserResult = CommandLineAuthentication.createParserResult(args);
      ControlCenter.controller(parserResult);
    }
  }

  /**
   * Returns the hash code value of the object which this method is invoked.
   *
   * @return the hash code value of the object which this method is invoked.
   */
  @Override
  public int hashCode() {
    return 42;
  }

  /**
   * Compares fields of objects to check if object passed to is equal to the object
   * on which it is invoked. Used for overriding assertEqual() in test so it can compare
   * to see if instance of the same class with same fields are equal.
   *
   * @param obj an object which it is invoked
   *
   * @return boolean value. True if both object is equal, false otherwise
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Main)) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Returns a formatted string of this objects common properties.
   *
   * @return returns  a formatted string of this objects common properties
   */
  @Override
  public String toString() {
    return "Main{}";
  }
}
