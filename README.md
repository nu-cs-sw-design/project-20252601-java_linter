# Project: Java Linter

## Contributors
Caroline Guerra

## Dependencies

### Language and Build Tool
- **Java 11** (JDK 11 or higher required)
- **Gradle** (with wrapper included)

### External Libraries

#### Runtime Dependencies
- **ASM 9.2** - Bytecode analysis framework
    - `org.ow2.asm:asm:9.2`
    - `org.ow2.asm:asm-tree:9.2`
    - `org.ow2.asm:asm-analysis:9.2`

#### Testing Dependencies
- **JUnit Jupiter 5.8.1** - Unit testing framework
    - `junit-jupiter-api:5.8.1`
    - `junit-jupiter-params:5.8.1`
    - `junit-jupiter-engine:5.8.1`
- **EasyMock 4.2** - Mocking framework for tests

### Running the Project
```bash
# Build the project
./gradlew build

# Run the linter
./gradlew run

# Run the linter with a specific file or directory
./gradlew run --args="path/to/YourClass.class"

# Run tests
./gradlew test --args="build/classes/java/test/myCode"

Add your own tests in `build/java/test`
```