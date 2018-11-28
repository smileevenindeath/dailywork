package com.pansoft.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 
 * @ClassName:  CustomDateConverter.java
 * @Description:TODO(自定义的参数绑定：日期转换器) 
 * @author      周生锋
 * @version     V1.0  
 * @Date        2018年9月27日 下午4:16:43
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
	
		//实现将日期串转成日期类型
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//转换成功直接返回
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		//如果参数绑定失败则返回null
		return null;
	}
	

}
  
