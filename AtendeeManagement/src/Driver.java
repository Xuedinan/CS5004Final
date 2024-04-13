/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// ====>  The major goal of whole program is to track each employee's attendance of the whole company employees. <====
// ====>                       People have Date attribute means they made an attendance                          <====

/*  
    Key Features;

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
*/

/*
    People Methods List;

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
    - Advanced methods for HR (Add on to all Head methods)
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
 */

/*
    Demo Process;

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
 */

public class Driver {
    public static <T> void main(String[] args) {

        ////////////////// Preparations

        // create employee (without input date)
        People emPeople1 = new Employee("Timothy Adams", DepartType.MARKETING, PeopleType.EMPLOYEE);
        People emPeople2 = new Employee("Jill Clayton MD", DepartType.SALES, PeopleType.EMPLOYEE);
        People emPeople3 = new Employee("Superman", DepartType.SALES, PeopleType.HEAD);

        // create dates for later use
        Date date1 = new Date(2, 23, 2024);
        Date date2 = new Date(12, 23, 2023);
        Date date3 = new Date(8, 17, 2023);

        // create to I/O attendee list and employee list
        AttendanceTool ft = new AttendanceTool();

        // create employee list
        DoublyLinkedList<People> employeeList = ft.readPeopleFromFile("full_fake_name_list_10.txt");

        // create attendee list with partial attendees out of whole company employees
        // 20 employees total, 10 attendees so far
        DoublyLinkedList<People> attendeeList = ft.readAttendees("attendees_fake_name_list_10.txt");

        DepartHead<People> head1 = new DepartHead<>("Christian Larson", DepartType.MARKETING, date1, PeopleType.HEAD, employeeList, attendeeList);
        HR<People> hr = new HR<>("Megan Lopez", DepartType.HRDEPART, date2, PeopleType.HR, employeeList, attendeeList);

        ////////////////// Get basic information

        System.out.println("\n////////////////// Get basic information\n");
        System.out.println("----- Check all employees -----\n");
        employeeList.printList(); // employee don't have attendance date yet

        System.out.println("----- Check all current attendees -----\n");
        attendeeList.printList(); // attendees have attendance date


        ////////////////// Start Demo

        System.out.println("\n////////////////// Start Demo\n");
        int headnum = head1.totalDepartEmployees();
        System.out.println("----- Head check own department  === Employees === with total " + headnum + " employees, Head1 from Marketing -----\n");
        DoublyLinkedList<People> head1List = head1.filterOwnDepartment();
        head1List.printList();

        int headAtten1 = head1.totalAttendees();
        System.out.println("----- Head check own department === Attendees === with total " + headAtten1 + " employees, Head1 from Marketing -----\n");
        DoublyLinkedList<People> head1Attendee = head1.filterOwnDepartmentAttendee();
        head1Attendee.printList();

        System.out.println("----- Head try to make attendance to employees -----\n");
        head1.makeEmployeeAttendance(emPeople1, date1);
        head1.makeEmployeeAttendance(emPeople3, date3);

        int headAtten2 = head1.totalAttendees();
        System.out.println("\n----- Head check own department attendees with total " + headAtten2 + " employees, Head1 from Marketing -----\n");
        DoublyLinkedList<People> head1updatedAttendee = head1.filterOwnDepartmentAttendee();
        head1updatedAttendee.printList();

        System.out.println("----- Head try to remove employee from own department -----\n");
        head1.removeEmployee(emPeople1);
        head1.removeEmployee(emPeople3);
        DoublyLinkedList<People> head1updatedEmployee = head1.filterOwnDepartment();
        System.out.println("\n");

        System.out.println("----- Head get own department employees again -----\n");
        head1updatedEmployee.printList();

        System.out.println("----- HR get all HEAD list -----\n");
        hr.createHeadList();
        hr.printCurrentHead();
        System.out.println("\n");

        System.out.println("----- HR Promote a Employee to HEAD -----\n");
        hr.addHead(emPeople2);
        System.out.println("\n");
        hr.printCurrentHead();
        System.out.println("\n");


        System.out.println("----- HR filter People Type for  === Employees only === list -----\n");
        DoublyLinkedList<People> hrEmployeelist = hr.filterAllByLevel(PeopleType.EMPLOYEE);
        hrEmployeelist.printList();

        System.out.println("----- HR filter Department Type === HR Department === only list -----\n");
        DoublyLinkedList<People> hrDepartmentList = hr.filterByDepartment(DepartType.HRDEPART);
        hrDepartmentList.printList();

        System.out.println("----- HR get whole company attendees -----\n");
        hr.checkAllAttendees();

    }
}
 
