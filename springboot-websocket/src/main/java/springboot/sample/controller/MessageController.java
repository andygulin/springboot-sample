package springboot.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import springboot.sample.bean.MessageRequest;
import springboot.sample.bean.MessageResponse;

@Controller
public class MessageController {

    private static final String TOPIC_PREFIX = "/topic/room/";

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/message")
    public void message(MessageRequest request) throws Exception {
        MessageResponse response = new MessageResponse();
        BeanUtils.copyProperties(request, response);
        simpMessagingTemplate.convertAndSend(TOPIC_PREFIX + request.getRoomId(), response);
    }
}
