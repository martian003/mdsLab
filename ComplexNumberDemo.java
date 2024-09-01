import java.util.Scanner;


class Complex {
    private double real;      // Real part
    private double imaginary;  // Imaginary part

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }
    public double getReal() {
        return real;
    }
    public double getImaginary() {
        return imaginary;
    }

    // Method to add two complex numbers
    public Complex add(Complex other) {
        double realPart = this.real + other.real;
        double imaginaryPart = this.imaginary + other.imaginary;
        return new Complex(realPart, imaginaryPart);
    }

    // Method to multiply two complex numbers
    public Complex multiply(Complex other) {
        double realPart = (this.real * other.real) - (this.imaginary * other.imaginary);
        double imaginaryPart = (this.real * other.imaginary) + (this.imaginary * other.real);
        return new Complex(realPart, imaginaryPart);
    }

    // Method to display the complex number
    @Override
    public String toString() {
        if (imaginary < 0) {
            return real + " - " + Math.abs(imaginary) + "i";
        } else {
            return real + " + " + imaginary + "i";
        }
    }
}

// Main class to test the Complex class
public class ComplexNumberDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user for the first complex number
        System.out.print("Enter the real part of the first complex number:");
        double real1 = scanner.nextDouble();
        System.out.print("Enter the imaginary part of the first complex number: ");
        double imaginary1 = scanner.nextDouble();
        Complex complex1 = new Complex(real1, imaginary1);

        // Prompt the user for the second complex number
        System.out.print("Enter the real part of the second complex number: ");
        double real2 = scanner.nextDouble();
        System.out.print("Enter the imaginary part of the second complex number: ");
        double imaginary2 = scanner.nextDouble();
        Complex complex2 = new Complex(real2, imaginary2);

        // Add the two complex numbers
        Complex sum = complex1.add(complex2);

        // Multiply the two complex numbers
        Complex product = complex1.multiply(complex2);

        // Display the results
        System.out.println("First Complex Number: " + complex1);
        System.out.println("Second Complex Number: " + complex2);
        System.out.println("Sum: " + sum);
        System.out.println("Product: " + product);
    }
}
