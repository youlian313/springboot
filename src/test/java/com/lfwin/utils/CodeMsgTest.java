package com.lfwin.utils;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
@SpringBootTest
public class CodeMsgTest {
    @Autowired
    private CodeMsg codeMsg;
    @Autowired
    private CodeMsg codeMsg2;



    @Test
    public void reSuccessTest(){
      //  CodeMsg codeMsg=new CodeMsg();
        Map m=new HashMap<String,String>();
        m.put("a","123");
        m.put("b","111");
        String s =codeMsg.setKeyObj("00014005").reSuccess(m);
       // String s =codeMsg.reSuccess(m);
        System.out.println(codeMsg);
        System.out.println(s);
    }
    @Test
    public void test2(){
        reSuccessTest();
        String s =codeMsg2.reError(3010);
        System.out.println(codeMsg2);;
    }
}
