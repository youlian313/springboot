package com.lfwin.service.impl;

import com.alibaba.fastjson.JSON;
import com.lfwin.dao.CodeDeviceDao;
import com.lfwin.pojo.CodeDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKey {
    private String key;
    @Autowired
    private CodeDeviceDao codeDeviceDao;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ApiKey() {

    }
    public ApiKey(String key) {
        this.key = key;
    }

    public String getKeyInfo(){
        String info=null;
        CodeDevice codeDevice = codeDeviceDao.getByApikey(key);

        String s = JSON.toJSONString(codeDevice);
        System.out.println(s);

        return info;
    }
}
