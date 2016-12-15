package springboot.sample.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("/hello")
	public Map<String, Object> hello() {
		Map<String, Object> obj = new HashMap<>();
		obj.put("name", "hello");
		return obj;
	}
}
