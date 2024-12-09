public class CreditCard {
    private final String cardNumber;
    private static final double creditLimit = 20000.0;
    private CardState cardState;
    private final Account linkedAccount;
    private final Client owner;
    private double availableCredits;

    public double getAvailableCredits() {
        return availableCredits;
    }

    public CreditCard(String cardNumber, Account linkedAccount, Client owner) {
        this.cardNumber = cardNumber;
        this.linkedAccount = linkedAccount;
        this.cardState = CardState.INACTIVE;
        this.owner = owner;
    }

    public CreditCard(String cardNumber, CardState cardState, Account linkedAccount, Client owner, double availableCredits) {
        this.cardNumber = cardNumber;
        this.cardState = cardState;
        this.linkedAccount = linkedAccount;
        this.owner = owner;
        this.availableCredits = availableCredits;
    }

    public Client getOwner() {
        return owner;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardState getCardState() {
        return cardState;
    }

    public Account getLinkedAccount() {
        return linkedAccount;
    }

    public int getAccountNumber() {
        return linkedAccount.getAccountNumber();
    }

    public CardState isActive() {
        return cardState;
    }

    public void activateCard() {
        this.cardState = CardState.ACTIVE;
    }

    public void deactivateCard() {
        this.cardState = CardState.INACTIVE;
    }

    public double getAvailableCredit() {
        return creditLimit - linkedAccount.getBalance();
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
        double availableCredit = getAvailableCredit();
        if (amount <= availableCredit) {
            linkedAccount.setBalance(linkedAccount.getBalance() - amount);
            owner.addLoyaltyPoints(amount);
            System.out.println("Available credit remaining: " + getAvailableCredit() + " LE.");
            return true;
        } else {
            System.out.println("Payment failed. Insufficient credit limit.");
            System.out.println("Available credit: " + getAvailableCredit() + " LE.");
            return false;
        }
    }
}
