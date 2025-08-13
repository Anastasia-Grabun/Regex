import org.example.Tasks;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TasksTest {

    private final Tasks validator = new Tasks();

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
    void nullOrEmptyPhoneNumber_ShouldReturnFalse() {
        Assertions.assertFalse(validator.isValidPhoneNumber(null));
        Assertions.assertFalse(validator.isValidPhoneNumber(""));
    }


}

