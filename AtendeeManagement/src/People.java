/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// This is abstract class, children is Employee, DepartHead, HR
public abstract class People {
    
    protected String name;
    protected DepartType department;
    protected PeopleType peopleType;
    protected Date date;

    // constructor for creating attendee people
    public People(String name, DepartType department, Date date, PeopleType peopleType) {
        this.name = name;
        this.department = department;
        this.date = date;
        this.peopleType = peopleType;
    }

    // constructor for getting all employees from file
    public People(String name, DepartType department, PeopleType peopleType) {
        this.name = name;
        this.department = department;
        this.peopleType = peopleType;
    }
    // getters
    protected abstract String getName();
    protected abstract void setName(String name);
    protected abstract PeopleType getPeopleType();
    protected abstract DepartType getDepartment();
    protected abstract Date getDate();
    // make attendance for employee itself
    protected abstract void makeAttendance(DoublyLinkedList<People> attendees);

}
