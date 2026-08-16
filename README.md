# EmPower - Employee Management System

EmPower is a robust, object-oriented console application built with **.NET 9** that allows you to manage employees, handle administration tasks, and process employee leave requests. 

This project was built to demonstrate core **C# Object-Oriented Programming (OOP)** concepts, including Inheritance, Abstract classes, Dependency Injection, and File-based Data Persistence.

## Features

### Admin Panel
* **Hire Employee:** Add new staff with specific roles (`Admin`, `HR`, `Employee`, `Intern`), departments, salaries, and positions.
* **Fire Employee:** Remove staff using their Employee ID.
* **List All Employees:** View the current database of all staff.
* **View Employee Profile:** Look up specific staff details using their ID.

### Employee Panel
* **View Profile:** View personal data securely using an Employee ID.
* **Edit Profile:** *(Planned feature)*
* **Apply For Leave:** Staff can submit leaves by providing start dates, end dates, and reasons.
* **View My Leaves:** Look up the status (`Pending`, `Approved`, `Rejected`) of submitted leave applications.

## Technologies Used
* **C# / .NET 9.0**
* Console User Interface
* **Text-File Database System** (`users.txt`, `leaves.txt`) for persistent tracking even when the app is closed.

## Architecture

This application follows a highly separated **Ecosystem Design Pattern** to prevent tight-coupling and make future modifications easy.

* **Models Layer (`Models/`, `Abstract/`):** Contains pure data structures such as `EmployeeBase` and `LeaveRequests`.
* **Repository Layer (`Repository/`):** Controls all interactions with the database (reading/writing to the `.txt` files) keeping state centralized.
* **Service Layer (`Services/`):** The logic and operation layer where business functionality executes.
* **UI/Menu Layer (`MenuUI/`):** View handlers (like Waiters in a restaurant) that talk to the user and pass data strictly to the Service layer.
* **Program.cs (`Manager`):** Utilizes **Dependency Injection** by building the Repositories and Services exactly once and passing their shared references down completely minimizing data de-sync.

## How to Run
1. Ensure you have the **.NET 9 SDK** installed.
2. Clone the repository to your local machine.
3. Open a terminal to the workspace root directory.
4. Run the application using the dotnet CLI:
```sh
dotnet run
```
5. Follow the required login prompts. (See source code for standard/default passwords).

## Building a Standalone Executable
If you would like to distribute this application as a single `.exe` file without the user needing to install the .NET runtime:
```sh
dotnet publish -c Release -r win-x64 --self-contained true -p:PublishSingleFile=true
```
You will find your ready-to-share executable inside the `bin/Release/net9.0/win-x64/publish/` folder.