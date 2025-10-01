package com.example.demo1.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionHandling {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionHandler(Exception ex){
        Map<String,String> err=new HashMap<>();
        err.put("result","failed");
        err.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> exceptionMethodHandler(MethodArgumentNotValidException exception){
        Map<String,String> err=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
         err.put(error.getField(),error.getDefaultMessage());});
        Map<String, Object> res=new HashMap<>();
        res.put("result","failed");
        res.put("errors",err);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);

    }
}
