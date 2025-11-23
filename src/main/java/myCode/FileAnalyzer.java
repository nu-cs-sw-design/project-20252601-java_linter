package myCode;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

public class FileAnalyzer {
    private final ClassNode classNode;
    private ClassInfo classInfo;

    public FileAnalyzer(ClassNode classNode) {
        this.classNode = classNode;
        analyze();
    }

    private void analyze() {
        String className = Type.getObjectType(classNode.name).getClassName();
        boolean hasEquals = false;
        boolean hasHashCode = false;
        boolean hasPublicConstructor = false;

        // Check methods
        List<MethodNode> methods = (List<MethodNode>) classNode.methods;
        for (MethodNode method : methods) {
            // Check for equals
            if (method.name.equals("equals") && method.desc.equals("(Ljava/lang/Object;)Z")) {
                hasEquals = true;
            }
            // Check for hashCode
            if (method.name.equals("hashCode") && method.desc.equals("()I")) {
                hasHashCode = true;
            }
            // Check for public constructor
            if (method.name.equals("<init>") && (method.access & Opcodes.ACC_PUBLIC) != 0) {
                hasPublicConstructor = true;
            }
        }

        this.classInfo = new ClassInfo(className, hasEquals, hasHashCode, hasPublicConstructor);
    }

    public ClassInfo getClassInfo() {
        return classInfo;
    }
}