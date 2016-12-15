package springboot.sample.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

	@MessageMapping("/message")
	@SendTo("/topic/room")
	public String message(String message) throws Exception {
		return message;
	}
}
