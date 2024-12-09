import java.util.ArrayList;
import java.util.Scanner;

public class Login {

    public static int login(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> employees) {
        System.out.print("Username: ");
        String username = scanner.nextLine().trim(); // Trim to remove extra spaces
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (isAdmin(username, password)) {
            System.out.println("Admin logged in successfully!");
            return 0;
        }

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                System.out.println("Employee logged in successfully!");
                return employee.getId();
            }
        }

        for (Client client : clients) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                System.out.println("Client logged in successfully!");
                return client.getId();
            }
        }

        System.out.println("Invalid username or password. Please try again.");
        return -1;
    }

    private static boolean isAdmin(String username, String password) {
        return username.equalsIgnoreCase("admin") && password.equals("admin");
    }
}
