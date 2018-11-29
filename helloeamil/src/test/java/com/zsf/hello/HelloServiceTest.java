package com.zsf.hello;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Description: test
 * @Author: ZhouShengfeng
 * @Date: 2018/11/29
 */
@RunWith(value = SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Test
    public void sayHello() {
        helloService.sayHello();
    }

}