package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.result.TaotaoResult;

import java.util.List;

public interface ContentCategoryService {
    public List<EUTreeNode> getCategoryList(long parentId);

    TaotaoResult insertContentCategory(long parentId, String name);
}
