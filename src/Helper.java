import java.util.ArrayList;

public class Helper {
    public static Account findAccountByNumber(int accountNumber, ArrayList<Account> accounts) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static Client findClientById(String ownerId, ArrayList<Client> clients) {
        for (Client client : clients) {
            if (String.valueOf(client.getId()).equals(ownerId)) {
                return client;
            }
        }
        return null;
    }

    public static CreditCard findCardByNumber(String cardNumber, ArrayList<CreditCard> creditCards) {
        for (CreditCard card : creditCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

}
