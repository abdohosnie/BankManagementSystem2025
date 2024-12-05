public class Account {
    private int accountNumber;
    private final int clientId;
    private AccountType accountType;
    private AccountState accountState;
    private double balance;
    public static int nextNumber = 0;

    public Account(int clientId, AccountState accountState, AccountType accountType, double balance) {
        this.accountNumber = 300000 + nextNumber;
        this.clientId = clientId;
        this.accountState = accountState;
        this.accountType = accountType;
        this.balance = balance;
        nextNumber++;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getClientId() {
        return clientId;
    }

    public AccountState getAccountState() {
        return accountState;
    }

    public void activateAccount(AccountState accountState) {
        this.accountState = AccountState.ACTIVE;
        System.out.println("Account activated successfully!");
    }

    public void deactivateAccount(AccountState accountState) {
        this.accountState = AccountState.INACTIVE;
        System.out.println("Account deactivated successfully!");
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
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
            System.out.println("Deposit successful. New balance: " + balance);

        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawn: " + amount + " LE.");

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
        double fees = 50.0F;
        System.out.println("Balance below minimum threshold. Applying fee of " + fees + " LE.");
        balance -= fees;

        // this checks if the balance went negative after fees
        if (balance < 0) {
            System.out.println("Warning: Account balance is now negative.");
        }
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber='" + accountNumber + '\'' +
                ", accountType='" + accountType + '\'' +
                ", accountState=" + accountState +
                ", balance=" + balance +
                '}';
    }
}
