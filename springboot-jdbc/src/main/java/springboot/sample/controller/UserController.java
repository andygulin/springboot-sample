package springboot.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import springboot.sample.entity.User;
import springboot.sample.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User id(@PathVariable("id") Integer id) {
		return userService.getUser(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<User> all() {
		return userService.getAll();
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public User save(User user) {
		return userService.save(user);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public Integer delete(@PathVariable("id") Integer id) {
		return userService.delete(id);
	}
}