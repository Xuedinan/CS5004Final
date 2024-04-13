import java.util.ArrayList;
import java.util.function.Predicate;
/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

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

    // constructor
    public HR(String name, DepartType department, Date date, PeopleType peopleType, DoublyLinkedList<T> people, DoublyLinkedList<T> attendees) {
        super(name, department, date, peopleType);
        this.name = name;
        this.date = date;
        this.department = department;
        this.peopleType = peopleType;
        this.people = people;
        this.attendees = attendees;
    }

    // getters for HR itself
    @Override
    public Date getDate() {return date;}
    @Override
    public String getName() {return name;}
    @Override
    public DepartType getDepartment() {return department;}
    @Override
    protected PeopleType getPeopleType() {return peopleType;}
    @Override
    public void setName(String name) {this.name = name;}

    // check HR department employees
    @Override
    public DoublyLinkedList<T> filterOwnDepartment() {
        // check if department is the same
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(department);
        // return a filtered list
        return people.filterByPredicate(isDepart);
    }
    // check all attendees in the same day for whole company
    @Override
    public DoublyLinkedList<T> filterDate(Date date) {
        // check if date is match
        Predicate<T> sameDate = employee -> ((People) employee).getDate().equals(date);
        return attendees.filterByPredicate(sameDate);
    }
    // get total employee numbers of HR department
    @Override
    public int totalDepartEmployees() {
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(department);
        return people.countNodesByPredicate(isDepart);
    }
    // get total attendees numbers
    @Override
    public int totalAttendees() {
        return attendees.countNodes();
    }
    // change HR own attendance date
    @Override
    public void changeOwnDate(Date date) {
        this.date = date;
    }
    // change any employee's date
    @Override
    public void changeEmployeeDate(People employee, Date date) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update date. ");
            }
            // If we get here, the employee is in the department and can be updated date.
            employee.date = date;
            System.out.println("Employee " + employee.getName() + " has been updated with new date: " + date);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }
    // remove any employee from company
    @Override
    public void removeEmployee(People employee) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee.");
            }            
            // If we get here, the employee is in the department and can be removed.
            people.remove(num);
            System.out.println("Employee " + employee.getName() + " has been removed.");

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        } catch (Exception e) {
            // catch exception
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
    // add any employee to company
    @Override
    public void addEmployee(T employee) {
        try {
            // add employee
                people.addLast(employee);
                System.out.println("Employee " + ((People) employee).getName() + " has been added.");
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when employee is invalid
            System.err.println(e.getMessage());
        }
    }
    // set any employees name
    @Override
    public void setEmployeeName(People employee, String name) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update name. ");
            }
            // If we get here, the employee is in the department and can be updated name.
            employee.setName(name);
            System.out.println("Employee " + employee.getName() + " has been updated to : " + name);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }
    // set employee to any department
    @Override
    public void setDepartment(People employee, DepartType targetDepart) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't change department ");
            }
            // If we get here, the employee is in the department and can be updated name.
            employee.department = targetDepart;
            System.out.println("Employee " + employee.getName() + "'s department has been update to department " + targetDepart);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }
    // HR can make attendance for any employees
    @SuppressWarnings("unchecked")
    @Override
    public void makeEmployeeAttendance(People employee, Date date) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update. ");
            }
            // If we get here, the employee is in the department and can be updated name.
            People tmp = new Employee(employee.name, employee.department, date, employee.peopleType);
            attendees.addLast((T) tmp);
            System.out.println("Made a attendance for employee " + employee.getName());

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }
    
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
    // remove head from list
    @Override
    public void removeHead(People employee) {
        int headIndex = findHeadIndex(employee);
        // person not found
        if (headIndex == -1) {
            System.out.println(employee.getName() + " is not a department head, can't remove.");
        } else {
            // remove head
            headList.remove(headIndex);
            System.out.println("Removed " + employee.getName() + " from HEAD list.");
        }
    }
    // helper method to get index of head list
    private int findHeadIndex(People employee) {
        for (int i = 0; i < headList.size(); i++) {
            // return index when we found person
            if (headList.get(i).getName().equals(employee.getName())) {
                return i;
            }
        }
        // return -1 when not found
        return -1;
    }
    // get all attendees for whole company
    @Override
    public void checkAllAttendees() {
        attendees.printList();
    }
    // get all employee by certain level
    @Override
    public DoublyLinkedList<T> filterAllByLevel(PeopleType type) {
        Predicate<T> isLevel = employee -> ((People) employee).getPeopleType().equals(type);
        return people.filterByPredicate(isLevel);
    }
    // get total employee numbers
    @Override
    public int totalCompanyEmployees() {
        return people.countNodes();
    }
    // filter people by given department
    @Override
    public DoublyLinkedList<T> filterByDepartment(DepartType type) {
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(type);
        return people.filterByPredicate(isDepart);
    }
    // create a ArrayList with head only
    @Override
    public void createHeadList() {
        // loop whole company's employees and get head
        DoublyLinkedList.Node<T> current = people.llHead.next;
        while (current != people.llTail) {
            People person = (People) current.people; // cast to People
            if (person.getPeopleType().equals(PeopleType.HEAD)) {
                headList.add(person); // add to head list
            }
            // check next node
            current = current.next;
        }
    }

    // print current HEAD in the head array list
    public void printCurrentHead(){
        for (int i = 0; i < headList.size(); i++) {
        System.out.println(headList.get(i).name + " is HEAD of " + headList.get(i).department); 
       }
    }

    // make attendance for HR itself
    @Override
    protected void makeAttendance(DoublyLinkedList<People> attendees) {
        try {
            if (date != null) {
                attendees.addLast(this);
            } else{
                System.out.println("Can't make attendance without date. ");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Can't make attendance without date. ");
        }
    }
    // tostring can print out head with date or without date (haven't made attendance yet)
    @Override
    public String toString(){
        if (date == null) {
            return ("name: " + name + "\nDepartment: " + department + "\nLevel: " + peopleType + "\n");
        }
        else{
            return ("name: " + name + "\nDepartment: " + department + "\nLevel: " + peopleType + "\nAttended date: " + date + "\n");
        }
    }
}
