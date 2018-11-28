package com.pansoft.ssm.controller;

import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @ClassName: LoginController.java
 * @Description:TODO(登陆校验的Controller)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月29日 下午3:43:45
 */
@Controller
public class LoginController {

	// 登陆
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String userpassword) throws Exception {
		// 调用 service 用户验证
		// 。。。。
		// 在session 中保持用户身份信息
		session.setAttribute("username", username);
		// 重定向到商品查询列表
		return "redirect:/items/queryitems.action";
	}
	// 退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		// 清除session 设置当前session过期
		// session.removeAttribute("username");
		session.invalidate();
		// 重定向到商品查询列表
		return "redirect:/items/queryitems.action";
	}

}
