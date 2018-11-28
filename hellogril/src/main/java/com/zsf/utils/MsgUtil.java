package com.zsf.utils;

import com.zsf.pojo.Result;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hellogril
 * @ClassName: ResultService
 * @description: 处理 json 格式
 * @author: 周生锋
 * @create: 2018-11-27 17:15
 **/
@RestController
public class MsgUtil {

    public static Result success(Object data) {
        Result result = new Result();
        result.setType("success");
        result.setCode(0);
        result.setMsg("成功");
        result.setData(data);
        return result;
    }

    public static Result success() {
        return success(null);
    }

    public static Result error(Integer code, String data) {
        Result result = new Result();
        result.setType("failed");
        result.setCode(code);
        result.setMsg(data);
        return result;

    }

}
