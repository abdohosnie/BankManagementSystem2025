import java.util.Random;

public class Generator {

    // Generate a valid 16-digit card number
    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        // Generate the first 15 digits randomly (card numbers are typically 16 digits long)
        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10)); // Append a random digit between 0 and 9
        }

        // Calculate the checksum using the Luhn algorithm
        int checksum = calculateLuhnChecksum(cardNumber.toString());
        cardNumber.append(checksum); // Add the checksum digit as the 16th digit

        return cardNumber.toString();
    }

    // Calculate the Luhn checksum for the first 15 digits
    private static int calculateLuhnChecksum(String number) {
        int sum = 0;
        boolean doubleDigit = true; // Start doubling from the second-to-last digit

        // Traverse the number from right to left
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (doubleDigit) {
                digit *= 2; // Double the digit
                if (digit > 9) {
                    digit -= 9; // Subtract 9 if the result is greater than 9
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit; // Toggle doubleDigit flag
        }

        // Return the digit needed to make the total divisible by 10
        return (10 - (sum % 10)) % 10;
    }

    public class AccountNumberGenerator {
        private static final Random random = new Random();

        public static int generateAccountNumber() {
            return 10000000 + random.nextInt(90000000); // Generates an 8-digit number
        }
    }

}
