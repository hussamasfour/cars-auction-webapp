package com.hussam.carsAuction.payload.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignUpRequest {
    @NotBlank(message = "Please enter a username")
    private String firstName;
    @NotBlank(message = "lastName is required")

    private String lastName;

    @NotBlank(message = "Please enter an email")
    @Email(message = "Please enter a valid email!!")
    private String email;

    @NotBlank
    @Size(min = 8, max = 30 ,message = "Password must be more than 8 characters")
    private String password;
}
