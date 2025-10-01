package com.example.demo1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IpDataService {
    @Value("${ip_info.token}")
    private String ipToken;
    public void getIpAddressData(String ipAddress){
        ipAddress="";
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers=new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String inputData="";
        HttpEntity<Object> httpEntity=new HttpEntity<>(inputData,headers);
        String url="https://ipinfo.io/"+ipAddress+"?"+ipToken;
        ResponseEntity<Object> response=restTemplate.exchange(url,HttpMethod.GET,httpEntity,Object.class);
        System.out.println(response);
    }

}
