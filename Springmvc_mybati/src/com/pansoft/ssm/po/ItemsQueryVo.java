package com.pansoft.ssm.po;

import java.util.List;

/**
 * 
 * @ClassName:  ItemsQueryVo.java
 * @Description:TODO(商品包装对象) 
 * @author      周生锋
 * @version     V1.0  
 * @Date        2018年9月26日 下午4:53:17
 */
public class ItemsQueryVo {
	
	//商品信息
	private Items items;
	
	//为了系统 可扩展性，对原始生成的po进行扩展
	private ItemsCustom itemsCustom;
	
	//批量商品信息
	private List<ItemsCustom> itemsList;
	
	//用户信息
	//private UserCustom usertom;

	public Items getItems() {
		return items;  
	}

	public void setItems(Items items) {
		this.items = items;
	}

	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}

	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}

	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}

	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
	
	

}
