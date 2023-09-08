package com.ags.backend.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@ControllerAdvice(basePackages = "com.ags.backend.controller")
public class VehicleControllerAdvice {
    @ResponseBody
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<VehicleExceptionHandler> destinationNotFound(VehicleNotFoundException destinationNotFound) {
        VehicleExceptionHandler error = new VehicleExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Vehicle not found.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<VehicleExceptionHandler> MethodArgumentNotValidException(MethodArgumentNotValidException exception){

        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            sb.append("The field " );
            sb.append(fieldError.getDefaultMessage());
        }

        VehicleExceptionHandler error = new VehicleExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
