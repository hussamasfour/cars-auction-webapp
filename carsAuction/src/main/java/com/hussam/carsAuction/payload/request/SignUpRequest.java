package com.hussam.carsAuction.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @NotBlank(message = "FirstName is required")
    private String firstName;
    @NotBlank(message = "LastName is required")
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email
    private String email;
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;
}
