package com.pansoft.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.pansoft.ssm.exception.CustomException;
import com.pansoft.ssm.mapper.ItemsMapper;
import com.pansoft.ssm.mapper.ItemsMapperCustom;
import com.pansoft.ssm.po.Items;
import com.pansoft.ssm.po.ItemsCustom;
import com.pansoft.ssm.po.ItemsQueryVo;
import com.pansoft.ssm.service.ItemsService;

/**
 * 
 * @ClassName: ItemsServiceImpl.java
 * @Description:TODO(商品管理)
 * @author 周生锋
 * @version V1.0
 * @Date 2018年9月26日 下午5:54:04
 */
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	private ItemsMapperCustom itemsMapperCustom;

	// 使用逆向工程生成的mapper 注入mapper
	@Autowired
	private ItemsMapper itemsMapperer;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		
		// 通过ItemsMapperCustom查询数据库
		
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemById(Integer id) throws Exception {
		// itemsMapperer.selectByPrimaryKey(id) 返回的是items对象
		Items items = itemsMapperer.selectByPrimaryKey(id);
		//在 service 层  抛出异常  
		if(items == null){
			throw new CustomException("修改的商品信息不存在！");
		}

		
		// 中间对商品信息进行业务处理
		// -----很多代码----
		// 返回itemscustoms
		ItemsCustom itemsCustom = null;
		// 将items属性拷贝到itemscustom
		if (items != null) {
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}

		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		// 业务校验，通常在service接口对关键参数进行校验
		// 校验id是否为空，如果为空。。。。抛出异常处理
		if (id.equals("")) {

		} else {

		}
		// 更新商品信息updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段 ，包括大文本类型
//		updateByPrimaryKeyWithBLOBs要求必须传入id
		itemsCustom.setId(id);
		itemsMapperer.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}

}
