package springboot.sample.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONPController {

	@GetMapping("/jsonp")
	public Map<String, Object> jsonp() {
		Map<String, Object> obj = new HashMap<>();
		obj.put("name", "呵呵");
		obj.put("age", 11);
		obj.put("time", new Date());
		return obj;
	}
}
