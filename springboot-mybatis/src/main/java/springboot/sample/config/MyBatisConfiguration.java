package springboot.sample.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyBatisConfiguration {

    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.put("dialect", "mysql");
        p.put("offsetAsPageNum", "true");
        p.put("rowBoundsWithCount", "true");
        p.put("pageSizeZero", "true");
        p.put("reasonable", "false");
        p.put("supportMethodsArguments", "false");
        p.put("returnPageInfo", "none");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}
