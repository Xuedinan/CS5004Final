import java.util.function.Predicate;
/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

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

    // constructor
    public DepartHead(String name, DepartType department, Date date, PeopleType peopleType, DoublyLinkedList<T> people, DoublyLinkedList<T> attendees) {
        super(name, department, date, peopleType);
        this.name = name;
        this.date = date;
        this.department = department;
        this.peopleType = peopleType;
        this.people = people;
        this.attendees = attendees;
    }

    // getters for head itself
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

    // remove a employee from department
    @Override
    public void removeEmployee(People employee) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee.");
            }
            
            if (!employee.getDepartment().equals(department)) {
                // check if the employee is not in this department
                System.out.println("Employee " + employee.getName() + " is not in our department - " + department + " can't remove.");
                return;
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

    // add employee to the department
    @Override
    public void addEmployee(T employee) {
        try {
            // only add employee when they are in the same department
            if (!((People) employee).getDepartment().equals(department)) {
                throw new IllegalArgumentException("\n" + ((People) employee).getName() + " is not our department employee. Can't add. \n");
            }
            else {
                people.addLast(employee);
                System.out.println("Employee " + ((People) employee).getName() + " has been added.");
            }
        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when employee is invalid
            System.err.println(e.getMessage());
        }
    }

    // change employee's name
    @Override
    public void setEmployeeName(People employee, String name) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update name. ");
            }
            
            if (!employee.getDepartment().equals(department)) {
                // check if the employee is not in this department
                System.out.println("Employee " + employee.getName() + " is not in our department - " + department);
                return;
            }
            // If we get here, the employee is in the department and can be updated name.
            employee.setName(name);
            System.out.println("Employee " + employee.getName() + " has been updated to : " + name);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }

    // change employee's department from other departments to this department
    @Override
    public void setDepartment(People employee, DepartType targetDepart) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't change department ");
            }
            
            if (employee.getDepartment().equals(department)) {
                // check if the employee is not in this department
                System.out.println("Employee " + employee.getName() + " is already in our department - " + department);
                return;
            }
            // If we get here, the employee is in the department and can be updated name.
            employee.department = department;
            System.out.println("Employee " + employee.getName() + "'s department has been update to our department " + department);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }

    // get all employee in the same department
    @Override
    public DoublyLinkedList<T> filterOwnDepartment() {
        // check if department is the same
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(department);
        // return a filtered list
        return people.filterByPredicate(isDepart);
    }
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
    // get total attendees number
    @Override
    public int totalAttendees() {
        return filterOwnDepartmentAttendee().countNodes();
    }
    // head make a attendance for own department employee
    @SuppressWarnings("unchecked")
    @Override
    public void makeEmployeeAttendance(People employee, Date date) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update. ");
            }
            
            if (!employee.getDepartment().equals(department)) {
                // check if the employee is not in this department
                System.out.println("Employee " + employee.getName() + " is not in our department - " + department);
                return;
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

    // change employee's attendance date
    @Override
    public void changeEmployeeDate(People employee, Date date) {
        try {
            int num = people.findIndexByPeople(employee);
            if (num == -1) { // return -1 means we can't find the employee in whole list
                throw new IllegalArgumentException("\n" + employee.getName() + " is not our employee. Can't update date. ");
            }
            
            if (!employee.getDepartment().equals(department)) {
                // check if the employee is not in this department
                System.out.println("Employee " + employee.getName() + " is not in our department - " + department);
                return;
            }
            // If we get here, the employee is in the department and can be updated date.
            employee.date = date;
            System.out.println("Employee " + employee.getName() + " has been updated with new date: " + date);

        } catch (IllegalArgumentException e) {
            // IllegalArgumentException thrown when the employee is not found.
            System.err.println(e.getMessage());
        }
    }

    // change head's own date
    @Override
    public void changeOwnDate(Date date) {
        this.date = date;
    }

    // get total number of department employees
    @Override
    public int totalDepartEmployees() {
        Predicate<T> isDepart = employee -> ((People) employee).getDepartment().equals(department);
        return people.countNodesByPredicate(isDepart);
    }

    // for this head to make a attendance for itself
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
