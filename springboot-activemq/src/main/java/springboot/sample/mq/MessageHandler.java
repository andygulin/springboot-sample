package springboot.sample.mq;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;
import java.io.Serializable;

@Component
public class MessageHandler {

    @JmsListener(destination = "activemq.queue", concurrency = "5-10")
    public void processMessage(@Payload Message message) {
        if (message instanceof TextMessage) {
            _processTextMessage(message);
        }
        if (message instanceof ObjectMessage) {
            _processObjectMessage(message);
        }
    }

    private void _processObjectMessage(Message message) {
        ObjectMessage msg = (ObjectMessage) message;
        try {
            Serializable obj = msg.getObject();
            System.out.println("Process ObjectMessage : " + obj);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

    private void _processTextMessage(Message message) {
        TextMessage msg = (TextMessage) message;
        try {
            String text = msg.getText();
            System.out.println("Process TextMessage : " + text);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
