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

    @Test
    void hasMinLength_shouldReturnError_whenCalledWithNull() {
        assertThrows(IllegalArgumentException.class, () -> PasswordValidator.hasMinLength(null, MIN_LENGTH));
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

    @ParameterizedTest
    @CsvSource({
            "HELLOWORLD1, false",
            "helloworld1, false",
            "HelloWorld2, true",
            "h, false"
    })
    void containsUpperAndLower_shouldReturnTrueOrFalse_whenCalledWithUpperLowerCase(String password, boolean expected){
        assertEquals(expected, PasswordValidator.containsUpperAndLower(password));
    }

    @ParameterizedTest
    @CsvSource({
            "testPassworD, true",
            "passwort1Test123, true",
            "isNotCommon, false",
    })
    void isCommonPassword_shouldReturnTrueOrFalse_whenCalledWithCommonAndNotCommon(String password, boolean expected) {
        assertEquals(expected, PasswordValidator.isCommonPassword(password));
    }

    @ParameterizedTest
    @CsvSource({
            "Abc1def, false",
            "Abc1defg, true",
            "Abcdefgh, false",
            "abcdefg1, false",
            "ABCDEFG1, false",
            "Passwort1, false",
            "Abcdef1g, true",
    })
    void isValid_shouldReturnTrueOrFalse_whenCalledWithValidAndNotValid(String password, boolean expected) {
        assertEquals(expected, PasswordValidator.isValid(password));
    }
}