package com.chngalaxy.controller;

import com.chngalaxy.constant.MessageConstant;
import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.entity.Result;
import com.chngalaxy.pojo.CheckGroup;
import com.chngalaxy.pojo.CheckItem;
import com.chngalaxy.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/checkgroup")
public class CheckGroupController {

    @Autowired
    private CheckGroupService checkGroupService;


    /**
     * 检查组分页查询
     * @return
     */
   @PostMapping("/findPage")
   public Result findPage(@RequestBody QueryPageBean queryPageBean){
       // 调用业务查询
       PageResult<CheckGroup> pageResult = checkGroupService.findPage(queryPageBean);
       // 响应，包装到Result
       return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS,pageResult);
   }

    /**
     * 添加检查组
     * @param checkGroup formdata数据 检查组信息
     * @param checkitemIds 选中的检查项id集合
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody CheckGroup checkGroup,@RequestParam List<Integer> checkitemIds){
        checkGroupService.add(checkGroup,checkitemIds);
        return new Result(true,MessageConstant.ADD_CHECKGROUP_SUCCESS);
    }

    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result findById(@PathVariable int id){
        CheckGroup checkGroup = checkGroupService.findById(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkGroup);
    }

    /**
     *通过检查组id查询选中的检查项id集合
     * @param id
     * @return
     */
    @GetMapping("/findCheckItemIdsByCheckGroupId/{id}")
    public Result findCheckItemIdsByCheckGroupId(@PathVariable int id){
        List<Integer> checkitemids = checkGroupService.findCheckItemIdsByCheckGroupId(id);
        return new Result(true,MessageConstant.QUERY_CHECKGROUP_SUCCESS,checkitemids);
    }

    /**
     * 修改检查组
     * @param checkGroup formData数据 检查组信息
     * @param checkitemIds 选中的检查项id集合
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody CheckGroup checkGroup, @RequestParam List<Integer> checkitemIds){
        checkGroupService.update(checkGroup, checkitemIds);
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }
}
