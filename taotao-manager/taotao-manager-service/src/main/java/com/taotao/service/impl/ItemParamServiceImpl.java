package com.taotao.service.impl;

import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 商品规格参数模版
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;


    @Override
    public TaotaoResult getItemParamByCid(Long itemCatId) {
        System.out.println("---jiij*******************");
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list2 = itemParamMapper.selectByExample(example);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        TbItemParam ii = itemParamMapper.selectByPrimaryKey(itemCatId);

        System.out.println("---jiij*******************"+ii);
        // 判断是否查询到结果
        if (list!=null && list.size() != 0){
            return TaotaoResult.ok(list.get(0));
        }

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult insertItemParam(TbItemParam itemParam) {

        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }
}
