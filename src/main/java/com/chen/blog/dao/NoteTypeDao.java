package com.chen.blog.dao;

import com.chen.blog.po.NoteType;
import com.chen.blog.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NoteTypeDao {
    //查询
    public List<NoteType> findTypeListByUserId(Integer userId){
        //1. 定义SQL语句
        String sql = "select typeId,typeName,userId from tb_note_type where userId = ? ";

        //2. 设置参数列表
        List<Object> add = new ArrayList<>();
        add.add(userId);

        //3. 调用BaseDao的查询方法，返回集合
        List<NoteType> list=BaseDao.queryRows(sql,add,NoteType.class);
        return  list;
    }

    //通过类型ID查询云记记录的数量，返回云记数量
    public long findBoteCountByTypeId(String typeId) {
        //定义sql语句
        String sql = "select count(1) from tb_note where typeId =?";

        //设置参数集合
        List<Object> obj =new ArrayList<>();
        obj.add(typeId);

        //调用BaseDao
        long count = (long) BaseDao.findSingleValue(sql,obj);

        return count;
    }

    //通过类型ID删除指定的类型记录，返回受影响的行数
    public int deleteTypeById(String typeId) {
        //定义sql语句
        String sql = "delete from tb_note_type where typeId = ?";

        //设置参数集合
        List<Object> obj =new ArrayList<>();
        obj.add(typeId);

        //调用BaseDao
        int row = BaseDao.executeUpdate(sql,obj);

        return row;
    }

    //查询当前登录用户下，类型名称是否唯一，返回1=成功 返回0=失败
    public Integer checkTypeName(String typeName, Integer userId, String typeId) {
        //定义sql语句
        String sql ="select * from tb_note_type where userId= ? and typeName= ?";
        //设置参数
        List<Object> obj= new ArrayList<>();
        obj.add(userId);
        obj.add(typeName);
        //执行查询操作
        NoteType noteType= (NoteType) BaseDao.queryRow(sql,obj,NoteType.class);
        //如果对象为空 ，就表示可用
        if (noteType == null){
            return 1;
        }else{
            //如果是修改操作，则需要判断是否是当前记录本身
            if (typeId.equals(noteType.getTypeId().toString())){
                return 1;
            }
        }
        return 0;
    }

    //添加方法，返回主键
    public Integer addType(String typeName, Integer userId) {
        Integer key =null;
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            // 1. 获取数据库连接
            connection=DBUtil.getConnection();
            //2. 定义sql语句
            String sql ="insert into tb_note_type (typeName,userId) values(?,?)";
            // 3. 预编译
            ps =connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //4. 设置参数
            ps.setString(1,typeName);
            ps.setInt(2,userId);
            //5. 执行查询，返回受影响的行数
            int row =ps.executeUpdate();
            //6.判断受影响的行数
            if (row>0){
                //获取主键返回主键的结果集
                rs=ps.getGeneratedKeys();
                //得到主键的值
                if (rs.next()){
                    key=rs.getInt(1);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtil.close(rs,ps,connection);
        }
        return  key;
    }

    //修改方法 返回受影响的行数
    public Integer updateType(String typeName, String typeId) {
        //定义sql语句
        String sql ="update tb_note_type set typename =? where typeId =?";
        //设置参数
        List<Object> obj =new ArrayList<>();
        obj.add(typeName);
        obj.add(typeId);
        //调用BadeDao方法
        int row =BaseDao.executeUpdate(sql,obj);
        return  row;
    }


}
