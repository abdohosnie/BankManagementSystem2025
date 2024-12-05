public class CreditCard {
    private final String cardNumber;
    private final double creditLimit = 20000.0;  // Fixed credit limit of 20,000 LE
    private double balance;  // Amount spent so far
    private boolean isActive = true;  // Flag to track if the card is active
    private final Client owner;  // The client who owns this card

    public CreditCard(String cardNumber, Client owner) {
        this.cardNumber = cardNumber;
        this.owner = owner;
        this.balance = 0.0;  // No balance initially
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void disableCard() {
        this.isActive = false;
        System.out.println("Credit card " + cardNumber + " has been disabled.");
    }

    public boolean pay(double amount) {
        if (!isActive) {
            System.out.println("This card is disabled.");
            return false;
        }
        if (amount <= (creditLimit - balance)) {
            balance += amount;
            owner.addLoyaltyPoints(amount);  // Add loyalty points
            System.out.println("Payment of " + amount + " LE successful with card " + cardNumber);
            return true;
        } else {
            System.out.println("Insufficient credit limit.");
            return false;
        }
    }

    public double getAvailableCredit() {
        return creditLimit - balance;
    }
}
