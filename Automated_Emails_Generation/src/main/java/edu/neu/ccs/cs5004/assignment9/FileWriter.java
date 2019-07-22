package edu.neu.ccs.cs5004.assignment9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents FileWriter object with its common behaviors.
 */
class FileWriter {

  /**
   * Writes a generated .txt file from the template with placeholder replaced with actual values
   * to the output directory.
   *
   * @param replaced a list of string text from the template that has placeholder replaced with
   actual value from the cvs file
   * @param firstName a firstName of the customer that will be used as a part of the filename
   * @param lastName a lastName of the customer that will be used as part of the filename
   * @param dir path to directory where the generated file will be saved
   */
  static void writeToFile(List<String> replaced, String firstName, String lastName, String dir)
      throws NoSuchFileException, IOException {
    try {
      Files.write(Paths.get(FileWriter.createFile(firstName, lastName, dir).getPath()), replaced);
    } catch (NoSuchFileException e) {
      System.err.println("There is no such a file exists!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Creates a file with in the given directory with given firstName and given lastName as filename.
   *
   * @param firstName a firstName of the customer that will be used as a part of the filename
   * @param lastName a lastName of the customer that will be used as part of the filename
   * @param dir path to directory where the generated file will be saved
   *
   * @return a new File with pathname "dir+filename"
   */
  private static File createFile(String firstName, String lastName, String dir) {
    String fileName = firstName.toLowerCase() + lastName.toLowerCase() + ".txt";
    return new File( dir + "/" + fileName);
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

    if (!(obj instanceof FileWriter)) {
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
    return "FileWriter{}";
  }
}
