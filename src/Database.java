import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    private final String clientsFileName = "./database/clients.txt";
    private final String employeesFileName = "./database/employees.txt";
    private final String accountsFileName = "./database/accounts.txt";
    private final String transactionsFileName = "./database/transactions.txt";
    private final String cardsFileName = "./database/cards.txt";

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public void load(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards) {
        try (BufferedReader clientsFile = new BufferedReader(new FileReader(clientsFileName))) {
            String str;
            while ((str = clientsFile.readLine()) != null && !str.isEmpty()) {
                try {
                    String[] data = str.split(",");
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String username = data[3];
                    String password = data[4];
                    String phoneNumber = data[5];
                    double balance = Double.parseDouble(data[6]);
                    double loyaltyPoints = Double.parseDouble(data[7]);

                    clients.add(new Client(id, firstName, lastName, username, password, phoneNumber, balance, loyaltyPoints));
                } catch (Exception e) {
                    System.out.println("Invalid data format in line: " + str + ". Skipping this record.");
                }
            }
            System.out.println("Finished loading clients.");
        } catch (IOException e) {
            System.out.println("Error reading clients file: " + e.getMessage());
        }

        try (BufferedReader employeesFile = new BufferedReader(new FileReader(employeesFileName))) {
            String str;
            while ((str = employeesFile.readLine()) != null && !str.isEmpty()) {
                try {
                    String[] data = str.split(",");
                    int id = Integer.parseInt(data[0]);
                    String firstName = data[1];
                    String lastName = data[2];
                    String username = data[3];
                    String password = data[4];
                    String phoneNumber = data[5];
                    AuthorizationState authorization = AuthorizationState.values()[Integer.parseInt(data[6])];
                    String address = data[7];
                    String position = data[8];
                    String graduatedCollege = data[9];
                    int yearOfGraduation = Integer.parseInt(data[10]);
                    String collegeGrade = data[11];

                    employees.add(new Employee(id, firstName, lastName, username, password, phoneNumber, authorization, address, position, graduatedCollege, yearOfGraduation, collegeGrade));
                } catch (Exception e) {
                    System.out.println("Invalid data format in line: " + str + ". Skipping this record.");
                }
            }
            System.out.println("Finished loading employees.");
        } catch (IOException e) {
            System.out.println("Error reading employees file: " + e.getMessage());
        }

        try (BufferedReader accountsFile = new BufferedReader(new FileReader(accountsFileName))) {
            String str;
            while ((str = accountsFile.readLine()) != null && !str.isEmpty()) {
                try {
                    String[] data = str.split(",");
                    int accountNumber = Integer.parseInt(data[0]);
                    int clientId = Integer.parseInt(data[1]);
                    AccountType accountType = AccountType.values()[Integer.parseInt(data[2])];
                    AccountState accountState = AccountState.values()[Integer.parseInt(data[3])];
                    double balance = Double.parseDouble(data[4]);

                    Account account = new Account(accountNumber, clientId, accountType, accountState, balance);
                    accounts.add(account);
                } catch (Exception e) {
                    System.out.println("Invalid data format in line: " + str + ". Skipping this record.");
                }
            }
            System.out.println("Finished loading accounts.");
        } catch (IOException e) {
            System.out.println("Error reading accounts file: " + e.getMessage());
        }

        try (BufferedReader transactionsFile = new BufferedReader(new FileReader(transactionsFileName))) {
            String str;
            while ((str = transactionsFile.readLine()) != null && !str.isEmpty()) {
                try {
                    String[] data = str.split(",");
                    int id = Integer.parseInt(data[0]);
                    Date date = sdf.parse(data[1]);
                    int clientId = Integer.parseInt(data[2]);
                    int accountNumber = Integer.parseInt(data[3]);
                    int employeeId = Integer.parseInt(data[4]);
                    TransactionType transactionType = TransactionType.values()[Integer.parseInt(data[5])];
                    double amount = Double.parseDouble(data[6]);
                    String cardNumber = data[7];

                    Transaction transaction = new Transaction(id, date, clientId, accountNumber, employeeId, transactionType, amount, cardNumber);
                    transactions.add(transaction);
                } catch (Exception e) {
                    System.out.println("Invalid data format in line: " + str + ". Skipping this record.");
                }
            }
            System.out.println("Finished loading transactions.");
        } catch (IOException e) {
            System.out.println("Error reading transactions file: " + e.getMessage());
        }

        try (BufferedReader cardsFile = new BufferedReader(new FileReader(cardsFileName))) {
            String str;
            while ((str = cardsFile.readLine()) != null && !str.isEmpty()) {
                try {
                    String[] data = str.split(",");
                    String cardNumber = data[0];
                    int accountNumber = Integer.parseInt(data[1]);
                    double totalSpent = Double.parseDouble(data[2]);
                    String ownerId = data[3];
                    CardState cardState = CardState.values()[Integer.parseInt(data[4])];

                    Account linkedAccount = Helper.findAccountByNumber(accountNumber, accounts);
                    if (linkedAccount == null) {
                        System.out.println("Account with number " + accountNumber + " not found. Skipping card.");
                        continue;
                    }

                    Client owner = Helper.findClientById(ownerId, clients);
                    if (owner == null) {
                        System.out.println("Client with ID " + ownerId + " not found. Skipping card.");
                        continue;
                    }

                    CreditCard card = new CreditCard(cardNumber, cardState, linkedAccount, owner, totalSpent);
                    cards.add(card);

                    owner.getCreditCards().add(card);
                } catch (Exception e) {
                    System.out.println("Invalid data format in line: " + str + ". Skipping this record.");
                }
            }
            System.out.println("Finished loading cards.");
        } catch (IOException e) {
            System.out.println("Error reading cards file: " + e.getMessage());
        }
    }

    public void save(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards) {
        try (BufferedWriter clientsFileWrite = new BufferedWriter(new FileWriter(clientsFileName))) {
            for (Client client : clients) {
                String str = client.getId() + "," +
                        client.getFirstName() + "," +
                        client.getLastName() + "," +
                        client.getUsername() + "," +
                        client.getPassword() + "," +
                        client.getPhoneNumber() + "," +
                        client.getBalance() + "," +
                        client.getLoyaltyPoints();
                clientsFileWrite.write(str);
                clientsFileWrite.newLine();
            }
            System.out.println("Clients saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving clients: " + e.getMessage());
        }

        try (BufferedWriter employeesFileWrite = new BufferedWriter(new FileWriter(employeesFileName))) {
            for (Employee employee : employees) {
                String str = employee.getId() + "," +
                        employee.getFirstName() + "," +
                        employee.getLastName() + "," +
                        employee.getUsername() + "," +
                        employee.getPassword() + "," +
                        employee.getPhoneNumber() + "," +
                        employee.getAuthorization().ordinal() + "," +
                        employee.getAddress() + "," +
                        employee.getPosition() + "," +
                        employee.getGraduatedCollege() + "," +
                        employee.getYearOfGraduation() + "," +
                        employee.getCollegeGrade();
                employeesFileWrite.write(str);
                employeesFileWrite.newLine();
            }
            System.out.println("Employees saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving employees: " + e.getMessage());
        }

        try (BufferedWriter accountsFileWrite = new BufferedWriter(new FileWriter(accountsFileName))) {
            for (Account account : accounts) {
                String str = account.getAccountNumber() + "," +
                        account.getClientId() + "," +
                        account.getAccountType().ordinal() + "," +
                        account.getAccountState().ordinal() + "," +
                        account.getBalance();
                accountsFileWrite.write(str);
                accountsFileWrite.newLine();
            }
            System.out.println("Accounts saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving accounts: " + e.getMessage());
        }

        try (BufferedWriter transactionsFileWrite = new BufferedWriter(new FileWriter(transactionsFileName))) {
            for (Transaction transaction : transactions) {
                String str = transaction.getTransactionId() + "," +
                        sdf.format(transaction.getDate()) + "," +
                        transaction.getClientId() + "," +
                        transaction.getAccountNumber() + "," +
                        transaction.getEmployeeId() + "," +
                        transaction.getTransactionType().ordinal() + "," +
                        transaction.getAmount() + "," +
                        (transaction.getCardNumber() == null ? "null" : transaction.getCardNumber());
                transactionsFileWrite.write(str);
                transactionsFileWrite.newLine();
            }
            System.out.println("Transactions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }

        try (BufferedWriter cardsFileWrite = new BufferedWriter(new FileWriter(cardsFileName))) {
            for (CreditCard card : cards) {
                String str = card.getCardNumber() + "," +
                        card.getLinkedAccount().getAccountNumber() + "," +
                        card.getAvailableCredits() + "," +
                        card.getOwner().getId() + "," +
                        card.getCardState().ordinal();
                cardsFileWrite.write(str);
                cardsFileWrite.newLine();
            }
            System.out.println("Credit cards saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving cards: " + e.getMessage());
        }
    }
}