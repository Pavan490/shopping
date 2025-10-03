package com.example.doctor_service.service;

import com.example.doctor_service.dto.DoctorDto;
import com.example.doctor_service.entity.Doctor;
import com.example.doctor_service.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
@Autowired
private DoctorRepository doctorRepository;

public PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public Doctor createDoctor(DoctorDto doctorDto) {
        Optional<Doctor> optionalDoctor = doctorRepository.findByEmail(doctorDto.getEmail());
        if(optionalDoctor.isPresent()){
            throw  new RuntimeException("Email is not Exist,Please signIn");
        }
        Doctor doctor=new Doctor();
        doctor.setFirstName(doctorDto.getFirstName());
        doctor.setLastName(doctorDto.getLastName());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setQualification(doctorDto.getQualification());
        doctor.setGender(doctorDto.getGender());
        doctor.setSpecialization(doctorDto.getSpecialization());
        doctor.setMobileNo(doctorDto.getMobileNo());
        doctor.setHospitalName(doctorDto.getHospitalName());
        doctor.setDoctorImage(doctorDto.getDoctorImage());
        doctor.setExperience(doctorDto.getExperience());
        doctor.setStreet(doctorDto.getStreet());
        doctor.setArea(doctorDto.getArea());
        doctor.setCity(doctorDto.getCity());
        doctor.setPinCode(doctorDto.getPinCode());
        doctor.setState(doctorDto.getState());
        doctor.setPassword(passwordEncoder.encode(doctorDto.getPassword()));
        return doctorRepository.save(doctor);
    }

    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Doctor doctorById(Long id){
        return doctorRepository.findById(id).orElseThrow(()->new RuntimeException("Doctor Not Found"));
    }
}
