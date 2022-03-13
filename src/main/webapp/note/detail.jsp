<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="col-md-9">



    <div class="data_list">
        <div class="data_list_title">
            <span class="glyphicon glyphicon-eye-open"></span>&nbsp;查看Blog
        </div>
        <div>


            <div class="note_title"><h2>${title}</h2></div>
            <div class="note_info">
                发布时间：『 <fmt:formatDate value="${note.pubTime}" pattern="yyyy-mm-dd HH:mm"/> 』
                &nbsp;&nbsp;&nbsp;&nbsp;Blog类别：${note.typeName}
            </div>
            <div class="note_content">
                <p>${note.content}</p>
            </div>
            <div class="note_btn">
                <button class="btn btn-primary" type="button" onclick="updateNote(${note.noteId})">修改</button>
                <button class="btn btn-danger" type="button" onclick="deleteNote(${note.noteId})">删除</button>
            </div>
        </div>
    </div>
    <script type="text/javascript">
     function  deleteNote(noteId){
         swal({
             title: "",  // 标题
             text: "<h3>您确认要删除该记录吗？</h3>", // 内容
             type: "warning", // 图标  error	  success	info  warning
             showCancelButton: true,  // 是否显示取消按钮
             confirmButtonColor: "orange", // 确认按钮的颜色
             confirmButtonText: "确定", // 确认按钮的文本
             cancelButtonText: "取消" // 取消按钮的文本
         }).then(function(){
             // 如果是，发送ajax请求后台（类型ID）
            $.ajax({
                type:"post",
                url: "note",
                data:{
                    actionName:"delete",
                    noteId:noteId,
                },
                success:function(code){
                    // 判断是否删除成功
                    if (code == 1) {
                        // 成功跳转到首页
                        window.location.href="index";
                    } else {
                        // 提示用户失败   swal("标题","内容","图标")
                        swal("","<h3>删除失败!</h3>","error");
                    }
                }

            })
         });

     }

     /**
      * 进入发布云记的页面
      * @param noteId
      */
     function updateNote(noteId){
         window.location.href="note?actionName=view&noteId=" + noteId;
     }
    </script>
</div>