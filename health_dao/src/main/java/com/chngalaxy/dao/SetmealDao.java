package com.chngalaxy.dao;

import com.chngalaxy.pojo.Setmeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SetmealDao {

    /**
     * 添加套餐1
     * @param setmeal
     */
    void add(Setmeal setmeal);



    /**
     * 添加套餐与检查组之间的关系
     * @param setmealId
     * @param checkgroupId
     */
    void addSetmealCheckGroup(@Param("setmealId") Integer setmealId, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 查询所有的图片
     * @return
     */
    List<String> selectImgs();
}
