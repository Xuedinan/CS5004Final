import java.util.ArrayList;

public class Driver {
    public static <T> void main(String[] args) {
        Date date1 = new Date(2, 23, 1989);
        Date date2 = new Date(12, 23, 1989);
        People tp = new Employee("haha", DepartType.SALES, PeopleType.EMPLOYEE);
        People tt = new Employee("Jesse Robinson", DepartType.SALES, PeopleType.HEAD);

        FileTool ft = new FileTool();

        // @SuppressWarnings("unchecked")
        DoublyLinkedList<People> test1 = ft.readPeopleFromFile("full_fake_name_list_10.txt");
        // test1.printList();

        // test1.addLast(head1);
        // test1.iterator();
        // test1.printList();
        DoublyLinkedList<People> attendees = new DoublyLinkedList<>();

        DepartHead<People> head1 = new DepartHead<>("Tom", DepartType.SALES, date1, PeopleType.HEAD, test1, attendees);

        // head1.removeEmployee(tp);

                People ppp = new Employee("Marketing yoyo", DepartType.MARKETING, PeopleType.EMPLOYEE);
        // head1.addEmployee(ppp);
        // head1.setEmployeeName(ppp, "new name");
        // head1.setDepartment(tt);

                // test1.printList();
        // salesEmployee.printList();

        DoublyLinkedList<People> headlist = head1.filterOwnDepartment();
        // headlist.printList();
        // System.out.println(test1.countNodes());
        // int tmp = head1.totalDepartEmployees();

        // System.out.println(head1.totalDepartEmployees());

        // System.out.println(test1.countNodes());
        // System.out.println(head1.totalDepartEmployees());
        // head1.people.printList();
        // System.out.println(tt.date);
        // head1.changeEmployeeDate(tt, date2);
        // head1.makeEmployeeAttendance(tt);
        // head1.attendees.printList();
        // System.out.println(head1.attendees.countNodes());
        ArrayList<String> al = new ArrayList<>();

        HR<People> hr = new HR<>("HR TEST", DepartType.HRDEPART, date2, PeopleType.HR, test1, attendees);
        hr.createHeadList();
    }
}
 