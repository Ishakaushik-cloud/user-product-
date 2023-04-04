package com.example.postgresql.validations;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class EmailValidator implements ConstraintValidator<EmailConstraint, String>{
    @Override
    public void initialize(EmailConstraint email) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext cxt) {
        return emailField!=null && emailField.matches("^(.+)@(\\S+)\\.(\\S+)$");
    }
}
