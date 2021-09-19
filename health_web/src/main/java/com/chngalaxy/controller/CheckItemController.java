package com.chngalaxy.controller;


import com.chngalaxy.constant.MessageConstant;
import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.entity.Result;
import com.chngalaxy.pojo.CheckItem;
import com.chngalaxy.service.CheckItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkitem")
public class CheckItemController {
    @Autowired
    private CheckItemService checkItemService;

    @GetMapping("/findAll")
    public Result findAll(){
        //调用业务查询
        List<CheckItem> list = checkItemService.findAll();

        //返回结果
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,list);
    }

    @RequestMapping("/add")
    public Result add(@RequestBody CheckItem checkItem){
        //调用业务查询
        checkItemService.add(checkItem);

        //返回结果
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @PostMapping("findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean){
        //调用业务查询
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);

        //响应，包装到result
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS,pageResult);
    }

    /**
     * 删除检查项
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable int id){
        checkItemService.deleteById(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);

    }

    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable int id){
        CheckItem checkItem  = checkItemService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKITEM_SUCCESS,checkItem);
    }

    /**
     *  更新检查项
     * @param id
     * @return
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable int id,@RequestBody CheckItem checkItem){
        checkItem.setId(id);
        checkItemService.updata(checkItem);
        return new Result(true,MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }
}
