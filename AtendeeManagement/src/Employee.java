/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

 // employee only can change name, and get information for themselves

public class Employee extends People {

    protected String name;
    protected DepartType department;
    protected PeopleType peopleType;
    protected Date date;


    public Employee(String name, DepartType department, Date date, PeopleType peopleType) {
        super(name, department, date, peopleType);
        this.name = name;
        this.department = department;
        this.date = date;
        this.peopleType = peopleType;
    }

    public Employee(String name, DepartType department, PeopleType peopleType){
        super(name, department, peopleType);
        this.name = name;
        this.department = department;
        this.peopleType = peopleType;
    }

    @Override
    public Date getDate() {return date;}

    @Override
    public String getName() {return name;}

    @Override
    public DepartType getDepartment() {return department;}

    @Override
    protected PeopleType getPeopleType(){return peopleType;}

    @Override
    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        if (date == null) {
            return ("name: " + name + "\nDepartment: " + department + "\nLevel: " + peopleType + "\n");
        }
        else{
            return ("name: " + name + "\nDepartment: " + department + "\nLevel: " + peopleType + "\nAttended date: " + date + "\n");
        }
    }

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


}
