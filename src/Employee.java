import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {
    private AuthorizationState authorization = AuthorizationState.UNAUTHORIZED;
    private String address;
    private String position;
    private final String graduatedCollege;
    private final int yearOfGraduation;
    private final String collegeGrade;
    public static int nextId = 0;

    public AuthorizationState getAuthorization() {
        return this.authorization;
    }

    public void authorize() {
        this.authorization = AuthorizationState.AUTHORIZED;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGraduatedCollege() {
        return this.graduatedCollege;
    }

    public int getYearOfGraduation() {
        return this.yearOfGraduation;
    }

    public String getCollegeGrade() {
        return this.collegeGrade;
    }

    public String details() {
        String str = super.details();
        str += "Authorization: " + this.getAuthorization() + "\n" +
                "Address: " + this.getAddress() + "\n" +
                "Position: " + this.getPosition() + "\n" +
                "Graduated college: " + this.getGraduatedCollege() + "\n" +
                "Year of graduation: " + this.getYearOfGraduation() + "\n" +
                "College grade: " + this.collegeGrade + "\n###################\n";
        return str;
    }

    public Employee(String firstName, String lastName, String username, String password, String phoneNumber, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(200000 + nextId);
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.collegeGrade = collegeGrade;
        ++nextId;
    }

    public Employee(int id, String firstName, String lastName, String username, String password, String phoneNumber, AuthorizationState authorization, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(id);
        this.authorization = authorization;
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.collegeGrade = collegeGrade;
        if (nextId < this.getId() - 199999) {
            nextId = this.getId() - 199999;
        }
    }

    public void editPersonalInfo(Scanner scanner) {
        while (true) {
            System.out.println("1. Edit address\n2. Edit position\n3. Back");
            int i = 0;
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
                System.out.println("Enter the new address: ");
                String newAddress = scanner.nextLine();
                this.setAddress(newAddress);
            } else if (i == 2) {
                System.out.println("Enter the new position:");
                String newPosition = scanner.nextLine();
                this.setPosition(newPosition);
            } else if (i == 3) {
                return;
            } else System.out.println("Invalid choice. Try again!");
        }
    }

    public void addNewClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts) {
        System.out.println("First name: ");
        String firstName = scanner.nextLine();
        System.out.println("Last name: ");
        String lastName = scanner.nextLine();
        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();
        System.out.println("Re-enter password: ");
        String passwordConfirm = scanner.nextLine();
        String phoneNumber = "";
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Phone number: ");
                phoneNumber = scanner.nextLine();
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }
        pass = false;
        double balance = 0.0;
        while (!pass) {
            try {
                System.out.println("Balance :");
                balance = Double.parseDouble(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only decimals are allowed.");
            }
        }
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
        if (username.equals("admin")) {
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
        Account account = new Account(client.getId(), AccountState.ACTIVE, AccountType.SAVING, balance);
        accounts.add(account);
        System.out.println("The new client's Id is: " + client.getId());
    }

    public void addNewClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts) {
        int id = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Client ID: ");
                id = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }

        for (Client client : clients) {
            if (client.getId() == id) {
                while (true) {
                    System.out.println("1. New current account\n2. New savings account\n3. back");
                    int i = 0;
                    pass = false;
                    while (!pass) {
                        try {
                            i = Integer.parseInt(scanner.nextLine());
                            pass = true;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid input. Only integers are allowed.");
                        }
                    }
                    if (i == 1) {
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
                        if (balance < 0.0) {
                            System.out.println("Can't have negative balance");
                            return;
                        }
                        Account account = new Account(client.getId(), AccountState.ACTIVE, AccountType.CURRENT, balance);
                        accounts.add(account);
                        Client.updateTotalBalance(clients, accounts);
                    } else if (i == 2) {
                        double balance = 0.0;
                        pass = false;
                        while (!pass) {
                            try {
                                System.out.println("Balance: ");
                                balance = Double.parseDouble(scanner.nextLine());
                                pass = true;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Invalid input. Only decimals are allowed.");
                            }
                        }
                        if (balance < 0.0) {
                            System.out.println("Can't have negative balance");
                            return;
                        }
                        Account account = new Account(client.getId(), AccountState.ACTIVE, AccountType.SAVING, balance);
                        accounts.add(account);
                        Client.updateTotalBalance(clients, accounts);
                    } else if (i == 3) {
                        return;
                    } else
                        System.out.println("Invalid input. Try again!");
                }
            }
        }
        System.out.println("Client doesn't exist");
    }

    public void editClientProfile(Scanner scanner, ArrayList<Client> clients) {
        int id = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Client ID:");
                id = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }

        for (Client client : clients) {
            if (client.getId() == id) {
                while (true) {
                    System.out.println("1. Edit first name\n2. Edit last name\n3. Edit password\n4. Edit phone number\n5. Back");
                    int i = 0;
                    pass = false;
                    while (!pass) {
                        try {
                            i = Integer.parseInt(scanner.nextLine());
                            pass = true;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid input. Only integers are allowed.");
                        }
                    }
                    if (i == 1) {
                        System.out.println("Enter the new first name:");
                        String newFirstName = scanner.nextLine();
                        client.setFirstName(newFirstName);
                    } else if (i == 2) {
                        System.out.println("Enter the new last name:");
                        String newLastName = scanner.nextLine();
                        client.setLastName(newLastName);
                    } else if (i == 3) {
                        System.out.println("Enter the new password:");
                        String newPassword = scanner.nextLine();
                        client.setPassword(newPassword);
                    } else if (i == 4) {
                        String newPhoneNumber = "";
                        pass = false;
                        while (!pass) {
                            try {
                                System.out.println("Enter the new phone number:");
                                newPhoneNumber = scanner.nextLine();
                                pass = true;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Invalid input. Only integers are allowed.");
                            }
                        }
                        client.setPhoneNumber(newPhoneNumber);
                    } else if (i == 5) {
                        return;
                    }
                }
            }
        }
        System.out.println("Client doesn't exist");
    }

    public void editClientAccount(Scanner scanner, ArrayList<Account> accounts) {
        int accountNumber = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Account number: ");
                accountNumber = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                while (true) {
                    System.out.println("1. Activate account\n2. Deactivate account\n3. Change account type\n4. Back");
                    int i = 0;
                    pass = false;
                    while (!pass) {
                        try {
                            i = Integer.parseInt(scanner.nextLine());
                            pass = true;
                        } catch (NumberFormatException nfe) {
                            System.out.println("Invalid input. Only integers are allowed.");
                        }
                    }
                    if (i == 1) {
                        account.activateAccount();
                    } else if (i == 2) {
                        account.deactivateAccount();
                    } else if (i == 3) {
                        account.changeAccountType();
                    } else if (i == 4) {
                        return;
                    } else
                        System.out.println("Invalid input. Try again!");
                }
            }
        }
        System.out.println("Account doesn't exist.");
    }

    public void findClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts) {
        while (true) {
            System.out.println("\n1. By ID\n2. By username\n3. By account number\n4. Back");
            int i = 0;
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
                int id = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Client ID: ");
                        id = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
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
                    System.out.println("Client doesn't exist");
                }
            } else if (i == 2) {
                System.out.println("Client username:");
                String username = scanner.nextLine();
                boolean exists = false;
                for (Client client : clients) {
                    if (client.getUsername().equals(username)) {
                        System.out.println(client.details());
                        exists = true;
                        break;
                    }
                }
                if (!exists) {
                    System.out.println("Client doesn't exist");
                }
            } else if (i == 3) {
                int accountNumber = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Client account number:");
                        accountNumber = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                boolean exists = false;
                for (Account account : accounts) {
                    if (account.getAccountNumber() == accountNumber) {
                        int id = account.getClientId();
                        for (Client client : clients) {
                            if (client.getId() == id) {
                                System.out.println(client.details());
                                exists = true;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (!exists) {
                    System.out.println("Client doesn't exist");
                }
            } else if (i == 4) {
                return;
            }
        }
    }

    public void deleteClientAccount(Scanner scanner, ArrayList<Account> accounts) {
        int accountNumber = 0;
        boolean pass = false;
        while (!pass) {
            try {
                System.out.println("Account number: ");
                accountNumber = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException nfe) {
                System.out.println("Invalid input. Only integers are allowed.");
            }
        }
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                accounts.remove(account);
                System.out.println("Account has been deleted!\n");
                return;
            }
        }
        System.out.println("Account doesn't exist.");
    }

    public void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards) {
        int i = 0;
        if (this.getAuthorization() == AuthorizationState.AUTHORIZED) {
            while (true) {
                System.out.println("1. Edit personal info\n2. Add a new client\n3. Create a new account for an existing client\n4. Edit a client profile\n5. Edit a client account\n6. Find a client\n7. Close a client account\n8. Logout");
                boolean pass = false;
                while (!pass) {
                    try {
                        i = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                    if (i == 1) {
                        this.editPersonalInfo(scanner);
                    } else if (i == 2) {
                        this.addNewClient(scanner, clients, employees, accounts);
                    } else if (i == 3) {
                        this.addNewClientAccount(scanner, clients, accounts);
                    } else if (i == 4) {
                        this.editClientProfile(scanner, clients);
                    } else if (i == 5) {
                        this.editClientAccount(scanner, accounts);
                    } else if (i == 6) {
                        this.findClient(scanner, clients, accounts);
                    } else if (i == 7) {
                        this.deleteClientAccount(scanner, accounts);
                    } else if (i == 8) {
                        return;
                    }
                }
            }
        } else {
            while (true) {
                System.out.println("1. Edit personal info\n2. Find a client\n3. Logout");
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
                    this.editPersonalInfo(scanner);
                } else if (i == 2) {
                    this.findClient(scanner, clients, accounts);
                } else return;
            }
        }
    }
}
