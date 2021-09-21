package com.chngalaxy.dao;

import com.chngalaxy.pojo.OrderSetting;

import java.util.Date;

public interface OrderSettingDao {


    /**
     * 通过日期查询预约设置表
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate);

    /**
     * 更新最大预约数
     * @param orderSetting
     */
    void updateNumber(OrderSetting orderSetting);

    /**
     * 添加预约设置
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);
}
