public interface HeadTool<T> {
    
    void changeOwnDate(Date date);
    void changeEmployeeDate(People employee, Date date);
    void removeEmployee(People employee);
    void addEmployee(T employee);
    void setEmployeeName(People employee, String name);
    void setDepartment(People employee, DepartType targetDepart);
    void makeEmployeeAttendance(People employee);
}
