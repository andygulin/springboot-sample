package springboot.sample.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "HelloController", description = "Hello API")
@RestController
public class HelloController {

    @ApiOperation(value = "返回字符串", httpMethod = "GET", response = String.class)
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
