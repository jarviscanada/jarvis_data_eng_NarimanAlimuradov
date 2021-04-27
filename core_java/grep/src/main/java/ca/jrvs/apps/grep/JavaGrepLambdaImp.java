package ca.jrvs.apps.grep;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaGrepLambdaImp extends JavaGrepImp {

    private static final Logger logger = LoggerFactory.getLogger(JavaGrepImp.class);

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Must provide: regex rootPath outFile");
        }

        JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
        javaGrepLambdaImp.setRegex(args[0]);
        javaGrepLambdaImp.setRootPath(args[1]);
        javaGrepLambdaImp.setOutFile(args[2]);

        try {
            javaGrepLambdaImp.process();
        } catch (Exception e) {
            logger.error("Error during process execution: ", e);
        }
    }

    @Override
    public List<String> readLines(File inputFile) throws IOException {
        List<String> allLines = new ArrayList<>();
        Stream<String> strStream = Files.lines(Paths.get(getRootPath() + "/" + inputFile.getName()));
        strStream.forEach(line -> allLines.add(line));
        strStream.close();
        return allLines;
    }

    @Override
    public List<File> listFiles(String rootDir) throws IOException {
        Stream<Path> filePath = Files.walk(Paths.get(rootDir));
        return filePath.filter(file -> Files.isRegularFile(file)).map(path -> path.toFile()).collect(Collectors.toList());
    }
}
