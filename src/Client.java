import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
    private double balance; // total balance among all accounts
    public static int nextId = 0;
    private final ArrayList<CreditCard> creditCards;
    private double loyaltyPoints;

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public Client(String firstName, String lastName, String username, String password, String phoneNumber, double balance) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(100000 + nextId);
        this.creditCards = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.balance = balance;
        nextId++;
    }

    public Client(int id, String firstName, String lastName, String username, String password, String phoneNumber, double balance) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(id);
        this.creditCards = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.balance = balance;
        if (nextId < this.getId() - 99999) {
            nextId = this.getId() - 99999;
        }
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void requestCreditCard(ArrayList<Client> clients, ArrayList<Account> accounts) {
        int accountNumber = -1;
        for (Account account : accounts) {
            if (account.getClientId() == this.getId()) {
                accountNumber = account.getAccountNumber();
                break;
            }
        }
        if (accountNumber == -1) {
            System.out.println("No associated account found for this client.");
            return;
        }
        String cardNumber = Generator.generateCardNumber();
        CreditCard newCard = new CreditCard(cardNumber, accountNumber, CardState.ACTIVE, this);
        creditCards.add(newCard);
        System.out.println("Credit card with number " + cardNumber + " has been issued and linked to account number " + accountNumber + ".");
    }

    public void disableCreditCard(String cardNumber) {
        for (CreditCard card : creditCards) {
            card.deactivateCard();
        }
        System.out.println("Card not found.");
    }

    public boolean payWithCreditCard(String cardNumber, double amount) {
        for (CreditCard card : creditCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card.pay(amount);
            }
        }
        System.out.println("Card not found.");
        return false;
    }

    public void addLoyaltyPoints(double amountSpent) {
        double points = amountSpent / 100;
        loyaltyPoints += points;
        System.out.println("Loyalty points added: " + points + ". Total points: " + loyaltyPoints);
    }

    public static void updateTotalBalance(ArrayList<Client> clients, ArrayList<Account> accounts) {
        for (Client client : clients) {
            client.setBalance(0);
            for (Account account : accounts) {
                if (client.getId() == account.getClientId()) {
                    client.setBalance(client.getBalance() + account.getBalance());
                }
            }
        }
    }

    public void editPersonalInfo(Scanner scanner) {
        while (true) {
            System.out.println("1. First name \n2. Last name\n3.phone number\n4.change password\n5. Back");
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
            scanner.nextLine();
            if (i == 1) {
                System.out.println("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                this.setFirstName(newFirstName);
            } else if (i == 2) {
                System.out.println("Enter new last name: ");
                String newLastName = scanner.nextLine();
                this.setLastName(newLastName);
            } else if (i == 3) {
                System.out.println("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                this.setPhoneNumber(newPhone);
            } else if (i == 4) {
                System.out.println("Enter old password: ");
                String oldPassword = scanner.nextLine();
                if (this.getPassword().equals(oldPassword)) {
                    System.out.println("Enter new password: ");
                    String newPassword = scanner.nextLine();
                    System.out.println("ReEnter new password: ");
                    String RenewPassword = scanner.nextLine();
                    if (newPassword.equals(RenewPassword)) {
                        this.setPassword(newPassword);
                    } else {
                        System.out.println("New password doesn't match.");
                        return;
                    }
                } else {
                    System.out.println("Old password doesn't match.");
                    return;
                }
            } else if (i == 5) {
                return;
            }
        }
    }

    public void displayAccounts(ArrayList<Account> accounts) {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : accounts) {
                if (account.getClientId() == this.getId()) {
                    System.out.println("Account Number: " + account.getAccountNumber() + '\n');
                    System.out.println("Account Type: " + account.getAccountType() + '\n');
                    System.out.println("Account Status: " + account.getAccountState() + '\n');
                    System.out.println("Balance: " + account.getBalance());
                    if (account.getAccountType().getDescription().equals("Saving")) {
                        System.out.println("Balance after 1 year: " + (account.getBalance() + (account.getBalance() * account.getAccountType().getInterestRate())));
                    } else if (account.getAccountType().getDescription().equals("Current")) {
                        System.out.println(" ");
                    }
                }
            }
        }
    }

    public void showTransactionsHistory(ArrayList<Transaction> transactions) {

    }

    public void makeNewAccount(Scanner scanner, ArrayList<Account> accounts, ArrayList<Client> clients) {

    }

    public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {
        while (true) {
            System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Pay with Credit Card\n5. Back");
            int i = 0;
            boolean pass = false;
            while (!pass) {
                try {
                    i = Integer.parseInt(scanner.nextLine());
                    pass = true;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Only integers are allowed.");
                }
            }
            if (i == 1) {
                int accNum = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number: ");
                        accNum = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Only integers are allowed.");
                    }
                }
                double amount = 0.0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Amount:");
                        amount = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Only decimals are allowed.");
                    }
                    if (amount < 0.0) {
                        System.out.println("Can't enter negative numbers.");
                    }
                    boolean exists = false;
                    for (Account account : accounts) {
                        if (account.getAccountNumber() == accNum) {
                            account.setBalance(account.getBalance() + amount);
                            Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), 0, TransactionType.DEPOSIT, amount);
                            transactions.add(transaction);
                            Client.updateTotalBalance(clients, accounts);
                            exists = true;
                            break;
                        } else {
                            System.out.println("Account doesn't exist!");
                        }
                    }
                }
            } else if (i == 2) {
                int accNum = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number:");
                        accNum = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input! Only integers are allowed.");
                    }
                }
                double amount = 0.0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Amount: ");
                        amount = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input! Only decimals are allowed.");
                    }
                }
                if (amount < 0.0) {
                    System.out.println("Can't enter negative amount");
                    return;
                }
                boolean exists = false;
                for (Account account : accounts) {
                    if (account.getAccountNumber() == accNum) {
                        if (account.getBalance() < amount) {
                            System.out.println("The account doesn't have enough money.\nBalance is:" + account.getBalance());
                            return;
                        } else {
                            account.setBalance(account.getBalance() - amount);
                            Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), 0, TransactionType.WITHDRAWAL, amount);
                            transactions.add(transaction);
                            Client.updateTotalBalance(clients, accounts);
                            exists = true;
                            break;
                        }
                    } else {
                        System.out.println("Account doesn't exist!");
                    }
                }
            } else if (i == 3) {
                int accNum = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number to transfer from: ");
                        accNum = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                double amount = 0.0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Amount:");
                        amount = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only decimals are allowed.");
                    }
                }
                if (amount < 0.0) {
                    System.out.println("Can't enter negative amount!");
                    return;
                }
                boolean exists = false;
                for (Account account : accounts) {
                    if (account.getAccountNumber() == accNum) {
                        if (account.getBalance() < amount) {
                            System.out.println("This account doesn't have enough money!\nBalance is: " + account.getBalance());
                            return;
                        }
                        int acc2Num = 0;
                        pass = false;
                        while (!pass) {
                            try {
                                System.out.println("Account number to transfer to:");
                                acc2Num = Integer.parseInt(scanner.nextLine());
                                pass = true;
                            } catch (NumberFormatException nfe) {
                                System.out.println("Invalid input. Only integers are allowed.");
                            }
                        }
                        boolean exists2 = false;
                        for (Account account2 : accounts) {
                            if (account2.getAccountNumber() == acc2Num) {
                                account.setBalance(account.getBalance() - amount);
                                account2.setBalance(account2.getBalance() + amount);
                                Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), 0, TransactionType.TRANSFER, amount);
                                Transaction transaction2 = new Transaction(account2.getClientId(), account2.getAccountNumber(), 0, TransactionType.TRANSFER, amount);
                                transactions.add(transaction);
                                transactions.add(transaction2);
                                Client.updateTotalBalance(clients, accounts);
                                exists2 = true;
                                break;
                            }
                        }
                        if (!exists2) {
                            System.out.println("Receiver's account doesn't exist!");
                        }
                    }
                }
            } else if (i == 4) {
                if (this.creditCards.isEmpty()) {
                    System.out.println("You don't have any credit cards. Please request one first.");
                    return;
                }

                System.out.println("Enter the credit card number: ");
                String cardNumber = scanner.nextLine();

                CreditCard selectedCard = null;
                for (CreditCard card : creditCards) {
                    if (card.getCardNumber().equals(cardNumber)) {
                        selectedCard = card;
                        break;
                    }
                }

                if (selectedCard == null) {
                    System.out.println("Credit card not found. Please enter a valid card number.");
                    return;
                }

                System.out.println("Enter the amount to pay:");
                double amount = 0.0;
                pass = false;

                while (!pass) {
                    try {
                        amount = Double.parseDouble(scanner.nextLine());
                        if (amount <= 0) {
                            System.out.println("Invalid amount. Please enter a positive number.");
                        } else {
                            pass = true;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Only decimals are allowed.");
                    }
                }

                boolean paymentSuccessful = selectedCard.pay(amount);
                if (paymentSuccessful) {
                    System.out.println("Payment of " + amount + " LE was successful using credit card " + selectedCard.getCardNumber());
                    Transaction transaction = new Transaction(this.getId(), selectedCard.getAccountNumber(), selectedCard.getCardNumber(), TransactionType.CREDIT_PAYMENT, amount);
                    transactions.add(transaction);
                } else {
                    System.out.println("Payment failed! Please check your card's status or available credit.");
                }
            } else if (i == 5) {
                return;
            }
        }
    }

    @Override
    public String details() {
        String str = super.details();
        str += "Loyalty Points: " + this.getLoyaltyPoints() + '\n';
        str += "Credit Cards: " + '\n';
        if (!creditCards.isEmpty()) {
            for (CreditCard card : creditCards) {
                System.out.println("- " + card.getCardNumber() + " (Active: " + card.isActive() + ")");
            }
            return str;
        }
        str += "No credit cards available.\n";
        return str;
    }

    @Override
    public void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {
        int i = 0;
        while (true) {
            System.out.println("1. Edit personal info\n2. Show profile details\n3. Show accounts' details\n4. Request credit card\n5. Make a new transaction\n6. Show transaction history\n7. Make a new account\n8. Logout");
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
                System.out.println(this.details());
            } else if (i == 3) {
                this.displayAccounts(accounts);
            } else if (i == 4) {
                requestCreditCard(clients,accounts);
            } else if (i == 5) {
                this.makeNewTransaction(scanner, clients, accounts, transactions);
            } else if (i == 6) {
                this.showTransactionsHistory(transactions);
            } else if (i == 7) {
                this.makeNewAccount(scanner, accounts, clients);
            } else if (i == 8) {
                return;
            }
        }
    }
}
