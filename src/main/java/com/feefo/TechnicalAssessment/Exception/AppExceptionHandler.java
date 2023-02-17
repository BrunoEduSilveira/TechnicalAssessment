package com.feefo.TechnicalAssessment.Exception;

import com.feefo.TechnicalAssessment.Model.ErrorMessage;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.InvalidParameterException;
import java.util.Date;

import static com.fasterxml.jackson.databind.util.ClassUtil.nonNull;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalArgumentException.class, InvalidParameterException.class})
    public ResponseEntity<ErrorMessage> handleBadRequestException(Exception e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .currentDate(new Date())
                .message(nonNull(e.getLocalizedMessage(), e.toString()))
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ErrorMessage> handleNotFoundException(Exception e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .currentDate(new Date())
                .message(nonNull(e.getLocalizedMessage(), e.toString()))
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorMessage> handleAnyException(Exception e) {
        ErrorMessage errorMessage = ErrorMessage.builder()
                .currentDate(new Date())
                .message(nonNull(e.getLocalizedMessage(), e.toString()))
                .build();
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
