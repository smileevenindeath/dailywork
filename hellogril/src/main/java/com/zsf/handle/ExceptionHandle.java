package com.zsf.handle;

import com.zsf.exception.GrilException;
import com.zsf.pojo.Result;
import com.zsf.utils.MsgUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @program: hellogil
 * @ClassName: ExceptionHandle
 * @description: 自定义异常捕获处理
 * @author: 周生锋
 * @create: 2018-11-27 18:35
 **/
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @Description: 自定义异常处理
     * @Param: [e]
     * @return: com.zsf.pojo.Result
     * @Author: ZhouShengfeng
     * @Date: 2018/11/27
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof GrilException) {
            GrilException grilException = (GrilException) e;
            return MsgUtil.error(grilException.getCode(), grilException.getMessage());
        } else {
            logger.error("【系统异常】{}", e);
            return MsgUtil.error(-1, "未知错误");
        }

    }
}
