// 2.	Thay thế bằng cấu trúc dữ liệu nâng cao (BST – binary search tree) và thuật toán quick sort hoặc merge sort
import Assignment.Student;
import BST.BST;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        String csvFile = "student.csv"; // Path to the CSV file
        BST bst = new BST(); // Binary Search Tree
        bst.buildBSTFromCSV(csvFile); // Read data from the CSV file and build the BST

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Student Management ===");
            System.out.println("1. Add a student");
            System.out.println("2. Edit a student");
            System.out.println("3. Delete a student");
            System.out.println("4. Sort students by marks");
            System.out.println("5. Search for students by name");
            System.out.println("6. Display the student list");
            System.out.println("7. Exit");
            System.out.print("Select a function: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addStudent(bst, scanner, csvFile);
                case 2 -> editStudent(bst, scanner, csvFile);
                case 3 -> deleteStudent(bst, scanner, csvFile);
                case 4 -> sortStudents(bst);
                case 5 -> searchStudent(bst, scanner);
                case 6 -> displayStudents(bst);
                case 7 -> {
                    System.out.println("Exiting the program.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    public static void addStudent(BST bst, Scanner scanner, String csvFile) {
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
    
   
        if (bst.isStudentExists(id, name)) {
            System.out.println("Student with this ID or name already exists. Cannot add duplicate.");
            return;
        }
    
        System.out.print("Enter student marks: ");
        double mark = scanner.nextDouble();
        scanner.nextLine();
    
        bst.addStudent(new Student(id, name, mark));
        System.out.println("Student added successfully.");
    
   
        try {
            bst.saveToCSV(csvFile);
        } catch (Exception e) {
            System.out.println("Error saving to CSV: " + e.getMessage());
        }
    }

    public static void editStudent(BST bst, Scanner scanner, String csvFile) {
        System.out.print("Enter the student ID to edit: ");
        String id = scanner.nextLine();
        Student student = bst.findStudentById(id);

        if (student != null) {
            System.out.print("Enter the new name: ");
            student.setName(scanner.nextLine());
            System.out.print("Enter the new marks: ");
            student.setMark(scanner.nextDouble());
            scanner.nextLine();
            System.out.println("Student edited successfully.");
            
            // Ghi dữ liệu lại vào file CSV
            try {
                bst.saveToCSV(csvFile);
            } catch (Exception e) {
                System.out.println("Error saving to CSV: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }

    public static void deleteStudent(BST bst, Scanner scanner, String csvFile) {
        // Kiểm tra nếu danh sách rỗng
        if (bst.getRoot() == null) { // Nếu root của cây BST null, cây trống
            System.out.println("The student list is empty. Cannot delete.");
            return;
        }
    
        System.out.print("Enter the student ID to delete: ");
        String id = scanner.nextLine();
    
        // Thực hiện xóa sinh viên
        if (bst.removeStudent(id)) {
            System.out.println("Student deleted successfully.");
            
            // Ghi dữ liệu lại vào file CSV
            try {
                bst.saveToCSV(csvFile);
            } catch (Exception e) {
                System.out.println("Error saving to CSV: " + e.getMessage());
            }
        } else {
            System.out.println("Student not found.");
        }
    }
    

    public static void sortStudents(BST bst) {
        List<Student> students = bst.getAllStudents();
        mergeSort(students);
        System.out.println("Student list after sorting:");
        for (Student student : students) {
            student.print();
        }
    }

    public static void searchStudent(BST bst, Scanner scanner) {
        System.out.print("Enter the name of the student to search for: ");
        String name = scanner.nextLine();
    
        List<Student> foundStudents = bst.searchByName(name);
        
        if (foundStudents.isEmpty()) {
            System.out.println("Student not found.");
        } else {
            System.out.println("Search results:");
            for (Student student : foundStudents) {
                student.print();
            }
        }
    }
    

    public static void displayStudents(BST bst) {
        System.out.println("Student list:");
        bst.inOrderDisplay(bst.getRoot()); // Sử dụng phương thức getRoot để lấy nút gốc
    }

    public static void mergeSort(List<Student> students) {
        if (students.size() <= 1) return;

        int mid = students.size() / 2;
        List<Student> left = new ArrayList<>(students.subList(0, mid));
        List<Student> right = new ArrayList<>(students.subList(mid, students.size()));

        mergeSort(left);
        mergeSort(right);

        merge(students, left, right);
    }

    private static void merge(List<Student> students, List<Student> left, List<Student> right) {
        students.clear();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getMark() <= right.get(j).getMark()) {
                students.add(left.get(i++));
            } else {
                students.add(right.get(j++));
            }
        }
        students.addAll(left.subList(i, left.size()));
        students.addAll(right.subList(j, right.size()));
    }
}




// 1.	Sử dụng cấu trúc dữ liệu Array hoặc Linked list và thuật toán bubble sort hoặc selection sort 
// import Assignment.Student;
// import Helper.readCSV;
// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.util.List;
// import java.util.Scanner;

// public class App {
//     public static void main(String[] args) throws Exception {
//         // Read student data from CSV file
//         String csvFile = "student.csv"; // CSV file path
//         List<Student> students = readCSV.csvToArray(csvFile);

//         Scanner scanner = new Scanner(System.in);

//         // Main menu for the user
//         while (true) {
//             System.out.println("=== Student Management ===");
//             System.out.println("1. Add student");
//             System.out.println("2. Edit student");
//             System.out.println("3. Delete student");
//             System.out.println("4. Sort students");
//             System.out.println("5. Search student");
//             System.out.println("6. Display students");
//             System.out.println("7. Exit");
//             System.out.print("Choose an option: ");
//             int choice = scanner.nextInt();
//             scanner.nextLine(); // Consume newline character

//             switch (choice) {
//                 case 1:
//                     addStudent(students, scanner, csvFile);
//                     break;
//                 case 2:
//                     editStudent(students, scanner, csvFile);
//                     break;
//                 case 3:
//                     deleteStudent(students, scanner, csvFile);
//                     break;
//                 case 4:
//                     sortStudents(students);
//                     break;
//                 case 5:
//                     searchStudent(students, scanner);
//                     break;
//                 case 6:
//                     displayStudents(students);
//                     break;
//                 case 7:
//                     System.out.println("Exiting program.");
//                     return;
//                 default:
//                     System.out.println("Invalid choice.");
//             }
//         }
//     }

//     // Add a student
//     public static void addStudent(List<Student> students, Scanner scanner, String csvFile) {
//         System.out.print("Enter student ID: ");
//         String id = scanner.nextLine();
//         // Check if the student ID already exists
//         if (findStudentById(students, id) != null) {
//             System.out.println("Student ID already exists. Please enter a different ID.");
//             return;
//         }

//         System.out.print("Enter student name: ");
//         String name = scanner.nextLine();
//         if (name.trim().isEmpty()) {
//             System.out.println("Name cannot be empty.");
//             return;
//         }

//         System.out.print("Enter student mark: ");
//         double mark = scanner.nextDouble();
//         scanner.nextLine(); // Consume newline character

//         if (mark < 0 || mark > 10) {
//             System.out.println("Mark must be between 0 and 10.");
//             return;
//         }

//         students.add(new Student(id, name, mark));
//         System.out.println("Student added successfully.");

//         // Save the list of students to CSV
//         try {
//             saveStudentsToCSV(students, csvFile);
//         } catch (Exception e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//     }

//     // Edit a student
//     public static void editStudent(List<Student> students, Scanner scanner, String csvFile) {
//         System.out.print("Enter the student ID to edit: ");
//         String id = scanner.nextLine();

//         Student student = findStudentById(students, id);
//         if (student != null) {
//             System.out.print("Enter new name: ");
//             String newName = scanner.nextLine();
//             if (newName.trim().isEmpty()) {
//                 System.out.println("Name cannot be empty.");
//                 return;
//             }
//             student.setName(newName);

//             System.out.print("Enter new mark: ");
//             double newMark = scanner.nextDouble();
//             scanner.nextLine(); // Consume newline character

//             if (newMark < 0 || newMark > 10) {
//                 System.out.println("Mark must be between 0 and 10.");
//                 return;
//             }
//             student.setMark(newMark);

//             System.out.println("Student updated successfully.");

//             // Save the list of students to CSV
//             try {
//                 saveStudentsToCSV(students, csvFile);
//             } catch (Exception e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             System.out.println("Student not found.");
//         }
//     }

//     // Delete a student
//     public static void deleteStudent(List<Student> students, Scanner scanner, String csvFile) {
//         System.out.print("Enter the student ID to delete: ");
//         String id = scanner.nextLine();

//         Student student = findStudentById(students, id);
//         if (student != null) {
//             students.remove(student);
//             System.out.println("Student deleted successfully.");

//             // Save the list of students to CSV
//             try {
//                 saveStudentsToCSV(students, csvFile);
//             } catch (Exception e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//         } else {
//             System.out.println("Student not found.");
//         }
//     }

//     // Sort students by mark using Bubble Sort
//     public static void sortStudents(List<Student> students) {
//         int n = students.size();
//         boolean swapped;
//         for (int i = 0; i < n - 1; i++) {
//             swapped = false;
//             for (int j = 0; j < n - i - 1; j++) {
//                 if (students.get(j).getMark() > students.get(j + 1).getMark()) {
//                     // Swap students[j] and students[j+1]
//                     Student temp = students.get(j);
//                     students.set(j, students.get(j + 1));
//                     students.set(j + 1, temp);
//                     swapped = true;
//                 }
//             }
//             // If no elements were swapped in the inner loop, break early
//             if (!swapped)
//                 break;
//         }
//         System.out.println("Students sorted by mark.");
//     }

//     // Search for a student by name
//     public static void searchStudent(List<Student> students, Scanner scanner) {
//         System.out.print("Enter student name to search: ");
//         String name = scanner.nextLine();

//         boolean found = false;
//         for (Student student : students) {
//             if (student.getName().toLowerCase().contains(name.toLowerCase())) {
//                 student.print();
//                 found = true;
//             }
//         }
//         if (!found) {
//             System.out.println("Student not found.");
//         }
//     }

//     // Display all students
//     public static void displayStudents(List<Student> students) {
//         if (students.isEmpty()) {
//             System.out.println("No students available.");
//         } else {
//             for (Student student : students) {
//                 student.print();
//             }
//         }
//     }

//     // Find student by ID
//     public static Student findStudentById(List<Student> students, String id) {
//         for (Student student : students) {
//             if (student.getId().equals(id)) {
//                 return student;
//             }
//         }
//         return null;
//     }

//     // Save the list of students to the CSV file
//     public static void saveStudentsToCSV(List<Student> students, String csvFile) throws Exception {
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
//             for (Student student : students) {
//                 writer.write(student.getId() + "," + student.getName() + "," + student.getMark());
//                 writer.newLine();
//             }
//         }
//         System.out.println("Student data saved to file.");
//     }
// }




//