package com.softengine.redis.redisclusterjava.api;

import com.softengine.redis.redisclusterjava.model.Kuyruk;
import com.softengine.redis.redisclusterjava.service.RedisService;
import lombok.RequiredArgsConstructor;
import net.logstash.logback.marker.Markers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Api {

    private static final Logger logger = LoggerFactory.getLogger(Api.class);

    private final RedisService redisService;

    @Value("${data.queueSize}")
    private long queueSize;

    @GetMapping("/metrics")
    public ResponseEntity<String> get() {
        Kuyruk kuyruk = new Kuyruk();

        for (int i = 0; i < queueSize; i++) {
            kuyruk.addKuyruk(i, redisService.zCount("app_order_" + i));
        }

        logger.info(Markers.appendEntries(kuyruk.toJSON()), null);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-type", "text/plain; version=0.0.4; charset=utf-8");
        return ResponseEntity.ok().headers(responseHeaders).body(kuyruk.toPrometheus());
    }
}
