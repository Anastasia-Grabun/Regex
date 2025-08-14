import org.example.LogEntry;
import org.example.Tasks;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TasksTest {

    private final Tasks validator = new Tasks();

    //task1
    @Test
    void validPhoneNumber_ShouldReturnTrue() {
        String phone = "+7(123)456-78-90";
        assertTrue(validator.isValidPhoneNumber(phone));
    }

    @Test
    void invalidPhoneNumber_ShouldReturnFalse() {
        String phone1 = "+7123456-78-90";
        String phone2 = "+8(123)456-78-90";

        assertFalse(validator.isValidPhoneNumber(phone1));
        assertFalse(validator.isValidPhoneNumber(phone2));
    }

    @Test
    void nullPhoneNumber_ShouldReturnFalse() {
        assertFalse(validator.isValidPhoneNumber(null));
    }

    @Test
    void emptyPhoneNumber_ShouldReturnFalse() {
        assertFalse(validator.isValidPhoneNumber(""));
    }

    //task2
    @Test
    void validEmail_ShouldReturnTrue() {
        String email = "user@example.com";
        assertTrue(validator.isValidEmail(email));
    }

    @Test
    void invalidEmail_ShouldReturnFalse() {
        String email1 = "user@";
        String email2 = "@domain.com";

        assertFalse(validator.isValidPhoneNumber(email1));
        assertFalse(validator.isValidPhoneNumber(email2));
    }

    @Test
    void nullEmail_ShouldReturnFalse() {
        assertFalse(validator.isValidEmail(null));
    }

    @Test
    void emptyEmail_ShouldReturnFalse() {
        assertFalse(validator.isValidPhoneNumber(""));
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

    //task7
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

    //task8
    @Test
    void testParseLogFile() {
        String log = "192.168.1.1 - - [10/Oct/2023:13:55:36 +0000] \"GET /index.html HTTP/1.1\" 200 1234\n" +
                "10.0.0.1 - - [10/Oct/2023:13:55:37 +0000] \"POST /api/data HTTP/1.1\" 404 567";

        List<LogEntry> entries = validator.parseLogFile(log);

        assertEquals(2, entries.size());

        LogEntry first = entries.get(0);
        assertEquals("192.168.1.1", first.ip);
        assertEquals("10/Oct/2023:13:55:36 +0000", first.timestamp);
        assertEquals(200, first.statusCode);

        LogEntry second = entries.get(1);
        assertEquals("10.0.0.1", second.ip);
        assertEquals("10/Oct/2023:13:55:37 +0000", second.timestamp);
        assertEquals(404, second.statusCode);
    }

    @Test
    void testParseEmptyLog() {
        String log = "";

        List<LogEntry> entries = validator.parseLogFile(log);

        assertTrue(entries.isEmpty(), "List should be empty for an empty log");
    }

    @Test
    void testParseMalformedLog() {
        String log = "malformed log line\n" +
                "123.123.123.123 - - [11/Oct/2023:10:00:00 +0000] \"GET / HTTP/1.1\" 500 1000";

        List<LogEntry> entries = validator.parseLogFile(log);

        assertEquals(1, entries.size());
        LogEntry entry = entries.get(0);
        assertEquals("123.123.123.123", entry.ip);
        assertEquals("11/Oct/2023:10:00:00 +0000", entry.timestamp);
        assertEquals(500, entry.statusCode);
    }

    //task9
    @Test
    public void testValidNestedTags() {
        String html = "<div><p>Text</p></div>";
        assertTrue(validator.isValidHtml(html));
    }

    @Test
    public void testInvalidNestedTags() {
        String html = "<div><p>Text</div></p>";
        assertFalse(validator.isValidHtml(html));
    }

    @Test
    public void testSelfClosingTag() {
        String html = "<img src='test.jpg' />";
        assertTrue(validator.isValidHtml(html));
    }

    @Test
    public void testMissingClosingTag() {
        String html = "<div><span>Text</div>";
        assertFalse(validator.isValidHtml(html));
    }

    @Test
    public void testEmptyString() {
        String html = "";
        assertTrue(validator.isValidHtml(html));
    }

    @Test
    public void testOnlyTextNoTags() {
        String html = "Hello world!";
        assertTrue(validator.isValidHtml(html));
    }

    @Test
    public void testMultipleNestedTags() {
        String html = "<div><span><p>Text</p></span></div>";
        assertTrue(validator.isValidHtml(html));
    }

    @Test
    public void testIncorrectClosingTagName() {
        String html = "<div><span>Text</div></span>";
        assertFalse(validator.isValidHtml(html));
    }

    @Test
    public void testMultipleSelfClosingTags() {
        String html = "<br/><img src='x.jpg'/><hr/>";
        assertTrue(validator.isValidHtml(html));
    }

}

