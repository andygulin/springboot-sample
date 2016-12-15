package springboot.sample.controller;

import java.io.IOException;

import org.msgpack.MessagePack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.sample.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/{userId}")
	public ResponseEntity<byte[]> getUser(@PathVariable("userId") int userId) throws IOException {
		MessagePack msgpack = new MessagePack();
		return new ResponseEntity<byte[]>(msgpack.write(userService.getUser(userId)), HttpStatus.OK);
	}

	@RequestMapping("/all")
	public ResponseEntity<byte[]> getAll() throws IOException {
		MessagePack msgpack = new MessagePack();
		return new ResponseEntity<byte[]>(msgpack.write(userService.getList()), HttpStatus.OK);
	}
}
