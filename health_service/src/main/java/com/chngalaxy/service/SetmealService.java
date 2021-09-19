package com.chngalaxy.service;

import com.chngalaxy.pojo.Setmeal;

import java.util.List;

public interface SetmealService {

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */
    void add(Setmeal setmeal, List<Integer> checkgroupIds);
}
