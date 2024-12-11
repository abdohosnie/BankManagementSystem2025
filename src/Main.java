import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Client> clients = new ArrayList<>();
        ArrayList<Employee> employees = new ArrayList<>();
        ArrayList<Account> accounts = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<CreditCard> cards = new ArrayList<>();
        Admin admin = new Admin("Admin", "", "admin", "admin", "");
        Database database = new Database();
        database.load(clients, employees, accounts, transactions, cards);
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        while (true) {
            int curId = -1;
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
                    curId = Authentication.login(scanner, clients, employees);
                } else if (i == 2) {
                    Authentication.signup(scanner, clients, employees, accounts);
                }
                if (i == -1 || curId != -1) {
                    break;
                }
            }
            if (curId == 0) {
                admin.menu(scanner, employees, clients, accounts, transactions, cards);
            } else if (curId / 100000 == 2) {
                for (Employee emp : employees) {
                    if (emp.getId() == curId) {
                        emp.menu(scanner, employees, clients, accounts, transactions, cards);
                        break;
                    }
                }
            } else if (curId / 100000 == 1) {
                for (Client client : clients) {
                    if (client.getId() == curId) {
                        client.menu(scanner, employees, clients, accounts, transactions, cards);
                        break;
                    }
                }
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