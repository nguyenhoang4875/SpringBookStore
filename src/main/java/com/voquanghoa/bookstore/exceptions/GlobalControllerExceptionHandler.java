package com.voquanghoa.bookstore.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
class ErrorModel{
    private String message;
    private String path;
}

@ControllerAdvice
@ConditionalOnProperty(prefix = "app", name = "disable-default-exception-handling")
class GlobalControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception ex, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(ex.getMessage(), request.getRequest().getRequestURI()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorModel> handleException(BadCredentialsException ex, ServletWebRequest request) {
        return new ResponseEntity<>(new ErrorModel(ex.getMessage(), request.getRequest().getRequestURI()), HttpStatus.BAD_REQUEST);
    }

}
