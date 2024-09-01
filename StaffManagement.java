import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Abstract class Staff
abstract class Staff {
    abstract void accept();
    abstract void display();
}

// Employee class
class Employee extends Staff {
    private String id;
    private String name;
    private String department;
    private double salary;

    public Employee(String id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public void accept() {
        // You can implement logic to accept employee details if needed
    }

    @Override
    public void display() {
        System.out.println("Employee ID: " + id + ", Name: " + name + ", Department: " + department + ", Salary: " + salary);
    }
}

// Manager class
class Manager extends Employee {
    private double bonus;
    private double totalSalary;

    public Manager(String id, String name, String department, double salary, double bonus) {
        super(id, name, department, salary);
        this.bonus = bonus;
        this.totalSalary = salary + bonus;
    }

    public double getTotalSalary() {
        return totalSalary;
    }

    @Override
    public void accept() {
        // You can implement logic to accept manager details if needed
    }

    @Override
    public void display() {
        System.out.println("Manager ID: " + getId() + ", Name: " + getName() + ", Department: " + getDepartment() + ", Salary: " + getSalary() + ", Bonus: " + bonus + ", Total Salary: " + totalSalary);
    }
}

public class StaffManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = 0;

        // Input validation for the number of managers
        while (true) {
            System.out.print("Enter the number of managers: ");
            if (scanner.hasNextInt()) {
                N = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                break; // Exit the loop if input is valid
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Clear the invalid input
            }
        }

        List<Manager> managers = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            System.out.print("Enter Manager ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter Manager Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Manager Department: ");
            String department = scanner.nextLine();
            double salary = 0;
            double bonus = 0;

            // Input validation for salary
            while (true) {
                System.out.print("Enter Manager Salary: ");
                if (scanner.hasNextDouble()) {
                    salary = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    break; // Exit the loop if input is valid
                } else {
                    System.out.println("Invalid input. Please enter a valid salary.");
                    scanner.next(); // Clear the invalid input
                }
            }

            // Input validation for bonus
            while (true) {
                System.out.print("Enter Manager Bonus: ");
                if (scanner.hasNextDouble()) {
                    bonus = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline character
                    break; // Exit the loop if input is valid
                } else {
                    System.out.println("Invalid input. Please enter a valid bonus.");
                    scanner.next(); // Clear the invalid input
                }
            }

            Manager manager = new Manager(id, name, department, salary, bonus);
            managers.add(manager);
        }

        // Display the manager with the maximum total salary
        displayMaxSalaryManager(managers);
    }

    private static void displayMaxSalaryManager(List<Manager> managers) {
        if (managers.isEmpty()) {
            System.out.println("No managers available.");
            return;
        }

        Manager maxManager = managers.stream()
                .max(Comparator.comparingDouble(Manager::getTotalSalary))
                .orElse(null);

        if (maxManager != null) {
            maxManager.display();
        }
    }
}
