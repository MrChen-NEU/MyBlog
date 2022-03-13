package com.chen.blog.dao;

import com.chen.blog.po.User;
import com.chen.blog.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    public User queryUserByName02(String userName){
        User user = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try{
             //1. 获取数据库连接
            connection = DBUtil.getConnection();
            // 2. 定义sql语句
            String sql = "select * from tb_user where uname = ?";
            // 3. 预编译
            preparedStatement = connection.prepareStatement(sql);
            // 4. 设置参数
            preparedStatement.setString(1, userName);
            // 5. 执行查询，返回结果集
            resultSet = preparedStatement.executeQuery();
            // 6. 判断并分析结果集
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("userId"));
                user.setUname(userName);
                user.setHead(resultSet.getString("head"));
                user.setMood(resultSet.getString("mood"));
                user.setNick(resultSet.getString("nick"));
                user.setUpwd(resultSet.getString("upwd"));
            }
        }catch(Exception e){
            e.printStackTrace(); //打印异常
        }finally{
            //7.关闭资源
            DBUtil.close(resultSet,preparedStatement,connection);
        }
        return user;
    }



    public User queryUserByName(String userName) {
        // 1. 定义sql语句
        String sql = "select * from tb_user where uname = ?";

        // 2. 设置参数集合
        List<Object> params = new ArrayList<>();
        params.add(userName);

        // 3. 调用BaseDao的查询方法
        User user = (User) BaseDao.queryRow(sql, params, User.class);

        return user;
    }

    /*
    通过昵称与id查询用户对象
     1. 定义SQL语句
     通过用户ID查询除了当前登录用户之外是否有其他用户使用了该昵称
     指定昵称  nick （前台传递的参数）
     当前用户  userId （session作用域中的user对象）
     String sql = "select * from tb_user where nick = ? and userId != ?";
     2. 设置参数集合
     3. 调用BaseDao的查询方法
     */
    public User queryUserByNickAndUserId(String nick, Integer userId) {
        // 1. 定义SQL语句
        String sql = "select * from tb_user where nick = ? and userId != ?";
        //2. 设置参数集合
        List<Object> params=new ArrayList<>();
        params.add(nick);
        params.add(userId);
        //3. 调用BaseDao的查询方法
        User user= (User) BaseDao.queryRow(sql,params,User.class);
        return user;
    }

    /*
     * 更新用户信息
     通过用户ID修改用户信息
     1. 定义SQL语句
     String sql = "update tb_user set nick = ?, mood = ?, head = ? where userId = ? ";
     2. 设置参数集合
     3. 调用BaseDao的更新方法，返回受影响的行数
     4. 返回受影响的行数
     */
    public int updateUser(User user) {
        //1. 定义SQL语句
        String sql = "update tb_user set nick = ?, mood = ?, head = ? where userId = ? ";
        //2. 设置参数集合
        List<Object> params=new ArrayList<>();
        params.add(user.getNick());
        params.add(user.getMood());
        params.add(user.getHead());
        params.add(user.getUserId());
        //3. 调用BaseDao的更新方法，返回受影响的行数
        int row=BaseDao.executeUpdate(sql,params);
        return row;
    }
}
