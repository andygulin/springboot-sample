package springboot.sample.repositories;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import springboot.sample.bean.User;

public interface UserRepository extends ElasticsearchRepository<User, String> {

}