package myCode;

public class EqualsHashCodeCheck implements Check {
    private String violations;

    public EqualsHashCodeCheck() {
        this.violations = "";
    }

    @Override
    public boolean run(ClassInfo classInfo) {
        violations = "";
        boolean hasEquals = classInfo.hasEquals();
        boolean hasHashCode = classInfo.hasHashCode();

        // Check if one is overridden but not the other
        if (hasEquals && !hasHashCode) {
            violations = "Class overrides equals() but not hashCode(). " +
                    "When overriding equals(), you must also override hashCode().";
            return false;
        } else if (!hasEquals && hasHashCode) {
            violations = "Class overrides hashCode() but not equals(). " +
                    "When overriding hashCode(), you must also override equals().";
            return false;
        }

        // Either both are overridden or neither is overridden - both are valid
        return true;
    }

    @Override
    public String getName() {
        return "Equals/HashCode Check";
    }

    @Override
    public String getViolations() {
        return violations;
    }
}