package myCode;
import java.io.File;
import java.io.FileInputStream;

import org.objectweb.asm.ClassReader;
import java.io.IOException;
import org.objectweb.asm.tree.ClassNode;
import java.io.FileInputStream;


public class LinterMain {
    public static void main(String[] args) {
        System.out.println("Welcome to the Code Linter!");
        if (args.length == 0) {
            System.out.println("Please provide a file path to analyze.");
            return;
        }

        String filePath = args[0];
        File directory = new File(filePath);

        // check that given path is valid
        if (!directory.exists()) {
            System.out.println("The provided path does not exist.");
            return;
        }

        if (directory.isDirectory()) {
            // get all .class files and loop over them, calling file analyzer each time
            File[] classFiles = directory.listFiles((dir, name) -> name.endsWith(".class"));

            if (classFiles == null || classFiles.length == 0) {
                System.out.println("No .class files found in the directory.");
                return;
            }

            System.out.println("Found " + classFiles.length + " .class file(s) to analyze.\n");

            for (File classFile : classFiles) {
                System.out.println("=== Analyzing: " + classFile.getName() + " ===");
                analyzeFile(classFile);
                System.out.println();
            }
        }
        else if (directory.isFile() && filePath.endsWith(".class")) {
            // analyze the single .class file
            System.out.println("=== Analyzing: " + directory.getName() + " ===");
            analyzeFile(directory);
        } else {
            System.out.println("Please provide a valid .class file or directory containing .class files.");
            return;
        }
    }

    private static void analyzeFile(File classFile) {
        try {
            // read .class file using ASM
            ClassReader reader = new ClassReader(new FileInputStream(classFile));
            ClassNode classNode = new ClassNode();
            reader.accept(classNode, ClassReader.EXPAND_FRAMES);

//            FileAnalyzer analyzer = new FileAnalyzer(classNode);
//
//            CheckRunner runner = new CheckRunner();
//            runner.runAllChecks(analyzer);

        } catch (IOException e) {
            System.out.println("Error reading file: " + classFile.getName());
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.out.println("Invalid .class file format: " + classFile.getName());
        }
    }
}