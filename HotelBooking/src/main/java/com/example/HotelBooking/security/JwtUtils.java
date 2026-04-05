package com.example.HotelBooking.security;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Service
@Slf4j
public class JwtUtils {

    private static final long EXPIRATION_TIME_IN_MILSEC=100L*60L*60L*24L*30L*6L;

    private SecretKey key;

    @Value("${secreteJwtString}")
    private String secreteJwtString;

    @PostConstruct
    private void init(){
        byte[]keyByte=secreteJwtString.getBytes(StandardCharsets.UTF_8);
        this.key=new SecretKeySpec(keyByte, )
    }


}
