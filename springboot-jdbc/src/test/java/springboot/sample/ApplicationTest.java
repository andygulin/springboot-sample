package springboot.sample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import springboot.sample.entity.User;
import springboot.sample.service.UserService;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private UserService userService;

    @Test
    public void userServiceTest() {
        List<User> users = userService.getAll();
        System.out.println(users);

        User user = userService.getUser(1);
        System.out.println(user);

        user = new User();
        user.setName("test");
        user.setAge(16);
        user.setAddress("shanghai");
        user.setCreateAt(new Date());
        userService.save(user);
        System.out.println(user);

        System.out.println(userService.delete(1));
    }
}
