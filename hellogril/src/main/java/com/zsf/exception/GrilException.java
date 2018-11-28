package com.zsf.exception;

import com.zsf.enums.MsgCodeEnums;

/**
 * @program: hellogril
 * @ClassName: GrilException
 * @description: 自定义信息异常处理 code
 * @author: 周生锋
 * @create: 2018-11-27 18:44
 **/

public class GrilException extends RuntimeException {
    private Integer code;

    public GrilException(MsgCodeEnums msgCodeEnums) {
        super(msgCodeEnums.getMsg());
        this.code = msgCodeEnums.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }


}
