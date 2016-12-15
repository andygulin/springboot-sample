package springboot.sample.service;

import java.util.List;

import springboot.sample.bean.User;

public interface UserService {

	User getUser(int id);

	List<User> getList();
}
