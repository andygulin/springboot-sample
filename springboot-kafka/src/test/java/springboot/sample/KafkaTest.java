package springboot.sample;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFuture;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void sendMessage() throws Exception {
        for (int i = 1; i <= 100; i++) {
            ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("TEST-TOPIC", String.valueOf(System.nanoTime()));
            SendResult<String, String> result = future.get();
            RecordMetadata metadata = result.getRecordMetadata();
            System.out.println("Send Message : " + metadata);
        }
    }
}