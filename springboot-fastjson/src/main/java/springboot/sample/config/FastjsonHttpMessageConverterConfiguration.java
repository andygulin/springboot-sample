package springboot.sample.config;

import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.support.spring.FastJsonpHttpMessageConverter4;
import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;

@Configuration
public class FastjsonHttpMessageConverterConfiguration {

	@Bean
	public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice() {
		return new FastJsonpResponseBodyAdvice("callback", "jsonp");
	}

	@Bean
	public HttpMessageConverters httpMessageConverters() {
		return new HttpMessageConverters(new FastJsonpHttpMessageConverter4());
	}
}
