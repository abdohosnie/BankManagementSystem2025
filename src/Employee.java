import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {
    private AuthorizationState authorization;
    private String address;
    private String position;
    private String graduatedCollege;
    private int yearOfGraduation;
    private String collegeGrade;
    public static int nextId = 0;

    public AuthorizationState getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(AuthorizationState authorization) {
        this.authorization = authorization;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGraduatedCollege() {
        return this.graduatedCollege;
    }

    public void setGraduatedCollege(String graduatedCollege) {
        this.graduatedCollege = graduatedCollege;
    }

    public int getYearOfGraduation() {
        return this.yearOfGraduation;
    }

    public void setYearOfGraduation(int yearOfGraduation) {
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getCollegeGrade() {
        return this.collegeGrade;
    }

    public void setCollegeGrade(String collegeGrade) {
        this.collegeGrade = collegeGrade;
    }

    public String details() {
        String str = super.details();
        str += "Authorization: " + this.getAuthorization() + "\n" +
                "Address: " + this.getAddress() + "\n" +
                "Position: " + this.getPosition() + "\n" +
                "Graduated college: " + this.getGraduatedCollege() + "\n" +
                "Year of graduation: " + this.getYearOfGraduation() + "\n" +
                "College grade: " + this.collegeGrade + "\n###################\n";
        return str;
    }

    public Employee(String firstName, String lastName, String username, String password, String phoneNumber, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(200000 + nextId);
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.collegeGrade = collegeGrade;
        ++nextId;
    }

    public Employee(int id, String firstName, String lastName, String username, String password, String phoneNumber, AuthorizationState authorization, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(id);
        this.authorization = authorization;
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.collegeGrade = collegeGrade;
        if (nextId < this.getId() - 199999) {
            nextId = this.getId() - 199999;
        }
    }

    public void menu(Scanner scanner, ArrayList<Employee> employees, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {

    }

    public void editPersonalInfo(Scanner scanner) {

    }

    public void addNewClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> employees, ArrayList<Account> accounts) {

    }

    public void addNewClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts) {

    }

    public void editClientProfile(Scanner scanner, ArrayList<Client> clients) {

    }

    public void editClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts) {

    }

    public void findClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts) {

    }

    public void deleteClientAccount(Scanner scanner, ArrayList<Account> accounts) {

    }

    public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accounts, ArrayList<Transaction> transactions) {

    }
}
