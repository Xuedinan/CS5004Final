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

public class FileTool {
	
	public FileTool() {};
	
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
        
    // take Task and follow data format to write on file
    public static  void writeFile(String filePath, People people) {
		try {
			// create a reader object
			BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
			// split string into file data format
			String str = people.getName() + "$" + people.getDepartment() + "$" + people.getDate();
			// write into file then close
			out.write(str);
			out.close();
			}
		catch(Exception e) {
			// handle exception
			e.printStackTrace();
			}
		}
}