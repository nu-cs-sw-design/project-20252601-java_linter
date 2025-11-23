package myCode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class LinterMainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testNoArgumentsProvided() {
        String[] args = {};
        LinterMain.main(args);

        String output = outContent.toString();
        assertTrue(output.contains("Please provide a file path to analyze"),
                "Should prompt user to provide a file path");
    }

    @Test
    public void testInvalidPathProvided() {
        String[] args = {"nonexistent/path/to/nowhere"};
        LinterMain.main(args);

        String output = outContent.toString();
        assertTrue(output.contains("The provided path does not exist"),
                "Should indicate that path does not exist");
    }

    @Test
    public void testValidDirectoryWithClassFiles() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("testSources");
            assertNotNull(resourceUrl, "testSources directory not found");

            // Decode the URL to handle special characters
            String decodedPath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8);

            String[] args = {decodedPath};
            LinterMain.main(args);

            String output = outContent.toString();
            assertTrue(output.contains("Found") && output.contains(".class file(s) to analyze"),
                    "Should find and report .class files in directory");
            assertTrue(output.contains("=== Analyzing: SimpleClass.class ==="),
                    "Should analyze SimpleClass.class");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testSingleClassFile() {
        try {
            URL resourceUrl = getClass().getClassLoader().getResource("testSources/SimpleClass.class");
            assertNotNull(resourceUrl, "SimpleClass.class not found in testSources");

            // Decode the URL to handle special characters
            String decodedPath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8);

            String[] args = {decodedPath};
            LinterMain.main(args);

            String output = outContent.toString();
            assertTrue(output.contains("=== Analyzing: SimpleClass.class ==="),
                    "Should analyze the single .class file");
        } catch (Exception e) {
            fail("Test failed with exception: " + e.getMessage());
        }
    }

    @Test
    public void testDirectoryWithNoClassFiles() {
        File emptyDir = new File("src/test/resources/emptyDir");
        if (!emptyDir.exists()) {
            emptyDir.mkdirs();
        }

        String[] args = {emptyDir.getAbsolutePath()};
        LinterMain.main(args);

        String output = outContent.toString();
        assertTrue(output.contains("No .class files found in the directory"),
                "Should report no .class files found");
    }

    @Test
    public void testInvalidFileType() {
        File javaFile = new File("src/main/java/myCode/LinterMain.java");

        if (javaFile.exists()) {
            String[] args = {javaFile.getAbsolutePath()};
            LinterMain.main(args);

            String output = outContent.toString();
            assertTrue(output.contains("Please provide a valid .class file"),
                    "Should reject non-.class files");
        } else {
            assertTrue(true);
        }
    }
}