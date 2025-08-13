import org.example.Tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
}

