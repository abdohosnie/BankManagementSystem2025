import java.util.ArrayList;
import java.util.Scanner;

public abstract class User {
    protected int id;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String phoneNumber;

    public User(String firstName, String lastName, String username, String password, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String details() {
        String str = "";
        str += "ID: " + this.getId() + '\n';
        str += "Full name: " + this.getFirstName() + ' ' + this.getLastName() + '\n';
        str += "Username: " + this.getUsername() + '\n';
        str += "Phone number: " + this.getPhoneNumber() + '\n';
        return str;
    }

    public abstract void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions, ArrayList<CreditCard> cards);
}
