import java.util.Scanner;

public class Account {
    private final int accountNumber;
    private final int clientId;
    private AccountType accountType;
    private AccountState accountState;
    private double balance;
    public static int nextId = 0;

    public Account(int clientId, AccountState accountState, AccountType accountType, double balance) {
        this.accountNumber = 300000 + nextId;
        this.clientId = clientId;
        this.accountState = accountState;
        this.accountType = accountType;
        this.balance = balance;
        ++nextId;
    }

    public Account(int accountNumber, int clientId, AccountType accountType, AccountState accountState, double balance) {
        this.accountNumber = accountNumber;
        this.clientId = clientId;
        this.accountType = accountType;
        this.accountState = accountState;
        this.balance = balance;
        if (nextId < this.getAccountNumber() - 299999) {
            nextId = this.getAccountNumber() - 299999;
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void activateAccount() {
        this.accountState = AccountState.ACTIVE;
        System.out.println("Account activated successfully!");
    }

    public void deactivateAccount() {
        this.accountState = AccountState.INACTIVE;
        System.out.println("Account deactivated successfully!");
    }

    public void changeAccountType() {
        System.out.println("Current account type: " + accountType);

        System.out.println("1. Switch to SAVING\n2. Switch to CURRENT\n3. Cancel");
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        boolean pass = false;

        while (!pass) {
            try {
                i = Integer.parseInt(scanner.nextLine());
                pass = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        if (i == 1 && accountType != AccountType.SAVING) {
            accountType = AccountType.SAVING;
            System.out.println("Account type changed to SAVING.");
        } else if (i == 2 && accountType != AccountType.CURRENT) {
            if (balance < AccountType.CURRENT.getMinBalance()) {
                System.out.println("Cannot switch to CURRENT account. Minimum balance required: " + AccountType.CURRENT.getMinBalance());
            } else {
                accountType = AccountType.CURRENT;
                System.out.println("Account type changed to CURRENT.");
            }
        } else if (i == 3) {
            System.out.println("Account type change canceled.");
        } else {
            System.out.println("Invalid choice or account is already of the selected type.");
        }
    }


    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount + " LE. to account: " + accountNumber);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrew: " + amount + " LE. from account: " + accountNumber);

                // Apply fees if Current Account drops below the minimum balance
                if (accountType == AccountType.CURRENT && balance < AccountType.CURRENT.getMinBalance()) {
                    applyFees();
                }
            } else {
                System.out.println("Not enough balance.");
            }
        } else {
            System.out.println("Withdrawal amount must be positive.");
        }
    }

    private void applyFees() {
        double fees = 50.0;
        System.out.println("Balance below minimum threshold. Applying fee of " + fees + " LE.");
        balance -= fees;

        // this checks if the balance went negative after fees
        if (balance < 0) {
            System.out.println("Warning: Account balance is now negative.");
        }
    }

    @Override
    public String toString() {
        return "accountNumber = " + accountNumber + "\n" +
                "accountType = " + accountType + "\n" +
                "accountState = " + accountState + "\n" +
                "balance = " + balance;
    }
}
