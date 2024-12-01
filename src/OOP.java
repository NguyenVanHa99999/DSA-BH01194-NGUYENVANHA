class Vehicle {
    protected float speed;

    public float getSpeed() {
        return speed;
    }

    public void fuel() {
        System.out.println("Đổ xăng");
    }
}

class Car extends Vehicle {
    // Thuộc tính
    private int year;
    private String brand;
    String color;

    // Phương thức
    // Constructor - Hàm tạo
    public Car(String color, String brand, int year) {
        this.color = color;
        this.brand = brand;
        this.year = year;
    }

    public void start() {
        System.out.println("The car is starting");
    }

    public void stop() {
        System.out.println("The car is stoping");
    }

    public void brake() {
        System.out.println("The car is braking");
    }

    public void fuel() {
    }
}

public class OOP {
    public static void main(String[] agrs) {
        Car car2 = new Car("White", "Volvo", 2020);
        // car2.year = 2024;
        // car2.color = "Red";
        car2.speed = 110;
        car2.fuel();
    }
}
