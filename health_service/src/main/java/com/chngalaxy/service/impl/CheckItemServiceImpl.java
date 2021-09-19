package com.chngalaxy.service.impl;

import com.chngalaxy.dao.CheckItemDao;
import com.chngalaxy.entity.PageResult;
import com.chngalaxy.entity.QueryPageBean;
import com.chngalaxy.exception.MyException;
import com.chngalaxy.pojo.CheckItem;
import com.chngalaxy.service.CheckItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    /**
     * 查询所有检查项
     * @return
     */
    @Override
    public List<CheckItem> findAll() {
        return checkItemDao.findAll();
    }

    /**
     * 添加检查项
     * @param checkItem
     */
    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }


    /**
     * 检查项分页查询
     * @param queryPageBean
     * @return
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        //1.设置分页参数 p1: 页码p2: 每页大小, 把页码与大小放进本线程
        Page<Object> page = PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());
        //2.条件查询 【注意】非空 要加 !
        //if(null != queryPageBean.getQueryString() && queryPageBean.getQueryString().length()==0)
        if(!StringUtil.isEmpty(queryPageBean.getQueryString())){
            //2.1条件判断
            // 有条件 拼接%，模糊查询
            queryPageBean.setQueryString("%"+queryPageBean.getQueryString()+"%");
        }
        //3.条件查询
        Page<CheckItem> checkItemPage =  checkItemDao.findByCodition(queryPageBean.getQueryString());
        //4.获取查询结果
        //5.获取总数
        long total = checkItemPage.getTotal();
        //6.分页结果、
        List<CheckItem> list = checkItemPage.getResult();
        //7.包装到PageResult再返回
        return new PageResult<>(total,list);
    }

    /**
     * 删除检查项
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //1.通过检查项id查询这个id是否被检查组使用了
        //2.统计这个检查项的id被检查组使用的次数
        int count = checkItemDao.findCountByCheckItemId(id);
        //3.计数>0被使用，报错，该检查项使用了，不能删除
        if(count>0){
            throw new MyException("该检查项被检查组使用了，无法删除");
        }
        //4.计数=0，没被使用，就可以删除
        checkItemDao.deleteById(id);
    }


    /**
     * 通过id查询检查项
     * @param id
     * @return
     */
    @Override
    public CheckItem findById(int id) {
        return checkItemDao.findById(id);
    }

    /**
     * 更新检查项
     * @param checkItem
     */
    @Override
    public void updata(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }


}
