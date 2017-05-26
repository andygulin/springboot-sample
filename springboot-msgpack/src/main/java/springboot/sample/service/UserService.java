package springboot.sample.service;

import springboot.sample.bean.User;

import java.util.List;

public interface UserService {

    User getUser(int id);

    List<User> getList();
}
