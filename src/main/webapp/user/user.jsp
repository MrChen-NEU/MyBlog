<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-9">


    <script>
        function checkUser(){
            var nickName=$('#nick').val();
            if(nickName.length==0){
                $("#msg").html("昵称不能为空,请核对！");
                return false;
            }
            return true;
        }
    </script>
    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon-edit"></span>&nbsp;个人中心 </div>
        <div class="container-fluid">
            <div class="row" style="padding-top: 20px;">
                <div class="col-md-8">
                    <form class="form-horizontal" method="post" action="user?act=save" enctype="multipart/form-data" onsubmit="return checkUser();">
                        <div class="form-group">
                            <input type="hidden" name="act" value="save">
                            <label for="nickName" class="col-sm-2 control-label">昵称:</label>
                            <div class="col-sm-3">
                                <input class="form-control" name="nick" id="nickName" placeholder="昵称" value="我思故我在">
                            </div>
                            <label for="img" class="col-sm-2 control-label">头像:</label>
                            <div class="col-sm-5">
                                <input type="file" id="img" name="img">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="mood" class="col-sm-2 control-label">心情:</label>
                            <div class="col-sm-10">
                                <textarea class="form-control" name="mood" id="mood" rows="3">以后的你会感谢现在努力的你</textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" id="btn" class="btn btn-success">修改</button>&nbsp;&nbsp;<span style="color:red" id="msg"></span>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-4"><img style="width:260px;height:200px" src="./个人中心_files/h2.jpg"></div>
            </div>
        </div>
    </div>
    <script>
        $(function(){

                var target=$("#nickName");
                //验证昵称唯一
                target.blur(
                    function(){
                        $("#btn").attr('disabled',false);
                        $("#msg").html('');
                        var value =target.val();
                        //不用ajax验证，没有填写或者与之前内容相同
                        if(value.length==0 ||value=='我思故我在'){
                            target.val('我思故我在');
                            return ;
                        }

                        //ajax验证
                        $.getJSON("user",{
                            act:'unique',
                            nick:value
                        },function(data){
                            if(data.resultCode==-1){
                                $("#msg").html(value+"此用户名已存在");
                                target.val('');
                                $("#btn").attr('disabled',true);
                            }else{
                                $("#btn").attr('disabled',false);
                            }
                        });
                    }

                );
            }
        );
    </script>
</div>

</body></html>