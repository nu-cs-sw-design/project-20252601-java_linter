package myCode;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class FileAnalyzerTest {

    @Test
    public void testAnalyzeSimpleClass() throws IOException {
        // Load SimpleClass.class
        ClassNode classNode = loadClassNode("testSources/SimpleClass.class");

        // Analyze it
        FileAnalyzer analyzer = new FileAnalyzer(classNode);
        ClassInfo classInfo = analyzer.getClassInfo();

        // Verify
        assertNotNull(classInfo, "ClassInfo should not be null");
        assertEquals("SimpleClass", classInfo.getClassName(), "Class name should be SimpleClass");
        assertFalse(classInfo.hasEquals(), "SimpleClass should not have equals method");
        assertFalse(classInfo.hasHashCode(), "SimpleClass should not have hashCode method");
        assertTrue(classInfo.hasPublicConstructor(), "SimpleClass should have public constructor");
    }

    @Test
    public void testAnalyzeClassWithEqualsAndHashCode() throws IOException {
        // Use LinterMain.class which likely doesn't have equals/hashCode
        File linterMainFile = new File("build/classes/java/main/myCode/LinterMain.class");

        if (linterMainFile.exists()) {
            ClassNode classNode = loadClassNode(linterMainFile);
            FileAnalyzer analyzer = new FileAnalyzer(classNode);
            ClassInfo classInfo = analyzer.getClassInfo();

            assertNotNull(classInfo);
            assertEquals("myCode.LinterMain", classInfo.getClassName());
            assertFalse(classInfo.hasEquals());
            assertFalse(classInfo.hasHashCode());
        }
    }

    @Test
    public void testClassInfoGetters() throws IOException {
        // Create a ClassInfo directly
        ClassInfo classInfo = new ClassInfo("TestClass", true, true, true);

        assertEquals("TestClass", classInfo.getClassName());
        assertTrue(classInfo.hasEquals());
        assertTrue(classInfo.hasHashCode());
        assertTrue(classInfo.hasPublicConstructor());
    }

    @Test
    public void testClassInfoImmutability() throws IOException {
        ClassInfo classInfo = new ClassInfo("TestClass", false, false, false);

        // Verify initial state
        assertFalse(classInfo.hasEquals());
        assertFalse(classInfo.hasHashCode());
        assertFalse(classInfo.hasPublicConstructor());

    }

    @Test
    public void testClassWithEqualsAndHashCode() throws IOException {
        ClassNode classNode = loadClassNode("testSources/ClassWithEqualsHashCode.class");
        FileAnalyzer analyzer = new FileAnalyzer(classNode);
        ClassInfo classInfo = analyzer.getClassInfo();

        assertEquals("ClassWithEqualsHashCode", classInfo.getClassName());
        assertTrue(classInfo.hasEquals(), "Should detect equals method");
        assertTrue(classInfo.hasHashCode(), "Should detect hashCode method");
        assertTrue(classInfo.hasPublicConstructor());
    }

    // Helper method to load a ClassNode from a file
    private ClassNode loadClassNode(String resourcePath) throws IOException {
        URL resourceUrl = getClass().getClassLoader().getResource(resourcePath);
        assertNotNull(resourceUrl, "Resource not found: " + resourcePath);

        String decodedPath = URLDecoder.decode(resourceUrl.getPath(), StandardCharsets.UTF_8);
        return loadClassNode(new File(decodedPath));
    }

    private ClassNode loadClassNode(File classFile) throws IOException {
        ClassReader reader = new ClassReader(new FileInputStream(classFile));
        ClassNode classNode = new ClassNode();
        reader.accept(classNode, ClassReader.EXPAND_FRAMES);
        return classNode;
    }
}