package com.zsf.pojo;

import org.springframework.stereotype.Component;

/**
 * @program: hellogril
 * @ClassName: Result
 * @description: 定义http请求返回json格式
 * @author: 周生锋
 * @create: 2018-11-27 17:03
 **/
@Component
public class Result<T> {

    /**
     * 消息类型
     */
    private String type;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
