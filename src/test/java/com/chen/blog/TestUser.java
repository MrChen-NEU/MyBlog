package com.chen.blog;

import com.chen.blog.dao.BaseDao;
import com.chen.blog.dao.UserDao;
import com.chen.blog.po.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class TestUser {
    @Test
    public void testQueryUserByName() {
        UserDao userDao = new UserDao();
        User user = userDao.queryUserByName("admin");
        System.out.println(user.getUpwd());
    }


    @Test
    public void testAdd() {
        String sql = "insert into tb_user (uname,upwd,nick,head,mood) values (?,?,?,?,?)";
        List<Object> params = new ArrayList<>();
        params.add("chen");
        params.add("e10adc3949ba59abbe56e057f20f883e");
        params.add("chen");
        params.add("10.jpg");
        params.add("Hello");
        int row = BaseDao.executeUpdate(sql,params);
        System.out.println(row);

    }
}
