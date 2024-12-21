import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String firstName, String lastName, String username, String password, String phoneNumber) {
        super(firstName, lastName, username, password, phoneNumber);
    }

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void AuthorizeEmployee(Scanner scanner, ArrayList<Employee> employees) {
        int id = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Enter employee Id: ");
                id = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.authorize();
                System.out.println("Employee " + id + " was authorized successfully!");
                return;
            }
        }
        System.out.println("Couldn't find employee with Id: " + id);
    }

    public static void AuthorizeAllEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            employee.authorize();
        }
        System.out.println("All employees were authorized successfully!");
    }

    public static void displayClient(Scanner scanner, ArrayList<Client> clients) {
        int id = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Enter client Id: ");
                id = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. only integers are allowed.");
            }
        }
        boolean exists = false;
        for (Client client : clients) {
            if (client.getId() == id) {
                System.out.println(client.details());
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("No client found with id: " + id);
        }
    }

    public static void displayAllClients(ArrayList<Client> clients) {
        for (Client client : clients) {
            System.out.println(client.details());
        }
    }

    public static void displayEmployee(Scanner scanner, ArrayList<Employee> employees) {
        int id = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Enter employee Id: ");
                id = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }
        boolean exists = false;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                System.out.println(employee.details());
                exists = true;
                break;
            }
        }
        if (!exists) {
            System.out.println("No employee found with id: " + id);
        }
    }

    public static void displayAllEmployees(ArrayList<Employee> employees) {
        for (Employee employee : employees) {
            System.out.println(employee.details());
        }
    }

    public static void displayTransactions(Scanner scanner, ArrayList<Transaction> transactions) {
        System.out.println("1. All transactions\n2. Transactions on a specific date\n3. Transactions by a specific client\n4. Back");
        int i = 0;
        boolean pass = false;
        while (!pass) {
            try {
                i = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
                return;
            }
        }
        if (i == 1) {
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    System.out.println(transaction);
                }
            } else System.out.println("No transactions found!");
        } else if (i == 2) {
            System.out.println("Enter date (yyyy-MM-dd):");
            Date date;
            try {
                date = sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            boolean exists = false;
            if (!transactions.isEmpty()) {
                for (Transaction transaction : transactions) {
                    if (transaction.getDate().equals(date)) {
                        System.out.println(transaction);
                        exists = true;
                    }
                }
            }
            if (!exists) {
                System.out.println("No transactions found on date: " + date);
            }
        } else if (i == 3) {
            int clientId = 0;
            pass = false;
            while (!pass) {
                try {
                    System.out.println("Enter client Id:");
                    clientId = Integer.parseInt(scanner.nextLine());
                    pass = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. Only integers are allowed.");
                }
            }
            boolean exists = false;
            for (Transaction transaction : transactions) {
                if (transaction.getClientId() == clientId) {
                    System.out.println(transaction);
                    exists = true;
                }
            }
            if (!exists) {
                System.out.println("No transactions found!");
            }
        } else if (i == 4) {
            return;
        } else System.out.println("Invalid input. Please try again!");
    }

    @Override
    public void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards) {
        int i = 0;
        while (true) {
            System.out.println("1. Authorize a new employee\n2. Authorize all new employees\n3. Display an employee\n4. Display all employees\n5. Display a client\n6. Display all clients\n7. Display transactions\n8. Logout");
            boolean pass = false;
            while (!pass) {
                try {
                    i = Integer.parseInt(scanner.nextLine());
                    pass = true;
                } catch (NumberFormatException nfe) {
                    System.out.println("Invalid input. only integers are allowed.");
                }
            }
            if (i == 1) {
                AuthorizeEmployee(scanner, employees);
            } else if (i == 2) {
                AuthorizeAllEmployees(employees);
            } else if (i == 3) {
                displayEmployee(scanner, employees);
            } else if (i == 4) {
                displayAllEmployees(employees);
            } else if (i == 5) {
                displayClient(scanner, clients);
            } else if (i == 6) {
                displayAllClients(clients);
            } else if (i == 7) {
                displayTransactions(scanner, transactions);
            } else if (i == 8) {
                return;
            } else
                System.out.println("Invalid input. Try again!");
        }
    }
}
