import java.security.SecureRandom;

public class EnhancedRandomPasswordGenerator {
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()_+";

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int passwordLength = 16;
        int minLowercase = 3;
        int minUppercase = 2;
        int minDigits = 2;
        int minSpecialChars = 1;

        String generatedPassword = generateRandomPassword(passwordLength, minLowercase, minUppercase, minDigits, minSpecialChars);
        System.out.println("Generated Password: " + generatedPassword);
    }

    public static String generateRandomPassword(int length, int minLowercase, int minUppercase, int minDigits, int minSpecialChars) {
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < minLowercase; i++) {
            password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
        }

        for (int i = 0; i < minUppercase; i++) {
            password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
        }

        for (int i = 0; i < minDigits; i++) {
            password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        }

        for (int i = 0; i < minSpecialChars; i++) {
            password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        }

        int remainingLength = length - (minLowercase + minUppercase + minDigits + minSpecialChars);
        for (int i = 0; i < remainingLength; i++) {
            int randomCategory = random.nextInt(4);
            switch (randomCategory) {
                case 0:
                    password.append(CHAR_LOWER.charAt(random.nextInt(CHAR_LOWER.length())));
                    break;
                case 1:
                    password.append(CHAR_UPPER.charAt(random.nextInt(CHAR_UPPER.length())));
                    break;
                case 2:
                    password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
                    break;
                case 3:
                    password.append(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
                    break;
            }
        }

        shuffleString(password); // Shuffling the password characters for added randomness
        return password.toString();
    }

    public static void shuffleString(StringBuilder sb) {
        for (int i = sb.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
    }
}
