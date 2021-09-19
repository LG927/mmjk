package com.chngalaxy.service.impl;

import com.chngalaxy.dao.SetmealDao;
import com.chngalaxy.pojo.Setmeal;
import com.chngalaxy.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealDao setmealDao;

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(Setmeal setmeal, List<Integer> checkgroupIds) {
        //1.添加套餐
        setmealDao.add(setmeal);
        //2.获取新套餐的ID
        Integer setmealId = setmeal.getId();
        //3.非空判断 选中的检查组id集合
        if(null !=checkgroupIds){
            //4.遍历添加套餐与检查组的关系
            for (Integer checkgroupId : checkgroupIds) {
                setmealDao.addSetmealCheckGroup(setmealId,checkgroupId);
            }
        }
        //5.事物控制
    }
}
