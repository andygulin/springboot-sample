package springboot.sample.service.impl;

import org.springframework.stereotype.Service;
import springboot.sample.bean.User;
import springboot.sample.service.UserService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        users.add(new User(1, "小明", 11, "上海", new Date()));
        users.add(new User(2, "小红", 12, "北京", new Date()));
        users.add(new User(3, "小蓝", 13, "广州", new Date()));
        users.add(new User(4, "小紫", 14, "香港", new Date()));
    }

    @Override
    public User getUser(int id) {
        User user = null;
        for (User u : users) {
            if (u.getId() == id) {
                user = u;
                break;
            }
        }
        return user;
    }

    @Override
    public List<User> getList() {
        return users;
    }

}
