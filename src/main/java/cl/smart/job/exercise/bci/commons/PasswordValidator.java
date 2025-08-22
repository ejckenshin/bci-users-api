package cl.smart.job.exercise.bci.commons;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    private final Pattern passwordPattern;

    public PasswordValidator(@Value(RegularExpression.KEY_PASSWORD) String regexPassword) {
        this.passwordPattern = Pattern.compile(regexPassword);
    }


    public boolean isValid(String password) {
        return passwordPattern.matcher(password).matches();
    }
}
