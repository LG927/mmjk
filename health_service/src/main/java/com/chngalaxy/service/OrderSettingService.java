package com.chngalaxy.service;

import com.chngalaxy.pojo.OrderSetting;

import java.util.List;


public interface OrderSettingService {


    /**
     * 批量导入预约设置
     * @param osList
     */
    void doImport(List<OrderSetting> osList);
}
