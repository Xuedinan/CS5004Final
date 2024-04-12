/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// This is interface for HR only 
public interface HRTool<T> {
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
}
