package com.pansoft.ssm.service;

import java.util.List;
import com.pansoft.ssm.po.ItemsCustom;
import com.pansoft.ssm.po.ItemsQueryVo;

/**
 * @ClassName:  ItemsService.java
 * @Description:TODO(商品管理) 
 * @author      周生锋
 * @version     V1.0  
 * @Date        2018年9月27日 上午9:43:53 
 */

public interface ItemsService {
	
	//商品查询列表
	/**
	 * 
	 * @Title:        findItemsList 
	 * @Description:  TODO(提供商品查询的接口) 
	 * @param:        @param itemsQueryVo
	 * @param:        @return
	 * @param:        @throws Exception    
	 * @return:       List<ItemsCustom>    
	 * @throws 
	 * @author        周生锋
	 * @Date          2018年9月27日 上午9:49:23
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//商品查询（id）
	/**
	 * 
	 * @Title:        findItemById 
	 * @Description:  TODO(提供根据id查询商品的接口) 
	 * @param:        @param id
	 * @param:        @return 
	 * @param:        @throws Exception    
	 * @return:       ItemsCustom    
	 * @throws 
	 * @author        周生锋
	 * @Date          2018年9月27日 上午9:49:43
	 */
	public  ItemsCustom findItemById(Integer id) throws Exception;
	
	//商品修改
	/**
	 * 
	 * @Title:        updateItems 
	 * @Description:  TODO( 提供商品修改的接口) 
	 * @param:        @param id 修改商品的id
	 * @param:        @param itemsCustom 要修改的商品信息
	 * @param:        @throws Exception    
	 * @return:       void    
	 * @throws 
	 * @author        周生锋
	 * @Date          2018年9月27日 上午9:48:19
	 */
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;
	
}
