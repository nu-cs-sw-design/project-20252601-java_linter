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
