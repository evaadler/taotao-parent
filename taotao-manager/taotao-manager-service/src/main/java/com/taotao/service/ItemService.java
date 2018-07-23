package com.taotao.service;

import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.pojo.TbItem;
import com.taotao.result.TaotaoResult;

public interface ItemService {
    public TbItem getItemById(long itemId);

    EUDataGridResult getItemList(Integer page, Integer rows);

    TaotaoResult createItem(TbItem item, String desc) throws Exception;
}
