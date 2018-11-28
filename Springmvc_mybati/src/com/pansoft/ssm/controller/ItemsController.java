package com.pansoft.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.codehaus.jackson.annotate.JsonTypeInfo.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.pansoft.ssm.controller.validation.ValidationGroup1;
import com.pansoft.ssm.exception.CustomException;
import com.pansoft.ssm.po.Items;
import com.pansoft.ssm.po.ItemsCustom;
import com.pansoft.ssm.po.ItemsQueryVo;
import com.pansoft.ssm.service.ItemsService;
import com.sun.org.apache.regexp.internal.recompile;

/**
 * 
 * @ClassName: ItemsController.java
 * @Description:TODO(商品的Controller)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月26日 下午7:52:29
 */
@Controller
//为了对url进行分类管理 可以定义根路径   最终访问url是 根路径+子路径  
//比如  ：商品列表  ：/items/queryItems.action
//@RequestMapping("items")
//限制HTTP的请求方法   可以post和get
@RequestMapping(value = "items", method = { RequestMethod.GET, RequestMethod.POST })

public class ItemsController {

	@Autowired
	private ItemsService itemsService;
	

	//商品分类
	//itemtypes 表示最终将方法返回值放到request中的  key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes(){
		
		Map<String, String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		
		return itemTypes;
	}

	

	// 商品的查询
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

//		测试forward之后request是否可以共享
		System.out.println(request.getParameter("id"));
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/itemsList");

		return modelAndView;
	}

//	// 商品信息的修改页面展示
//	@RequestMapping("/editItems")
//	public ModelAndView editItems() throws Exception {
//
//		// 调用service根据id查询商品信息
//		// 先测试 写死id
//		ItemsCustom itemsCustom = itemsService.findItemById(1);
//
//		// 返回modelandview
//		ModelAndView modelAndView = new ModelAndView();
//
//		// 将商品信息放入modelandview
//		modelAndView.addObject("itemsCustom", itemsCustom);
//
//		// 商品修改界面
//		modelAndView.setViewName("items/editItems");
////		返回modelandview
//		return modelAndView;
//	}

	// 商品信息的修改页面展示
	@RequestMapping("/editItems")
	// @RequestParam里面指定request传入参数名称进行形参绑定
	// 通过requried设置指定参数是否必须传入
	// 通过defaultValue可以设置默认值，如果参数没有传入，可以将默认值与形参进行绑定
	public String editItems(Model model,
			@RequestParam(value = "id", required = true, defaultValue = "") Integer items_id) throws Exception {

		// 调用service根据id查询商品信息
		// 先测试 写死id
		ItemsCustom itemsCustom = itemsService.findItemById(items_id);
		
		//判断商品是否为空  如果为空 抛出异常，提示用户商品信息不存在。
		if(itemsCustom == null){
			throw new CustomException("修改的商品信息不存在！");
		}

		// 返回modelandview
//			ModelAndView modelAndView = new ModelAndView();
//
//			// 将商品信息放入modelandview
//			modelAndView.addObject("itemsCustom", itemsCustom);
//
//			// 商品修改界面
//			modelAndView.setViewName("items/editItems");
//			返回modelandview

		// 使用形参中的model将model数据传到页面
		// 相当于modelandview.addobject方法
		model.addAttribute("itemsCustom", itemsCustom);

		return "items/editItems";
	}

	// 商品修改页面修改后提交信息
//	@RequestMapping("/editItemsSubmit")
//	public ModelAndView editItemsSubmit() throws Exception {
//
//		// 调用service更新信息，页面需要将商品信息传递到此方法
//		// 。。。。
//
//		// 返回modelandview
//		ModelAndView modelAndView = new ModelAndView();
//		// 返回一个成功页面
//		modelAndView.setViewName("success");
//		return modelAndView;
//
//	}
	// 商品修改页面修改后提交信息 重定向
	//在需要校验的  pojo  前添加  @validated ,在需要校验的 pojo 后添加 BindingResults bindingResults 接收验证出错信息
	//注意 ：  @validated 和 BindingResults bindingResults 配对出现 且顺序固定（一前一后）。
	//添加Model  将错误信息显示到页面
	//@Validated(value= {ValidationGroup1.class})  value指定使用ValidationGroup1分组的校验  
	//@ModelAttribute("items")	 可以指定 pojo  回显到request域的Key
//	MultipartFile items_pic 用于接收 图片数据
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit( Model model,HttpServletRequest request, Integer id, 
		@ModelAttribute("items")	@Validated(value= {ValidationGroup1.class}) ItemsCustom itemsCustom, 
		BindingResult bindingResult, MultipartFile items_pic
		) throws Exception {

		//或取校验错误信息
		if (bindingResult.hasErrors()) {
			//输出出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError objectError: allErrors) {
				//输出错误信息
				System.out.println(objectError.getDefaultMessage());
				
			}
			//将错误信息传到页面 
			model.addAttribute("allErroes", allErrors);
			
			//可以直接使用model将提交的 pojo 回显到页面
			model.addAttribute("items", itemsCustom);
			
			//出错重新到商品修改页面
			return "items/editItems";
   		}
		//获取上传图片原始名称   统一命名
		String oldname  = items_pic.getOriginalFilename();
		//上传图pain
		if (items_pic != null && oldname == null && oldname.length() > 0) {
			
			//存储图片的物理路径
			String pic_path = "D:\\Tomcat\\uploadSource";
			
		
			//生成新的图片名称    格式为   （ 随机数 + .文件格式）
			//oldname.substring(oldname.lastIndexOf("."))  字符串剪切 以最后一个  点  为剪切开始点
			String newname = UUID.randomUUID() + oldname.substring(oldname.lastIndexOf("."));
			//创建 存储 新图片
			File newfile = new java.io.File(pic_path + newname);
			//将内存中数据写入磁盘
			items_pic.transferTo(newfile);
			//将新图片名称写入到itemsCustom中
			itemsCustom.setPic(newname);
		}
		
		
		
		// 调用service 更新信息页面需要将商品信息传递到此方法
		// 。。。。
		// 调用service更新商品信息，页面需要将商品信息传到词方法
		itemsService.updateItems(id, itemsCustom);

		// 重定向 在同一个controller 中不用添加根路径
		// return "redirect:queryItems.action";
		// 页面转发
		// return "forward:queryItems.action";
		return "success";

	}

	// 批量删除商品的信息
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_id) throws Exception {
		// 调用service批量删除商品
		// 。。。。

		// 返回一个成功页面
		return "success";
	}

	//批量修个商品信息页面，将商品信息查询出来
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {

		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("itemsList", itemsList);
		modelAndView.setViewName("items/editItemsQuery");

		return modelAndView;
	}
	
	//批量修改商品的提交  
	//通过itemsqueryvo 接收批量提交的商品信息， 将商品信息存储到 itemsQueryVo中itemsList属性中
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		
		
		
		
		return "success";
	}
	
	//查询商品信息  输出json   
	///itemsView/{id} id表示 将这个位置的参数传递到  @PathVariable("id") 指定的 “id” 中 
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id )throws Exception{

		//调用  service  查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemById(id);
		
		return itemsCustom;
		
	}
	
	
	
	
	

}
