package com.taotao.rest.service.impl;

import com.taotao.rest.pojo.CatResult;

import java.util.List;

public interface ItemCatService {
    public List<?> getCatList(long parentId);

    public CatResult getItemCatList();
}
