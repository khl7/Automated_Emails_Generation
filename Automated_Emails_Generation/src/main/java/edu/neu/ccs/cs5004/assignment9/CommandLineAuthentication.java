package edu.neu.ccs.cs5004.assignment9;

import java.util.HashSet;
import java.util.Set;

/**
 * Command line authentication according to company's requirement.
 */
public class CommandLineAuthentication {
  
  private static final String ERROR_LENGTH = "Error: Command line does not have the correct "
      + "number of arguments. The correct number of argument is seven\n";
  private static final String ERROR_OUTPUT = "Error: --Output-dir does not have the correct"
      + "path formats.\n";
  private static final String ERROR_STANDARD = "Usage:\n--email\t\tgenerates emails messages\n"
      + "--email-template<file> accept a file name that holds email template.\n"
      + "--letter\t\tonly generate letters\n--letter-template<file> accepts a file name that holds"
      + "email template.\n--output-dir <path> accept the name of a folder, all "
      + "output is placed in this folder.\n--csv-file <path> accept the name "
      + "of the csv file to process.\n\nExamples:\n\t--email --email-template email-template.txt "
      + "--output-dir\n"
      + "emails --csv-file customer.csv\n\t"
      + "--letter --letter-template letter-template.txt --output-dir "
      + "letters --csv-file customer.csv\n\n";
  private static final String EMAIL = "--email";
  private static final String EMAIL_TEMPLATE = "--email-template";
  private static final String LETTER = "--letter";
  private static final String LETTER_TEMPLATE = "--letter-template";
  private static final String OUTPUT_DIR = "--output-dir";
  private static final String CSV_FILE = "--csv-file";
  private static final Integer ONE_PLUS_ARG = 6;
  private static final Integer NO_STRING = -1;
  private static final Integer MAX_LENGTH = 7;
  
  /**
   * Create parser result object from parsing command lines. Only use this method after successfully
   * authentication.
   * @param args - command line arguments.
   * @return - parser result object given template name, csv name and directory name.
   * @throws IllegalArgumentException if client class use this method without authenticating.
   */
  static ParserResult createParserResult(String[] args) throws IllegalArgumentException {
    if (!isCorrectCommand(args)) {
      throw new IllegalArgumentException("Command line was not authenticated.");
    }
    String template = "";
    String csv = "";
    String dir = "";
    for (int i = 0; i < args.length - 1; i++) {
      switch (args[i]) {
        case EMAIL_TEMPLATE:
          template = args[i + 1];
          break;
        case LETTER_TEMPLATE:
          template = args[i + 1];
          break;
        case OUTPUT_DIR:
          dir = args[i + 1];
          break;
        case CSV_FILE:
          csv = args[i + 1];
          break;
        default:
          break;
      }
    }
    return new ParserResult(template, csv, dir);
  }
  
  /**
   * Return true if command line meets all requirement by the company and false other wise.
   * @param args - command line arguments.
   * @return - true if meet requirement and false if not.
   */
  static boolean isCorrectCommand(String[] args) {
    Set<String> emailSet = new HashSet<>();
    Set<String> letterSet = new HashSet<>();
    if (!isEnoughArguments(args)) {
      System.out.println(ERROR_LENGTH);
      System.out.println(ERROR_STANDARD);
      return false;
    }
    if (!isEmailTag(args, emailSet) && !isLetterTag(args, letterSet)) {
      printArgumentError(emailSet, EMAIL);
      printArgumentError(letterSet, LETTER);
      System.out.println(ERROR_STANDARD);
      return false;
    }
    if (!isCorrectExtension(args)) {
      System.out.println(ERROR_STANDARD);
      return false;
    }
    if (!isCorrectOutputDirPath(args)) {
      System.out.println(ERROR_OUTPUT);
      System.out.println(ERROR_STANDARD);
      return false;
    }
    return true;
  }
  
  /**
   * Print the missing arguments that the users need to add in.
   * @param set - set with the leftover arguments.
   * @param type - the type of the set.
   */
  private static void printArgumentError(Set<String> set, String type) {
    if (!set.isEmpty()) {
      String print = String.format("For %s, the following arguments are missing: ", type);
      System.out.println(print);
      for (String string : set) {
        System.out.println(string);
      }
    }
  }
  
  /**
   * Private method that checks if number of arguments is correct.
   * @param args - command line arguments.
   * @return - true if has enough argument and false if not.
   */
  private static boolean isEnoughArguments(String[] args) {
    return args.length == MAX_LENGTH;
  }
  
  /**
   * Create a set given string arguments.
   * @param firstRequired - the first string argument.
   * @param secondRequired - the second string argument.
   * @param set - set to add the string arguments into.
   */
  private static void createSet(String firstRequired, String secondRequired, Set<String> set) {
    set.add(firstRequired);
    set.add(secondRequired);
  }
  
  /**
   * Return true if the command line has the 4 required arguments for email.
   * @param args - command line arguments.
   * @return - true if meet the 4 required argument for letter.
   */
  private static boolean isEmailTag(String[] args, Set<String> emailSet) {
    createSet(EMAIL, EMAIL_TEMPLATE, emailSet);
    addCommonArguments(emailSet);
    return isCompleteTag(args, emailSet);
  }
  
  /**
   * Return true if command line has the 4 required arguments for letter and false if not.
   * @param args - command line argument.
   * @return - true if command line has required arguments for letter and false if not.
   */
  private static boolean isLetterTag(String[] args, Set<String> letterSet) {
    createSet(LETTER, LETTER_TEMPLATE, letterSet);
    addCommonArguments(letterSet);
    return isCompleteTag(args, letterSet);
  }
  
  /**
   * Helper method that adds common arguments to the passed in set.
   * @param set - passed in set.
   */
  private static void addCommonArguments(Set<String> set) {
    set.add(OUTPUT_DIR);
    set.add(CSV_FILE);
  }
  
  /**
   * Return true if the set with complete tags is zero.
   * @param args - command line arguments.
   * @param set - set with the required arguments.
   * @return - true if the set is empty and false otherwise.
   */
  private static boolean isCompleteTag(String[] args, Set<String> set) {
    for (String string : args) {
      set.remove(string);
    }
    return set.size() == 0;
  }
  
  /**
   * Check if the argument with the template and csv arguments have the correct extension.
   * @param args - command line arguments.
   * @return - true if csv argument has .csv extension and template argument has .txt extension.
   */
  private static boolean isCorrectExtension(String[] args) {
    boolean txtExtension = false;
    boolean csvExtension = false;
    for (int i = 0; i < ONE_PLUS_ARG; i++) {
      if (args[i].equals(EMAIL_TEMPLATE) || args[i].equals(LETTER_TEMPLATE)) {
        txtExtension = isCorrectTxt(args[i + 1]);
        if (!txtExtension) {
          System.out.println("Error: template file name does not have the correct"
              + " txt extension.");
        }
      }
      if (args[i].equals(CSV_FILE)) {
        csvExtension = isCorrectCsv(args[i + 1]);
        if (!csvExtension) {
          System.out.println("Error: csv file does not have the correct csv extension.");
        }
      }
    }
    return txtExtension && csvExtension;
  }
  
  /**
   * Return true if extension is txt and false otherwise.
   * @param string - an argument of command line.
   * @return - true if extension is txt and false otherwise.
   */
  private static boolean isCorrectTxt(String string) {
    int lastIndexOf = string.lastIndexOf(".");
    if (lastIndexOf == NO_STRING) {
      System.out.println("Error: --template-file name does not have any extension.");
      return false;
    }
    return string.substring(lastIndexOf).equals(".txt");
  }
  /**
   * Return true if extension is csv and false if not.
   * @param string - an argument of command line.
   * @return - true if extension is csv and false if not.
   */
  private static boolean isCorrectCsv(String string) {
    int lastIndexOf = string.lastIndexOf(".");
    if (lastIndexOf == NO_STRING) {
      System.out.println("Error: --csv-file name does not have any extension.");
      return false;
    }
    return string.substring(lastIndexOf).equals(".csv");
  }
  
  /**
   * Return true if the output directory has correct path format and false otherwise.
   * @param args - an argument on command line.
   * @return - true if output directory has correct path format and false otherwise.
   */
  private static boolean isCorrectOutputDirPath(String[] args) {
    for (int i = 0; i < ONE_PLUS_ARG; i++) {
      if (args[i].equals(OUTPUT_DIR)) {
        return args[i + 1].matches("([\\w]+[/|\\\\]?)+");
      }
    }
    return false;
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

    if (!(obj instanceof CommandLineAuthentication)) {
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
    return "CommandLineAuthentication{}";
  }
}