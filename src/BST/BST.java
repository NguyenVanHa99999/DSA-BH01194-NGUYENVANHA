package BST;

import Assignment.Student;
import Helper.readCSV;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BST {
    Node root;

    public void buildBSTFromCSV(String csvFile) throws Exception {
        List<Student> students = readCSV.csvToArray(csvFile);
        for (Student student : students) {
            addStudent(student);
        }
    }

    public void addStudent(Student student) {
        root = addStudentRecursive(root, student);
    }

    private Node addStudentRecursive(Node node, Student student) {
        if (node == null) {
            return new Node(student);
        }

        if (student.getId().compareTo(node.student.getId()) < 0) {
            node.left = addStudentRecursive(node.left, student);
        } else if (student.getId().compareTo(node.student.getId()) > 0) {
            node.right = addStudentRecursive(node.right, student);
        }

        return node;
    }

    public boolean isStudentExists(String id, String name) {
        return isStudentExists(root, id, name);
    }

    private boolean isStudentExists(Node node, String id, String name) {
        if (node == null) return false;
        // Kiểm tra trùng ID hoặc trùng tên
        if (node.student.getId().equals(id) || node.student.getName().equalsIgnoreCase(name)) {
            return true;
        }
        // Kiểm tra ở cây con trái và phải
        return isStudentExists(node.left, id, name) || isStudentExists(node.right, id, name);
    }

    public Student findStudentById(String id) {
        return findStudentByIdRecursive(root, id);
    }

    private Student findStudentByIdRecursive(Node node, String id) {
        if (node == null) return null;

        if (id.equals(node.student.getId())) {
            return node.student;
        }

        if (id.compareTo(node.student.getId()) < 0) {
            return findStudentByIdRecursive(node.left, id);
        } else {
            return findStudentByIdRecursive(node.right, id);
        }
    }

    public boolean editStudent(String id, String newName, Double newMarks) {
        Node node = findNodeById(root, id); 

        if (node == null) {
            System.out.println("Student not found.");
            return false;
        }
        if (newName != null && !newName.trim().isEmpty()) {
            node.student.setName(newName);
        }
        if (newMarks != null) {
            if (newMarks >= 0 && newMarks <= 10) {
                node.student.setMark(newMarks);
            } else {
                System.out.println("Marks must be between 0 and 10. Update canceled for marks.");
            }
        }
        System.out.println("Student updated successfully.");
        return true;
    }

    private Node findNodeById(Node node, String id) {
        if (node == null) return null;

        if (id.equals(node.student.getId())) {
            return node;
        }

        if (id.compareTo(node.student.getId()) < 0) {
            return findNodeById(node.left, id);
        } else {
            return findNodeById(node.right, id);
        }
    }

    public boolean removeStudent(String id) {
        if (findStudentById(id) == null) return false;
        root = removeStudentRecursive(root, id);
        return true;
    }

    private Node removeStudentRecursive(Node node, String id) {
        if (node == null) return null;

        if (id.compareTo(node.student.getId()) < 0) {
            node.left = removeStudentRecursive(node.left, id);
        } else if (id.compareTo(node.student.getId()) > 0) {
            node.right = removeStudentRecursive(node.right, id);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node minNode = findMin(node.right);
            node.student = minNode.student;
            node.right = removeStudentRecursive(node.right, minNode.student.getId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void inOrderDisplay(Node node) {
        if (node != null) {
            inOrderDisplay(node.left);
            node.student.print();
            inOrderDisplay(node.right);
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        inOrderCollect(root, students);
        return students;
    }

    private void inOrderCollect(Node node, List<Student> students) {
        if (node != null) {
            inOrderCollect(node.left, students);
            students.add(node.student);
            inOrderCollect(node.right, students);
        }
    }

    public List<Student> searchByName(String name) {
        List<Student> foundStudents = new ArrayList<>();
        searchByNameRecursive(root, name.toLowerCase(), foundStudents);
        return foundStudents;
    }
    
    private void searchByNameRecursive(Node node, String name, List<Student> foundStudents) {
        if (node != null) {
            if (node.student.getName().toLowerCase().contains(name)) {
                foundStudents.add(node.student);
            }
            searchByNameRecursive(node.left, name, foundStudents);
            searchByNameRecursive(node.right, name, foundStudents);
        }
    }
    

    // Ghi dữ liệu từ BST ra file CSV
    public void saveToCSV(String csvFile) throws IOException {
        List<Student> students = getAllStudents(); // Lấy danh sách sinh viên từ cây BST
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile))) {
            writer.write("ID,Name,Mark\n"); // Ghi header CSV
            for (Student student : students) {
                writer.write(student.getId() + "," + student.getName() + "," + student.getMark() + "\n");
            }
        }
    }

    static class Node {
        Student student;
        Node left, right;

        Node(Student student) {
            this.student = student;
        }
    }

    public Node getRoot() {
        return root;
    }
}


// Thay thế Array/List làm cấu trúc dữ liệu quản lý sinh viên:
// BST được sử dụng thay vì Array/List để lưu trữ và quản lý danh sách sinh viên.
// Hỗ trợ thao tác dữ liệu hiệu quả:
//Tìm kiếm nhanh chóng: Nhờ cấu trúc của BST, việc tìm kiếm sinh viên theo ID hoặc tên có độ phức tạp trung bình là O(logn).
// Xóa sinh viên: Được thực hiện dễ dàng thông qua các thao tác trên nút cây.
// Sắp xếp: Dữ liệu trong BST luôn có thể trích xuất theo thứ tự tăng dần thông qua duyệt cây (In-Order Traversal).
// Chuẩn bị dữ liệu cho Merge Sort:
// BST cho phép lấy toàn bộ danh sách sinh viên theo thứ tự ID hoặc điểm (In-Order Traversal) để áp dụng Merge Sort.