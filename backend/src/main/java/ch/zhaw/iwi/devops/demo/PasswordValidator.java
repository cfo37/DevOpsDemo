package ch.zhaw.iwi.devops.demo;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final String SPECIAL_CHARS = "!@#$%^&*";

    public boolean isValid(String password) {
        return hasMinLength(password)
            && hasUpperCase(password)
            && hasDigit(password)
            && hasSpecialChar(password);
    }

    private boolean hasMinLength(String password) {
        return password.length() >= MIN_LENGTH;
    }

    private boolean hasUpperCase(String password) {
        return password.chars().anyMatch(Character::isUpperCase);
    }

    private boolean hasDigit(String password) {
        return password.chars().anyMatch(Character::isDigit);
    }

    private boolean hasSpecialChar(String password) {
        return password.chars().anyMatch(c -> SPECIAL_CHARS.indexOf(c) >= 0);
    }
}