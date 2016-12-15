package springboot.sample.service;

import java.util.List;

import springboot.sample.entity.User;

public interface UserService {

	User getUser(Integer id);

	List<User> getAll();

	User save(User user);

	int delete(Integer id);
}
