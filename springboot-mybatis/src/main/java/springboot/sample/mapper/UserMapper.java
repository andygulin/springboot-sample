package springboot.sample.mapper;

import org.apache.ibatis.annotations.*;
import springboot.sample.entity.User;
import springboot.sample.mapper.provider.UserProvider;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user WHERE id=#{id}")
    User select(@Param("id") int id);

    @Select("SELECT * FROM user")
    List<User> selectAll();

    @SelectProvider(type = UserProvider.class, method = "selectByName")
    List<User> selectByName(@Param("name") String name);

    @Options(useGeneratedKeys = true, keyProperty = "user.id")
    @Insert("INSERT INTO user VALUES(NULL,#{user.name},#{user.age},#{user.address},NOW())")
    void insert(@Param("user") User user);

    @Update("UPDATE user SET name=#{user.name},age=#{user.age},address=#{user.address} WHERE id=#{user.id}")
    int update(@Param("user") User user);

    @Delete("DELETE FROM user WHERE id=#{id}")
    int delete(@Param("id") int id);
}
