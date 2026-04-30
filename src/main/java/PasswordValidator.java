import org.jetbrains.annotations.NotNull;

public final class PasswordValidator {
    public static boolean hasMinLength(@NotNull String password, int min) {
        if(password.isBlank()){
            return false;
        }

        return password.length() >= min;
    }

    public static boolean containsDigit(@NotNull String password) {
        final String digits = "1234567890";
        for(String letter: password.split("")) {
            if(digits.contains(letter)) {
                return true;
            }
        }

        return false;
    }

    public static boolean containsUpperAndLower(String password) {
        boolean containsUpper = false;
        boolean containsLower = false;
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c)) containsLower = true;
            if (Character.isUpperCase(c)) containsUpper = true;

            if (containsLower && containsUpper) return true;
        }

        return false;
    }

    public static boolean isCommonPassword(String password) // kleine interne Liste
    {
        return false;
    }

    // Bonus:
    public static boolean containsSpecialChar(String password, String allowed) {
        return false;
    }

    // Optionale Gesamtsicht:
    public static boolean isValid(String password) // nutzt die obenstehenden Checks
    {
        return false;
    }
}
