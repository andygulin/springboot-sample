package springboot.sample.repo;

import org.springframework.data.repository.CrudRepository;
import springboot.sample.bean.User;

public interface UserRepository extends CrudRepository<User, String> {

}