# Analysis of the Changes 

1.	FileAnalyzer Input Changed: String filePath → ClassNode
      •	Why: Separates file I/O concerns from analysis logic
      •	Why: Enables dependency injection for better testability
      •	Why: FileAnalyzer shouldn't be responsible for reading files; this violates Single Responsibility Principle
2.	Removed fileExists() Method from LinterMain
      •	Why: Simplifies the public interface
      •	Why: File validation likely happens naturally when reading the file
      •	Why: Reduces unnecessary helper methods cluttering the main class
3.	Removed methods: List<MethodInfo> from ClassInfo
      •	Why: Current checks don't need detailed method information
      •	Why: Reduces complexity, storing full method lists was over-engineering
4.	Removed fields: List<FieldInfo> from ClassInfo
      •	Why: No checks currently examine field details
      •	Why: Eliminates need to create and maintain FieldInfo class
5.	Removed constructors: List<ConstructorInfo> from ClassInfo
      •	Why: Only need to know if a public constructor exists, not all constructor details
      •	Why: Replaced with simple hasPublicConstructor: boolean flag
      •	Why: More efficient: stores one boolean instead of entire list
6.	Added hasPublicConstructor: boolean Flag
      •	Why: Sufficient for PublicConstructorCheck requirements
      •	Why: Direct answer to "does class have public constructor?" question
      •	Why: Simplifies check implementation there is no iteration needed
7.	ClassInfo Now Stores Only Boolean Flags
      •	Why: All current checks need only yes/no answers
      •	Why: Reduces coupling between ClassInfo and individual check implementations
8.	Moved hasEquals and hasHashCode to ClassInfo (from implicit)
      •	Why: Analysis happens once; multiple checks can use results
      •	Why: Better performance, avoid re-scanning for same information


# Analysis of Anticipated Requirement Changes

1. Add GUI (Graphical User Interface)

Requirement: Replace command-line interface with graphical window showing results
Design Analysis:

Current design can handle this reasonably well
Why: Business logic (FileAnalyzer, CheckRunner, Checks) is separated from LinterMain
What's needed: Create new LinterGUI class; keep FileAnalyzer and CheckRunner unchanged
Impact: Minimal to core design: only presentation layer changes
Solution:

Create LinterGUI that uses same FileAnalyzer and CheckRunner
GUI calls same methods as LinterMain currently does
MVC architecture is supported: Model (ClassInfo) and Controller (CheckRunner) stay the same; only View changes

2. Add Configurable Rules (Enable/Disable Checks via Configuration)

Requirement: Users can enable/disable checks via linter-config.json
Design Analysis:

Current design can handle this easily
Why: CheckRunner already manages a list of checks
What's needed: Configuration loader that conditionally adds checks to the list
Impact: Minimal: only affects CheckRunner initialization
Solution:

Add ConfigLoader class that reads config file
ConfigLoader instantiates appropriate checks based on configuration
CheckRunner initialization changes from hardcoded list to config-driven list
No changes needed to Check interface or individual check implementations

3. Add Severity Levels (ERROR, WARNING, INFO)

Requirement: Different violations should have different severity levels
Design Analysis:

Current design can handle this easily
Why: Check interface is well-defined; just needs enhancement
What's needed: Add getSeverity(): Severity method to Check interface
Impact: Minor: modify Check interface and implementing classes
Solution:

Create Severity enum with values: ERROR, WARNING, INFO
Add getSeverity() method to Check interface
Each check implementation returns appropriate severity level
CheckRunner displays results with severity indication (color coding, icons, sorting by severity)
