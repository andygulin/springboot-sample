package springboot.sample;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.WriteListener;
import org.springframework.data.cassandra.mapping.Table;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import springboot.sample.bean.User;
import springboot.sample.repo.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CassandraTest {

    private static final String TABLE_NAME = User.class.getAnnotation(Table.class).value();

    @Autowired
    protected CassandraTemplate cassandraTemplate;
    @Autowired
    protected UserRepository userRepository;

    private String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    private String getName() {
        RandomStringGenerator generator = new RandomStringGenerator.Builder().withinRange('a', 'z').build();
        return generator.generate(10);
    }

    private int getAge() {
        return RandomUtils.nextInt(0, 100) + 10;
    }

    private String getAddress() {
        final String[] addrs = {"shanghai", "beijing", "guangzhou", "shenzhen", "hunan", "henan", "xinjiang", "haerbing", "jinan", "nanjing"};
        return addrs[RandomUtils.nextInt(0, addrs.length - 1)];
    }

    private Date getCreatedAt() {
        return DateUtils.addDays(new Date(), RandomUtils.nextInt(0, 100) + 10);
    }

    @Repeat(10)
    @Test
    public void save() {
        final int BATCH_SIZE = 1000;
        List<User> users = new ArrayList<>(BATCH_SIZE);
        for (int i = 1; i <= BATCH_SIZE; i++) {
            User user = new User();
            user.setId(getId());
            user.setName(getName());
            user.setAge(getAge());
            user.setAddress(getAddress());
            user.setCreatedAt(getCreatedAt());
            users.add(user);
        }
        userRepository.save(users);
    }

    @Test
    public void asyncSave() {
        User user = new User();
        user.setId(getId());
        user.setName(getName());
        user.setAge(getAge());
        user.setAddress(getAddress());
        user.setCreatedAt(getCreatedAt());
        cassandraTemplate.insertAsynchronously(user, new WriteListener<User>() {
            @Override
            public void onWriteComplete(Collection<User> entities) {
                System.out.println(entities);
            }

            @Override
            public void onException(Exception x) {
                x.printStackTrace();
            }
        });
    }

    @Test
    public void count() {
        System.out.println(userRepository.count());
    }

    @Test
    public void query() {
        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
    }

    @Test
    public void selectByAge() {
        List<User> users = cassandraTemplate.select("SELECT * FROM " + TABLE_NAME + " WHERE age = 10", User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void selectBuilderByAge() {
        Select select = QueryBuilder.select().from(TABLE_NAME);
        select.where(QueryBuilder.eq("age", 16));
        List<User> users = cassandraTemplate.select(select, User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByAgeRange() {
        Select select = QueryBuilder.select().from(TABLE_NAME);
        select.where(QueryBuilder.gte("age", 10)).and(QueryBuilder.lte("age", 15));
        List<User> users = cassandraTemplate.select(select, User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void selectByLimit() {
        Select select = QueryBuilder.select().from(TABLE_NAME);
        select.where().limit(10);
        List<User> users = cassandraTemplate.select(select, User.class);
        users.forEach(System.out::println);
    }

    @Test
    public void delete() {
        User user = cassandraTemplate.selectOne("SELECT * FROM " + TABLE_NAME + " WHERE name = 'aaaa'", User.class);
        System.out.println(user);
        if (user != null)
            cassandraTemplate.delete(user);
    }

    @Test
    public void update() {
        List<User> users = cassandraTemplate.select("SELECT * FROM " + TABLE_NAME + " WHERE age = 11", User.class);
        System.out.println(users.size());
        if (!CollectionUtils.isEmpty(users)) {
            for (User user : users) {
                user.setAge(111);
            }
            cassandraTemplate.update(users);
        }
    }

    @Test
    public void deleteAll() {
        userRepository.deleteAll();
    }

}