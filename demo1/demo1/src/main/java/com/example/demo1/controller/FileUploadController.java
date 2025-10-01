package com.example.demo1.controller;

import com.example.demo1.service.FileUploadImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FileUploadController {
@Autowired
private FileUploadImg fileUploadImg;
@PostMapping("/image")
    public ResponseEntity<?> imageUpload(@RequestParam("file")MultipartFile inputFile)throws Exception{
fileUploadImg.fileUploadImage(inputFile);
        Map<String,Object> res= new HashMap<>();
        res.put("result","success");
        res.put("message","Image uploaded successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    @PostMapping("/pdf")
    public ResponseEntity<?> pdfUpload(@RequestParam("pdf")MultipartFile inputFile)throws Exception{
        fileUploadImg.fileUploadPdf(inputFile);
        Map<String,Object> res= new HashMap<>();
        res.put("result","success");
        res.put("message","Pdf uploaded successfully");
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
