package springboot.sample.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springboot.sample.entity.User;
import springboot.sample.mapper.UserMapper;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/get/{id}")
    public User get(@PathVariable("id") int id) {
        return userMapper.select(id);
    }

    @GetMapping("/all")
    public List<User> all() {
        return userMapper.selectAll();
    }

    @GetMapping("/page/{pageNo}")
    public PageInfo<User> page(@PathVariable("pageNo") int pageNo) {
        PageHelper.startPage(pageNo, 5);
        return new PageInfo<>(userMapper.selectAll());
    }

    @GetMapping("/query/{name}")
    public List<User> query(@PathVariable("name") String name) {
        return userMapper.selectByName(name);
    }

    @PutMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int insert(@RequestBody User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public int update(@RequestBody User user) {
        return userMapper.update(user);
    }

    @DeleteMapping("/delete/{id}")
    public int delete(@PathVariable("id") int id) {
        return userMapper.delete(id);
    }
}