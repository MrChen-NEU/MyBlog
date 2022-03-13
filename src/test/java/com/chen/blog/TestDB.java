package com.chen.blog;

import com.chen.blog.util.DBUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class TestDB {


    //使用日志工厂类
    private Logger logger = LoggerFactory.getLogger(TestDB.class);

    @Test
    public void textDB(){
        System.out.println(DBUtil.getConnection());

        //使用日志
        logger.info("获取数据库连接：" + DBUtil.getConnection());
    }
}
