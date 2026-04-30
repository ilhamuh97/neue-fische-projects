import org.jetbrains.annotations.NotNull;

import java.util.Set;

public final class PasswordValidator {
    public static final Set<String> COMMON_PASSWORDS =
            Set.of("password", "Passwort1", "12345678", "Aa345678");

    public static boolean hasMinLength(@NotNull String password, int min) {
        if (password == null) {
            throw new IllegalArgumentException("Password must not be null");
        }

        if(password.isBlank()){
            return false;
        }

        return password.length() >= min;
    }

    public static boolean containsDigit(@NotNull String password) {
        for(char character: password.toCharArray()) {
            if(Character.isDigit(character)) {
                return true;
            }
        }

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

        return false;
    }

    public static boolean isCommonPassword(@NotNull String password) {
        String normalizedPassword = password.trim().toLowerCase();

        for (String commonPassword : COMMON_PASSWORDS) {
            if (normalizedPassword.contains(commonPassword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    // Bonus:
    public static boolean containsSpecialChar(String password, String allowed) {
        return false;
    }

    // Optionale Gesamtsicht:
    public static boolean isValid(String password) {
        if (!hasMinLength(password, 8)) return false;
        if (!containsDigit(password)) return false;
        if (!containsUpperAndLower(password)) return false;
        if (isCommonPassword(password)) return false;

        return true;
    }
}
