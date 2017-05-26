package springboot.sample.service;

import org.springframework.data.redis.core.StringRedisTemplate;

public interface RedisService {

    StringRedisTemplate getRedisTemplate();

    void set(String key, String value);

    String get(String key);

    boolean exist(String key);

    long del(String key);
}
