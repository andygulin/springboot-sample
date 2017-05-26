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
    public void receiveQueue(@Payload Message message) {
        if (message instanceof TextMessage) {
            TextMessage msg = (TextMessage) message;
            try {
                String text = msg.getText();
                System.out.println("Receive TextMessage : " + text);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
        if (message instanceof ObjectMessage) {
            ObjectMessage msg = (ObjectMessage) message;
            try {
                Serializable obj = msg.getObject();
                System.out.println("Receive ObjectMessage : " + obj);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
}
