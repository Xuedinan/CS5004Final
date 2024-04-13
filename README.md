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
  - :earth_asia: **Inheritance and Abstraction**
    - People abstract classes with extended children classes (Employee, HR, DepartHead)
```
 // employee only can change name, and get information for themselves
public class Employee extends People {

    protected String name;
    protected DepartType department;
    protected PeopleType peopleType;
    protected Date date;
```
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
```
  - :earth_asia: **Polymorphism**
    - Most mehods in the tool interfacese are taking People object, however, in the impletment method, I'm using Employee objects (HRTool, HeadTool Interfaces)
```
    // change other department's employee to head's department
    void setDepartment(People employee, DepartType targetDepart);

    // make attendance for employee when they are in the same department
    void makeEmployeeAttendance(People employee, Date date);
```
![image](https://github.com/Xuedinan/CS5004Final/assets/144306521/6fb5743a-4d1b-4d08-b9c5-e8e0fb0bac77)
  - Also using polymorphism in the Driver
```
        // create employee (without input date)
        People emPeople1 = new Employee("Timothy Adams", DepartType.MARKETING, PeopleType.EMPLOYEE);
        People emPeople2 = new Employee("Jill Clayton MD", DepartType.SALES, PeopleType.EMPLOYEE);
        People emPeople3 = new Employee("Superman", DepartType.SALES, PeopleType.HEAD);
```
  - :earth_asia: **Composition**
    - Using AttendanceTool class to load data from local file then composite with DoublyLinkedList class to create list (In Driver)
```
// create to I/O attendee list and employee list
        AttendanceTool ft = new AttendanceTool();

        // create employee list
        DoublyLinkedList<People> employeeList = ft.readPeopleFromFile("full_fake_name_list_10.txt");
```
  - :earth_asia: **Recursion and Equality**
    - Using recursion helper to iterate Doubly linked list (DoublyLinkedList class)
    - Using Equality in all methods when matching String (DepartHead class, HR class)
```
    // find index for input people name by recursion
    public int findIndexByPeople(People target) {
        // start searching
        int index = recursiveFindIndexByPeople(target, llHead.next, 0);
        if (index == -1) {
        }
        return index;
    }
    // helper method to check each node
    private int recursiveFindIndexByPeople(People target, Node<T> currentNode, int currentIndex) {
        // base case when reach to tail
        if (currentNode == llTail) {
            return -1; // return to -1 when not find
        }
        // check if name is matching
        if (((People) currentNode.people).getName().trim().replaceAll("\\s+", " ").equalsIgnoreCase(target.getName().trim().replaceAll("\\s+", " "))) {
            return currentIndex;
        }
        // recursive check next
        return recursiveFindIndexByPeople(target, currentNode.next, currentIndex + 1);
    }
```
  - :earth_asia: **Lambda and Filter method**
    - Using Lambda expression in all *Filter* methods (DepartHead class and HR class)
```
    // check own department attendees number
    public DoublyLinkedList<T> filterOwnDepartmentAttendee() {
        // check if department is the same
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(department);
        // return a filtered list
        return attendees.filterByPredicate(isDepart);
    }

    // get all attendees in the same date and only for this department
    @Override
    public DoublyLinkedList<T> filterDate(Date date) {
        // check if date is match
        Predicate<T> sameDate = employee -> ((People) employee).getDate().equals(date) && ((People) employee).getDepartment().equals(department);
        return attendees.filterByPredicate(sameDate);
    }
```
```
    // filter list by provided predicate
    public DoublyLinkedList<T> filterByPredicate(Predicate<T> predicate) {
        DoublyLinkedList<T> filteredList = new DoublyLinkedList<>();
        Node<T> currentNode = llHead.next;
        while (currentNode != llTail) { // Iterate through the list
            if (predicate.test(currentNode.people)) { // Check if the current node matches the predicate
                filteredList.addLast(currentNode.people); // Add matching elements to the new list
            }
            currentNode = currentNode.next; // Move to the next node
        }
        return filteredList;
    }
```
  - :earth_asia: **Stream**
    - Using Stream in the HR class (addHead method)
```
    // provide a employee to department head
    @Override
    public void addHead(People employee) {
        // check if input employee is existing in head list
        boolean isHeadAlready = headList.stream()
                                        .anyMatch(person -> person.getName().equals(employee.getName()));
        // if exist, can't add
        if (isHeadAlready) {
            System.out.println(employee.getName() + " is a department HEAD already");
        } else {
            employee.peopleType = PeopleType.HEAD;
            headList.add(employee); // add to head list
            System.out.println("Added " + employee.name + " to HEAD list. ");
        }
    }
```
  - :earth_asia: **ADT and Genreic Class**
    - Using Doubly linked list with head and tail sentinel (DoublyLinkedList class)
    - Using ArrayList to store HEAD list (HR class)
    - Put node inside ADT as an inner class
    - DoublyLinkedList class is a generic class
```
 // Generic doubly linked list class
public class DoublyLinkedList<T> {

    // node inner class to store value
    static class  Node<T> {
        Node<T> prev; // previous node
        T people; // people
        Node<T> next; // next node

        // constructor
        public Node(DoublyLinkedList.Node<T> prev, T people, DoublyLinkedList.Node<T> next) {
            this.prev = prev;
            this.people = people;
            this.next = next;
        }
    }
    
    protected Node<T> llHead; // head sentinel of linked list
    protected Node<T> llTail; // tail sentinel of linked list
```
  - :earth_asia: **Defensive Programming**
    - All methods that have user interaction handling exceptions (DepartHead class, HR class, DoublyLinked class, AttendanceTool class)
    - One of the example from DoublyLinkedList class
```
    // helper method to throw exception
    private IllegalArgumentException illegalIndex(int index){
        return new IllegalArgumentException(String.format("index[%d] is invalid", index));
    }
```
  - :earth_asia: **Apply SOLID principle**
    - Based on different types of people, separate methods into multiple interfaces
    - Generic ADT class
      
## :star: Demo Process
```
    1. Create basic objects for testing (employee list, attendee list, Employee, Head, HR)
    2. Print out basic information (Employees & Current Attendees) for record
    3. Head to get own department employees
    4. Head to get own department attendees
    5. Head make a attendance for a employee
        - only can operate own department employee
        - provide notification for unqualified employee
    6. Head check updated attendee list
    7. Head remove a employee from own department
        - only can operate own department employee
        - provide notification for unqualified employee
    8. HR to get all HEAD list of whole company
    9. HR promote a Employee to HEAD
    10. HR filter People Type (Employee only) list of whole company
    11. HR filter Department Type (HR Department only) list of whole company
    12. HR get whole company attendees
```
```
////////////////// Start Demo

----- Head check own department  === Employees === with total 3 employees, Head1 from Marketing -----

name: Timothy Adams
Department: MARKETING
Level: EMPLOYEE

name: Christian Larson
Department: MARKETING
Level: HEAD

name: Kelly Richmond
Department: MARKETING
Level: EMPLOYEE

----- Head check own department === Attendees === with total 2 employees, Head1 from Marketing -----

name: Jill Clayton MD
Department: MARKETING
Level: EMPLOYEE
Attended date: 12/4/2024

name: Savannah White
Department: MARKETING
Level: HR
Attended date: 7/5/2024

----- Head try to make attendance to employees -----

Made a attendance for employee Timothy Adams

Superman is not our employee. Can't update. 

----- Head check own department attendees with total 3 employees, Head1 from Marketing -----

name: Jill Clayton MD
Department: MARKETING
Level: EMPLOYEE
Attended date: 12/4/2024

name: Savannah White
Department: MARKETING
Level: HR
Attended date: 7/5/2024

name: Timothy Adams
Department: MARKETING
Level: EMPLOYEE
Attended date: 2/23/2024

----- Head try to remove employee from own department -----

Employee Timothy Adams has been removed.

Superman is not our employee.


----- Head get own department employees again -----

name: Christian Larson
Department: MARKETING
Level: HEAD

name: Kelly Richmond
Department: MARKETING
Level: EMPLOYEE

----- HR get all HEAD list -----

James Ayala is HEAD of SALES
Jesse Robinson is HEAD of SALES
Savannah White is HEAD of HRDEPART
Bonnie Morrison is HEAD of SALES
Christian Larson is HEAD of MARKETING


----- HR Promote a Employee to HEAD -----

Added Jill Clayton MD to HEAD list. 


James Ayala is HEAD of SALES
Jesse Robinson is HEAD of SALES
Savannah White is HEAD of HRDEPART
Bonnie Morrison is HEAD of SALES
Christian Larson is HEAD of MARKETING
Jill Clayton MD is HEAD of SALES


----- HR filter People Type for  === Employees only === list -----

name: John Rodriguez
Department: SALES
Level: EMPLOYEE

name: Cynthia Wolfe
Department: SALES
Level: EMPLOYEE

name: Jill Clayton MD
Department: SALES
Level: EMPLOYEE

name: Kristine Roberts
Department: SALES
Level: EMPLOYEE

name: Sara Pope
Department: HRDEPART
Level: EMPLOYEE

name: William Hernandez
Department: HRDEPART
Level: EMPLOYEE

name: Carla Miller
Department: SALES
Level: EMPLOYEE

name: Carl Castillo
Department: SALES
Level: EMPLOYEE

name: Karen Carey
Department: HRDEPART
Level: EMPLOYEE

name: Crystal Johnson
Department: HRDEPART
Level: EMPLOYEE

name: Nicholas Clark
Department: SALES
Level: EMPLOYEE

name: Kelly Richmond
Department: MARKETING
Level: EMPLOYEE

----- HR filter Department Type === HR Department === only list -----

name: Benjamin Watson
Department: HRDEPART
Level: HR

name: Megan Lopez
Department: HRDEPART
Level: HR

name: Savannah White
Department: HRDEPART
Level: HEAD

name: Sara Pope
Department: HRDEPART
Level: EMPLOYEE

name: William Hernandez
Department: HRDEPART
Level: EMPLOYEE

name: Karen Carey
Department: HRDEPART
Level: EMPLOYEE

name: Crystal Johnson
Department: HRDEPART
Level: EMPLOYEE

----- HR get whole company attendees -----

name: Benjamin Watson
Department: SALES
Level: HR
Attended date: 4/4/2024

cut off the list to save space.....
```
## :star: Academic Integrity Statement
![image](https://github.com/Xuedinan/CS5004Final/assets/144306521/33833daf-1139-4655-9ac8-61b139563741)
