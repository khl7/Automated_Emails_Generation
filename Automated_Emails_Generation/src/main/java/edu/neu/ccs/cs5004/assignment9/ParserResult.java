package edu.neu.ccs.cs5004.assignment9;

import java.io.File;
import java.util.Objects;

/**
 * Parser result contains template name, directory, and csv file name.
 */
public class ParserResult {
  private String template;
  private String dir;
  private String csv;
  
  /**
   * Create a parser result object given template name, directory and csv file name.
   * @param template - template name.
   * @param dir - directory name.
   * @param csv - csv name.
   * @throws IllegalArgumentException if one of the inputs is empty string.
   */
  public ParserResult(String template, String csv, String dir) throws IllegalArgumentException {
    validateInput(template, dir, csv);
    this.template = template;
    this.dir = correctFileSeperator(dir);
    this.csv = csv;
  }
  
  /**
   * Private helper method to check if any of the pass in input is empty.
   * @param template - template name.
   * @param dir - directory name.
   * @param csv - csv file name.
   */
  private void validateInput(String template, String dir, String csv) {
    if (template.isEmpty() || dir.isEmpty() || csv.isEmpty()) {
      throw new IllegalArgumentException("One of the string inputs is empty");
    }
  }
  
  /**
   * Helper method that changes given directory path format to the directory path format we have
   * on system.
   * @param dir - directory.
   * @return - directory with correct seperator for our current system.
   */
  private String correctFileSeperator(String dir) {
    if (!dir.contains("\\") && !dir.contains("/")) {
      return dir;
    }
    String[] splitSeperator = splitSeperator(dir);
    StringBuffer buf = new StringBuffer();
    for (String string : splitSeperator) {
      buf.append(string);
      buf.append(File.separator);
    }
    return buf.toString();
  }
  
  /**
   * Split directory path my "/" and "\".
   * @param dir - directory.
   * @return - array of string split by "/" and "\".
   */
  private String[] splitSeperator(String dir) {
    return dir.split("/|\\\\");
  }
  
  /**
   * Get template name.
   * @return - template name.
   */
  public String getTemplate() {
    return template;
  }
  
  /**
   * Get directory name.
   * @return - directory name.
   */
  public String getDir() {
    return dir;
  }
  
  /**
   * Get csv name.
   * @return - csv name.
   */
  public String getCsv() {
    return csv;
  }
  
  /**
   * Return string representation.
   * @return - string representation.
   */
  @Override
  public String toString() {
    return "ParserResult{"
        + "template='" + template + '\''
        + ", dir='" + dir + '\''
        + ", csv='" + csv + '\''
        + '}';
  }
  
  /**
   * Return true if pass in object is equal to current object and false otherwise.
   * @param obj - pass in object.
   * @return true if equal and false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    ParserResult that = (ParserResult) obj;
    return template.equals(that.template)
        && dir.equals(that.dir)
        && csv.equals(that.csv);
  }
  
  /**
   * Return hash code.
   * @return - hash code.
   */
  @Override
  public int hashCode() {
    return Objects.hash(template, dir, csv);
  }
}
