package com.keycloak.api.service;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private static final String TRANSACTION_KEY = "transacciones";
    private static final int LIMIT = 1;

    public boolean limitReached(){
        long tiempo = Instant.now().getEpochSecond();
        String key = TRANSACTION_KEY + ":" + tiempo;
        //Integer contador = redisTemplate.opsForValue().get(key);
        
        if (redisTemplate.opsForValue().get(key) == null) {
            redisTemplate.opsForValue().set(key, 1,1,TimeUnit.SECONDS);
            System.out.println(redisTemplate.opsForValue().get(key) + "=================================");
            return true;
        }

        if (redisTemplate.opsForValue().get(key) < LIMIT){
            redisTemplate.opsForValue().increment(key);
            System.out.println(redisTemplate.opsForValue().get(key) + "=================================");
            return true;
        }
        
        return false;
    }

    public void complete(){
        long tiempo = Instant.now().getEpochSecond();
        String key = TRANSACTION_KEY + ":" + tiempo;

        if (redisTemplate.opsForValue().get(key) != null && redisTemplate.opsForValue().get(key) > 0){
            redisTemplate.opsForValue().decrement(key);
        }
    }
}
