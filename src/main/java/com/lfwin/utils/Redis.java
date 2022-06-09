package com.lfwin.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Redis {

    private RedisConnectionFactory redisConnectionFactory;
    public Redis(){

    }
}
