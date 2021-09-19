package com.chngalaxy.dao;

import com.chngalaxy.pojo.CheckGroup;
import com.chngalaxy.pojo.CheckItem;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CheckGroupDao {


    /**
     * 检查组分页查询
     * @param queryString
     * @return
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 添加检查组
     * @param checkGroup
     */
    void add(CheckGroup checkGroup);



    /**
     * 添加检查组与检查项关系
     * @param checkGroupId
     * @param checkitemId
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkitemId") Integer checkitemId);

    /**
     *
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
     * 更新检查组
     * @param checkGroup
     */
    void update(CheckGroup checkGroup);

    /**
     * 通过检查组id来删除检查组与检查项关系表
     * @param checkGroupId
     */
    void deleteCheckGroupCheckItem(Integer checkGroupId);


}
