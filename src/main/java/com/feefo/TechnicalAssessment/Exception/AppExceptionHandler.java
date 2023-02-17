package com.feefo.TechnicalAssessment.Exception;

import com.feefo.TechnicalAssessment.Model.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static com.fasterxml.jackson.databind.util.ClassUtil.nonNull;


@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception e) {

        ErrorMessage errorMessage = ErrorMessage.builder()
                .currentDate(new Date())
                .message(nonNull(e.getLocalizedMessage(), e.toString()))
                .build();

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
