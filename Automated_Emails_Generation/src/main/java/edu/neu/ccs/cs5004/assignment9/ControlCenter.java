package edu.neu.ccs.cs5004.assignment9;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * ControlCenter class includes controller method, which
 * will accept the parserResult object from main(), validate csv, txt,
 * and dir input, read the .txt template, read the .csv contacts, and send
 * each individual contact to generator to generate the desired letter/email.
 */
public class ControlCenter {

  /**
   * dirValidator validates if the output directory exists,
   * if not, directory will be created in the corresponding address.
   * @param parserResult parserResult from user input.
   */
  static void dirValidator(ParserResult parserResult) {
    Path path = Paths.get(parserResult.getDir());
    if (Files.notExists(path)) {
      try {
        Files.createDirectory(path);
        System.out.println("Dir not found. New dir folder created at target location.");
      } catch (IOException Exception) {
        System.out.println("IOException! Printing stackTrace. Check main.");
        Exception.printStackTrace();
      }
    }
  }

  /**
   * fullPathHelper takes in a filePath String, and checks if it is a fullpath
   * by detecting if a File.separator is present. If yes, the original path will
   * be returned, otherwise, the full path to the file will be created.
   * @param filePath String. file name or path to file to input file (template or csv).
   * @return String. Full path to input file (template or csv).
   */
  static String fullPathHelper(String filePath) {
    if (!filePath.contains(File.separator)) {
      return System.getProperty("user.dir") + File.separator + filePath;
    } else {
      return filePath;
    }
  }

  /**
   * csvValidator validates if the csv input file exists,
   * if not, a NoSuchFileException will be thrown.
   * @param parserResult ParserResult object. parserResult from user input.
   * @throws NoSuchFileException when input file does not exist.
   */
  static void csvValidator(ParserResult parserResult) throws NoSuchFileException {
    Path path = Paths.get(parserResult.getCsv());
    if (Files.notExists(path)) {
      throw new NoSuchFileException("*.csv file did not found at target location.");
    }
  }

  /**
   * templateValidator validates if the template.txt input file exists,
   * if not, a NoSuchFileException will be thrown.
   * @param parserResult ParserResult object. parserResult from user input.
   * @throws NoSuchFileException when input file does not exist.
   */
  static void templateValidator(ParserResult parserResult) throws NoSuchFileException {
    Path path = Paths.get(parserResult.getTemplate());
    if (Files.notExists(path)) {
      throw new NoSuchFileException("Template *.txt file did not found at target location.");
    }
  }

  /**
   * columnCounter loop through a String[] and return the number of elements insides it.
   * @param columnName String[]. columnName.
   * @return int. number of elements inside the columnName String[].
   */
  static int columnCounter(String[] columnName) {
    int num = 0;
    for (int i = 0; i < columnName.length; i++) {
      if (columnName[i] != null) {
        num++;
      }
    }
    //System.out.println(num);
    return num;
  }

  /**
   * processContacts is the main function supporting controller. it receive a parserResult obj,
   * a String list template, read csv file,
   * read the first line of csv file, and parse all column name into a String array.
   * For each line, the parsing results are put in the map with the column names as keys.
   * The template String List, the map, first and last names of contacts,
   * and the output directory will be wrapped into GeneratorRunnable objects
   * and run by treads to generate the letter / email files.
   * @param parserResult ParserResult object. parserResult from user input.
   * @param template String list. Template read from the template.txt input file.
   */
  private static void processContacts(ParserResult parserResult, List<String> template) {
    Integer numThread = 4;  // set the thread number
    // prepare the dir for generator
    String outDir = parserResult.getDir();

    try {
      // read the file
      File csvInput = new File(parserResult.getCsv());
      InputStream csvInputStream = new FileInputStream(csvInput);
      InputStreamReader csvInputStreamReader = new InputStreamReader(csvInputStream, UTF_8);
      BufferedReader csvRead = new BufferedReader(csvInputStreamReader);

      // get the column names
      String currentLine = csvRead.readLine();
      String[] columnName = null;
      if (currentLine != null) {
        columnName = currentLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
      } else {
        throw new RuntimeException("First line read is null!");
      }

      // get the number of column
      Integer numColumn = columnCounter(columnName);

      // remove quotation mark from column names
      for (int i = 0; i < numColumn; i++) {
        columnName[i] = columnName[i].replace("\"", "");
      }

      //initialize the ThreadPoolExecutor
      ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numThread);

      // loop through all contacts, and send tasks to threads
      for (String line = csvRead.readLine(); line != null; line = csvRead.readLine()) {
        String[] entry = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        // check if have matching number of columns
        if (columnCounter(entry) == numColumn) {
          // if so, put into a map for generator
          Map<String,String> map = new HashMap<>();
          for (int i = 0; i < numColumn; i++) {
            entry[i] = entry[i].replace("\"", "");
            map.put(columnName[i],entry[i]);
          }
          // assign tasks to executor threads
          executor.execute(new GeneratorRunnable(template,map,entry[0],entry[1],outDir));
        } else {
          System.out.println("Line entry have more/less field compare to header!");
          System.out.println("Line Skipped: ");
          System.out.println(line);
        }
      }
      // wait all threads to complete, and shut down the executor.
      executor.shutdown();
      csvRead.close(); // shutdown the BufferedReader, InputStream will close automatically.

    } catch (IOException exception) {
      System.out.println("IOException in processContacts! Printing stackTrace...");
      exception.printStackTrace();
    }
  }

  /**
   * controller is the function that handles the overall flow of the process.
   * It validate the dir, csv, and template address of the parser result, read the
   * template to a String list, and kick start the processContact method.
   * @param parserResult ParserResult object. parserResult from user input.
   * @throws NoSuchFileException when csv or template input file does not exist.
   */
  static void controller(ParserResult parserResult) throws NoSuchFileException {
    dirValidator(parserResult); // check dir
    csvValidator(parserResult); // check csv contacts
    templateValidator(parserResult); // check template.txt

    // if everything looks good, read the template, and kick start the processContacts method.
    List<String> template = null;
    try {
      template = ReadTemplate.readTemplate(parserResult.getTemplate());
    } catch (IOException exception) {
      throw new NoSuchFileException("readTemplate IOException!");
    }
    processContacts(parserResult, template);
  }

  /**
   * toString, "ControlCenter{}".
   * @return "ControlCenter{}".
   */
  @Override
  public String toString() {
    return "ControlCenter{}";
  }

  /**
   * as ControlCenter have no fields,
   * the meaning of life is returned as hashcode.
   * @return meaning of life, 42.
   */
  @Override
  public int hashCode() {
    return 42;
  }

  /**
   * as ControlCenter have no fields,
   * the equals function evaluate if it is the same obj,
   * or if it is an instanceof the ControlCenter class.
   * @return true if mentioned check passes.
   */
  @Override
  public boolean equals(Object obj) {

    return this == obj
        || obj instanceof ControlCenter;
  }
}
