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


    public void clear(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {
        clients.clear();
        emps.clear();
        accs.clear();
        transactions.clear();
    }

    public void load(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {

    }

    public void save(ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs, ArrayList<Transaction> transactions) {

    }
}
