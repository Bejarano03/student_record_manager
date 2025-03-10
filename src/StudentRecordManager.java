import java.util.TreeMap;

public class StudentRecordManager {
    TreeMap<Integer, Student> students = new TreeMap<>();

    public void addStudent(int id, String name, double gpa) {
        if (!students.containsKey(id)) {
            students.put(id, new Student(name, gpa));
        } else {
            System.out.println("Student with ID " + id + " already exists.");
        }
    }

    public void removeStudent(int id) {
        if(students.containsKey(id)){
            students.remove(id);
            System.out.println("Student with ID "+id+" was removed");
        } else {
            System.out.println("Student with ID "+id+" does not exist");
        }

    }

    public void updateStudentGPA(int id, double gpa) {
        if (students.containsKey(id)) {
            students.get(id).setGpa(gpa);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public void displayAllStudents() {
        System.out.println("All Student Records:");
        for (Integer id : students.keySet()) {
            System.out.println("ID: " + id + ", " + students.get(id));
        }
    }

    public void displayStudentsAboveGPA(double threshold) {
        System.out.println("Students with GPA above " + threshold + ":");
        for (Integer id : students.keySet()) {
            Student student = students.get(id);
            if (student.getGpa() > threshold) {
                System.out.println("ID: " + id + ", " + student);
            }
        }
    }
}