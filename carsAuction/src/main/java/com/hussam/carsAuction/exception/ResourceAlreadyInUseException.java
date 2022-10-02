package com.hussam.carsAuction.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.CONFLICT)
public class ResourceAlreadyInUseException extends RuntimeException{

    private final String name;
    private final String fieldName;
    private final String fieldValue;

    public ResourceAlreadyInUseException(String name,String fieldName,String fieldValue){
        super(name + " already in use with " +fieldName + " : " + fieldValue  );

        this.name = name;
        this.fieldName =fieldName;
        this.fieldValue = fieldValue;
    }
}
