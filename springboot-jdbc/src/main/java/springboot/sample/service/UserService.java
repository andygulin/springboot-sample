package springboot.sample.service;

import springboot.sample.entity.User;

import java.util.List;

public interface UserService {

    User getUser(Integer id);

    List<User> getAll();

    User save(User user);

    int delete(Integer id);
}
