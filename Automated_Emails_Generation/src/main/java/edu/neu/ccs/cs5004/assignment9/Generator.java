package edu.neu.ccs.cs5004.assignment9;


import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * Represents a Generator object with its common behavior.
 */
public class Generator {

  /**
   * Generates a file from the template file with all of the placeholder replaced with the
   * correct values from the csv file.
   *
   * @param placeHolder a List of the string built from the template file
   * @param inValues a Map create from the cvs file with column header as key and value as value
   * @param firstName a firstName of the customer from the csv file, which will be used as part
   of the output filename
   * @param lastName a lastName of the customer from the csv file, which will be used as part
   of the output filename
   */
  static void fileGenerator(List<String> placeHolder, Map<String, String> inValues,
      String firstName, String lastName, String dir) throws IOException {
    FileWriter.writeToFile(FindAndReplace.replace(placeHolder, inValues), firstName, lastName, dir);
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

    if (!(obj instanceof Generator)) {
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
    return "Generator{}";
  }
}