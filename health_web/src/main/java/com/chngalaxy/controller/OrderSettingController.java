package com.chngalaxy.controller;

import com.chngalaxy.constant.MessageConstant;
import com.chngalaxy.entity.Result;
import com.chngalaxy.pojo.OrderSetting;
import com.chngalaxy.service.OrderSettingService;
import com.chngalaxy.utils.POIUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ordersetting")
public class OrderSettingController {
    private static final Logger log = LoggerFactory.getLogger(OrderSettingController.class);

    @Autowired
    private OrderSettingService orderSettingService;

    /**
     *  批量导入预约设置
     * @param excelFile
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile excelFile){
        try {
            //1.POIUtils读取excel内容
            List<String[]> dataList = POIUtils.readExcel(excelFile);
            // 【注意取反!】如果dataList集合不为空，且dataList有值
            if(!CollectionUtils.isEmpty(dataList)){
                //2. List<String[]> 转成List<OrderSetting>
                List<OrderSetting> osList = new ArrayList<>();
                SimpleDateFormat sdf = new SimpleDateFormat(POIUtils.DATE_FORMAT);
                for (String[] stringArr : dataList) {
                    //1.日期
                    String orderDateStr = stringArr[0];
                    //2.可预约数量
                    Integer number = Integer.valueOf(stringArr[1]);
                    OrderSetting os = new OrderSetting();
                    os.setNumber(number);
                    //3.要转成日期类型
                    os.setOrderDate(sdf.parse(orderDateStr));
                    //加入到list
                    osList.add(os);
                }
                //3.调用业务批量导入
                orderSettingService.doImport(osList);
                //4.返回成功
                return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
            }
        } catch (Exception e) {
            log.error("批量导入预约数据失败",e);
        }
        return new Result(true, MessageConstant.IMPORT_ORDERSETTING_FAIL);
    }
}
