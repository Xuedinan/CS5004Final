/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// ====>  The major goal of whole program is to track each employee's attendance of the whole company employees. <====
// ====>                       People has Date attribute means they made a attendance                            <====

/*  
    The key features are;

    - Three different department (Sales, Marketing, HR)
        - DepartType ENUM

    - Three different level people (Employee, Department Head, HR)
        - PeopleType ENUM
        - People Abstract Classes
            - Children: Employee, DepartHead, HR

    - Different accessibility 1.Employee (general), 2.Department Head (high), 3.HR (top) for decoupling
        - HeadTool Interface (Head and HR)
        - AnalysisTool Interface (Head and HR)
        - HRTool Interface (HR)

    - Generic Double linked list class and Operation Class
        - DoublyLinkedList Class
        - AttendanceTool Class

    - Covered different concepts (Inheritance, Polymorphism, Composition, Abstraction, 
                                    Recursion, High-Order, Lambda, ADT, Filter, Equality, Stream, MVC, Defensive Programming, I/O)
*/

/*
    Demo Process;
    
    1. 

 */

public class Driver {
    public static <T> void main(String[] args) {
        Date date1 = new Date(2, 23, 2023);
        Date date2 = new Date(12, 23, 2023);
        People tp = new Employee("haha", DepartType.SALES, date1, PeopleType.EMPLOYEE);
        People tt = new Employee("Jesse Robinson", DepartType.SALES, date2, PeopleType.HEAD);

        AttendanceTool ft = new AttendanceTool();

        // @SuppressWarnings("unchecked")
        DoublyLinkedList<People> test1 = ft.readPeopleFromFile("full_fake_name_list_10.txt");
        // test1.printList();
        DoublyLinkedList<People> attendees = new DoublyLinkedList<>();

        // DepartHead<People> head1 = new DepartHead<>("Tom", DepartType.SALES, date1, PeopleType.HEAD, test1, attendees);

        // head1.removeEmployee(tp);

        People ppp = new Employee("Marketing yoyo", DepartType.MARKETING, PeopleType.EMPLOYEE);


        // DoublyLinkedList<People> headlist = head1.filterOwnDepartment();

        // head1.attendees.printList();
        // test1.printList();

        DoublyLinkedList<People> test2 = ft.readAttendees("attendees_fake_name_list_10.txt");
        // test2.printList();
        // attpeople.printList();
        // DepartHead<People> head2 = new DepartHead<>("Tom", DepartType.SALES, date1, PeopleType.HEAD, test1, test2);
//         // attpeople.printList();
        HR<People> hr = new HR<>("HR TEST", DepartType.HRDEPART, date2, PeopleType.HR, test1, test2);
//         System.out.println(head2.getDepartment());
//         Employee tim = new Employee("Timothy Adams", DepartType.SALES, date2, PeopleType.EMPLOYEE);
//         // head2.makeEmployeeAttendance(tim);
                // attpeople.printList();
        Employee kris = new Employee("Kristine Roberts", DepartType.SALES, date2, PeopleType.EMPLOYEE);
                        test2.printList();

                hr.makeEmployeeAttendance(kris);
                                test2.printList();

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
 