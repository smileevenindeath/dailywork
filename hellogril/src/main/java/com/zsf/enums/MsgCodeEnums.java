package com.zsf.enums;

public enum MsgCodeEnums {
    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_SCHOOL(100, "小学生"),
    MIDDLE_SCHOOL(101, "初中生"),
    ADULT(102, "成年人")
    ;

    private Integer code;

    private String msg;

    MsgCodeEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
