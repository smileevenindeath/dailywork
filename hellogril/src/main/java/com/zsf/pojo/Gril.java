package com.zsf.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @program: hellogril
 * @ClassName: Gril
 * @description: bean
 * @author: 周生锋
 * @create: 2018-11-27 11:52
 **/


@Entity //@entity 表示该类对应数据库中一个表
public class Gril {

    @Id
    @GeneratedValue // 自增
    private Integer id;
    private String cupSize;
    @Min(value = 18, message = "未成年少女禁止入内")
    private Integer age;
    @NotNull(message = "金额必填")
    private BigDecimal money;

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Gril() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Gril{" +
                "id=" + id +
                ", cupSize='" + cupSize + '\'' +
                ", age=" + age +
                ", money=" + money +
                '}';
    }
}
