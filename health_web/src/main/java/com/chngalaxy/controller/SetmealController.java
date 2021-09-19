package com.chngalaxy.controller;

import com.chngalaxy.constant.MessageConstant;
import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.entity.Result;
import com.chngalaxy.pojo.CheckGroup;
import com.chngalaxy.pojo.Setmeal;
import com.chngalaxy.service.SetmealService;
import com.chngalaxy.utils.QiNiuUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.channels.MulticastChannel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    //p1: 记录哪个类字节码，打印的log日志，会输入它这个日志是在哪个类且第几行代码
    private static final Logger log = LoggerFactory.getLogger(SetmealController.class);

    @Autowired
    private SetmealService setmealService;

    /**
     *  添加套餐时 上传图片
     * @param imgFile
     * @return
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile imgFile){
        //1.获取文件名
        String filename = imgFile.getOriginalFilename();
        //2.获取文件名后缀
        String suffix = filename.substring(filename.indexOf("."));
        //3.生成一个唯一的文件名
        String imgName = UUID.randomUUID().toString() + suffix;
        //4.调用七牛工具上传文件
        try {
            QiNiuUtils.uploadViaByte(imgFile.getBytes(),imgName);
            //5.构建map
            Map<String,String> resultMap = new HashMap<String,String>();
            //6. 2个key: domain, imgName
            //返回结果给前端：
            /**
             * result{
             *     flag:
             *     message:
             *     data:{
             *     	  imgName: 唯一的文件名(七牛上的文件名) 补充formData.img
             * 		  domain: 七牛上bucket的域名  imageUrl=domain+imgName
             *     }
             * }
             */
            resultMap.put("domain",QiNiuUtils.DOMAIN);
            resultMap.put("imgName",imgName);
            //返回resullt
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS,resultMap);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("上传文件失败",e);
            return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
        }
    }

    /**
     * 添加套餐
     * @param setmeal
     * @param checkgroupIds
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam List<Integer> checkgroupIds){
        setmealService.add(setmeal,checkgroupIds);
        return new Result(true,MessageConstant.ADD_SETMEAL_SUCCESS);
    }


}
