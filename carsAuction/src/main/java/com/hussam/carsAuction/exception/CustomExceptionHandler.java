package com.hussam.carsAuction.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {
        @ExceptionHandler(NotFoundException.class)
        public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest webRequest){
            List<String> details  = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Record not found", details, webRequest.getDescription(false));

            return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }

}
