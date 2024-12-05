import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends User {
    private String authorization = "Unauthorized";
    private String address;
    private String position;
    private String graduatedCollege;
    private int yearOfGraduation;
    private String collegeGrade;
    public static int nextId = 0;

    public String getAuthorization() {
        return this.authorization;
    }

    public void setAuthorization(String authorization) {
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
        str = str + "Authorization: " + this.getAuthorization() + "\n";
        str = str + "Address: " + this.getAddress() + "\n";
        str = str + "Position: " + this.getPosition() + "\n";
        str = str + "Graduated college: " + this.getGraduatedCollege() + "\n";
        str = str + "Year of graduation: " + this.getYearOfGraduation() + "\n";
        str = str + "College grade: " + this.collegeGrade + "\n###################\n";
        return str;
    }

    public Employee(String firstName, String lastName, String username, String password, int phoneNumber, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
        super(firstName, lastName, username, password, phoneNumber);
        this.setId(200000 + nextId);
        this.address = address;
        this.position = position;
        this.graduatedCollege = graduatedCollege;
        this.yearOfGraduation = yearOfGraduation;
        this.collegeGrade = collegeGrade;
        ++nextId;
    }

    public Employee(int id, String firstName, String lastName, String username, String password, int phoneNumber, String authorization, String address, String position, String graduatedCollege, int yearOfGraduation, String collegeGrade) {
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
    public void menu(Scanner scanner, ArrayList<Employee> emps, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions){

    }

    public void editPersonalInfo(Scanner scanner){

    }

    public void addNewClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps, ArrayList<Account> accs) {

    }

    public void addNewClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs){

    }

    public void editClientProfile(Scanner scanner, ArrayList<Client> clients) {

    }

    public void editClientAccount(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs) {

    }

    public void findClient(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs){

    }

    public void deleteClientAccount(Scanner scanner, ArrayList<Account> accs) {

    }

    public void makeNewTransaction(Scanner scanner, ArrayList<Client> clients, ArrayList<Account> accs, ArrayList<Transaction> transactions) {

    }
}
