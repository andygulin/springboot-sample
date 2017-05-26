package springboot.sample.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import springboot.sample.entity.User;
import springboot.sample.service.RedisService;
import springboot.sample.service.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final String CACHE_USERS_KEY = "users";
    private static final String CACHE_USER_KEY = "user:%s";
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RedisService redisService;

    @Override
    public User getUser(Integer id) {
        final String key = String.format(CACHE_USER_KEY, id);
        if (redisService.exist(key)) {
            return JSON.parseObject(redisService.get(key), User.class);
        }
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM `user` WHERE id = ?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            redisService.set(key, JSON.toJSONString(user));
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        if (redisService.exist(CACHE_USERS_KEY)) {
            return JSON.parseArray(redisService.get(CACHE_USERS_KEY), User.class);
        }
        try {
            List<User> users = jdbcTemplate.query("SELECT * FROM `user`",
                    BeanPropertyRowMapper.newInstance(User.class));
            redisService.set(CACHE_USERS_KEY, JSON.toJSONString(users));
            return users;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User save(final User user) {
        final String sql = "INSERT INTO `user` VALUES(NULL,?,?,?,?)";
        KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setInt(2, user.getAge());
                ps.setString(3, user.getAddress());
                ps.setObject(4, new Date());
                return ps;
            }
        }, generatedKeyHolder);
        return getUser(generatedKeyHolder.getKey().intValue());
    }

    @Override
    public int delete(Integer id) {
        final String key = String.format(CACHE_USER_KEY, id);
        redisService.del(key);
        redisService.del(CACHE_USERS_KEY);
        int rows = jdbcTemplate.update("DELETE FROM `user` WHERE id = ?", id);
        return rows;
    }
}
