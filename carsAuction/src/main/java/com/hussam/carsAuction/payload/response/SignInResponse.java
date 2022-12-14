package com.hussam.carsAuction.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class SignInResponse {

    private Long id;
    private String firstName;
    private String email;
    private String accessToken;
    private String type = "Bearer";
    private List<String> roles;
}
