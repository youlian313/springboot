package com.lfwin.service;

import com.lfwin.service.impl.ApiKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ApikeyTest {
    @Autowired
    private ApiKey apiKey;
    @Test
    public void getKeyInfoTest(){
        apiKey.setKey("00014005");
        String keyInfo = null;
        try {
            keyInfo = apiKey.getKeyInfo();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(keyInfo);
    }
}
