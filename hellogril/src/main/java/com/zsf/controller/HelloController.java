package com.zsf.controller;

import com.zsf.properties.Grilproperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hellogril
 * @ClassName: HelloController
 * @description: demo
 * @author: 周生锋
 * @create: 2018-11-27 10:19
 **/
/*
 * @RestController 等同于  @Controller + @ResponseBody
 * */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private Grilproperties grilproperties;

    @Value("${cupSize}")
    private String cupsize;

    @Value("${age}")
    private String age;

    @Value("${content}")
    private String content;

    /*
     * @PathVaiable("id")  获取请求url 中的值 value = "/say/{id}"   localhost:8080/hello/sqy/100
     * @RequestParam("id")  value = "/say"    localhost:8080/hello/sqy?id=100
     * @RequestParam(value = "id", required = false, defaultValue = "0")  设置为 非必填  默认值 0
     * RequestMapping  简便写法   不用特意声明请求方法
     * @GetMapping
     * @Postmapping
     * @DeleteMapping
     * @PutMapping
     * */
    //@RequestMapping(value = "/say/{id}", method = RequestMethod.GET)
    @GetMapping(value = "/say")
    public String sqy(@RequestParam(value = "id", required = false, defaultValue = "0") Integer id) {
        return "id : " + id;
        //return "hello springboot";
        //return cupsize + age;
        //return content;
        //return grilproperties.getCupSize() + grilproperties.getAge() ;
    }


}
