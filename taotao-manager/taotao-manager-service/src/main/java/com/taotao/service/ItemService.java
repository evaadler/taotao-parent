package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.pojo.TbItem;
import com.taotao.result.TaotaoResult;

public interface ItemService {
    public TbItem getItemById(long itemId);

    EasyUIResult getItemList(Integer page, Integer rows);

    TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception;

}
