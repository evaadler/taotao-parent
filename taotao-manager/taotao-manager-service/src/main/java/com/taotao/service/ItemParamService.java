package com.taotao.service;

import com.taotao.pojo.TbItemParam;
import com.taotao.result.TaotaoResult;

/**
 * 规格参数 ItemParam
 */
public interface ItemParamService {
    TaotaoResult getItemParamByCid(Long itemCatId);
    TaotaoResult insertItemParam(TbItemParam itemParam);

}
