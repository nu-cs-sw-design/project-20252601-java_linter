package myCode;

public class ClassInfo {
    private final String className;
    private final boolean hasEquals;
    private final boolean hasHashCode;
    private final boolean hasPublicConstructor;

    public ClassInfo(String className, boolean hasEquals, boolean hasHashCode, boolean hasPublicConstructor) {
        this.className = className;
        this.hasEquals = hasEquals;
        this.hasHashCode = hasHashCode;
        this.hasPublicConstructor = hasPublicConstructor;
    }

    public String getClassName() {
        return className;
    }

    public boolean hasEquals() {
        return hasEquals;
    }

    public boolean hasHashCode() {
        return hasHashCode;
    }

    public boolean hasPublicConstructor() {
        return hasPublicConstructor;
    }
}