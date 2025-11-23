package myCode;

import java.util.ArrayList;
import java.util.List;

public class CheckRunner {
    private List<Check> checks;

    public CheckRunner() {
        this.checks = new ArrayList<>();
        // Initialize all checks
        checks.add(new NamingConventionCheck());
        checks.add(new EqualsHashCodeCheck());
        checks.add(new PublicConstructorCheck());
    }

    /**
     * Runs all checks on the given FileAnalyzer's ClassInfo
     * @param analyzer the FileAnalyzer containing the class to check
     */
    public void runAllChecks(FileAnalyzer analyzer) {
        ClassInfo classInfo = analyzer.getClassInfo();

        System.out.println("Running checks on class: " + classInfo.getClassName());
        System.out.println("---------------------------------------------------");

        for (Check check : checks) {
            boolean passed = check.run(classInfo);
            displayResults(check, passed);
        }

        System.out.println("---------------------------------------------------");
    }

    /**
     * Displays the results of a check
     * @param check the check that was run
     * @param passed whether the check passed
     */
    private void displayResults(Check check, boolean passed) {
        if (passed) {
            System.out.println("✓ " + check.getName() + ": PASSED");
        } else {
            System.out.println("✗ " + check.getName() + ": FAILED");
            System.out.println("  Violations:");
            System.out.println("  " + check.getViolations());
        }
    }
}