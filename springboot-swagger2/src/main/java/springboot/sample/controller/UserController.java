package springboot.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.sample.bean.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Api(value = "UserController", description = "用户相关API")
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value = "根据ID返回User对象", httpMethod = "GET", response = User.class)
    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") int id) {
        User user = new User();
        user.setId(id);
        user.setName(UUID.randomUUID().toString());
        user.setAge(11);
        user.setAddress("shanghai");
        user.setCreatedAt(new Date());
        return user;
    }

    @ApiOperation(value = "返回所有User对象", httpMethod = "GET", response = User.class)
    @GetMapping("/all")
    public List<User> all() {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(i);
            user.setName(UUID.randomUUID().toString());
            user.setAge(i + 10);
            user.setAddress("shanghai");
            user.setCreatedAt(new Date());
            users.add(user);
        }
        return users;
    }
}
