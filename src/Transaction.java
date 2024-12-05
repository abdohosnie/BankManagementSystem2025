import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    protected int id;
    protected String date;
    protected int clientId;
    protected int accountNumber;
    protected int employeeId;
    protected TransactionType transactionType;
    protected double amount;
    public static int nextId = 0;

    public Transaction(int clientId, int accountNumber, int employeeId, TransactionType transactionType, double amount) {
        this.id = 400000 + nextId;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        this.date = sdf.format(date);
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.employeeId = employeeId;
        this.transactionType = transactionType;
        this.amount = amount;
        ++nextId;
    }

    public Transaction(int id, String date, int clientId, int accountNumber, int employeeId, TransactionType transactionType, float amount) {
        this.id = id;
        this.date = date;
        this.clientId = clientId;
        this.accountNumber = accountNumber;
        this.employeeId = employeeId;
        this.transactionType = transactionType;
        this.amount = amount;
        if (nextId < this.id - 399999) {
            nextId = this.id - 399999;
        }
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

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public TransactionType getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
