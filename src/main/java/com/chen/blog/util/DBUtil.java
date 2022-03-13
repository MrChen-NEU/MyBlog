package com.chen.blog.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {

    //1.得到文件配置对象
    private static Properties properties = new Properties();

    static{

        try {
            //加载配置文件，输入流
            InputStream in = DBUtil.class.getClassLoader().getResourceAsStream("db.properties");

            //通过load方法将输入流de内容加载到文件配置对象
            properties.load(in);

            //通过配置文件对象getProperty()的方法获取驱动名，并加载驱动
            Class.forName(properties.getProperty("jdbcName"));

        } catch (Exception e) {  //为了方便，抛出大的异常
            e.printStackTrace();
        }

    }


    //2.获取数据库链接
    public static Connection getConnection(){
        Connection connection = null; //代表数据库

        //得到数据库链接
        try {
            //得到数据链接的相关信息
            String dbUrl = properties.getProperty("dbUrl");
            String dbName = properties.getProperty("dbName");
            String dbPwd = properties.getProperty("dbPwd");

            connection = DriverManager.getConnection(dbUrl,dbName,dbPwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    //3.释放资源
    public static void close(ResultSet resultSet, PreparedStatement preparedStatement, Connection connection){
        try {
            //判断资源如果不为空则关闭
            if (resultSet!=null){
                resultSet.close();
            }
            if (preparedStatement!=null){
                preparedStatement.close();
            }
            if (connection!=null){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
