package edu.neu.ccs.cs5004.assignment9;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.management.openmbean.InvalidKeyException;

/**
 * Represents a FindAndReplace object with its common behavior.
 */
public class FindAndReplace {

  /**
   * Returns a List of String with all of the placeholders from the template file replaced with the
   * correct value from the cvs file. It uses stream to replace the placeholder in the
   * templateHolder List with the correct value from the given cvsValue Map.
   *
   * @param templatePlaceHolder a List of the string/text build from the template file
   * @param csvValue a Map with the key as the column header and value as actual value of the each
   row
   *
   * @return returns the list of text from the template file with the placeholder replaced with the
   actual valued from the cvs file
   */
  static List<String> replace(List<String> templatePlaceHolder, Map<String, String> csvValue) {
    return templatePlaceHolder.stream().map(line -> FindAndReplace
        .findWords(line, csvValue)).collect(Collectors.toList());
  }

  /**
   * Returns a new line/String with the words that matches regex pattern, which is placeholder,
   * replaced with the actual valued from the csv file. This is done by following methods:
   * <p>
   *   1. Given line, which is a string of text from the template is copied over to the another
   *   String variable so original is not modified and can be reused.
   *
   *   2. Using Pattern and Matcher all occurrences of the placeholders (any text that starts with
   *   "[[" and ends with "]]", are found from the given line.
   *
   *   3. Using loop, iterate thru the all of the placeholders found by the matcher then create a
   *   key by extracting just the words from the placeholders e.g; [[first_name]] will be extract as
   *   key = first_name. This key is used to find the actual value from the Map that's been create
   *   from the csv file e.g; key is column header and value is actual value. If the key is in the
   *   Map, it replaces the placeholder with the actual value, if there is no key, error will be
   *   thrown
   *
   *   4. returns new String with the placeholder replaced with the value from the csv file
   * </p>
   *
   * @param line a String of the text from the templatePlaceHolder List
   * @param csvValues a Map with the key as column header and value as actual value from the each
   row
   *
   * @return returns a new line/String with the words that matches regex pattern, which is
   placeholder replaced with the correct value from the csvValues Map
   * @throws InvalidKeyException raises when placeholder is not a key for the csvValues Map.
   In other word when there is value from the cvs file that exist for the placeholder in the
   template file
   */
  private static String findWords(String line, Map<String, String> csvValues)
      throws InvalidKeyException {
    String replace = line;
    Matcher match = Pattern.compile("\\[\\[(.*?)\\]\\]").matcher(line);
    while (match.find()) {
      String key = match.group().substring(2, match.group().length() - 2);
      if (!csvValues.containsKey(key)) {
        throw new InvalidKeyException("Require placeholder not found in CSV file");
      }
      replace = replace.replace(match.group(), csvValues.get(key));

    }
    return replace;
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

    if (!(obj instanceof FindAndReplace)) {
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
    return "FindAndReplace{}";
  }
}
