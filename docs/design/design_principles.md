# Design Principles

1. **Encapsulation**: Bundle data (fields) and methods that work on that data together in classes.

2. **Delegation**: One object passes work to another object instead of doing it itself.

3. **Information hiding**: Hide implementation details. Only expose what others need to know.

4. **Encapsulate what varies**: Find the parts of your code that change frequently and separate them from parts that don't.

5. **Favor composition over inheritance**: Instead of using extends, use a "has-a" relationship.

6. **Program to interface, not implementation**: Depend on abstract types (interfaces), not concrete classes.

7. **Strive for loosely coupled designs between objects that interact**: Classes should depend on each other as little as possible.

8. **Open-Closed Principle**: Classes should be open for extension but closed for modification.

9. **Hollywood Principle**: High-level components should control low-level components, not the other way around.

10. **Principle of Least Knowledge**: Reduce dependencies as much as possible; objects shouldn't know about the internal structure of other objects.
    - e.g. bad: `customer.getWallet().getMoneyQ.subtract(price);`
    - good: `customer.pay(price);`

11. **SOLID**
    - **Single Responsibility Principle**: A class should only have one reason to change, meaning it only has one purpose
    - **Open/Closed Principle**: (already listed as #8)
    - **Liskov Substitution Principle**: Subclasses should be substitutable for their parent classes without breaking the program.
    - **Interface Segregation Principle**: Don't force classes to implement methods they don't need. Make small, specific interfaces.
    - **Dependency Inversion Principle**: High-level modules should not depend on low-level modules. Both should depend on abstractions.

12. **Low coupling and high cohesion**: Classes should be as independent as possible; Everything in a class should be related to the class's single purpose.

## Justification for each
1. **Encapsulation**: ✅ FileAnalyzer encapsulates the file path and analysis logic with its ClassInfo data. 

2. **Delegation**: ✅LinterMain delegates the linting checks to CheckRunner, which further delegates to individual Check implementations. LinterMain also delegates file analyzing to FileAnalyzer class which delegates to ASM.

3. **Information hiding**: ✅ Checks hide their violation detection algorithms—users just call run() and get a boolean result.

4. **Encapsulate what varies**: ✅ The different linting rules are encapsulated behind the Check interface, so new checks can be added easily. 

5. **Favor composition over inheritance**: ✅ CheckRunner has a list of checks, which is composition. 

6. **Program to interface, not implementation**: ✅ the CheckRunner depends on the Check interface, not specific implementations of checks (new checks can be swapped easily).

7. **Strive for loosely coupled designs between objects that interact**: ✅ Checks don't know about each other (only depend on Class Info)

8. **Open-Closed Principle**: ✅ New linting rules can be added by creating new classes that implement the Check interface without modifying existing CheckRunner

9. **Hollywood Principle**: ✅ High-level CheckRunner controls when low-level Check implementations execute. LinterMain orchestrates FileAnalyzer and CheckRunner.

10. **Principle of Least Knowledge**: ✅ Checks don't access data directly instead they get processed data from ClassInfo

11. **SOLID**
    - **Single Responsibility Principle**: 🆗 each class has its own check for the most part, e.g. FileAnalyzer analyzes one file, CheckRunner runs check, Check specifies one rule at a time, and ClassInfo holds class metadata. Although the LinterMain has a few responsibilities, e.g. parse command lines, validate files that exist, and handle output, this was seen as the simplest way to implement the code because otherwise this would require additional classes for each of these tasks that are on the smaller side.  
    - **Open/Closed Principle**: ✅ (already justified as #8)
    - **Liskov Substitution Principle**: ✅ Any check implementation can be substituted for another
    - **Interface Segregation Principle**: ✅ The check interface is minimal (3 methods which are all necessary)
    - **Dependency Inversion Principle**: ✅ CheckRunner depends on abstraction check, not concrete classes. 

12. **Low coupling and high cohesion**: ✅ low coupling was justified already, as for high cohesion, each class has its own purpose e.g. FileAnalyzer analyzes files, CheckRunner runs checks, Check specifies one rule at a time, and ClassInfo holds class metadata.