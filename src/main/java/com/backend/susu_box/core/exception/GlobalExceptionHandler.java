package com.backend.susu_box.core.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @ExceptionHandler(SusuBoxException.class)
    public ResponseEntity<ProblemDetail> handleExceptions(SusuBoxException exception) {

        HttpStatus status = HttpStatus.OK;
        SusuBoxExceptionMessages message = exception.boxExceptionMessage;

        switch (message) {
            case EMAIL_ALREADY_EXISTS,
                 NO_EMPTY_FIELD_ALLOWED -> status = HttpStatus.BAD_REQUEST;
            case ERROR_CREATING_USER,
                 BAD_CREDENTIALS,
                 UPDATE_UNSUCCESSFUL -> status = HttpStatus.CONFLICT;
            case USER_NOT_FOUND,
                 NOT_FOUND -> status = HttpStatus.NOT_FOUND;
            case UNAUTHORIZED_USER -> status = HttpStatus.UNAUTHORIZED;
        }

        return ResponseEntity
                .status(status)
                .body(ProblemDetail.forStatusAndDetail(status, exception.getMessage()));
    }
}
