import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class StudentRecordManagerTest {

    private StudentRecordManager manager;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        manager = new StudentRecordManager();
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
    }

    @org.junit.jupiter.api.AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void addStudent_shouldAddStudent() {
        manager.addStudent(101, "Alice", 3.8);
        assertEquals(1, manager.students.size());
        assertTrue(manager.students.containsKey(101));
        Student student = manager.students.get(101);
        assertEquals("Alice", student.getName());
        assertEquals(3.8, student.getGpa());
    }

    @Test
    void addStudent_duplicateId_shouldNotAdd() {
        manager.addStudent(101, "Alice", 3.8);
        manager.addStudent(101, "Bob", 3.5);
        assertEquals(1, manager.students.size());
        String expectedOutput = "Student with ID 101 already exists.\n";
        assertEquals(expectedOutput, outContent.toString());

        Student student = manager.students.get(101);
        assertEquals("Alice", student.getName());
        assertEquals(3.8, student.getGpa());

    }

    @Test
    void removeStudent_shouldRemoveStudent() {
        manager.addStudent(101, "Alice", 3.8);
        manager.removeStudent(101);
        assertEquals(0, manager.students.size());
        String expectedOutput = "Student with ID 101 was removed\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void removeStudent_nonExistentId_shouldNotChangeList() {
        manager.addStudent(101, "Alice", 3.8);
        manager.removeStudent(102);
        assertEquals(1, manager.students.size());
        String expectedOutput = "Student with ID 102 does not exist\n";
        assertEquals(expectedOutput, outContent.toString());
        assertTrue(manager.students.containsKey(101));

    }

    @Test
    void updateStudentGPA_shouldUpdateGPA() {
        manager.addStudent(101, "Alice", 3.8);
        manager.updateStudentGPA(101, 3.9);
        assertEquals(3.9, manager.students.get(101).getGpa());
    }

    @Test
    void updateStudentGPA_nonExistentId_shouldNotUpdate() {
        manager.addStudent(101, "Alice", 3.8);
        manager.updateStudentGPA(102, 3.9);
        assertEquals(3.8, manager.students.get(101).getGpa());
        String expectedOutput = "Student with ID 102 not found.\n";
        assertEquals(expectedOutput, outContent.toString());

    }

    @Test
    void displayAllStudents_emptyList_shouldPrintAllStudentRecordMessage() {
        manager.displayAllStudents();
        String expectedOutput = "All Student Records:\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void displayAllStudents_shouldPrintAllStudents() {
        manager.addStudent(101, "Alice", 3.8);
        manager.addStudent(102, "Bob", 3.5);
        manager.displayAllStudents();
        String expectedOutput = "All Student Records:\nID: 101, Name: Alice, GPA: 3.8\nID: 102, Name: Bob, GPA: 3.5\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void displayStudentsAboveGPA_shouldPrintCorrectStudents() {
        manager.addStudent(101, "Alice", 3.8);
        manager.addStudent(102, "Bob", 3.5);
        manager.addStudent(103, "Charlie", 3.9);
        manager.addStudent(104, "David", 3.4);
        manager.displayStudentsAboveGPA(3.5);

        String expectedOutput = "Students with GPA above 3.5:\nID: 101, Name: Alice, GPA: 3.8\nID: 103, Name: Charlie, GPA: 3.9\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void displayStudentsAboveGPA_noStudentsAboveGPA_shouldPrintOnlyHeader() {
        manager.addStudent(101, "Alice", 3.0);
        manager.addStudent(102, "Bob", 3.1);
        manager.addStudent(103, "Charlie", 3.2);
        manager.displayStudentsAboveGPA(3.5);
        String expectedOutput = "Students with GPA above 3.5:\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void displayStudentsAboveGPA_emptyList_shouldPrintOnlyHeader(){
        manager.displayStudentsAboveGPA(3.5);
        String expectedOutput = "Students with GPA above 3.5:\n";
        assertEquals(expectedOutput, outContent.toString());
    }
}