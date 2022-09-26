package com.hussam.carsAuction.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ExceptionResponse {

    private Date timeStamp;
    private String title;
    private List<String> message;
    private String path;
}
