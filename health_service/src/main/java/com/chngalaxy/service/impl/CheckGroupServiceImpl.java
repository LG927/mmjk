package com.chngalaxy.service.impl;

import com.chngalaxy.dao.CheckGroupDao;
import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.pojo.CheckGroup;
import com.chngalaxy.service.CheckGroupService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;


    /**
     * 检查组分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        //1.设置分页参数
        PageHelper.startPage(queryPageBean.getCurrentPage(),queryPageBean.getPageSize());
        //2.查询条件判断，有条件模糊查询
        if(!StringUtil.isEmpty(queryPageBean.getQueryString())){
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //3.条件查询
        Page<CheckGroup> checkGroupPage =  checkGroupDao.findByCondition(queryPageBean.getQueryString());
        //构建pageresult返回
        return  new PageResult<>(checkGroupPage.getTotal(),checkGroupPage.getResult());
    }


    /**
     * 添加检查组
     */
    @Override
    public void add(CheckGroup checkGroup, List<Integer> checkitemIds) {
        //1.先添加检查组
        checkGroupDao.add(checkGroup);
        //2.获取新增价检查组的id
        Integer checkGroupId = checkGroup.getId();
        //3.非空判断检查项id
        if(null !=checkGroupId){
            //4.遍历检查项ids
            for (Integer checkitemId : checkitemIds) {
                //5.添加检查组与检查项之间的关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
        //6.添加事物控制 (操作两次或两条记录以上)
    }

    /**
     * 通过id查询检查组
     * @param id
     * @return
     */
    @Override
    public CheckGroup findById(int id) {
        return checkGroupDao.findById(id);
    }

    /**
     * 通过检查组id查询选中的检查项id集合
     * @param id
     * @return
     */
    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(int id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    /**
     * 修改检查组
     *
     * @param checkGroup
     * @param checkitemIds
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(CheckGroup checkGroup, List<Integer> checkitemIds) {
        //1. 更新检查组
        checkGroupDao.update(checkGroup);
        Integer checkGroupId = checkGroup.getId();
        //2. 删除旧关系，通过检查组id来删除关系表
        checkGroupDao.deleteCheckGroupCheckItem(checkGroupId);
        //3. 非空判断
        if(null != checkitemIds) {
            //4. 遍历选中的检查项id集合
            for (Integer checkitemId : checkitemIds) {
                //5. 添加新关系
                checkGroupDao.addCheckGroupCheckItem(checkGroupId,checkitemId);
            }
        }
        //6. 事务控制
    }
}
