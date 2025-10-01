package com.example.demo1.service;
import com.example.demo1.entity.User;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
@Service
public class EmailService {
    @Autowired
    public JavaMailSender javaMailSender;
 @Autowired
 public TemplateEngine templateEngine;

    public void simpleTextEmail(String fromEmail, String toEmail, String subject, String mailBody){
        SimpleMailMessage message=new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(mailBody);
        javaMailSender.send(message);
    }

    public void htmlFormatEmail(String fromEmail, String toEmail, String subject, String mailBody) throws Exception{
        MimeMessage mes=javaMailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mes,true,"UTF-8");
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(mailBody,true);
        javaMailSender.send(mes);
    }
    public void templateFormatEmail(String fromEmail, String toEmail, String subject, String fileName,User userDto) throws Exception{

        Context ctx = new Context();
        ctx.setVariable("name",userDto.getName() );
        ctx.setVariable("expiresIn", "30 minutes");
        ctx.setVariable("companyName", "Longbow Technologies");
        ctx.setVariable("supportEmail", "support@yourcompany.com");
        ctx.setVariable("year", LocalDate.now().getYear());

        String mailBody= templateEngine.process(fileName,ctx);
        MimeMessage mes=javaMailSender.createMimeMessage();
        MimeMessageHelper message=new MimeMessageHelper(mes,true,"UTF-8");
        message.setFrom(fromEmail);
        message.setTo(toEmail);
        message.setSubject(subject);
        message.setText(mailBody,true);
        javaMailSender.send(mes);
          }
}
