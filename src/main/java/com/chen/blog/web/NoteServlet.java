package com.chen.blog.web;


import cn.hutool.core.util.StrUtil;
import com.chen.blog.po.Note;
import com.chen.blog.po.NoteType;
import com.chen.blog.po.User;
import com.chen.blog.service.NoteService;
import com.chen.blog.service.NoteTypeService;
import com.chen.blog.vo.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/note")
public class NoteServlet  extends HttpServlet {


    private NoteService noteService = new NoteService();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置首页导航高亮
        req.setAttribute("menu_page","note");
        //得到用户行为
        String actionName = req.getParameter("actionName");
        //判断用户行为
        if ("view".equals(actionName)){
            //进入发布云记页面
            noteView(req,resp);
        }else if ("addOrUpdate".equals(actionName)){
            //添加或修改
            addOrUpdate(req,resp);
        }else if ("detail".equals(actionName)){
            //查询云记详情
            noteDetail(req,resp);
        }else if ("delete".equals(actionName)){
            //删除云记
            noteDelete(req,resp);
        }
    }

    //删除云记
    private void noteDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 1. 接收参数 （noteId）
        String noteId=req.getParameter("noteId");
        // 2. 调用Service层删除方法，返回状态码 （1=成功，0=失败）
        Integer code =noteService.deleteNote(noteId);
        // 3. 通过流将结果响应给ajax的回调函数 （输出字符串）
        resp.getWriter().write(code +"");
        resp.getWriter().close();
    }

    //查询云记详情
    private void noteDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 接收参数 （noteId）
        String noteId=req.getParameter("noteId");
        // 2. 调用Service层的查询方法，返回Note对象
        Note note =noteService.findNoteById(noteId);
        // 3. 将Note对象设置到request请求域中
        req.setAttribute("note",note);
        //4. 设置首页动态包含的页面值
        req.setAttribute("changePage","note/detail.jsp");
        //5. 请求转发跳转到index.jsp
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    //添加或修改
    private void addOrUpdate(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        // 1. 接收参数 （类型ID、标题、内容）
        String typeId=req.getParameter("typeId");
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        //获取经纬度
        String lon =req.getParameter("lon");
        String lat =req.getParameter("lat");
        //如果是修改操作接收noteId
        String noteId =req.getParameter("noteId");

        //2. 调用Service层方法，返回resultInfo对象
        ResultInfo<Note> resultInfo =noteService.addOrUpdate(typeId,title,content,noteId,lon,lat);

        //3. 判断resultInfo的code值
        if (resultInfo.getCode() ==1){
            resp.sendRedirect("index");
        }else {
            //将resultInfo 对象设置到req作用域
            req.setAttribute("resultInfo",resultInfo);
            //请求转发跳转到note?actionName=view
            //修改操作传noteId
            String url="note?actionName=view";
            if (StrUtil.isBlank(url)){
                url +="&noteId="+noteId;
            }
            req.getRequestDispatcher(url).forward(req,resp);
        }
    }

    //进入发布云记页面
    private void noteView(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //修改操作
        //得到要修改的id
        String noteId=req.getParameter("noteId");
        //通过noteId查询对象
        Note note = noteService.findNoteById(noteId);
        //将对象设置道歉请求域
        req.setAttribute("noteInfo",note);
        //修改操作
        //1. 从Session对象中获取用户对象
        User user= (User) req.getSession().getAttribute("user");
        //2. 通过用户ID查询对应的类型列表
        List<NoteType> typeList =new NoteTypeService().findTypeList(user.getUserId());
        //3. 将类型列表设置到request请求域中
        req.setAttribute("typeList",typeList);
        //4、  设置首页动态包含的页面值
        req.setAttribute("changePage","note/view.jsp");
        // 5、  请求转发跳转到index.jsp
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
