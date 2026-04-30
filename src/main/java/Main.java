import java.util.Arrays;
import java.util.Scanner;

class Main {
     static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password;
        boolean isValid;

        do {
            System.out.print("Enter a password: ");
            password = scanner.nextLine();

            isValid = PasswordValidator.isValid(password);

            if (!isValid) {
                System.out.println("Please try again.\n");
            }

        } while (!isValid);

        System.out.println("Success! '" + password + "' is a secure password.");
        scanner.close();
    }
}
