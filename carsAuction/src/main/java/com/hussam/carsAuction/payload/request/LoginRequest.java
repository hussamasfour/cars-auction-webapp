package com.hussam.carsAuction.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {
    @NotBlank(message = "Please enter an email")
    @Email
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
