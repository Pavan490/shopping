package com.example.demo1.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResetPassword {
    @NotNull(message = "LinkId is Required")
    private String linkId;
    @NotNull(message = "Password is Required")
    @Size(min = 8,message = "Password minium 8 Characters")
    private String password;
    @NotNull(message = "Confirm Password is Required")
    @Size(min = 8,message = "Confirm Password minium 8 Characters")
    private String confirmPassword;
}
