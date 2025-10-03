package com.example.doctor_service.entity;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "doctors",uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private  Long id;
    private  String firstName;
    private String lastName;
    private String email;
    private String qualification;
    private String gender;
    private String specialization;
    private String mobileNo;
    private String hospitalName;
    private String doctorImage;
    private Integer experience;
    private String street;
    private String area;
    private String city;
    private Integer pinCode;
    private String state;
    private String password;

}
