package com.keycloak.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class LoadBalancerService {

    private static final String KEY = "roundRobinIndex";
    private static final int INSTANCE_COUNT = 2;
    private static final int TPS_LIMIT = 5;

    @Autowired
    private RedisTemplate<String, Integer> redisTemplate;

    private List<String> endpoints = List.of(
            "http://localhost:8081/endpoint",
            "http://localhost:8082/endpoint"
        );

    public String getEndpoint() {
        Integer index = redisTemplate.opsForValue().get(KEY);

        if (index == null) {
            index = 0;
        }

        index = (index + 1) % INSTANCE_COUNT;

        redisTemplate.opsForValue().set(KEY, index);

        return endpoints.get(index);
    }

    public boolean isRequestAllowed(String endpoint) {
        String key = "tps:" + endpoint;
        Integer tps = redisTemplate.opsForValue().get(key);

        if (tps == null) {
            tps = 0;
        }

        if (tps < TPS_LIMIT) {
            redisTemplate.opsForValue().increment(key);
            return true;
        } else {
            return false;
        }
    }
}
