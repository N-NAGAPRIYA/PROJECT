import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Step 1: Define the Component Interface
interface SchoolMember {
    String getName();
    void printDetails();
}

// Step 2: Create Concrete Leaf
class Student implements SchoolMember {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printDetails() {
        System.out.println("Student: " + name);
    }
}

// Step 3: Create Composite
class Classroom implements SchoolMember {
    private String name;
    private List<SchoolMember> members = new ArrayList<>();

    public Classroom(String name) {
        this.name = name;
    }

    public void add(SchoolMember member) {
        members.add(member);
    }

    public void remove(SchoolMember member) {
        members.remove(member);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void printDetails() {
        System.out.println("Classroom: " + name);
        for (SchoolMember member : members) {
            member.printDetails();
        }
    }
}

// Example Usage
public class Composite {
    private static Scanner scanner = new Scanner(System.in);

    // Function to add members to a classroom
    private static void addMembersToClassroom(Classroom classroom) {
        // Using a for loop with break condition instead of while(true)
        for (String type; !(type = getInput("Do you want to add a student or a classroom? (student/classroom/none): ")).equalsIgnoreCase("none");) {
            if (type.equalsIgnoreCase("student")) {
                String studentName = getInput("Enter the name of the student: ");
                Student student = new Student(studentName);
                classroom.add(student);
            } else if (type.equalsIgnoreCase("classroom")) {
                String classroomName = getInput("Enter the name of the new classroom: ");
                Classroom newClassroom = new Classroom(classroomName);
                classroom.add(newClassroom);

                // Add members to the newly created classroom
                addMembersToClassroom(newClassroom);
            }
        }
    }

    private static String getInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static void main(String[] args) {
        // Create the root classroom
        String rootClassName = getInput("Enter the name of the root classroom: ");
        Classroom rootClassroom = new Classroom(rootClassName);

        // Start adding members to the root classroom
        addMembersToClassroom(rootClassroom);

        // Print the structure
        System.out.println("School Structure:");
        rootClassroom.printDetails();

        // Close the scanner
        scanner.close();
    }
}
