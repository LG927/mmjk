package com.chngalaxy.dao;

import com.chngalaxy.pojo.CheckItem;
import com.github.pagehelper.Page;

import java.util.List;

public interface CheckItemDao {


    /**
     * 查询所有
     * @return
     */
    List<CheckItem> findAll();


    /**
     * 新增检查项
     */
    void add(CheckItem checkItem);


    /**
     * 检查项分页查询
     * @param queryString
     * @return
     */
    Page<CheckItem> findByCodition(String queryString);

    /**
     * 统计这个检查项id被检查组使用的次数
     * @param id
     * @return
     */
    int findCountByCheckItemId(int id);

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
    void update(CheckItem checkItem);
}
