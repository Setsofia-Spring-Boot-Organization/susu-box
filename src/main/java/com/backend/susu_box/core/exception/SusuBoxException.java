package com.backend.susu_box.core.exception;

public class SusuBoxException extends RuntimeException {

    SusuBoxExceptionMessages boxExceptionMessage;

    public SusuBoxException(SusuBoxExceptionMessages message) {
        super(message.label);
        this.boxExceptionMessage = message;
    }
}
