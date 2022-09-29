package com.hussam.carsAuction.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class SignInResponse {

    private Long id;
    private String email;
    private List<String> roles;
}
