package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EUDataGridResult;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.result.TaotaoResult;
import com.taotao.service.ItemService;
import com.taotao.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper itemDescMapper;

    @Autowired
    private TbItemParamItemMapper itemParamMapper;

    @Override
    public TbItem getItemById(long itemId) {
        TbItemExample example = new TbItemExample();
        // 添加查询条件
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size()>0){
            TbItem item = list.get(0);
            return item;
        }
        return null;
    }

    @Override
    public EasyUIResult getItemList(Integer page, Integer rows) {
        TbItemExample example = new TbItemExample();

        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);

        PageInfo<TbItem> pageInfo = new PageInfo<>();
        long total  = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult(total, list);
        return result;
    }




    @Override
    public TaotaoResult createItem(TbItem item, String desc, String itemParam) throws Exception {
        // item 补全
        // 生成商品id
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        // 商品状态： 1-正常 2-下架 3-删除
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());

        itemMapper.insert(item);

        // 添加商品描述信息
        TaotaoResult result = insertItemDesc(itemId, desc);

        if (result.getStatus() != 200){
            throw new Exception();
        }
        // 添加规格参数
        TaotaoResult insertItemParamItem = insertItemParamItem(itemId, itemParam);
        if (result.getStatus() != 200){
            throw new Exception();
        }

        return TaotaoResult.ok();
    }

    /**
     * 添加商品描述
     * @param itemId
     * @param desc
     * @return
     */
    private TaotaoResult insertItemDesc(Long itemId, String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(new Date());
        itemDesc.setUpdated(new Date());

        itemDescMapper.insert(itemDesc);
        return TaotaoResult.ok();
    }

    /**
     * 添加规格参数
     * @param itemId
     * @param itemParam
     * @return
     */
    private TaotaoResult insertItemParamItem(Long itemId, String itemParam){
        //创建pojo
        TbItemParamItem item = new TbItemParamItem();
        item.setCreated(new Date());
        item.setItemId(itemId);
        item.setParamData(itemParam);
        item.setUpdated(new Date());

        // 向表中插入数据
        itemParamMapper.insert(item);

        return TaotaoResult.ok();
    }


}
