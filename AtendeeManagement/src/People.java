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

    protected abstract String getName();
    protected abstract void setName(String name);
    protected abstract PeopleType getPeopleType();
    protected abstract DepartType getDepartment();
    protected abstract Date getDate();

}
