# CS5004Final
Repository for CS5004 Final assignment submission

Name: Xuedinan(Dinan) Gao\
Semester: 2024 Spring

## :star: Program Overview
- **The major goal of the whole program is to track/manipulate each employee's attendance of the whole company employees.**
  <br/><br/>
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

    - Generic Doubly linked list class and Operation Class
        - DoublyLinkedList Class
        - AttendanceTool Class, MVC

    - Covered different concepts (Inheritance, Polymorphism, Composition, Abstraction, 
                                    Recursion, High-Order, Lambda, ADT, Filter, Equality, Stream, MVC, Defensive Programming, I/O)
## :star: UML for the program
![image](https://github.com/Xuedinan/CS5004Final/assets/144306521/ddc5765b-4df4-4b0d-8e80-ec9ba024b498)

-  Sample file for Employee list ("full_fake_name_list_10.txt" / "full_fake_name_list_100.txt" )
-  Sample file for Attendee list ("attendees_fake_name_list_10.txt" )

## :star: Major functional methods / People Methods List

    Employee
    - Getters for attributes
    - Make attendance

    Head
    - Getters for attributes
    - Make attendance
    - Advanced methods for Head

        // change HEAD and HR own date
        void changeOwnDate(Date date);

        // change date for input employee
        void changeEmployeeDate(People employee, Date date);

        // remove employee from company employee list
        // head can only remove employee in the same department
        void removeEmployee(People employee);
        
        // add employee from company employee list
        // head can only add employee in the same department
        void addEmployee(T employee);

        // set employee's name when they are in the department
        void setEmployeeName(People employee, String name);

        // change other department's employee to head's department
        void setDepartment(People employee, DepartType targetDepart);

        // make attendance for employee when they are in the same department
        void makeEmployeeAttendance(People employee);

        // checking for expiration date
        DoublyLinkedList<T> filterOwnDepartment();
        
        // checking for task status
        DoublyLinkedList<T> filterDate(Date date);

        // get total employee numbers
        int totalDepartEmployees();

        // get total attendees number for certain department
        int totalAttendees();

    HR
    - Getters for attributes
    - Make attendance
    - Advanced methods for HR (On top of all Head methods)

        // promote employee to HEAD
        void addHead(People employee);

        // demote HEAD to employee
        void removeHead(People employee);

        // generate all head list from whole company employee list
        void createHeadList();

        // get whole company's attendees
        void checkAllAttendees();

        // get all employee with certain company wise
        DoublyLinkedList<T> filterAllByLevel(PeopleType type);

        // get whole employee number
        int totalCompanyEmployees();
        
        //get each department employees
        DoublyLinkedList<T> filterByDepartment(DepartType type);
        
## :star: Concept MAP
  - Inheritance and Abstraction
    - People abstract classes with extended children classes (Employee, HR, DepartHead)
```
 // employee only can change name, and get information for themselves
public class Employee extends People {

    protected String name;
    protected DepartType department;
    protected PeopleType peopleType;
    protected Date date;
```
// Department Head class, can only operate employee in the same department
public class DepartHead<T> extends People implements HeadTool<T>, AnalysisTool<T> {

    // list to hold whole company employees
    DoublyLinkedList<T> people;
    // list to hold attendees
    DoublyLinkedList<T> attendees;

    private String name;
    private DepartType department;
    private PeopleType peopleType;
    private Date date;
    
```
// HR class, has the biggest power to operate whole company employees, include head
public class HR<T> extends People implements HeadTool<T>, AnalysisTool<T>, HRTool<T>{
    
    // list to hold whole company employees
    protected DoublyLinkedList<T> people;
    // list to hold attendees
    protected DoublyLinkedList<T> attendees;
    // array to hold all department HEAD only
    protected ArrayList<People> headList = new ArrayList<>();

    private String name;
    private DepartType department;
    private PeopleType peopleType;
    private Date date;
