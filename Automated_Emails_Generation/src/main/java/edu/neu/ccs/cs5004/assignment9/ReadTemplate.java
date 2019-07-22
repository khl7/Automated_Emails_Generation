package edu.neu.ccs.cs5004.assignment9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Rerpresents ReadTemplate object with its common behavior.
 */
public class ReadTemplate {

  /**
   * Returns the list of the string constructed from the template file.
   *
   * @param templateName a string name of the template file
   *
   * @return the list of the string that is constructed from the template file
   */
  static List<String> readTemplate(String templateName) throws NoSuchFileException, IOException {
    List<String> templatePlaceholder = new ArrayList<String>();
    try {
      templatePlaceholder = Files.readAllLines(Paths.get(templateName));
    } catch (NoSuchFileException e) {
      System.err.println("There is no such a file exists!");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return templatePlaceholder;
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

    if (!(obj instanceof ReadTemplate)) {
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
    return "ReadTemplate{}";
  }
}


