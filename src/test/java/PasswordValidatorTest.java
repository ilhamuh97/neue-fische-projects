import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    final static int MIN_LENGTH = 8;

    @ParameterizedTest
    @CsvSource({
            "test123, false",
            "test1234, true",
            "test12345, true"
    })
    void hasMinLength_shouldReturnTrueOrFalse_whenCalledWithLength7_8_9(String password, boolean expected) {
        assertEquals(expected, PasswordValidator.hasMinLength(password, MIN_LENGTH));
    }

    @Test
    void hasMinLength_shouldReturnFalse_whenCalledWithEmptyString() {
        assertFalse(PasswordValidator.hasMinLength("", MIN_LENGTH));
    }

    @ParameterizedTest
    @CsvSource({
            "test, false",
            "test1, true",
            "test123, true",
            "12345678, true"
    })
    void containsDigit_shouldReturnTrueOrFalse_whenCalledWithContainOrNotContainDigit(String password, boolean expected){
        assertEquals(expected, PasswordValidator.containsDigit(password));
    }
}