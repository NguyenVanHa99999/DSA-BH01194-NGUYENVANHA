package Assignment;

public class Student {
    // Attributes
    private String name;
    private String id;
    private double mark;
    private String rank;
    private Student[] students;
    
        // Constructor
        public Student(String id, String name, double mark) {
            this.name = name;
            this.id = id;
            this.mark = mark;
            setRank();  // Set the rank
        }
        
        // Constructor to accept data array (used when reading from a CSV file)
        public Student(String[] data) {
            this.id = data[0]; 
            this.name = data[1];
            this.mark = Double.valueOf(data[2]);
            setRank();  // Set the rank
        }
    
        // Method to convert marks to rank
        public String convertToRank() {
            if (mark >= 0 && mark < 5) {
                return "Fail";
            } else if (mark < 6.5) {
                return "Medium";
            } else if (mark < 7.5) {
                return "Good";
            } else if (mark < 9) {
                return "Very Good";
            } else if (mark <= 10) {
                return "Excellent";
            } else {
                System.out.println("Invalid");
                return null;
            }
        }
    
        // Method to set the rank
        public void setRank() {
            this.rank = convertToRank();
        }
    
        // Method to print student information
        public void print() {
            System.out.println("Student Name: " + name);
            System.out.println("Student ID: " + id);
            System.out.println("Marks: " + mark);
            System.out.println("Rank: " + rank);
        }
    
        // Getter and Setter
        public String getName() {
            return name;
        }
    
        public void setName(String name) {
            this.name = name;
        }
    
        public String getId() {
            return id;
        }
    
        public void setId(String id) {
            this.id = id;
        }
    
        public double getMark() {
            return mark;  // Added getter for marks
        }
    
        public void setMark(double mark) {
            if (mark < 0 || mark > 10) {
                throw new IllegalArgumentException("Invalid marks! Please enter a value between 0 and 10.");
            }
            this.mark = mark;
        }
        
        public boolean isDuplicate(String id, String name) {
            for (Student s : students) {
            if (s.getId().equals(id) || s.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public String getRank() {
        return rank;
    }
}


