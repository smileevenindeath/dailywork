package com.zsf.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: hellogril
 * @ClassName: Grilproperties
 * @description:
 * @author: 周生锋
 * @create: 2018-11-27 10:42
 **/
@Component
@ConfigurationProperties(prefix = "gril")
public class Grilproperties {

    private String cupSize;

    private Integer age;

    public String getCupSize() {
        return cupSize;
    }

    public void setCupSize(String cupSize) {
        this.cupSize = cupSize;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
