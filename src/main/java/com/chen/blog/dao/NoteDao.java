package com.chen.blog.dao;

import cn.hutool.core.util.StrUtil;
import com.chen.blog.po.Note;
import com.chen.blog.vo.NoteVo;

import java.util.ArrayList;
import java.util.List;

public class NoteDao {

    // 添加Or修改,返回受影响的行数
    public int addOrUpdate(Note note) {
        //定义sql语句
        String sql = "";
        //设置参数
        List<Object> obj =new ArrayList<>();
        obj.add(note.getTypeId());
        obj.add(note.getTitle());
        obj.add(note.getContent());

        //判断noteId是否为空，为空则添加 不为空为修改
        if (note.getNoteId()==null){//修改操作
            sql="insert into tb_note(typeId,title,content,pubTime,lon,lat) values (?,?,?,now(),?,?)";
            obj.add(note.getLon());
            obj.add(note.getLat());
        } else {
            sql="update tb_note set typeId= ?,title=?,content= ? where noteId =?";
            obj.add(note.getNoteId());
        }
        //调用BaseDao更新方法
        int row =BaseDao.executeUpdate(sql,obj);
        return  row;
    }

    //查询当前用户的数量，返回总数量
    public long findNoteCount(Integer userId, String title,String date,String TypeId) {
        //定义sql语句
        String sql = "SELECT count(1) FROM tb_note n INNER JOIN tb_note_type t on n.typeId = t.typeId  WHERE userId = ? ";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(userId);
        //判断条件查询的参数是否为空 则拼接sql语句并设置相关参数
        if (!StrUtil.isBlank(title)){
            //拼接sql语句
            sql += " and title like concat('%',?,'%')";
            //设置相关参数
            obj.add(title);
        }else  if (!StrUtil.isBlank(date)){
            //拼接sql语句
            sql += " and date_format(pubTime,'%Y年%m月') = ? ";
            //设置相关参数
            obj.add(date);
        }
        else  if (!StrUtil.isBlank(TypeId)){
            //拼接sql语句
            sql += " and n.typeId = ? ";
            //设置相关参数
            obj.add(TypeId);
        }

        //调用BadeDao查询方法
        long count = (long) BaseDao.findSingleValue(sql,obj);
        return count;
    }

    //分页查询列表 返回note集合
    public List<Note> findNoteListByPage(Integer userId, Integer index, Integer pageSize, String title,String date,String TypeId) {
        //定义sql语句
        String sql = "SELECT noteId,title,pubTime FROM tb_note n INNER JOIN  tb_note_type t on n.typeId = t.typeId WHERE userId = ?";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(userId);
        //判断条件查询的参数是否为空 则拼接sql语句并设置相关参数
        if (!StrUtil.isBlank(title)){
            //拼接sql语句
            sql += " and title like concat('%',?,'%')";
            //设置相关参数
            obj.add(title);
        }else if (!StrUtil.isBlank(date)){
            //拼接sql语句
            sql += " and date_format(pubTime,'%Y年%m月') = ? ";
            //设置相关参数
            obj.add(date);
        }else  if (!StrUtil.isBlank(TypeId)){
            //拼接sql语句
            sql += " and n.typeId = ? ";
            //设置相关参数
            obj.add(TypeId);
        }
        //拼接分页的sql语句
        sql += " order by pubTime desc limit ?,?";
        obj.add(index);
        obj.add(pageSize);

        //调用BadeDao查询方法
        List<Note> list=BaseDao.queryRows(sql,obj,Note.class);
        return list;
    }

    //通过日期分组查询当前登录用户下的数量
    public List<NoteVo> findNoteCountByDate(Integer userId) {
        //定义sql语句
        String sql ="SELECT count(1) noteCount, DATE_FORMAT( pubTime, '%Y年%m月') groupName FROM tb_note a LEFT JOIN tb_note_type b ON a.typeId = b.typeId WHERE userId = ? " +
                "GROUP BY DATE_FORMAT( pubTime, '%Y年%m月' ) " +
                "ORDER BY DATE_FORMAT( pubTime, '%Y年%m月' ) DESC";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(userId);
        List<NoteVo> list =BaseDao.queryRows(sql,obj,NoteVo.class);
        return list;
    }

    //通过类型分组查询当前登录用户下的数量
    public List<NoteVo> findNoteCountByType(Integer userId) {
        //定义sql语句
        String sql = "SELECT count(noteId) noteCount, t.typeId, typeName groupName FROM tb_note n " +
                " RIGHT JOIN tb_note_type t ON n.typeId = t.typeId WHERE userId = ? " +
                " GROUP BY t.typeId ORDER BY COUNT(noteId) DESC ";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(userId);
        List<NoteVo> list =BaseDao.queryRows(sql,obj,NoteVo.class);
        return list;
    }

    //通过id查询对象
    public Note findNoteById(String noteId) {
        //定义sql语句
        String sql ="select noteId,title,content,pubTime,typeName,n.typeId from tb_note n inner join tb_note_type t on n.typeId=t.typeId where noteId=?";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(noteId);
        Note note= (Note) BaseDao.queryRow(sql,obj,Note.class);
        return note;
    }

    //删除云记
    public int deleteNoteById(String noteId) {
        //定义sql语句
        String sql="delete from tb_note where noteId = ?";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(noteId);
        int row =BaseDao.executeUpdate(sql,obj);
        return row;
    }

    //通过用户id查询记录
    public List<Note> queryNoteList(Integer userId) {
        //定义sql语句
        String sql="select lon,lat from tb_note n inner join tb_note_type t on n.typeId=t.typeId  where userId =?";
        //设置 参数
        List<Object> obj =new ArrayList<>();
        obj.add(userId);
        //调用BaseDao方法
        List<Note> list=BaseDao.queryRows(sql,obj,Note.class);
        return list;
    }
}
