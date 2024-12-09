import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    protected int id;
    protected Date date;
    protected int clientId;
    protected int accountNumber;
    private String cardNumber;
    protected int employeeId;
    protected TransactionType transactionType;
    protected double amount;
    public static int nextId = 0;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Transaction(int clientId, int accountNumber, int employeeId, TransactionType transactionType, double amount) {
        this.id = 400000 + nextId;
        this.date = new Date();
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.employeeId = employeeId;
        this.transactionType = transactionType;
        this.amount = amount;
        ++nextId;
    }

    public Transaction(int id, String date, int clientId, int accountNumber, int employeeId, TransactionType transactionType, double amount, String cardNumber) {
        this.id = id;
        try {
            this.date = sdf.parse(date);
        } catch (Exception e) {
            System.out.println("Invalid date format: " + date + ". Setting to current date.");
            this.date = new Date();
        }
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.employeeId = employeeId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.cardNumber = cardNumber;
        if (nextId < this.id - 399999) {
            nextId = this.id - 399999;
        }
    }

    public Transaction(int clientId, int accountNumber, String cardNumber, TransactionType transactionType, double amount) {
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.transactionType = transactionType;
        this.amount = amount;
    }

    public String details() {
        String str = "";
        str = str + "Transaction ID: " + this.id + "\n";
        str = str + "Date: " + this.date + "\n";
        str = str + "Amount: " + this.amount + "\n";
        str = str + "Transaction type: " + this.transactionType + "\n";
        str = str + "Client ID: " + this.clientId + "\n";
        str = str + "Account number: " + this.accountNumber + "\n";
        return str;
    }

    public int getId() {
        return this.id;
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

    public int getEmployeeId() {
        return this.employeeId;
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
