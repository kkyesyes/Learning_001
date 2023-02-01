import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        String name = myScanner.next();
        int age = myScanner.nextInt();
        int salary = myScanner.nextInt();

        System.out.println("Your name is:" + name);
        System.out.println("Your age is:" + age);
        System.out.println("Your salary is:" + salary);
    }
}