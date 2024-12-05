import java.util.ArrayList;
import java.util.Scanner;

public class Client extends User {
    private float balance; // total balance among all accounts
    public static int nextId = 0;
    private final ArrayList<CreditCard> creditCards;
    private double loyaltyPoints;

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public float getBalance() {
        return balance;
    }

    public Client(String firstName, String lastName, String username, String password, int phoneNumber, float balance) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(100000 + nextId);
        this.creditCards = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.balance = balance;
        nextId++;
    }
    public Client(int id, String firstName, String lastName, String username, String password, int phoneNumber, float balance) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(id);
        this.creditCards = new ArrayList<>();
        this.loyaltyPoints = 0;
        this.balance = balance;
        if (nextId < this.getId() - 99999)
        {
            nextId = this.getId() - 99999;
        }
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void requestCreditCard(String cardNumber) {
        CreditCard newCard = new CreditCard(cardNumber, this);
        creditCards.add(newCard);
        System.out.println("Credit card with number " + cardNumber + " has been issued.");
    }

    public void disableCreditCard(String cardNumber) {
        for (CreditCard card : creditCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                card.disableCard();
                return;
            }
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
        // Assuming 1 point per 100 LE spent
        double points = amountSpent / 100;
        loyaltyPoints += points;
        System.out.println("Loyalty points added: " + points + ". Total points: " + loyaltyPoints);
    }

    public static void updateTotalBalance(ArrayList<Client> clients, ArrayList<Account> accs){

    }

    public void menu (Scanner scanner, ArrayList<Employee> emps, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
        System.out.println("Hello, " + this.getFirstName() + " " + this.getLastName() + '\n');

    }

    public void editPersonalInfo(Scanner scanner) {

    }

    @Override
    public String details() {
        String str = "";
        str += "Client Name: " + this.getFirstName()+ " " + this.getLastName() + '\n';
        str += "Loyalty Points: " + this.getLoyaltyPoints() + '\n';
        str += "Credit Cards: " + '\n';
        for (CreditCard card : creditCards) {
            System.out.println("- " + card.getCardNumber() + " (Active: " + card.isActive() + ")");
        }
        return str;
    }

    public void displayAccounts(ArrayList<Account> accs) {
        if (accs.isEmpty()) {
            System.out.println("No accounts found.");
        }
        else {
        for (Account account:accs) {
            if (account.getClientId() == this.getId()) {
                System.out.println("Account Number: " + account.getAccountNumber() + '\n');
                System.out.println("Account Type: " + account.getAccountType() + '\n');
                System.out.println("Account Status: " + account.isAccountState() + '\n');
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

    public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
        while (true) {
            System.out.println("1. Deposit\n2. Withdraw\n3. Transfer\n4. Back");
            int i = 0;
            boolean pass = false;
            while(!pass) {
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
                double amount = 0;
                pass = false;
                while (!pass) {
                    try {
                        System.out.println("Amount:");
                        amount = Float.parseFloat(scanner.nextLine());
                        pass = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Only decimals are allowed.");
                    }
                    if (amount < 0.0F) {
                        System.out.println("Can't enter negative numbers.");
                    }
                    boolean exists = false;
                    for (Account account:accs) {
                        if (account.getAccountNumber() == accNum) {
                            account.setBalance(account.getBalance() + amount);
                            Transaction transaction = new Transaction(account.getClientId(), account.getAccountNumber(), 0, "Deposit",  amount);
                            transactions.add(transaction);
                            Client.updateTotalBalance(clients, accs);
                            exists = true;
                            break;
                        } if (!exists) {
                            System.out.println("Account doesn't exist!");
                        }
                    }
                }
                } else if (i == 2) {
                int accNum = 0;
                pass = false;
                while (!pass)
                {
                    try
                    {
                        System.out.println("Account number:");
                        accNum = Integer.parseInt(scanner.nextLine());
                        pass = true;
                    }
                    catch (NumberFormatException nfe)
                    {
                        System.out.println("Invalid input! Only integers are allowed.");
                    }
                }
                double amount = 0;
                pass = false;
                while (!pass) {
                    try
                    {
                        System.out.println("Amount: ");
                        amount = Double.parseDouble(scanner.nextLine());
                        pass = true;
                    }
                    catch (NumberFormatException nfe)
                    {
                        System.out.println("Invalid input! Only decimals are allowed.");
                    }
                }
            }
        }
    }

    public void showTransactionsHistory(ArrayList<Transaction> transactions) {

    }

    public void makeNewAccount(Scanner scanner, ArrayList<Account> accs, ArrayList<Client> clients) {

    }
}
