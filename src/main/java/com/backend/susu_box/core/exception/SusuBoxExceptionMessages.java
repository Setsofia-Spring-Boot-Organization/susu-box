package com.backend.susu_box.core.exception;

public enum SusuBoxExceptionMessages {

    EMAIL_ALREADY_EXISTS("Email already exist"),
    ERROR_CREATING_USER("User could not be created. Please try again"),
    BAD_CREDENTIALS("The submitted credentials are invalid"),
    NO_EMPTY_FIELD_ALLOWED("Some fields are empty or invalid. Correct them and try again");

    final String label;
    SusuBoxExceptionMessages(String label) {
        this.label = label;
    }
}
