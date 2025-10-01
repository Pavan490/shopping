package com.example.demo1.service;

import com.example.demo1.dto.ForgotPassword;
import com.example.demo1.dto.LoginDto;
import com.example.demo1.dto.ResetPassword;
import com.example.demo1.dto.UserDto;
import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    public EmailService emailService;
    @Autowired
    private JwtService jwtService;
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final WebClient.Builder webClientBuilder;

    public UserService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public User createAccount(UserDto userDto) throws Exception{
        Optional<User> user=userRepo.findByEmail(userDto.getEmail());
        if(user.isPresent()){
            throw new Exception("User email not exist.Please signup");
        }
        User user1=new User();
        user1.setName(userDto.getName());
        user1.setEmail(userDto.getEmail());
        user1.setMobile(userDto.getMobile());
        user1.setPassword(passwordEncoder.encode(userDto.getPassword()));
       return userRepo.save(user1);

    }

    public List<User> getAllUsers(){
        return userRepo.findAll();
    }
    public User getById(Long id){
        return userRepo.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));

    }


    public Map<String,Object> login(LoginDto loginDto) throws Exception{
        Optional<User> user=userRepo.findByEmail(loginDto.getEmail());
        if(user.isEmpty()){
            throw new Exception("User email not exist.Please signup");
        }
        User user1=user.get();
        boolean isMatched=passwordEncoder.matches(loginDto.getPassword(),user1.getPassword());
        if (isMatched==true){
            Map<String,Object>tokenData=new HashMap<>();
            tokenData.put("userData",user1);
            tokenData.put("token",jwtService.generateToken(user1));
            return tokenData;
        }else{
            throw new Exception("Passwords are not matched.please try again");
        }
    }
    public void forgotPassword(ForgotPassword forgotPassword) throws Exception{
        Optional<User> user=userRepo.findByEmail(forgotPassword.getEmail());
        if(user.isEmpty()){
            throw new Exception("User email not exist.Please signup");
        }else {
            String passkey = UUID.randomUUID().toString();
            User user1 = user.get();
            user1.setLinkId(passkey);
            userRepo.save(user1);
            emailService.templateFormatEmail("kattapavankumar490@gmail.com",forgotPassword.getEmail(),
                    "Forgot Password", "welcome",user1);

        }
    }
    public void resetPassword(ResetPassword resetPassword) throws Exception{
        if(resetPassword.getPassword().equals(resetPassword.getConfirmPassword())==false){
            throw new Exception("Passwords are not matched");
        }
        Optional<User> user=userRepo.findByLinkId(resetPassword.getLinkId());
        if (user.isEmpty()){
            throw new Exception("Invalid passkey LinkId");
        }
        User user1=user.get();
        user1.setPassword(passwordEncoder.encode(resetPassword.getPassword()));
        user1.setLinkId("");
        userRepo.save(user1);

    }






}



