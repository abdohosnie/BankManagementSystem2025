import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<CreditCard> cards = new ArrayList<>();
        Database database = new Database();
        database.load(clients, employees, accounts, transactions, cards);
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        while (true) {
            while (true) {
                System.out.println("\n1. Login\n2. Signup\n-1. Exit");
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
                    User user = Authentication.login(scanner, clients, employees);
                    if (user != null) {
                        if (user instanceof Admin) {
                            Admin admin;
                            admin = (Admin) user;
                            admin.menu(scanner, employees, clients, accounts, transactions, cards);
                        } else if (user instanceof Client) {
                            Client client;
                            client = (Client) user;
                            client.menu(scanner, employees, clients, accounts, transactions, cards);
                        } else if (user instanceof Employee) {
                            Employee employee;
                            employee = (Employee) user;
                            employee.menu(scanner, employees, clients, accounts, transactions, cards);
                        }
                    }
                } else if (i == 2) {
                    Authentication.signup(scanner, clients, employees, accounts);
                }
                if (i == -1) {
                    break;
                }
            }
            while (true) {
                System.out.println("Save changes to the database (y/n)?");
                String c;
                c = scanner.nextLine();
                if (c.equalsIgnoreCase("y")) {
                    database.save(clients, employees, accounts, transactions, cards);
                    System.out.println("Changes where saved successfully.");
                    return;
                } else if (c.equalsIgnoreCase("n")) {
                    System.out.println("Changes were not saved.");
                    return;
                } else {
                    System.out.println("Type y for yes or n for no.");
                }
            }
        }
    }
}