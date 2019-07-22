package edu.neu.ccs.cs5004.assignment9;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * GeneratorRunnable is a Runnable wrapper for fileGenerator static function.
 * It contains the required input of the fileGenerator function.
 */
class GeneratorRunnable implements Runnable {
  private List<String> placeHolder;
  private Map<String, String> inValues;
  private String firstName;
  private String lastName;
  private String dir;

  /**
   * Constructor that created a GeneratorRunnable object with specified field.
   * @param placeHolder template String list.
   * @param inValues one contact map.
   * @param firstName firstName of the contact. line parsing result index 0.
   * @param lastName lastName of the contact. line parsing result index 1.
   * @param dir output directory.
   */
  GeneratorRunnable(List<String> placeHolder,
      Map<String, String> inValues, String firstName, String lastName, String dir) {
    this.placeHolder = placeHolder;
    this.inValues = inValues;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dir = dir;
  }

  /**
   * run fileGenerator static method in the Generator class.
   */
  @Override
  public void run() {
    try {
      Generator.fileGenerator(placeHolder, inValues, firstName, lastName, dir);
    } catch (IOException exception) {
      System.err.println("IOException in fileGenerator");
    }
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
    if (!(obj instanceof GeneratorRunnable)) {
      return false;
    }
    GeneratorRunnable that = (GeneratorRunnable) obj;
    return Objects.equals(placeHolder, that.placeHolder)
        && Objects.equals(inValues, that.inValues)
        && Objects.equals(firstName, that.firstName)
        && Objects.equals(lastName, that.lastName)
        && Objects.equals(dir, that.dir);
  }

  /**
   * Returns the hash code value of the object which this method is invoked.
   *
   * @return the hash code value of the object which this method is invoked.
   */
  @Override
  public int hashCode() {
    return Objects.hash(placeHolder, inValues, firstName, lastName, dir);
  }

  /**
   * Returns a formatted string of this objects common properties.
   *
   * @return returns  a formatted string of this objects common properties
   */
  @Override
  public String toString() {
    return "GeneratorRunnable{"
        + "placeHolder=" + placeHolder
        + ", inValues=" + inValues
        + ", firstName='" + firstName + '\''
        + ", lastName='" + lastName + '\''
        + ", dir='" + dir + '\''
        + '}';
  }
}
