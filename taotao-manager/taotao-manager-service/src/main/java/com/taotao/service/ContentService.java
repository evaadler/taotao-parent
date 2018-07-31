package com.taotao.service;

import com.taotao.pojo.TbContent;
import com.taotao.result.TaotaoResult;

import java.util.List;

public interface ContentService {
    public TaotaoResult insertContent(TbContent content);
    public List<TbContent> getContentList(long contentCid);
}
