package com.zsf.hello;

import org.springframework.stereotype.Service;

/**
 * @program: helloeamil
 * @ClassName: HelloService
 * @description: hello word！
 * @author: 周生锋
 * @create: 2018-11-29 10:33
 **/
@Service
public class HelloService {

    public void sayHello() {
        System.out.println("hello world!!!");
    }
}
