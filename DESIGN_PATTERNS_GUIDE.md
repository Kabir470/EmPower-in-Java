# EmPower - Design Patterns & Architecture Learning Guide

Welcome to the **EmPower** Design Patterns Guide! This document details all 8 design patterns implemented in this codebase, explaining **Where** each pattern lives, **Why** it was chosen, **How** it operates, and what **Alternatives** could have been used.

Use this guide to study the architecture, understand the design decisions, and prepare for lab presentations or viva defense with your instructor.

---

## Quick Reference Table

| Pattern Name | Pattern Type | Key File Location(s) | Primary Purpose |
| :--- | :--- | :--- | :--- |
| **1. Singleton** | Creational | [EmployeeRepository.java](file:///d:/code/EmPower%20in%20java/Repository/EmployeeRepository.java), [LeaveRepository.java](file:///d:/code/EmPower%20in%20java/Repository/LeaveRepository.java) | Ensures a single global instance of data stores. |
| **2. Factory Method** | Creational | [EmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/EmployeeFactory.java) | Centralizes polymorphic employee creation. |
| **3. Strategy** | Behavioral | [ISalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/ISalaryStrategy.java), [Strategy/](file:///d:/code/EmPower%20in%20java/Strategy/) | Decouples role-specific payroll calculations. |
| **4. Observer** | Behavioral | [ILeaveObserver.java](file:///d:/code/EmPower%20in%20java/Observer/ILeaveObserver.java), [HRLeaveObserver.java](file:///d:/code/EmPower%20in%20java/Observer/HRLeaveObserver.java) | Emits real-time alerts when leave status changes. |
| **5. Command** | Behavioral | [ICommand.java](file:///d:/code/EmPower%20in%20java/Command/ICommand.java), [Command/](file:///d:/code/EmPower%20in%20java/Command/) | Encapsulates user menu actions as executable objects. |
| **6. Facade** | Structural | [EmPowerFacade.java](file:///d:/code/EmPower%20in%20java/Facade/EmPowerFacade.java) | Simplifies entry point and subsystem initialization. |
| **7. Template Method** | Behavioral | [EmployeeBase.java](file:///d:/code/EmPower%20in%20java/Abstract/EmployeeBase.java) | Defines fixed detail-printing algorithm skeleton. |
| **8. Repository** | Data Access | [Repository/](file:///d:/code/EmPower%20in%20java/Repository/) | Encapsulates text-file data storage (`users.txt`/`leaves.txt`). |

---

## Detailed Pattern Breakdown

---

### 1. Singleton Pattern

- **Category**: Creational
- **Where in Code**: 
  - [EmployeeRepository.java](file:///d:/code/EmPower%20in%20java/Repository/EmployeeRepository.java#L18-L26)
  - [LeaveRepository.java](file:///d:/code/EmPower%20in%20java/Repository/LeaveRepository.java#L21-L29)
- **Why it was used**:
  In a console application, reading from and writing to disk files (`users.txt` and `leaves.txt`) must be synchronized across all screens and menus. If multiple repositories were created, changes made in one menu wouldn't reflect in another, or file writes could conflict.
- **How it Works**:
  - The repository constructor is marked `private`.
  - A `private static instance` holds the single created object.
  - A `public static synchronized getInstance()` method checks if `instance == null`, creates it once, and returns it everywhere.
- **Alternatives & Comparison**:
  - *Alternative 1: Dependency Injection Framework (e.g. Spring Container)*. Ideal for large enterprise apps, but introduces heavy external dependencies.
  - *Alternative 2: Passing raw instances everywhere manually*. Prone to duplicate instantiation errors when new handlers or sub-menus are added.

---

### 2. Factory Method Pattern (GoF Classic)

- **Category**: Creational
- **Where in Code**: 
  - Abstract Creator: [EmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/EmployeeFactory.java)
  - Concrete Factories: [AdminEmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/AdminEmployeeFactory.java), [HrEmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/HrEmployeeFactory.java), [StandardEmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/StandardEmployeeFactory.java), [InternEmployeeFactory.java](file:///d:/code/EmPower%20in%20java/Factory/InternEmployeeFactory.java)
- **Why it was used**:
  Instantiating different employee types (`AdminMember`, `HrMember`, `Employee`, `InternEmployee`) requires a dedicated creator hierarchy. The abstract `EmployeeFactory` class defines the `CreateEmployee()` interface, and each concrete factory overrides it to instantiate its respective model.
- **How it Works**:
  - `EmployeeFactory` is an abstract class with `public abstract EmployeeBase CreateEmployee(...)`.
  - Concrete creators override `CreateEmployee(...)` returning new instances of their respective model class.
  - A static lookup helper `EmployeeFactory.Create(role, ...)` maps role strings to factory instances for seamless instantiation.
- **Alternatives & Comparison**:
  - *Alternative 1: Parameterized Simple Factory*. Uses a single `switch` statement in one factory class. Simple, but requires modifying the factory when adding new roles.
  - *Alternative 2: Abstract Factory Pattern*. Used when creating families of related objects (e.g., UI widgets or OS drivers). Unnecessary here since we only construct employee entities.

---

### 3. Strategy Pattern

- **Category**: Behavioral
- **Where in Code**: 
  - Interface: [ISalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/ISalaryStrategy.java)
  - Concrete Strategies: [AdminSalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/AdminSalaryStrategy.java), [HrSalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/HrSalaryStrategy.java), [EmployeeSalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/EmployeeSalaryStrategy.java), [InternSalaryStrategy.java](file:///d:/code/EmPower%20in%20java/Strategy/InternSalaryStrategy.java)
  - Injected in [EmployeeBase.java](file:///d:/code/EmPower%20in%20java/Abstract/EmployeeBase.java#L111-L115) and subclass constructors.
- **Why it was used**:
  Different employee roles have different compensation rules (Admin gets a 20% allowance, HR gets a 10% allowance, Interns get a fixed stipend). Hardcoding logic in employee classes couples domain entities with payroll rules. Strategy decouples calculation algorithms into standalone objects.
- **How it Works**:
  - `EmployeeBase` maintains a reference `ISalaryStrategy salaryStrategy`.
  - When `CalculateSalary()` is called, it delegates to `salaryStrategy.CalculateSalary(getName(), getSalary())`.
- **Alternatives & Comparison**:
  - *Alternative 1: Hardcoding `switch(role)` inside a single `CalculateSalary()` method*. Violates the Single Responsibility Principle.
  - *Alternative 2: Overriding `CalculateSalary()` in subclasses without strategy objects*. Works for static roles, but Strategy allows dynamic strategy changes at runtime (e.g., assigning a temporary performance bonus strategy to an employee).

---

### 4. Observer Pattern

- **Category**: Behavioral
- **Where in Code**: 
  - Interface: [ILeaveObserver.java](file:///d:/code/EmPower%20in%20java/Observer/ILeaveObserver.java)
  - Concrete Observer: [HRLeaveObserver.java](file:///d:/code/EmPower%20in%20java/Observer/HRLeaveObserver.java) (wires into [INotificationService.java](file:///d:/code/EmPower%20in%20java/Interfaces/INotificationService.java))
  - Subject Registration & Notification: [LeaveRepository.java](file:///d:/code/EmPower%20in%20java/Repository/LeaveRepository.java#L31-L43) (`AddObserver`, `NotifyObservers`)
- **Why it was used**:
  When a leave request status changes (e.g., from `Pending` to `Approved` or `Rejected`), other modules (HR notification logs, audit logs, email notifications) need to react automatically without tightly coupling the repository to specific notification services.
- **How it Works**:
  - `LeaveRepository` acts as the Subject maintaining a list of `ILeaveObserver` instances.
  - Whenever `AddLeaveRequest()` or `UpdateLeaveStatus()` executes, `NotifyObservers()` iterates through observers calling `OnLeaveStatusChanged(leave)`.
- **Alternatives & Comparison**:
  - *Alternative 1: Direct method calls from `LeaveRepository` to `EmailService` or `NotificationService`*. Tightly couples repository to concrete notification classes.
  - *Alternative 2: Polling*. Periodically checking database for leave status changes. Inefficient and introduces delays.

---

### 5. Command Pattern

- **Category**: Behavioral
- **Where in Code**: 
  - Interface: [ICommand.java](file:///d:/code/EmPower%20in%20java/Command/ICommand.java)
  - Concrete Commands: [HireEmployeeCommand.java](file:///d:/code/EmPower%20in%20java/Command/HireEmployeeCommand.java), [FireEmployeeCommand.java](file:///d:/code/EmPower%20in%20java/Command/FireEmployeeCommand.java), [ListEmployeesCommand.java](file:///d:/code/EmPower%20in%20java/Command/ListEmployeesCommand.java), [ViewProfileCommand.java](file:///d:/code/EmPower%20in%20java/Command/ViewProfileCommand.java), [SubmitLeaveCommand.java](file:///d:/code/EmPower%20in%20java/Command/SubmitLeaveCommand.java), [UpdateLeaveStatusCommand.java](file:///d:/code/EmPower%20in%20java/Command/UpdateLeaveStatusCommand.java)
  - Executed in [AdminMenuHandler.java](file:///d:/code/EmPower%20in%20java/MenuUI/AdminMenuHandler.java#L58-L69) and [EmployeeMenuHandler.java](file:///d:/code/EmPower%20in%20java/MenuUI/EmployeeMenuHandler.java#L40-L48)
- **Why it was used**:
  Decouples the UI menu handlers (`AdminMenuHandler`, `EmployeeMenuHandler`) from direct business service logic. Each menu option becomes an object implementing `Execute()`.
- **How it Works**:
  - The menu handler receives user input, constructs a matching `ICommand` object, and calls `command.Execute()`.
- **Alternatives & Comparison**:
  - *Alternative 1: Monolithic `switch-case` statements calling service methods directly*. Tightly couples UI code to service logic and makes adding undo/redo features or action logging difficult.

---

### 6. Facade Pattern

- **Category**: Structural
- **Where in Code**: 
  - [EmPowerFacade.java](file:///d:/code/EmPower%20in%20java/Facade/EmPowerFacade.java)
  - Invoked in [Program.java](file:///d:/code/EmPower%20in%20java/Program.java)
- **Why it was used**:
  Setting up the application requires instantiating repositories, wiring services, attaching observers, initializing menus, and starting authentication. Without a Facade, `Program.java` becomes bloated with setup code.
- **How it Works**:
  - `EmPowerFacade` encapsulates the entire subsystem setup inside its constructor and exposes a single simple `StartSystem()` method.
  - `Program.java` only needs two lines of code: `new EmPowerFacade().StartSystem();`.
- **Alternatives & Comparison**:
  - *Alternative 1: Initializing everything in `main()`*. Pollutes entry point with complex dependency setup.

---

### 7. Template Method Pattern

- **Category**: Behavioral
- **Where in Code**: 
  - Skeleton: [EmployeeBase.java](file:///d:/code/EmPower%20in%20java/Abstract/EmployeeBase.java#L87-L97) (`PrintDetails()`)
  - Primitive Operations: `GetRole()` & `CalculateBonus()` overridden in [AdminMember.java](file:///d:/code/EmPower%20in%20java/Models/AdminMember.java), [HrMember.java](file:///d:/code/EmPower%20in%20java/Models/HrMember.java), [Employee.java](file:///d:/code/EmPower%20in%20java/Models/Employee.java), [InternEmployee.java](file:///d:/code/EmPower%20in%20java/Models/InternEmployee.java)
- **Why it was used**:
  Formatting employee details (headers, ID, Name, Department, Position, Salary, Footers) is identical for all roles. However, the specific role title (`GetRole()`) is role-specific. Template Method enforces uniform detail layout while letting subclasses customize key steps.
- **How it Works**:
  - `PrintDetails()` executes fixed print steps, calling `GetRole()` which is resolved polymorphically.
- **Alternatives & Comparison**:
  - *Alternative 1: Duplicate printing logic in every subclass*. Leads to code duplication.

---

### 8. Repository Pattern

- **Category**: Enterprise Architecture / Data Access
- **Where in Code**: 
  - [EmployeeRepository.java](file:///d:/code/EmPower%20in%20java/Repository/EmployeeRepository.java)
  - [LeaveRepository.java](file:///d:/code/EmPower%20in%20java/Repository/LeaveRepository.java)
- **Why it was used**:
  Separates business logic from data storage mechanisms (`users.txt` and `leaves.txt`). Services operate on high-level collection methods (`AddEmployee`, `GetByID`, `GetAllEmployees`) without knowing how data is serialized or saved to files.
- **Alternatives & Comparison**:
  - *Alternative 1: Direct File I/O inside Menu handlers or Services*. Hard to maintain and test.

---

## Instructor Viva / Presentation Cheat Sheet

When asked by your teacher to defend your design:

1. **"Why did you use Singleton for Repositories?"**
   > *"Because our application uses text files (`users.txt` and `leaves.txt`) as its persistence storage. Having multiple instances of `EmployeeRepository` would risk out-of-sync memory states or file write corruption. Singleton guarantees a single, synchronized data store."*

2. **"How does Factory Method improve your code?"**
   > *"It encapsulates employee object creation (`AdminMember`, `HrMember`, etc.) into `EmployeeFactory.CreateEmployee()`. If we add a new role like `ManagerMember` tomorrow, we only update `EmployeeFactory` without touching repository or service code."*

3. **"Explain the difference between your Strategy pattern and simple inheritance."**
   > *"Inheritance hardcodes salary calculation into the employee class hierarchy. Strategy decouples calculation logic into `ISalaryStrategy` implementations (`AdminSalaryStrategy`, `HrSalaryStrategy`), allowing calculation rules to change or be swapped dynamically at runtime."*

4. **"Where is your Observer pattern used?"**
   > *"In `LeaveRepository`. When a leave status updates, `NotifyObservers()` notifies registered observers like `HRLeaveObserver`, which triggers alerts without coupling the repository directly to notification logic."*

5. **"What is the role of your Facade?"**
   > *"It hides the complex wiring of singletons, observers, services, and UI handlers behind `EmPowerFacade.StartSystem()`, keeping `Program.java` clean and minimal."*

---

## Build & Execution Instructions

To compile all Java classes into the `bin` directory:
```powershell
javac -d bin Program.java Abstract/*.java Command/*.java Facade/*.java Factory/*.java Interfaces/*.java Login/*.java MenuUI/*.java Models/*.java Observer/*.java Repository/*.java Services/*.java Strategy/*.java
```

To run the application from the `bin` directory:
```powershell
java -cp bin Program
```

