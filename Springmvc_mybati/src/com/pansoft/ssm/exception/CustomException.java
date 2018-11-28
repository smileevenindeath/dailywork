package com.pansoft.ssm.exception;

/**
 * 
 * @ClassName: CustomException.java
 * @Description:TODO(系统自定义异常类,针对预期的异常，需要在程序中抛出此类的异常)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月28日 下午3:58:24
 */
public class CustomException extends Exception {

	// 异常信息
	public String message;

	public CustomException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
