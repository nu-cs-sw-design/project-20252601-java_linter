package myCode;

public class NamingConventionCheck implements Check {
    private String violations;

    public NamingConventionCheck() {
        this.violations = "";
    }

    @Override
    public boolean run(ClassInfo classInfo) {
        violations = "";
        String className = classInfo.getClassName();

        // Extract just the simple class name (without package)
        String simpleName = className;
        if (className.contains(".")) {
            simpleName = className.substring(className.lastIndexOf('.') + 1);
        }

        // Check if class name is PascalCase
        // PascalCase rules:
        // - Starts with uppercase letter
        // - Can contain letters and digits
        // - Each word starts with uppercase
        if (!isPascalCase(simpleName)) {
            violations = "Class name '" + simpleName + "' does not follow PascalCase convention. " +
                    "Class names should start with an uppercase letter.";
            return false;
        }

        return true;
    }

    private boolean isPascalCase(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }

        // Must start with uppercase letter
        if (!Character.isUpperCase(name.charAt(0))) {
            return false;
        }

        // Should only contain letters and digits (no underscores or special chars)
        for (char c : name.toCharArray()) {
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String getName() {
        return "Naming Convention Check";
    }

    @Override
    public String getViolations() {
        return violations;
    }
}