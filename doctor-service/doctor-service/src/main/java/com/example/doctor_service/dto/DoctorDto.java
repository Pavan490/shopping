package com.example.doctor_service.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DoctorDto {
    @NotNull(message = "FirstName is Required")
    @Size(min = 2,message = "FirstName Min 2 Characters")
    private  String firstName;
    @NotNull(message = "LastName is Required")
    @Size(min = 2,message = "LastName Min 2 Characters")
    private String lastName;
    @NotNull(message = "Email is Required")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",message = " Invalid EmailId")
    private String email;
    @NotNull(message = "Qualification is Required")
    @Size(min = 2,message = "Qualification Min 2 Characters")
    private String qualification;
    @NotNull(message = "Gender is Required")
    @Size(min = 1,message = " Gender Min 1 Characters")
    private String gender;
    @NotNull(message = "Specialization is Required")
    @Size(min = 2,message = " Specialization Min 2 Characters")
    private String specialization;
    @NotNull(message = "MobileNo is Required")
    @Size(min = 10,message = " MobileNo Min 10 Characters")
    private String mobileNo;
    @NotNull(message = "HospitalName is Required")
    @Size(min = 2,message = " HospitalName Min 2 Characters")
    private String hospitalName;
    @NotNull(message = "DoctorImage is Required")
    private String doctorImage;
    @NotNull(message = "Experience is Required")
    private Integer experience;
    @NotNull(message = "Street is Required")
    @Size(min = 2,message = " Street Min 2 Characters")
    private String street;
    @NotNull(message = "Area is Required")
    @Size(min = 2,message = " Area Min 2 Characters")
    private String area;
    @NotNull(message = "City is Required")
    @Size(min = 2,message = " City Min 2 Characters")
    private String city;
    @NotNull(message = "PinCode is Required")
    @Min(100000)
    @Max(999999)
    private Integer pinCode;

    @NotNull(message = "State is Required")
    @Size(min = 2,message = " State Min 2 Characters")
    private String state;
    @NotNull(message = "Password is Required")
    @Size(min = 8,message = " Password Min 8 Characters")
    private String password;

}
