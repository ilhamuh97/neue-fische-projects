import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class PasswordValidator {
    public static final Set<String> COMMON_PASSWORDS =
            Set.of("password", "Passwort1", "12345678", "Aa345678");

    public static boolean hasMinLength(@NotNull String password, int min) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }

        if (password.isBlank()) {
            System.out.println("Validation Failed: Password is blank.");
            return false;
        }

        boolean isValid = password.length() >= min;
        if (!isValid) {
            System.out.println("Validation Failed: Password length is " + password.length() + " (Minimum required: " + min + ")");
        }
        return isValid;
    }

    public static boolean containsDigit(@NotNull String password) {
        for (char character : password.toCharArray()) {
            if (Character.isDigit(character)) {
                return true;
            }
        }

        System.out.println("Validation Failed: Password must contain at least one digit.");
        return false;
    }

    public static boolean containsUpperAndLower(@NotNull String password) {
        boolean containsUpper = false;
        boolean containsLower = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) containsLower = true;
            if (Character.isUpperCase(c)) containsUpper = true;

            if (containsLower && containsUpper) return true;
        }

        System.out.println("Validation Failed: Password must contain both uppercase and lowercase letters.");
        return false;
    }

    public static boolean isCommonPassword(@NotNull String password) {
        String normalizedPassword = password.trim().toLowerCase();

        for (String commonPassword : COMMON_PASSWORDS) {
            if (normalizedPassword.contains(commonPassword.toLowerCase())) {
                System.out.println("Validation Failed: Password is too common or contains a forbidden sequence: " + commonPassword);
                return true;
            }
        }
        return false;
    }

    public static boolean containsSpecialChar(@NotNull String password, String allowed) {
        for (int i = 0; i < password.length(); i++) {
            // Checking if the character exists within the 'allowed' string
            if (allowed.indexOf(password.charAt(i)) != -1) {
                return true;
            }
        }

        System.out.println("Validation Failed: Password must contain at least one special character from: " + allowed);
        return false;
    }

    // Optionale Gesamtsicht:
    public static boolean isValid(String password) {
        if (!hasMinLength(password, 8)) return false;
        if (!containsDigit(password)) return false;
        if (!containsUpperAndLower(password)) return false;
        if (!containsSpecialChar(password, "!@#$%^&*()-_+=?.,;:")) return false;
        if (isCommonPassword(password)) return false;

        return true;
    }

    public static String generateSecurePassword(int length, String allowedSpecials) {
        if(length < 8) {
            throw new IllegalArgumentException("Length must be at least 8");
        }

        StringBuilder sb = new StringBuilder(length);

        sb.append(getRandomLowerCase());
        sb.append(getRandomUpperCase());
        sb.append(getRandomDigits());
        sb.append(getRandomSpecialCase(allowedSpecials));

        for (int i = 0; i < length-4; i++) {
            int randomRequirementIndex = (int) (Math.random() * 4);
            int randomPasswordIndex = (int) (Math.random() * sb.length());
            sb.insert(randomPasswordIndex, getRandomPassword(randomRequirementIndex, allowedSpecials));
        }

        return sb.toString();
    }

    private static String getRandomPassword(int index, String allowed) {
        return switch (index) {
            case 0 -> getRandomLowerCase();
            case 1 -> getRandomUpperCase();
            case 2 -> getRandomDigits();
            case 3 -> getRandomSpecialCase(allowed);
            default -> "";
        };
    }

    private static String getRandomLowerCase() {
        String lowercases = "abcdefghijklmnopqrstuvwxyz";
        int index = (int) (Math.random() * lowercases.length());

        return lowercases.substring(index, index+1);
    }

    private static String getRandomUpperCase() {
        String uppercases = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int index = (int) (Math.random() * uppercases.length());

        return uppercases.substring(index, index+1);
    }

    private static String getRandomDigits() {
        String uppercases = "1234567890";
        int index = (int) (Math.random() * uppercases.length());

        return uppercases.substring(index, index+1);
    }

    private static String getRandomSpecialCase(String allowed) {
        int index = (int) (Math.random() * allowed.length());

        return allowed.substring(index, index+1);
    }
}
