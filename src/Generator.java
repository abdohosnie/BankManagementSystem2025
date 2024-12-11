import java.util.Random;

public class Generator {

    // Generate a valid 16-digit card number
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
