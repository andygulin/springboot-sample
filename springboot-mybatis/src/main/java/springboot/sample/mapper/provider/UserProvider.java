package springboot.sample.mapper.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    private static final String TABLE_NAME = "user";

    public String selectByName(String name) {
        return new SQL().SELECT("*").FROM(TABLE_NAME).WHERE("name LIKE CONCAT('%',#{name},'%')").toString();
    }
}
