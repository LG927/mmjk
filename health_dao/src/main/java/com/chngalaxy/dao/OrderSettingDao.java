package com.chngalaxy.dao;

import com.chngalaxy.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 根据日期查询预约设置数据(获取指定日期所在月份的预约设置数据)
     * @param map
     * @return
     */
    List<OrderSetting> getOrderSettingByMonth(Map map);

    long findCountByOrderDate(Date orderDate);

    void editNumberByOrderDate(OrderSetting orderSetting);
}
