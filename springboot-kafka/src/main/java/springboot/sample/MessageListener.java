package springboot.sample;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {

    @KafkaListener(topics = "TEST-TOPIC")
    public void processMessage(String content) {
        System.out.println("ProcessMessage : " + content);
    }
}