package com.taotao.rest.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.impl.ItemCatService;
import com.taotao.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类列表
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    // 加入MediaType解决返回json数据乱码问题，指定用utf-8字符集
    @RequestMapping(value = "/itemcat/list", produces = MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        // 把pojo转换成字符串，直接返回对象数据不行，因为是jsonp跨越调用
        String json = JsonUtils.objectToJson(catResult);
        // 拼装返回值
        String result = callback + "(" + json + ")";
        return result;
    }

    @RequestMapping(value = "/itemcat4/list")
    @ResponseBody
    public Object getItemCatLsit_spring4(String callback){
        CatResult catResult = itemCatService.getItemCatList();
        // 把pojo转换成字符串，直接返回对象数据不行，因为是jsonp跨越调用
        String json = JsonUtils.objectToJson(catResult);
        // 新的工具类
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
        mappingJacksonValue.setJsonpFunction(callback);
        return mappingJacksonValue;
    }
}
