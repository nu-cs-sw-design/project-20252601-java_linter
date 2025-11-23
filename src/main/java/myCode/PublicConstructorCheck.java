package myCode;

public class PublicConstructorCheck implements Check {
    private String violations;

    public PublicConstructorCheck() {
        this.violations = "";
    }

    @Override
    public boolean run(ClassInfo classInfo) {
        violations = "";

        if (!classInfo.hasPublicConstructor()) {
            violations = "Class does not have a public constructor. " +
                    "Classes should have at least one public constructor for instantiation.";
            return false;
        }

        return true;
    }

    @Override
    public String getName() {
        return "Public Constructor Check";
    }

    @Override
    public String getViolations() {
        return violations;
    }
}