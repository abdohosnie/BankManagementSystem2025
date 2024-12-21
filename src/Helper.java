import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class Helper {

    public static Account findAccountByNumber(int accountNumber, ArrayList<Account> accounts) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        System.out.println("Account was not found!");
        return null;
    }

    public static Client findClientById(String ownerId, ArrayList<Client> clients) {
        for (Client client : clients) {
            if (String.valueOf(client.getId()).equals(ownerId)) {
                return client;
            }
        }
        System.out.println("Client was not found!");
        return null;
    }

    public static Client findClientById(int ownerId, ArrayList<Client> clients) {
        for (Client client : clients) {
            if (client.getId() == ownerId) {
                return client;
            }
        }
        System.out.println("Client was not found!");
        return null;
    }

    public static CreditCard findCardByNumber(String cardNumber, ArrayList<CreditCard> creditCards) {
        for (CreditCard card : creditCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        System.out.println("Card was not found!");
        return null;
    }

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }

        int checksum = calculateLuhnChecksum(cardNumber.toString());
        cardNumber.append(checksum);

        return cardNumber.toString();
    }

    private static int calculateLuhnChecksum(String number) {
        int sum = 0;
        boolean doubleDigit = true;

        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return (10 - (sum % 10)) % 10;
    }
}
