# CS5004Final
Repository for CS5004 Final assignment submission

Name: Xuedinan(Dinan) Gao\
Semester: 2024 Spring

## :star: Program Overview
- **The major goal of the whole program is to track each employee's attendance of the whole company employees.** \
  - People have Date attribute means they made an attendance
  - Provide tier authorization for multiple-layer people at the company
    
## :star: Key Features

    - Three different department (Sales, Marketing, HR)
        - DepartType ENUM

    - Three different level people (Employee, Department Head, HR)
        - PeopleType ENUM
        - People Abstract Classes
            - Children: Employee, DepartHead, HR
            - Head can only operate own department information
            - HR can operate whole company information, include HEAD

    - Different accessibility 1.Employee (general), 2.Department Head (high), 3.HR (top) for decoupling, SOLID
        - HeadTool Interface (Head and HR)
        - AnalysisTool Interface (Head and HR)
        - HRTool Interface (HR)

    - People only in the company employees list can make a attendance
        - Company employee list, doesn't has Date
        - Attendees list, has Date

    - Generic Double linked list class and Operation Class
        - DoublyLinkedList Class
        - AttendanceTool Class, MVC

    - Covered different concepts (Inheritance, Polymorphism, Composition, Abstraction, 
                                    Recursion, High-Order, Lambda, ADT, Filter, Equality, Stream, MVC, Defensive Programming, I/O)
UML
![image](https://github.com/Xuedinan/CS5004Final/assets/144306521/ddc5765b-4df4-4b0d-8e80-ec9ba024b498)

