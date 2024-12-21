import java.util.Date;

public class Transaction {
    protected int transactionId;
    protected Date date;
    protected int clientId;
    protected int accountNumber;
    private String cardNumber;
    protected TransactionType transactionType;
    protected double amount;
    public static int nextId = 0;

    public Transaction(int clientId, int accountNumber, TransactionType transactionType, double amount) {
        this.transactionId = 400000 + nextId;
        this.date = new Date();
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        ++nextId;
    }

    public Transaction(int clientId, int accountNumber, String cardNumber, TransactionType transactionType, double amount) {
        this.transactionId = 400000 + nextId;
        this.date = new Date();
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        ++nextId;
    }

    public Transaction(int transactionId, Date date, int clientId, int accountNumber, TransactionType transactionType, double amount, String cardNumber) {
        this.transactionId = transactionId;
        this.date = date;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.transactionType = transactionType;
        this.amount = amount;
        this.cardNumber = cardNumber;
        if (nextId < this.transactionId - 399999) {
            nextId = this.transactionId - 399999;
        }
    }

    @Override
    public String toString() {
        return "Transaction ID: " + this.transactionId + "\n" +
                "Date: " + this.date + "\n" +
                "Amount: " + this.amount + "\n" +
                "Transaction type: " + this.transactionType + "\n" +
                "Client ID: " + this.clientId + "\n" +
                "Account number: " + this.accountNumber + "\n";
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public Date getDate() {
        return date;
    }

    public int getClientId() {
        return this.clientId;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

}
