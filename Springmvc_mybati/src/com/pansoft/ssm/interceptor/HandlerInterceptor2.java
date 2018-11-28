package com.pansoft.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @ClassName:  HandlerInterceptor1.java
 * @Description:TODO(测试拦截器1) 
 * @author      周生锋
 * @version     V1.0  
 * @Date        2018年9月29日 下午2:40:01
 */
public class HandlerInterceptor2 implements HandlerInterceptor{

	//在进入handler方法之前 执行
	//应用场景:用于身份认证，身份授权 
	//比如 身份认证， 如果认证不通过，表示当前用户未登录，需要此方法拦截不在向下执行
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("HandlerInterceptor2...preHandle");
		
		
		//return false 表示 拦截住了 不向下执行
		//return true  表示放行
		return false;
	}

	//进入Handle方法在返回ModelandView之前 执行
	//应用场景从modelandview 出发: 将公用的模型数据（比如菜单的导航）在这里传到视图，也可以在这里统一指定指定
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("HandlerInterceptor2...postHandle");
		
	}

	//执行Handler完成之后执行
	//应用场景: 统一的异常处理、 统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptor2...afterCompletion");
	}

	
	
	
	
}
  
