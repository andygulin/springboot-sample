package springboot.sample;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.context.junit4.SpringRunner;
import springboot.sample.bean.User;
import springboot.sample.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsTest {

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;

    protected String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    protected String getName() {
        return RandomStringUtils.random(10, "abcdefghijklmnopqrstuvwxyz1234567890");
    }

    protected int getAge() {
        return RandomUtils.nextInt(100) + 10;
    }

    protected String getAddress() {
        final String[] addrs = {"shanghai", "beijing", "guangzhou", "shenzhen", "hunan", "henan", "xinjiang", "haerbing", "jinan", "nanjing"};
        return addrs[RandomUtils.nextInt(addrs.length - 1)];
    }

    protected Date getCreatedAt() {
        return DateUtils.addDays(new Date(), RandomUtils.nextInt(100) + 10);
    }

    protected void printQuery(QueryBuilder query) {
        Page<User> page = userRepository.search(query, new PageRequest(0, 10));
        System.out.println(page.getTotalElements());
        List<User> users = page.getContent();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Repeat(100)
    @Test
    public void index() {
        final int INDEX_COUNT = 10000;
        List<User> users = new ArrayList<>(INDEX_COUNT);
        for (int i = 0; i < INDEX_COUNT; i++) {
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
    public void count() {
        System.out.println(userRepository.count());
    }

    @Test
    public void searchByAge() {
        QueryBuilder query = QueryBuilders.termQuery("age", 11);
        printQuery(query);
    }

    @Test
    public void searchRangeByAge() {
        QueryBuilder query = QueryBuilders.rangeQuery("age").from(10).to(15).includeLower(true).includeUpper(true);
        printQuery(query);
    }

    @Test
    public void searchLikeByName() {
        QueryBuilder query = QueryBuilders.wildcardQuery("name", "*abc*");
        printQuery(query);
    }

    @Test
    public void searchPrefixByName() {
        QueryBuilder query = QueryBuilders.prefixQuery("name", "38");
        printQuery(query);
    }

    @Test
    public void searchInByAddress() {
        QueryBuilder query = QueryBuilders.termsQuery("address", Arrays.asList("shanghai", "beijing"));
        printQuery(query);
    }

    @Test
    public void searchNameAndAge() {
        QueryBuilder query = QueryBuilders.boolQuery()
                .must(QueryBuilders.wildcardQuery("name", "*abc*"))
                .must(QueryBuilders.termQuery("age", 11));
        printQuery(query);
    }
}