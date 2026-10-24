
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    int id;
    String name;
    String department;
    int roomNo;
    boolean checkedIn;

    Student(int id, String name, String department, int roomNo) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.roomNo = roomNo;
        this.checkedIn = false;
    }

    void display() {
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Department : " + department);
        System.out.println("Room No    : " + roomNo);
        System.out.println("Stay Status: " +
                (checkedIn ? "Checked In" : "Not Checked In"));
        System.out.println("---------------------------");
    }
}

public class HostelManagement {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static boolean[] rooms = new boolean[11];

    static int findStudent(int id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                return i;
            }
        }
        return -1;
    }

    static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        if (findStudent(id) != -1) {
            System.out.println("Student ID already exists!");
            return;
        }

        int room = -1;
        for (int i = 1; i <= 10; i++) {
            if (!rooms[i]) {
                room = i;
                break;
            }
        }

        if (room == -1) {
            System.out.println("Hostel is full!");
            return;
        }

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Department: ");
        String department = sc.nextLine();

        Student s = new Student(id, name, department, room);
        students.add(s);
        rooms[room] = true;

        System.out.println("Room allocated successfully!");
        System.out.println("Allocated Room Number: " + room);
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
            return;
        }

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student not found!");
        } else {
            students.get(index).display();
        }
    }

    static void checkIn() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student not found!");
        } else if (students.get(index).checkedIn) {
            System.out.println("Student is already checked in.");
        } else {
            students.get(index).checkedIn = true;
            System.out.println("Check-in successful!");
        }
    }

    static void checkOut() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student not found!");
            return;
        }

        Student s = students.get(index);

        if (!s.checkedIn) {
            System.out.println("Student is not checked in.");
            return;
        }

        s.checkedIn = false;
        System.out.println("Check-out successful!");
    }

    static void vacateRoom() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student not found!");
            return;
        }

        Student s = students.get(index);
        rooms[s.roomNo] = false;
        students.remove(index);

        System.out.println("Room vacated successfully!");
    }

    static void roomAvailability() {
        System.out.println("\nRoom Availability");
        for (int i = 1; i <= 10; i++) {
            System.out.println("Room " + i + ": " +
                    (rooms[i] ? "Occupied" : "Available"));
        }
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n===== HOSTEL MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student & Allocate Room");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Student Check-In");
            System.out.println("5. Student Check-Out");
            System.out.println("6. Vacate Room");
            System.out.println("7. Room Availability");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    searchStudent();
                    break;
                case 4:
                    checkIn();
                    break;
                case 5:
                    checkOut();
                    break;
                case 6:
                    vacateRoom();
                    break;
                case 7:
                    roomAvailability();
                    break;
                case 8:
                    System.out.println("Thank you for using the system!");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 8);

        sc.close();
    }
}
