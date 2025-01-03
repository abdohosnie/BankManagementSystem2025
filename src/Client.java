import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
    private double balance; // total balance among all accounts
    public static int nextId = 0;
    private final ArrayList<CreditCard> creditCards;
    private final ArrayList<Account> clientAccounts;
    private double loyaltyPoints;

    public Client(String firstName, String lastName, String username, String password, String phoneNumber, double balance) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(100000 + nextId);
        this.creditCards = new ArrayList<>();
        this.clientAccounts = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.balance = balance;
        nextId++;
    }

    public Client(int id, String firstName, String lastName, String username, String password, String phoneNumber, double balance, double loyaltyPoints) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(id);
        this.creditCards = new ArrayList<>();
        this.clientAccounts = new ArrayList<>();
        this.balance = balance;
        this.loyaltyPoints = loyaltyPoints;
        if (nextId < this.getId() - 99999) {
            nextId = this.getId() - 99999;
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<CreditCard> getCreditCards() {
        return creditCards;
    }

    public ArrayList<Account> getClientAccounts() {
        return clientAccounts;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void requestCreditCard(ArrayList<Account> accounts, ArrayList<CreditCard> cards) {
        Account linkedAccount = null;
        for (Account account : accounts) {
            if (account.getClientId() == this.getId()) {
                linkedAccount = account;
                break;
            }
        }
        if (linkedAccount == null) {
            System.out.println("No associated account found for this client.");
            return;
        }
        String cardNumber = Helper.generateCardNumber();
        CreditCard newCard = new CreditCard(cardNumber, linkedAccount, this);
        creditCards.add(newCard);
        cards.add(newCard);
        System.out.println("Credit card with number - " + cardNumber + " - has been issued and linked to account number: " + linkedAccount.getAccountNumber() + ".");
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
            System.out.println("1. First name \n2. Last name\n3. Phone number\n4. Change password\n5. Back");
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
                System.out.println("Enter new first name: ");
                String newFirstName = scanner.nextLine();
                this.setFirstName(newFirstName);
                System.out.println("First name changed successfully!");
            } else if (i == 2) {
                System.out.println("Enter new last name: ");
                String newLastName = scanner.nextLine();
                this.setLastName(newLastName);
                System.out.println("Last name changed successfully!");
            } else if (i == 3) {
                System.out.println("Enter new phone number: ");
                String newPhone = scanner.nextLine();
                this.setPhoneNumber(newPhone);
                System.out.println("Phone number changed successfully!");
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
                        System.out.println("Password changed successfully!");
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

    public void displayAccounts(ArrayList<Account> clientAccounts) {
        if (clientAccounts.isEmpty()) {
            System.out.println("No accounts found.");
        } else {
            for (Account account : clientAccounts) {
                if (account.getClientId() == this.getId()) {
                    System.out.println("Account Number: " + account.getAccountNumber());
                    System.out.println("Account Type: " + account.getAccountType());
                    System.out.println("Account Status: " + account.getAccountState());
                    System.out.println("Balance: " + account.getBalance() + " LE.\n");
                    if (account.getAccountType().getDescription().equals("Saving")) {
                        System.out.println("Balance after 1 year: " + (account.getBalance() + (account.getBalance() * account.getAccountType().getInterestRate())) + "\n");
                    }
                }
            }
        }
    }

    public void showTransactionsHistory(ArrayList<Transaction> transactions) {
        if (!transactions.isEmpty()) {
            for (Transaction transaction : transactions) {
                if (transaction.clientId == this.getId()) {
                    System.out.println(transaction);
                }
            }
        } else System.out.println("No transactions found!");
    }

    public void makeNewAccount(Scanner scanner, ArrayList<Account> accounts, ArrayList<Client> clients) {
        while (true) {
            System.out.println("1. New current account\n2. New savings account\n3. back");
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
                System.out.println("Warning! Minimum balance for current accounts is: " + AccountType.CURRENT.getMinBalance());
                double balance = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Balance: ");
                        balance = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                if (balance < 0) {
                    System.out.println("Can't have negative balance!");
                    return;
                } else if (balance < AccountType.CURRENT.getMinBalance()) {
                    System.out.println("Minimum balance is: " + AccountType.CURRENT.getMinBalance());
                    return;
                } else {
                    Account account = new Account(this.getId(), AccountState.ACTIVE, AccountType.CURRENT, balance);
                    accounts.add(account);
                    clientAccounts.add(account);
                    Client.updateTotalBalance(clients, accounts);
                    System.out.println("New current account created successfully!\nAccount number: " + account.getAccountNumber() + ". \t" + "Balance: " + balance);
                    return;
                }
            } else if (i == 2) {
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
                if (balance < 0) {
                    System.out.println("Can't have negative balance");
                } else {
                    Account account = new Account(this.getId(), AccountState.ACTIVE, AccountType.SAVING, balance);
                    accounts.add(account);
                    clientAccounts.add(account);
                    Client.updateTotalBalance(clients, accounts);
                    System.out.println("New savings account created successfully!\nAccount number: " + account.getAccountNumber() + ". \t" + "Balance: " + balance);
                }
                return;
            } else if (i == 3) {
                return;
            }
        }
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
                int accountNumber = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number: ");
                        accountNumber = Integer.parseInt(scanner.nextLine());
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
                    Account account = Helper.findAccountByNumber(accountNumber, accounts);
                    if (account != null) {
                        account.deposit(amount);
                        Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), TransactionType.DEPOSIT, amount);
                        transactions.add(transaction);
                        Client.updateTotalBalance(clients, accounts);
                    } else return;
                }
            } else if (i == 2) {
                int accountNumber = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number:");
                        accountNumber = Integer.parseInt(scanner.nextLine());
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
                    System.out.println("Can't enter negative amount.");
                    return;
                }
                Account account = Helper.findAccountByNumber(accountNumber, clientAccounts);
                if (account != null) {

                    if (account.getBalance() < amount) {
                        System.out.println("The account doesn't have enough money.\nBalance is:" + account.getBalance());
                        return;
                    } else {
                        account.withdraw(amount);
                        Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), TransactionType.WITHDRAWAL, amount);
                        transactions.add(transaction);
                        Client.updateTotalBalance(clients, accounts);
                        break;
                    }
                } else return;

            } else if (i == 3) {
                int accountNumber = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number to transfer from: ");
                        accountNumber = Integer.parseInt(scanner.nextLine());
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

                Account account = Helper.findAccountByNumber(accountNumber, clientAccounts);
                if (account != null) {
                    if (account.getBalance() < amount) {
                        System.out.println("This account doesn't have enough money!\nBalance is: " + account.getBalance());
                        return;
                    }
                } else return;
                int secondAccountNumber = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Account number to transfer to:");
                        secondAccountNumber = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Only integers are allowed.");
                    }
                }
                Account secondAccount = Helper.findAccountByNumber(secondAccountNumber, accounts);
                if (secondAccount != null) {
                    account.withdraw(amount);
                    secondAccount.deposit(amount);
                    Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), TransactionType.TRANSFER, amount);
                    Transaction transaction2 = new Transaction(secondAccount.getClientId(), secondAccount.getAccountNumber(), TransactionType.TRANSFER, amount);
                    transactions.add(transaction);
                    transactions.add(transaction2);
                    Client.updateTotalBalance(clients, accounts);
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

                boolean paymentSuccessful = this.payWithCreditCard(cardNumber, amount);
                if (paymentSuccessful) {
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

    public void manageCards(Scanner scanner, ArrayList<Account> accounts, ArrayList<CreditCard> cards) {
        while (true) {
            System.out.println("\nCard Management\n1. View cards\n2. Request a new credit card\n3. Activate a credit card\n4. Deactivate a credit card\n5. Back to main menu");
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
                this.viewCards();
            } else if (i == 2) {
                this.requestCreditCard(accounts, cards);
            } else if (i == 3) {
                this.activateCard(scanner);
            } else if (i == 4) {
                this.deactivateCard(scanner);
            } else if (i == 5) {
                return;
            } else System.out.println("Invalid choice. Please try again.");
        }
    }

    private void viewCards() {
        if (this.creditCards.isEmpty()) {
            System.out.println("You don't have any credit cards.");
        } else {
            System.out.println("Your Credit Cards:");
            for (CreditCard card : creditCards) {
                System.out.println("Card Number: " + card.getCardNumber());
                System.out.println("Linked Account: " + card.getLinkedAccount().getAccountNumber());
                System.out.println("Card State: " + card.getCardState());
                System.out.println("Available Credit: " + (20000 - card.getTotalSpent()));
                System.out.println("--------------------");
            }
        }
    }

    private void activateCard(Scanner scanner) {
        if (this.creditCards.isEmpty()) {
            System.out.println("You don't have any credit cards to activate.");
            return;
        }

        System.out.print("Enter the card number to activate: ");
        String cardNumber = scanner.nextLine();

        CreditCard selectedCard = Helper.findCardByNumber(cardNumber, creditCards);
        if (selectedCard == null) {
            System.out.println("Card not found.");
        } else if (selectedCard.getCardState() == CardState.ACTIVE) {
            System.out.println("This card is already active.");
        } else {
            selectedCard.activateCard();
            System.out.println("Credit card " + cardNumber + " has been activated successfully!");
        }
    }

    private void deactivateCard(Scanner scanner) {
        if (this.creditCards.isEmpty()) {
            System.out.println("You don't have any credit cards to deactivate.");
            return;
        }

        System.out.print("Enter the card number to deactivate: ");
        String cardNumber = scanner.nextLine();

        CreditCard selectedCard = Helper.findCardByNumber(cardNumber, creditCards);
        if (selectedCard == null) {
            System.out.println("Card not found.");
        } else if (selectedCard.getCardState() == CardState.INACTIVE) {
            System.out.println("This card is already inactive.");
        } else {
            selectedCard.deactivateCard();
            System.out.println("Credit card " + cardNumber + " has been deactivated successfully!");
        }
    }

    @Override
    public String details() {
        StringBuilder str = new StringBuilder(super.details());
        str.append("Total balance: ").append(this.getBalance()).append('\n');
        str.append("Loyalty Points: ").append(this.getLoyaltyPoints()).append('\n');
        if (!creditCards.isEmpty()) {
            str.append("Credit Cards: " + '\n');
            for (CreditCard card : creditCards) {
                str.append("- ").append(card.getCardNumber()).append(" (Active: ").append(card.isActive()).append(")\n");
            }
            return str.toString();
        }
        str.append("No credit cards available.\n");
        return str.toString();
    }

    @Override
    public void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards) {
        int i = 0;
        while (true) {
            System.out.println("\n1. Edit personal info\n2. Show profile details\n3. Show accounts' details\n4. Manage cards\n5. Make a new transaction\n6. Show transaction history\n7. Make a new account\n8. Logout");
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
                this.manageCards(scanner, accounts, cards);
            } else if (i == 5) {
                this.makeNewTransaction(scanner, clients, accounts, transactions);
            } else if (i == 6) {
                this.showTransactionsHistory(transactions);
            } else if (i == 7) {
                this.makeNewAccount(scanner, accounts, clients);
            } else if (i == 8) {
                return;
            } else System.out.println("Invalid Choice! Try again.");
        }
    }
}
