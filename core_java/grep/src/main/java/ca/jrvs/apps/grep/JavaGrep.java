package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface JavaGrep {

    /**
     * Top level search workflow
     * @throws IOException
     */
    void process() throws IOException;

    /**
     * List all files in a given directory
     * @param rootDir input directory
     * @return list of files in the directory
     */
    List<File> listFiles(String rootDir) throws IOException;

    /**
     * Read lines from a file
     * @param inputFile file being read from
     * @return lines that are read
     * @throws IllegalArgumentException if incorrect input file format
     */
    List<String> readLines(File inputFile) throws IOException;

    /**
     * Check if line contains the regex pattern passed
     * @param line input string
     * @return true if there is a match, false otherwise
     */
    boolean containsPattern(String line);

    /**
     * Write to a file
     * @param lines
     * @throws IOException
     */
    void writeToFile(List<String> lines) throws IOException;

    // getters and setters
    String getRootPath();
    void setRootPath(String rootPath);
    String getRegex();
    void setRegex(String regex);
    String getOutFile();
    void setOutFile(String outFile);
}
