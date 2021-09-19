package com.chngalaxy.service;

import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.pojo.CheckGroup;
import com.chngalaxy.pojo.CheckItem;

import java.util.List;

public interface CheckGroupService {


    /**
     * 分页查询
     * @param queryPageBean
     * @return
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组
     */
    void add(CheckGroup checkGroup, List<Integer> checkitemIds);


    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    CheckGroup findById(int id);

    /**
     * 通过检查组id查询选中的检查项id集合
     * @param id
     * @return
     */
    List<Integer> findCheckItemIdsByCheckGroupId(int id);

    /**
     * 修改检查组
     * @param checkGroup
     * @param checkitemIds
     */
    void update(CheckGroup checkGroup, List<Integer> checkitemIds);


    /**
     * 查询所有的检查组
     * @return
     */

    List<CheckGroup> findAll();


}
