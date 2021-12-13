package com.bridgelabz.addressbook.exceptionhandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorObject object = new ErrorObject();
        object.setTimestamp(new Date());
        object.setStatus(status.value());
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach((error -> {
            errors.add(error.getDefaultMessage());
        }));
        object.setError(errors);
        return new ResponseEntity<>(object, HttpStatus.BAD_REQUEST);
    }
}
