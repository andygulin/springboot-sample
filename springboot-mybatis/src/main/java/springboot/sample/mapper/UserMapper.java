package springboot.sample.mapper;

import org.apache.ibatis.annotations.Mapper;
import springboot.sample.base.BaseMapper;
import springboot.sample.entity.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}