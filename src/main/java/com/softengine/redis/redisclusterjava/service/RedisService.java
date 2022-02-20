package com.softengine.redis.redisclusterjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {

    private final RedisClusterConnection redisClusterConnection;

    public Long zCount(String query) {
        return redisClusterConnection.zSetCommands().zCount(query.getBytes(), Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }
}
