package com.chngalaxy.service.impl;

import com.chngalaxy.dao.OrderSettingDao;
import com.chngalaxy.exception.MyException;
import com.chngalaxy.pojo.OrderSetting;
import com.chngalaxy.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;
    /**
     * 批量导入预约设置
     * @param osList
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void doImport(List<OrderSetting> osList) {
        //1.非空判断
        if(!CollectionUtils.isEmpty(osList)){
            //2.遍历
            for (OrderSetting orderSetting : osList) {
                //3. 获取导入的设置信息的日期
                Date orderDate = orderSetting.getOrderDate();
                //4. 通过日期查询预约设置表
                OrderSetting osInDB = orderSettingDao.findByOrderDate(orderDate);
                //5.如果记录存在
                if(null !=osInDB){
                    //5.1取出数据库中的已预约人数
                    int reservations = osInDB.getReservations();
                    //5.2取出要更新的最大的预约数
                    int number = osInDB.getNumber();
                    //5.3如果已预约人数>要更新的最大的预约人数，报错
                    if(reservations>number){
                        throw new MyException("最大预约数不能小于已预约人数");
                    }
                    //5.4否则可以更新最大预约数
                    orderSettingDao.updateNumber(orderSetting);
                }else {
                    //6.如果记录不存在，添加新纪录
                    orderSettingDao.add(orderSetting);
                }
                //7.事物控制
            }
        }
    }

    /**
     * 根据日期查询预约设置数据
     * @param date
     * @return
     */
    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        // 1.组织查询Map，dateBegin表示月份开始时间，dateEnd月份结束时间
        String dateBegin = date + "-1";//2019-03-1
        String dateEnd = date + "-31";//2019-03-31
        Map map = new HashMap();
        map.put("dateBegin",dateBegin);
        map.put("dateEnd",dateEnd);
        // 2.查询当前月份的预约设置
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        // 3.将List<OrderSetting>，组织成List<Map>
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            orderSettingMap.put("date",orderSetting.getOrderDate().getDate());//获得日期（几号）
            orderSettingMap.put("number",orderSetting.getNumber());//可预约人数
            orderSettingMap.put("reservations",orderSetting.getReservations());//已预约人数
            data.add(orderSettingMap);
        }
        return data;
    }

    //根据日期修改可预约人数
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            //当前日期已经进行了预约设置，需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            //当前日期没有进行预约设置，进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }
}
