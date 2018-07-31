package com.taotao.rest.service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.CatResult;
import com.taotao.rest.service.impl.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 查询分类列表
     * @param parentId
     * @return
     */
    @Override
    public List<?> getCatList(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.addParentIdEqualTo(parentId);

        // 返回值list
        List  resultList = new ArrayList<>();

        List<TbItemCat> list = itemCatMapper.selectByExample(example);

        for (TbItemCat tbItemCat : list){
            // 判断是否是父节点
            if(tbItemCat.getIsParent()) {
                CatNode node = new CatNode();
                if (parentId == 0) {
                    node.setName("<a href='/products/" + tbItemCat.getId() + ".html" + tbItemCat.getName() + "></a>");
                } else {
                    node.setName(tbItemCat.getName());
                }
                node.setUrl("/products/" + tbItemCat.getId() + ".html");
                node.setItem(getCatList(tbItemCat.getId()));

                resultList.add(node);
            }else{
                resultList.add("/products/"+tbItemCat.getId()+".html|"+tbItemCat.getName());
            }
        }

        return resultList;
    }

    @Override
    public CatResult getItemCatList() {
        CatResult catResult = new CatResult();
        catResult.setItemCat(itemCatMapper.selectByExample(new TbItemCatExample()));
        return catResult;
    }
}
