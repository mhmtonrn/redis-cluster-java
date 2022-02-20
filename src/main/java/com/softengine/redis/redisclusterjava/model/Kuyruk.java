package com.softengine.redis.redisclusterjava.model;

import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;

public class Kuyruk {

    private final Map<Integer, Long> kuyruk = new HashMap<>();

    public void addKuyruk(Integer key, Long value) {
        kuyruk.put(key, value);
    }

    @SneakyThrows
    public Map<String, Object> toJSON() {
        Map<String, Object> map = new HashMap<>();

        Long total = 0L;

        for (Map.Entry<Integer, Long> entry : kuyruk.entrySet()) {
            Integer key = entry.getKey();
            Long val = entry.getValue();

            map.put(String.format("gbs_redis_queue_count_%s", key), val);
            total += val;
        }

        map.put("gbs_redis_queue_sum", total);
        return map;
    }

    public String toPrometheus() {
        Long total = 0L;
        StringBuilder response = new StringBuilder();

        response.append(String.format("# HELP gbs_redis_queue Bekleyen basvuru sayisi%n"));
        response.append(String.format("# TYPE gbs_redis_queue summary%n"));
        for (Map.Entry<Integer, Long> entry : kuyruk.entrySet()) {
            Integer key = entry.getKey();
            Long val = entry.getValue();

            response.append(String.format("gbs_redis_queue_count{kuyruk=\"%s\"} %s%n", key, val));
            total += val;
        }

        response.append(String.format("gbs_redis_queue_sum %s%n", total));

        return response.toString();
    }
}
