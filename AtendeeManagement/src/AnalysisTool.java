/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

 // This is a interface for Head and HR to manipulate the employee list and attendance list
public interface AnalysisTool<T> {

	// checking for expiration date
	DoublyLinkedList<T> filterOwnDepartment();
	
    // checking for task status
	DoublyLinkedList<T> filterDate(Date date);

    // get total employee numbers
    int totalDepartEmployees();

    // get total attendees number for certain department
    int totalAttendees();
}
