package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.UUID;

@Service
public class FileUploadImg {

    @Value("${file.upload.image.path}")
    private String IMAGE_PATH;

    private final int MAX_SIZE_IMAGE= 5*1024*1024;
    public void fileUploadImage(MultipartFile inputFile) throws Exception{

        String fileName= StringUtils.cleanPath(inputFile.getOriginalFilename());
        String fileType=StringUtils.getFilenameExtension(fileName);
        String[] allowedType={"png","jpg"};
        Boolean isFileAllowed= Arrays.stream(allowedType).allMatch(fileType::equals);
        if(!isFileAllowed){
            throw new Exception(fileType +"file is not allowed");
        }
      if(inputFile.getSize()>MAX_SIZE_IMAGE){
          throw new Exception("Max 5MB Size allowed");
      }
  String uploadImageName= UUID.randomUUID().toString() + "."+ fileName;
        Path path= Paths.get(IMAGE_PATH+uploadImageName);
        Files.copy(inputFile.getInputStream(),path);
    }
    @Value("${file.upload.pdf.path}")
    private String PDF_PATH;

    private final int MAX_PDF_SIZE=10*1024*1024;
    public void fileUploadPdf(MultipartFile inputFile) throws Exception{
       String fileName=StringUtils.cleanPath(PDF_PATH);
       String fileType=StringUtils.getFilenameExtension(fileName);
       if(!fileType.equals("pdf")){
           throw new Exception(fileName +"file is not allowed");
       }

       if(inputFile.getSize()>MAX_PDF_SIZE){
           throw new Exception("Maximum 10MB");
       }
       String uploadName=UUID.randomUUID().toString()+"."+fileType;
       Path path=Paths.get(PDF_PATH+uploadName);
       Files.copy(inputFile.getInputStream(),path);
    }
}
