import java.util.ArrayList;
import java.util.Scanner;

public class Authentication {
    public static void signup(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts) {
        int i = 0;
        while (true) {
            System.out.println("1. Signup as a client\n2. Signup as an employee");
            boolean pass = false;
            while (!pass) {
                try {
                    i = Integer.parseInt(scanner.nextLine());
                    pass = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Only integers are allowed.");
                }
            }
            if (i == 1) {
                System.out.println("First name:");
                String firstName = scanner.nextLine();
                System.out.println("Last name:");
                String lastName = scanner.nextLine();
                System.out.println("Username");
                String username = scanner.nextLine();
                System.out.println("Password:");
                String password = scanner.nextLine();
                System.out.println("Re-enter password:");
                String passwordConfirm = scanner.nextLine();
                String phoneNumber = "";
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Phone number:");
                        phoneNumber = scanner.nextLine();
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                double balance = 0.0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Balance:");
                        balance = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only decimals are allowed.");
                    }
                }
                // Validations
                if (username.isEmpty() || password.isEmpty()) {
                    System.out.println("Can't have an empty username or password!");
                    return;
                }
                if (username.length() > 30) {
                    System.out.println("Username is too long");
                    return;
                }
                boolean containsAlpha = false;
                for (int j = 0; j < username.length(); j++) {
                    if ((username.charAt(j) >= 'a' && username.charAt(j) <= 'z') || (username.charAt(j) >= 'A' && username.charAt(j) <= 'Z')) {
                        containsAlpha = true;
                        break;
                    }
                }
                if (!containsAlpha) {
                    System.out.println("Username must contain at least one alphabetical character");
                    return;
                }
                if (username.equalsIgnoreCase("admin")) {
                    System.out.println("Username can't be \"admin\"");
                    return;
                }
                for (Client client : clients) {
                    if (client.getUsername().equals(username)) {
                        System.out.println("Username already exists");
                        return;
                    }
                }
                for (Employee employee : employees) {
                    if (employee.getUsername().equals(username)) {
                        System.out.println("Username already exists");
                        return;
                    }
                }
                if (password.length() > 30) {
                    System.out.println("Password is too long");
                    return;
                }
                if (!password.equals(passwordConfirm)) {
                    System.out.println("Password doesn't match. Please try again.");
                    return;
                }
                if (balance < 0.0) {
                    System.out.println("Can't have negative balance");
                    return;
                }

                Client client = new Client(firstName, lastName, username, password, phoneNumber, balance);
                clients.add(client);
                Account account = new Account(client.getId(), AccountState.ACTIVE, AccountType.CURRENT, balance);
                accounts.add(account);
                System.out.println("Your id is: " + client.getId());
                return;
            } else if (i == 2) {
                System.out.println("First name:");
                String firstName = scanner.nextLine();
                System.out.println("Last name:");
                String lastName = scanner.nextLine();
                System.out.println("Username");
                String username = scanner.nextLine();
                System.out.println("Password:");
                String password = scanner.nextLine();
                System.out.println("Re-enter password:");
                String passwordConfirm = scanner.nextLine();
                String phoneNumber = "";
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Phone number:");
                        phoneNumber = scanner.nextLine();
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                System.out.println("Address:");
                String address = scanner.nextLine();
                System.out.println("Position:");
                String position = scanner.nextLine();
                System.out.println("Graduated college:");
                String graduatedCollege = scanner.nextLine();
                pass = false;
                int yearOfGraduation = 0;
                while (!pass) {
                    try {
                        System.out.println("Year of graduation:");
                        yearOfGraduation = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                System.out.println("College grade: ");
                String collegeGrade = scanner.nextLine();
                // Validations
                if (username.isEmpty() || password.isEmpty()) {
                    System.out.println("Can't have an empty username or password");
                    return;
                }
                if (username.length() > 30) {
                    System.out.println("Username is too long");
                    return;
                }
                boolean containsAlpha = false;
                for (int j = 0; j < username.length(); j++) {
                    if ((username.charAt(j) >= 'a' && username.charAt(j) <= 'z') || (username.charAt(j) >= 'A' && username.charAt(j) <= 'Z')) {
                        containsAlpha = true;
                        break;
                    }
                }
                if (!containsAlpha) {
                    System.out.println("Username must contain at least one alphabetical character");
                    return;
                }
                for (Client client : clients) {
                    if (client.getUsername().equals(username)) {
                        System.out.println("Username already exists");
                        return;
                    }
                }
                for (Employee employee : employees) {
                    if (employee.getUsername().equals(username)) {
                        System.out.println("Username already exists");
                        return;
                    }
                }
                if (password.length() > 30) {
                    System.out.println("Password is too long");
                    return;
                }
                if (!password.equals(passwordConfirm)) {
                    System.out.println("Password doesn't match. Please try again.");
                    return;
                }
                Employee employee = new Employee(firstName, lastName, username, password, phoneNumber, address, position, graduatedCollege, yearOfGraduation, collegeGrade);
                employees.add(employee);
                System.out.println("Your id is: " + employee.getId());
                return;
            }
        }
    }

    public static User login(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> employees) {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Admin logged in successfully!");
            return new Admin("Admin", "", "admin", "admin", "");
        }

        for (Employee employee : employees) {
            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
                System.out.println("Employee logged in successfully!\n");
                return employee;
            }
        }

        for (Client client : clients) {
            if (client.getUsername().equals(username) && client.getPassword().equals(password)) {
                System.out.println("Client logged in successfully!");
                return client;
            }
        }

        System.out.println("Invalid username or password. Please try again.");
        return null;
    }
}
