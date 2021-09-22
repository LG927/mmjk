package com.chngalaxy.service;

import com.chngalaxy.pojo.OrderSetting;

import java.util.List;
import java.util.Map;


public interface OrderSettingService {


    /**
     * 批量导入预约设置
     * @param osList
     */
    void doImport(List<OrderSetting> osList);

    /**
     * 根据日期查询预约设置数据
     * @param date
     * @return
     */
    List<Map> getOrderSettingByMonth(String date);

    /**
     * 根据指定日期修改可预约人数
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);
}
