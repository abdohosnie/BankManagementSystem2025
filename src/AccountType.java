public enum AccountType {
    SAVING("Saving", 0, 0.05),
    CURRENT("Current", 3000, 0);

    private final String description;
    private final double minBalance;
    private final double interestRate;

    AccountType(String description, double minBalance, double interestRate) {
        this.description = description;
        this.minBalance = minBalance;
        this.interestRate = interestRate;
    }

    public String getDescription() {
        return description;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
