package sptingboot.aoplogging;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class LoggingTest {
    private Logger log = LoggerFactory.getLogger(LoggingTest.class);

    @Value("${logging.file.name}")
    private String logFile;

    @Test
    public void whenLoggingToFile() {
        try {
            clearFile();

            // check
            log.info("Info test");
            log.error("Error test");

            assertTrue(searchDataInsideFile("Info test"));
            assertTrue(searchDataInsideFile("Error test"));
            clearFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean searchDataInsideFile(String searchParam) throws IOException {
        List<String> fileLines = Files.readAllLines(Paths.get(logFile));
        for (String line : fileLines) {
            if (line.contains(searchParam)) {
                return true;
            }
        }
        return false;
    }

    private void clearFile() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(logFile);
        writer.print("");
        writer.close();
    }
}
