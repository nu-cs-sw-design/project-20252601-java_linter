package myCode;

public interface Check {
    /**
     * Runs the check on the given ClassInfo
     * @param classInfo the class information to check
     * @return true if the check passed, false if violations were found
     */
    boolean run(ClassInfo classInfo);

    /**
     * Gets the name of this check
     * @return the check name
     */
    String getName();

    /**
     * Gets the violation messages if the check failed
     * @return a string describing the violations, or empty string if no violations
     */
    String getViolations();
}