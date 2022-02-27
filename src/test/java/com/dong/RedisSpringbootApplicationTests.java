package com.dong;

import com.dong.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class RedisSpringbootApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {

    }
}
