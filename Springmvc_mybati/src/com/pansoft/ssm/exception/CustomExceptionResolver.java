package com.pansoft.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.AbstractDocument.LeafElement;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName: CustomExceptionResolver.java
 * @Description:TODO(全局异常处理器)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月28日 下午4:28:18
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {
	/**
	 * 
	 * <p>
	 * Title: resolveException
	 * </p>
	 * <p>
	 * Description:异常信息处理
	 * </p>
	 * 
	 * @param request
	 * @param response
	 * @param handler
	 * @param ex       系统抛出的异常
	 * @return
	 * @see org.springframework.web.servlet.HandlerExceptionResolver#resolveException(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      java.lang.Exception)
	 */
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		// handle 就是 处理器适配器要执行的Handle对象（只有method）

//		// 1.如果异常是系统自定义的异常，直接取出异常信息，在错误页面展示。
//		String message = null;
//		if (ex instanceof CustomException) {
//			message = ((CustomException) ex).getMessage();
//		} else {
//			// 2.如果异常不是系统自定义的异常 ，构造一个系统自定义的异常类型（信息为：未知错误）
//			message="未知错误";
//			
//			
//		}

		// 上边代码变为
		CustomException exception = null;

		if (ex instanceof CustomException) {
			exception = (CustomException) ex;
		} else {
			// 2.如果异常不是系统自定义的异常 ，构造一个系统自定义的异常类型（信息为：未知错误）
			exception = new CustomException("未知错误");
 
		}
		//错误信息
		String message = exception.getMessage();

		ModelAndView modelAndView = new ModelAndView();
		//将错误信息传到页面
		modelAndView.addObject("errorMessage", message);
		
		//指向错误界面
		modelAndView.setViewName("errorException");
		return modelAndView;
	}

}
