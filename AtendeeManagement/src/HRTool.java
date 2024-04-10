import java.util.ArrayList;
import java.util.function.Predicate;

public interface HRTool<T> {
    
    void addHead();
    void removeHead();
    void createHeadList();
    void checkAllAttendees();
    Predicate<T> filterLevel();
    int totalCompanyEmployees();
    double calculateDepartNum();
    DoublyLinkedList<T> filterByDepartment();
}
