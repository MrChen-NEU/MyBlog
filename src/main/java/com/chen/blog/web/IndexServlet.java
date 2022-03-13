package com.chen.blog.web;

import com.chen.blog.po.Note;
import com.chen.blog.po.User;
import com.chen.blog.service.NoteService;
import com.chen.blog.util.Page;
import com.chen.blog.vo.NoteVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置首页导航高亮
        req.setAttribute("menu_page","index");

        //得到用户行为 判断 是什么条件查询(标题，类型，日期)模糊查询
        String actionName =req.getParameter("actionName");

        //将用户行为设置到作用域中（分页导航中需要获取）
        req.setAttribute("action",actionName);

        //得到用户行为
        if ("searchTitle".equals(actionName)){//标题查询
            String title =req.getParameter("title");
            //将查询条件设置到请求域中查询回显
            req.setAttribute("title",title);
            noteList(req,resp,title,null,null);
        }else if ("searchDate".equals(actionName)){//日期查询
            //得到查询条件
            String date = req.getParameter("date");
            //将查询条件设置到请求域中查询回显
            req.setAttribute("date",date);
            noteList(req,resp,null,date,null);
        }
        else if ("searchType".equals(actionName)){//日期查询
            //得到查询条件
            String TypeId = req.getParameter("TypeId");
            //将查询条件设置到请求域中查询回显
            req.setAttribute("TypeId",TypeId);
            noteList(req,resp,null,null,TypeId);
        }
        else {
            //分页查询列表
            noteList(req,resp,null,null,null);
        }


        //设置首页动态包含的页面
        req.setAttribute("changePage","note/list.jsp");
        //请求转发到index.jsp
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    /**
     * 分页查询列表
     1. 接收参数 （当前页、每页显示的数量）
     2. 获取Session作用域中的user对象
     3. 调用Service层查询方法，返回Page对象
     4. 将page对象设置到request作用域中
     * @param req
     * @param resp
     * @param title
     */
    private void noteList(HttpServletRequest req, HttpServletResponse resp, String title,String date,String TypeId) {
        // 1. 接收参数 （当前页、每页显示的数量）
        String pageNum=req.getParameter("pageNum");
        String pageSize=req.getParameter("pageSize");
        //2. 获取Session作用域中的user对象
        User user = (User) req.getSession().getAttribute("user");
        // 3. 调用Service层查询方法，返回Page对象
        Page<Note> page =new NoteService().findNoteListByPage(pageNum,pageSize,user.getUserId(),title,date,TypeId);
        //4. 将page对象设置到request作用域中
        req.setAttribute("page",page);
        //通过日期分组查询当前登录用户下的数量
        List<NoteVo> dateInfo =new NoteService().findNoteCountByDate(user.getUserId());
        //设置集合存放在作用域
        req.getSession().setAttribute("dateInfo",dateInfo);
        //通过类型分组查询当前登录用户下的数量
        List<NoteVo> typeInfo =new NoteService().findNoteCountByType(user.getUserId());
        //设置集合存放在作用域
        req.getSession().setAttribute("typeInfo",typeInfo);
    }
}
