package com.pansoft.ssm.mapper;

import com.pansoft.ssm.po.Items;
import com.pansoft.ssm.po.ItemsCustom;
import com.pansoft.ssm.po.ItemsExample;
import com.pansoft.ssm.po.ItemsQueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param; 

public interface ItemsMapperCustom {
    //商品查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo)throws Exception;
}