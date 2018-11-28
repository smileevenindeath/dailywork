package com.pansoft.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pansoft.ssm.po.ItemsCustom;

/**
 * 
 * @ClassName: JsonDemo.java
 * @Description:TODO(Json交互测试)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月29日 上午10:24:39
 */
@Controller
public class JsonDemo {

	// 请求json(商品信息) , 输出 json（商品信息）
	// @RequestBody将请求的商品信息j'son 串 转成ItemsCuston 对象
	// @responseBody 将 ItemsCuston 对象 转成 j'son 格式 输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) throws Exception {

		// @responseBody 将 ItemsCuston 对象 转成 j'son 格式 输出
		return itemsCustom;
	}
	
	
	
	// 请求key/value(商品信息) , 输出 json（商品信息）
	// @responseBody 将 ItemsCuston 对象 转成 j'son 格式 输出
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom responJson( ItemsCustom itemsCustom) throws Exception {

		// @responseBody 将 ItemsCuston 对象 转成 j'son 格式 输出
		return itemsCustom;
	}

}
