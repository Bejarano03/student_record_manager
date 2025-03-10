public class Main {
    public static void main(String[] args) {
        StudentRecordManager manager = new StudentRecordManager();

        manager.addStudent(101, "Alice", 3.8);
        manager.addStudent(102, "Bob", 3.5);
        manager.addStudent(103, "Charlie", 3.9);
        manager.addStudent(104, "David", 3.7);
        manager.addStudent(105, "Elijah", 3.6);
        manager.addStudent(106, "Fred", 3.4);

        manager.displayAllStudents();

        manager.updateStudentGPA(102, 3.6);
        System.out.println("\nAfter updating Bob's GPA:");
        manager.displayAllStudents();

        manager.removeStudent(104);
        System.out.println("\nAfter removing David:");
        manager.displayAllStudents();

        System.out.println();
        manager.displayStudentsAboveGPA(3.5);

    }
}