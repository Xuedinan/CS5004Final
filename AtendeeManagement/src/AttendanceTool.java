import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
/*
 * Course: CS5004
 * Semester: Spring 2024
 * Assignment: Final
 * Name: Xuedinan Gao
 */

// This is the class to help read employees from file to create linked list and write employee into file with correct format

public class AttendanceTool {
	
	public AttendanceTool() {};
	
    // Assuming DoublyLinkedList and People classes are defined elsewhere
    public DoublyLinkedList<People> readPeopleFromFile(String filePath) {
        DoublyLinkedList<People> people = new DoublyLinkedList<>();
        // Use try-with-resources for automatic resource management
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                People employee = stringParts(line); // Convert line to a People object
                people.addLast(employee); // Add the object to the list
            }
        } catch (IOException e) {
            e.printStackTrace(); // Consider more specific error handling
        }
        return people;
    }

    // helper method to make string in parts
    public Employee stringParts(String line) {
        // split the content from file
        String[] parts = line.split("\\&");
        // check if out of bounds
        if (parts.length >= 3) {
            // get name
            String name = parts[0];
            // get department type
            DepartType depart = DepartType.valueOf(parts[1].toUpperCase());
            // get people level
            PeopleType peopleType = PeopleType.valueOf(parts[2].toUpperCase());

            // create new employee
            return new Employee(name, depart, peopleType);
        } else {
            return null;
        }
    }
    // read attendees from attendance file with date
    public DoublyLinkedList<People> readAttendees(String filePath){
        DoublyLinkedList<People> attendees = new DoublyLinkedList<>();
        // Use try-with-resources for automatic resource management
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                People employee = attendeeStringParts(line); // Convert line to a People object
                attendees.addLast(employee); // Add the object to the list
            }
        } catch (IOException e) {
            e.printStackTrace(); // Consider more specific error handling
        }
        return attendees;
    }
    // helper method to split attendee string to part
    private Employee attendeeStringParts(String line){
                // split the content from file
        String[] parts = line.split("\\&");
        // check if out of bounds
        if (parts.length >= 4) {
            // get name
            String name = parts[0];
            // get department type
            DepartType depart = DepartType.valueOf(parts[1].toUpperCase());
            // get people level
            PeopleType peopleType = PeopleType.valueOf(parts[2].toUpperCase());
            // split date string to match date constructor
            String[] dateParts = parts[3].split("\\/");
            int year = Integer.parseInt(dateParts[0]);
            int month = Integer.parseInt(dateParts[1]);
            int day = Integer.parseInt(dateParts[2]);
            Date date = new Date(month, day, year);
            // create new employee
            return new Employee(name, depart, date, peopleType);
        } else {
            return null;
        }
    }

    // write all current attendees to the file
    public <T extends People> void writeAttendeesFile(String filePath, DoublyLinkedList<People> attendees) {
        // Initialize BufferedWriter outside the try block to handle its scope correctly
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filePath));
            // get first node
            DoublyLinkedList.Node<People> current = attendees.llHead.next;
            // iterate whole linked list
            while (current != attendees.llTail) {
                if (current.people != null) {
                    // build the string from current node
                    String str = current.people.getName() + "$" +
                                 current.people.getDepartment() + "$" +
                                 current.people.getPeopleType() + "$" +
                                 current.people.getDate() + "\n";  // added newline for formatting
                    // Write the string to file
                    out.write(str);
                    
                }
                current = current.next;
            }
            out.close();
        } catch (IOException e) {
            System.err.println("Can't writing to attendees file: " + e.getMessage());
            }
        }
}