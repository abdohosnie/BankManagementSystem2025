import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Database {
    private final String accountsFileName = "./database/accounts.txt";
    private final String clientsFileName = "./database/clients.txt";
    private final String employeesFileName = "./database/employees.txt";
    private final String transactionsFileName = "./database/transactions.txt";


    public void clear(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {
        clients.clear();
        employees.clear();
        accounts.clear();
        transactions.clear();
    }

    public void load(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {

    }

    public void save(ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {

    }
}
