package ch.zhaw.iwi.devops.demo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PasswordValidatorTest {

    @Test
    public void testValidPassword() {
        PasswordValidator validator = new PasswordValidator();
        assertTrue(validator.isValid("Abcdef1!"));
    }

    @Test
    public void testTooShortPassword() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("Ab1!"));
    }

    @Test
    public void testPasswordWithoutUppercase() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("abcdefg1!"));
    }

    @Test
    public void testPasswordWithoutNumber() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("Abcdefgh!"));
    }

    @Test
    public void testPasswordWithoutSpecialChar() {
        PasswordValidator validator = new PasswordValidator();
        assertFalse(validator.isValid("Abcdefg1"));
    }

@Test
public void testPasswordWithoutLowercase() {
    PasswordValidator validator = new PasswordValidator();
    assertFalse(validator.isValid("ABCDEFG1!"));
}


}