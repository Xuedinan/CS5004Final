/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

 // This is a interface for Head and HR use to operate employee list, attendees list
public interface HeadTool<T> {
    
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
}
