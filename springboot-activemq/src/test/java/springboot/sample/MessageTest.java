package springboot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.sample.bean.User;

import javax.jms.Queue;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MessageTest {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Test
    public void send() {
        final int MESSAGE_COUNT = 100;
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            jmsMessagingTemplate.convertAndSend(queue, UUID.randomUUID().toString());

            User user = new User();
            user.setId(1);
            user.setName(UUID.randomUUID().toString());
            user.setAge(12);
            user.setAddress("shanghai");
            user.setCreatedAt(new Date());
            jmsMessagingTemplate.convertAndSend(queue, user);
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
