package springboot.sample;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.sample.bean.User;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MsgPackTests {

    private static final Logger logger = Logger.getLogger(MsgPackTests.class);

    @Autowired
    private TestRestTemplate testRestTemplate;
    private String host;
    @LocalServerPort
    private int port;
    private String baseURL;

    @Before
    public void init() throws UnknownHostException {
        host = InetAddress.getLocalHost().getHostAddress();
        baseURL = "http://" + host + ":" + port + "/user/";
    }

    @Test
    public void getUser() throws IOException {
        String url = baseURL + "1";
        ResponseEntity<byte[]> entity = testRestTemplate.getForEntity(url, byte[].class);
        MessagePack msgpack = new MessagePack();
        User user = msgpack.read(entity.getBody(), User.class);
        logger.info(url);
        logger.info(user);
    }

    @Test
    public void getList() throws IOException {
        String url = baseURL + "all";
        ResponseEntity<byte[]> entity = testRestTemplate.getForEntity(url, byte[].class);
        MessagePack msgpack = new MessagePack();
        List<User> users = msgpack.read(entity.getBody(), Templates.tList(msgpack.lookup(User.class)));
        logger.info(url);
        logger.info(users);
    }
}
