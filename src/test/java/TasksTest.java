import org.example.Tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TasksTest {

    private final Tasks validator = new Tasks();

    //task1
    @Test
    void validPhoneNumber_ShouldReturnTrue() {
        String phone = "+7(123)456-78-90";
        Assertions.assertTrue(validator.isValidPhoneNumber(phone));
    }

    @Test
    void invalidPhoneNumber_ShouldReturnFalse() {
        String phone1 = "+7123456-78-90";
        String phone2 = "+8(123)456-78-90";

        Assertions.assertFalse(validator.isValidPhoneNumber(phone1));
        Assertions.assertFalse(validator.isValidPhoneNumber(phone2));
    }

    @Test
    void nullPhoneNumber_ShouldReturnFalse() {
        Assertions.assertFalse(validator.isValidPhoneNumber(null));
    }

    @Test
    void emptyPhoneNumber_ShouldReturnFalse() {
        Assertions.assertFalse(validator.isValidPhoneNumber(""));
    }

    //task2
    @Test
    void validEmail_ShouldReturnTrue() {
        String email = "user@example.com";
        Assertions.assertTrue(validator.isValidEmail(email));
    }

    @Test
    void invalidEmail_ShouldReturnFalse() {
        String email1 = "user@";
        String email2 = "@domain.com";

        Assertions.assertFalse(validator.isValidPhoneNumber(email1));
        Assertions.assertFalse(validator.isValidPhoneNumber(email2));
    }

    @Test
    void nullEmail_ShouldReturnFalse() {
        Assertions.assertFalse(validator.isValidEmail(null));
    }

    @Test
    void emptyEmail_ShouldReturnFalse() {
        Assertions.assertFalse(validator.isValidPhoneNumber(""));
    }

    //task3
    @Test
    void sumNumbers_MixedTextWithNumbers_ShouldReturnCorrectSum() {
        String text = "У меня есть 5 яблок и 10 груш";
        int result = validator.extractAndSumNumbers(text);

        assertEquals(15, result);
    }

    @Test
    void sumNumbers_NoNumbers_ShouldReturnZero() {
        String text = "Нет чисел в тексте";
        int result = validator.extractAndSumNumbers(text);

        assertEquals(0, result);
    }

    @Test
    void sumNumbers_NumbersInterleavedWithLetters_ShouldReturnCorrectSum() {
        String text = "123abc456def789";
        int result = validator.extractAndSumNumbers(text);

        assertEquals(1368, result);
    }

    @Test
    void sumNumbers_NullString_ShouldReturnZero() {
        String text = null;
        int result = validator.extractAndSumNumbers(text);
        assertEquals(0, result);
    }

    @Test
    void sumNumbers_EmptyString_ShouldReturnZero() {
        String text = "";
        int result = validator.extractAndSumNumbers(text);
        assertEquals(0, result);
    }

    //task4
    @Test
    void shouldNormalizeSpacesWithLeadingAndTrailing() {
        String input = "  hello    world  ";
        String expected = "hello world";
        assertEquals(expected, validator.normalizeSpaces(input));
    }

    @Test
    void shouldNormalizeMultipleSpacesInside() {
        String input = "multiple   spaces   here";
        String expected = "multiple spaces here";

        assertEquals(expected, validator.normalizeSpaces(input));
    }

    @Test
    void shouldReturnNullWhenInputIsNull() {
        assertNull(validator.normalizeSpaces(null));
    }

    @Test
    void shouldReturnEmptyWhenInputIsEmpty() {
        assertEquals("", validator.normalizeSpaces(""));
    }

    //task5
    @Test
    void shouldReturnTrueWhenPasswordIsStrong(){
        String password = "MyPass123!";

        assertNotEquals(true, validator.isStrongPassword(password));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNotStrong(){
        String password1 = "weakpass";
        String password2 = "NODIGITS!";

        assertEquals(false, validator.isStrongPassword(password1));
        assertEquals(false, validator.isStrongPassword(password2));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsNull(){
        assertEquals(false, validator.isStrongPassword(null));
    }

    @Test
    void shouldReturnFalseWhenPasswordIsEmpty(){
        assertEquals(false, validator.isStrongPassword(""));
    }

    //task6
    @Test
    void shouldReturnNull_CardNumberIsNull() {
        assertNull(validator.formatCardNumber(null));
    }

    @Test
    void shouldReturnNull_CardNumberIsEmpty() {
        assertNull(validator.formatCardNumber(""));
    }

    @Test
    void shouldReturnNull_IncorrectLengthTooShort() {
        assertNull(validator.formatCardNumber("1234 56"));
    }

    @Test
    void shouldReturnNull_IncorrectLengthTooLong() {
        assertNull(validator.formatCardNumber("12345678901234567890"));
    }

    @Test
    void testValidContinuousDigits() {
        assertEquals("1234 5678 9012 3456",
                validator.formatCardNumber("1234567890123456"));
    }

    @Test
    void testValidWithDashes() {
        assertEquals("1234 5678 9012 3456",
                validator.formatCardNumber("1234-5678-9012-3456"));
    }

    @Test
    void testValidWithSpacesAndDashesMixed() {
        assertEquals("1234 5678 9012 3456",
                validator.formatCardNumber("1234 - 5678 - 9012 - 3456"));
    }

}

