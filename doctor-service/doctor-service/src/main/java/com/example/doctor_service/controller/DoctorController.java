package com.example.doctor_service.controller;

import com.example.doctor_service.dto.DoctorDto;
import com.example.doctor_service.entity.Doctor;
import com.example.doctor_service.service.DoctorService;
import com.example.doctor_service.utils.ResponseData;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@Valid  @RequestBody DoctorDto doctorDto){
        Doctor doctor=doctorService.createDoctor(doctorDto);
        Map<String,Object> doctorData=new HashMap<>();
        doctorData.put(ResponseData.RESULT,ResponseData.SUCCESS);
        doctorData.put(ResponseData.DATA,doctor);
        return ResponseEntity.status(HttpStatus.OK).body(doctorData);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAllDoctors(){
       List<Doctor> doctor=doctorService.getAllDoctors();
        Map<String,Object> doctorData=new HashMap<>();
        doctorData.put(ResponseData.RESULT,ResponseData.SUCCESS);
        doctorData.put(ResponseData.DATA,doctor);
        return ResponseEntity.status(HttpStatus.OK).body(doctorData);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> doctorById(@PathVariable Long id){
        Doctor doctor=doctorService.doctorById(id);
        Map<String,Object> doctorData=new HashMap<>();
        doctorData.put(ResponseData.RESULT,ResponseData.SUCCESS);
        doctorData.put(ResponseData.DATA,doctor);
        return ResponseEntity.status(HttpStatus.OK).body(doctorData);
    }
}
