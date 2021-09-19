package com.chngalaxy.service;

import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.pojo.CheckItem;

import java.util.List;

public interface CheckItemService {

    /**
     * 查询所有检查项
     * @return
     */
    List<CheckItem> findAll();

    /**
     * 新增检查项
     * @param checkItem
     */
    void add(CheckItem checkItem);

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 删除检查项
     * @param id
     */
    void deleteById(int id);

    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    CheckItem findById(int id);

    /**
     * 更新检查项
     * @param checkItem
     */
    void updata(CheckItem checkItem);
}
