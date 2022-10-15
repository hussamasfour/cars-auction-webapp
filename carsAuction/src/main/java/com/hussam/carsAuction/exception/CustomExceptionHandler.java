package com.hussam.carsAuction.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler {
        @ExceptionHandler(Exception.class)
        public final ResponseEntity<ExceptionResponse> handleAllException(Exception ex, WebRequest webRequest){
        List<String> details =  new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Internal Server Error" ,details, webRequest.getDescription(false));

        return new ResponseEntity(exceptionResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
        @ExceptionHandler(NotFoundException.class)
        public final ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest webRequest){
            List<String> details  = new ArrayList<>();
            details.add(ex.getLocalizedMessage());
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Record not found", details, webRequest.getDescription(false));

            return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
        }
        @ExceptionHandler(ConstraintViolationException.class)
        public final ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex, WebRequest webRequest){
            List<String> details  = new ArrayList<>();
            details.add(ex.getMessage());
            ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Error", details, webRequest.getDescription(false));

            return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<ExceptionResponse> handleDataIntegrityViolationException (DataIntegrityViolationException ex, WebRequest webRequest){
        List<String> details  = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Error", details, webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public final ResponseEntity<ExceptionResponse> handleBadCredentialsException (BadCredentialsException ex, WebRequest webRequest){
        List<String> details  = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Validation Error", details, webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidBidException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidBidException (InvalidBidException ex, WebRequest webRequest){
        List<String> details  = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Invalid bid", details, webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceAlreadyInUseException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceAlreadyInUseException (ResourceAlreadyInUseException ex, WebRequest webRequest){
        List<String> details  = new ArrayList<>();
        details.add(ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),"Resource Already in use", details, webRequest.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {

        List<String > details = new ArrayList<>();
        for (ObjectError error: ex.getBindingResult().getAllErrors()){
            details.add(error.getDefaultMessage());
        }
        ExceptionResponse exceptionResponse =  new ExceptionResponse(new Date(), "Validation Failed", details,webRequest.getDescription(false) );
        return  new ResponseEntity<>(exceptionResponse, headers, HttpStatus.BAD_REQUEST);
    }
}
