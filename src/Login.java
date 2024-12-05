import java.util.ArrayList;
import java.util.Scanner;

public class Login {
    public Login() {
    }

    public static int login(Scanner scanner, ArrayList<Client> clients, ArrayList<Employee> emps) {
        System.out.println("Username or ID: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String s = scanner.nextLine();

        try {
            int i = Integer.parseInt(username);
            if (s.equals("admin") && i == 0) {
                return 0;
            } else {
                if (i / 100000 == 1) {
                    for(Client client : clients) {
                        if (i == client.getId() && s.equals(client.getPassword())) {
                            return client.getId();
                        }
                    }
                } else if (i / 100000 == 2) {
                    for(Employee emp : emps) {
                        if (i == emp.getId() && s.equals(emp.getPassword())) {
                            return emp.getId();
                        }
                    }
                }

                return -1;
            }
        } catch (NumberFormatException var9) {
            if (s.equals("admin") && username.equals("admin")) {
                return 0;
            } else {
                for(Employee emp : emps) {
                    if (s.equals(emp.getPassword()) && username.equals(emp.getUsername())) {
                        return emp.getId();
                    }
                }

                for(Client client : clients) {
                    if (s.equals(client.getPassword()) && username.equals(client.getUsername())) {
                        return client.getId();
                    }
                }

                return -1;
            }
        }
    }
}
