# EmPower — Java Design Pattern-Driven Employee & Leave System

**EmPower** is a Java-based console application designed to demonstrate the real-world implementation of Gang of Four (GoF) software design patterns. It models an Enterprise Employee & Leave Management System, featuring multi-role security (Admin, HR, Employee, Intern), compensation rule processing, dynamic leave request workflows, and atomic file-backed data storage (`users.txt` and `leaves.txt`).

---

## 🚀 Key Features

### 🔑 Authentication
* Password-protected login flow (`CheckPassword`) preventing unauthorized access.

### 🛡️ Admin Operations Panel
* **Hire Employee**: Dynamically create staff profiles (`Admin`, `HR`, `Employee`, `Intern`) via the **Factory Method** pattern.
* **Fire Employee**: Remove staff from the system using Employee ID.
* **List All Employees**: Print current employee records using the **Template Method** pattern.
* **View Employee Profile**: Search and view detailed employee profiles and role-specific bonuses.
* **Review & Update Leave Requests**: View pending employee leave applications and update status (`Pending`, `Approved`, `Rejected`).

### 👤 Employee Self-Service Panel
* **View Profile**: Access personal employment details.
* **Apply For Leave**: Submit leave requests with dynamic start/end dates and leave reasons.
* **View My Leaves**: Track personal leave application statuses.
* **Real-Time Observer Notifications**: Receive automatic notification alerts upon leave status changes.

---

## 🏗️ Applied Design Patterns Architecture

The application is structured into a 7-tier architecture powered by **8 Software Design Patterns**:

| Pattern | Category | Where Used | Architectural Benefit |
| :--- | :--- | :--- | :--- |
| **1. Singleton** | Creational | `EmployeeRepository`, `LeaveRepository` | Guarantees single-instance thread safety for text-file persistence (`users.txt`, `leaves.txt`). |
| **2. Factory Method** | Creational | `EmployeeFactory` | Centralizes creation of role-based employee objects (`AdminMember`, `HrMember`, etc.). |
| **3. Strategy** | Behavioral | `ISalaryStrategy` (`AdminSalaryStrategy`, etc.) | Decouples role-specific payroll allowance formulas from domain entity models. |
| **4. Observer** | Behavioral | `ILeaveObserver`, `HRLeaveObserver` | Automatically dispatches notification alerts when leave request status changes. |
| **5. Command** | Behavioral | `ICommand` (`HireEmployeeCommand`, etc.) | Encapsulates UI menu choices into executable objects, decoupling UI from services. |
| **6. Facade** | Structural | `EmPowerFacade` | Hides complex subsystem setup, observer wiring, and authentication behind a clean startup method. |
| **7. Template Method** | Behavioral | `EmployeeBase.PrintDetails()` | Enforces a uniform layout skeleton for displaying employee profile details. |
| **8. Repository** | Data Access | `Repository/` package | Encapsulates text-file serialization and isolates persistence logic from services. |

---

## 📁 Project Directory Structure

```
EmPower in java/
├── Abstract/                  # Abstract base classes (EmployeeBase, LeaveRequestBase, CheckPassword)
├── Command/                   # Command Pattern implementations (ICommand, HireEmployeeCommand, etc.)
├── Facade/                    # Structural Facade entry point (EmPowerFacade)
├── Factory/                   # Factory Method implementation (EmployeeFactory)
├── Interfaces/                 # Common contract interfaces (IEmployee, ISalaryCalculation, etc.)
├── Login/                     # Authentication view handler (LoginPage)
├── MenuUI/                    # UI View Handlers (AdminMenuHandler, EmployeeMenuHandler)
├── Models/                    # Concrete domain models (AdminMember, HrMember, Employee, InternEmployee, LeaveRequests)
├── Observer/                  # Observer Pattern implementations (ILeaveObserver, HRLeaveObserver)
├── Repository/                # Singleton Data Access Repositories (EmployeeRepository, LeaveRepository)
├── Services/                  # Business logic services (EmployeeService, LeaveService, ProfileServices)
├── Strategy/                  # Strategy Pattern implementations (ISalaryStrategy & Role Strategies)
├── index.html                 # Standalone single-file HTML/CSS/JS Web Dashboard
├── bin/                       # Compiled bytecode target directory (.class files)
├── Program.java               # Clean 2-line Main Entry Point
├── users.txt / leaves.txt     # Text-file persistence stores
├── DESIGN_PATTERNS_GUIDE.md   # Comprehensive student learning guide & viva cheat sheet
└── EmPower_Project_Report.docx # Official academic project report (Word document)
```

---

## 🛠️ How to Build and Run

### Prerequisites
* **Java Development Kit (JDK 17 or Java 26)** installed and added to PATH.

### 1. Compile into the `bin/` Directory
```powershell
javac -d bin Program.java Abstract/*.java Command/*.java Facade/*.java Factory/*.java Interfaces/*.java Login/*.java MenuUI/*.java Models/*.java Observer/*.java Repository/*.java Services/*.java Strategy/*.java
```

### 2. Run the Application
```powershell
java -cp bin Program
```

---

## 📖 Documentation Artifacts

- **[DESIGN_PATTERNS_GUIDE.md](DESIGN_PATTERNS_GUIDE.md)**: A student learning guide detailing pattern definitions, code snippets, trade-offs, and viva defense questions.
- **[EmPower_Project_Report.docx](EmPower_Project_Report.docx)**: The official academic project report matching university lab standards.