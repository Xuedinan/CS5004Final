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

 */

public class Driver {
    public static <T> void main(String[] args) {

        ////////////////// Preparations

        // create employee (without input date)
        People emPeople1 = new Employee("Timothy Adams", DepartType.MARKETING, PeopleType.EMPLOYEE);
        People emPeople2 = new Employee("Jill Clayton MD", DepartType.SALES, PeopleType.EMPLOYEE);
        People emPeople3 = new Employee("Failed name", DepartType.SALES, PeopleType.HEAD);

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

        ////////////////// Demo

        // employeeList.printList(); de-comment to see list
        // attendeeList.printList(); de-comment to see list



//         // DepartHead<People> head1 = new DepartHead<>("Tom", DepartType.SALES, date1, PeopleType.HEAD, test1, attendees);

//         // head1.removeEmployee(tp);

//         People ppp = new Employee("Marketing yoyo", DepartType.MARKETING, PeopleType.EMPLOYEE);


//         // DoublyLinkedList<People> headlist = head1.filterOwnDepartment();

//         // head1.attendees.printList();
//         // test1.printList();

//         DoublyLinkedList<People> test2 = ft.readAttendees("attendees_fake_name_list_10.txt");
//         // test2.printList();
//         // attpeople.printList();
//         // DepartHead<People> head2 = new DepartHead<>("Tom", DepartType.SALES, date1, PeopleType.HEAD, test1, test2);
// //         // attpeople.printList();
//         HR<People> hr = new HR<>("HR TEST", DepartType.HRDEPART, date2, PeopleType.HR, test1, test2);
// //         System.out.println(head2.getDepartment());
// //         Employee tim = new Employee("Timothy Adams", DepartType.SALES, date2, PeopleType.EMPLOYEE);
// //         // head2.makeEmployeeAttendance(tim);
//                 // attpeople.printList();
//         Employee kris = new Employee("Kristine Roberts", DepartType.SALES, date2, PeopleType.EMPLOYEE);
//                         test2.printList();

//                 hr.makeEmployeeAttendance(kris);
//                                 test2.printList();

        // System.out.println(kris.getName()); 
//                 // hr.attendees.printList();
        // hr.people.printList();
                // hr.makeEmployeeAttendance(kris);
                // head2.makeEmployeeAttendance(kris);
        // System.out.println(test1.findIndexByPeople(kris));
        // test1.printList();
        // test1.printList();
// // // Kristine Roberts&Sales&EMPLOYEE&2024/05/03

//         kris.makeAttendance(attpeople);
        // test2.printList();

//         // DoublyLinkedList<People> newAttendees = ft.readAttendees("attendees_fake_name_list_10.txt");
//         // newAttendees.printList();
        // hr.attendees.printList();
// // //         // attpeople.printList();

        // ft.writeAttendeesFile("attendees_fake_name_list_10.txt", attpeople);
// //         head2.attendees.printList();

// // head2.attendees.printList();
// hr.attendees.printList();

//         ft.writeAttendeesFile("attendees_fake_name_list_10.txt", attpeople);



    }
}
 
