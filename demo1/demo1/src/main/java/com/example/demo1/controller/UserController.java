package com.example.demo1.controller;

import com.example.demo1.dto.ForgotPassword;
import com.example.demo1.dto.LoginDto;
import com.example.demo1.dto.ResetPassword;
import com.example.demo1.dto.UserDto;
import com.example.demo1.entity.User;
import com.example.demo1.service.IpDataService;
import com.example.demo1.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class UserController {
@Autowired
private UserService userService;
@Autowired
private IpDataService ipDataService;
@PostMapping("/signup")
    public ResponseEntity<Map<String,Object>> createAccount( @Valid  @RequestBody UserDto userDto) throws Exception{
    User user=userService.createAccount(userDto);
    Map<String,Object> res=new HashMap<>();
    res.put("result","success");
    res.put("data",user);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginDto loginDto, HttpServletRequest httpServletRequest) throws Exception{
       ipDataService.getIpAddressData(httpServletRequest.getRemoteAddr());
        Map<String,Object> user = userService.login(loginDto);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("data",user);


        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/forgot")
    public ResponseEntity<Map<String,Object>> forgotPassword(@Valid @RequestBody ForgotPassword forgotPassword) throws Exception{
        userService.forgotPassword(forgotPassword);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("message","we sent an email.you reset your password below link");

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @PostMapping("/reset")
    public ResponseEntity<Map<String,Object>> resetPassword(@Valid @RequestBody ResetPassword resetPassword) throws Exception{
        userService.resetPassword(resetPassword);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("message","Password are Upadted Successfully");

        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @GetMapping("/getAll")
    public ResponseEntity<?>getAllUsers() {
        List<User> users=userService.getAllUsers();
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("message",users);

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }
    @GetMapping("/{id}")
    public ResponseEntity<?>getById(@PathVariable Long id) {
        User user=userService.getById(id);
        Map<String,Object> res=new HashMap<>();
        res.put("result","success");
        res.put("message",user);

        return ResponseEntity.status(HttpStatus.OK).body(res);

    }




}
