package com.softengine.redis.redisclusterjava.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories
public class RedisConf {

    @Bean
    public RedisClusterConnection redisConnection(RedisConnectionFactory connectionFactory) {
        return connectionFactory.getClusterConnection();
    }
}
