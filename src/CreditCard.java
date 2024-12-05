public class CreditCard {
    private final String cardNumber;
    private final double creditLimit = 20000.0;
    private double balance;  // Amount spent so far
    private CardState cardState;
    private final Client owner;
    private final int accountNumber;

    public CreditCard(String cardNumber, int accountNumber, CardState cardState, Client owner) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.cardState = CardState.INACTIVE;
        this.owner = owner;
        this.balance = 0.0;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public CardState isActive() {
        return cardState;
    }

    public void activateCard() {
        this.cardState = CardState.ACTIVE;
        System.out.println("Credit card " + cardNumber + " has been activated successfully!");
    }

    public void deactivateCard() {
        this.cardState = CardState.INACTIVE;
        System.out.println("Credit card " + cardNumber + " has been deactivated successfully!");
    }

    public boolean pay(double amount) {
        if (cardState == CardState.INACTIVE) {
            System.out.println("This card is inactive. Please activate it before making payments.");
            return false;
        }
        if (amount <= 0) {
            System.out.println("Invalid amount. Payment amount must be greater than zero.");
            return false;
        }
        if (amount <= (creditLimit - balance)) {
            balance += amount;
            owner.addLoyaltyPoints(amount);
            System.out.println("Payment of " + amount + " LE successful using card " + cardNumber + ".");
            System.out.println("Available credit remaining: " + getAvailableCredit() + " LE.");
            return true;
        } else {
            System.out.println("Payment failed. Insufficient credit limit.");
            System.out.println("Available credit: " + getAvailableCredit() + " LE.");
            return false;
        }
    }

    public double getAvailableCredit() {
        return creditLimit - balance;
    }
}
