# User Flow Assumptions

## Document Assumptions

**For simplicity, I will only say if it's a failure or success. Later, more detail, e.g. what line failed, can be added.**
## Flow of the Program

1. In the terminal, the program is started

2. It prints "Welcome to Java Linter!"

3. It prompts "Type the file path on your machine that you wish to perform linting on"

4. The user enters their file path
    - *It must only work on .class files*
    - Directories can be accepted (use a loop to go through each .class file in the directory)

5. If it doesn't exist or the file extension name is wrong, the user gets an error message telling them that the file does not exist

6. If it works, the three functions of checking the naming conventions, hash code vs equals, and classes that cannot be constructed are called

7. For each one, it either prints "success" or it prints "failure" for each check

8. The program stops