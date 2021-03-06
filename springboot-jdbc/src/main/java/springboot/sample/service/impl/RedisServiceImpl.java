package springboot.sample.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import springboot.sample.service.RedisService;

import java.nio.charset.Charset;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    private StringRedisSerializer serializer = new StringRedisSerializer(Charset.forName("UTF-8"));

    @Override
    public StringRedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public void set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(serializer.serialize(key), serializer.serialize(value));
                return null;
            }
        });
    }

    @Override
    public String get(final String key) {
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                return serializer.deserialize(connection.get(serializer.serialize(key)));
            }
        });
    }

    @Override
    public boolean exist(final String key) {
        return redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.exists(serializer.serialize(key));
            }
        });
    }

    @Override
    public long del(final String key) {
        return redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(serializer.serialize(key));
            }
        });
    }
}