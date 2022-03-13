<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>MyBlog 登录</title>
    <link href="statics/css/login.css" rel="stylesheet" type="text/css" />
    <script src="statics/js/jquery-1.11.3.js" type=text/javascript></script>
    <script src="statics/js/util.js" type=text/javascript></script>
    <script src="statics/js/config.js" type=text/javascript></script>
    <link rel="icon" href="./statics/金木研.ico">
</head>
<body>
<!--head-->
<div id="head">
    <div class="top">
        <div class="fl yahei18">记录某个时刻的美好！</div>
    </div>
</div>

<!--login box-->
<div class="wrapper">
    <div id="box">
        <div class="loginbar">用户登录</div>
        <div id="tabcon">
            <div class="box show">
                <form action="user" method="post" id="loginForm">
                    <%-- actionName表示用户行为，通过这个参数可以在UserServlet中判断用户当前想要操作的功能 --%>
                    <input type="hidden" name="actionName" value="login"/>
                    <input type="text" class="user yahei16" id="userName" name="userName" value="${resultInfo.result.uname}" /><br /><br />
                    <input type="password" class="pwd yahei16" id="userPwd" name="userPwd" value="${resultInfo.result.upwd}" /><br /><br />
                    <input name="rem" type="checkbox" value="1"  class="inputcheckbox"/> <label>记住我</label>&nbsp; &nbsp;
                    <span id="msg" style="color: red;font-size: 12px;">${resultInfo.msg}</span><br /><br />
                    <input type="button" class="log jc yahei16" value="登 录" onclick="checkLogin()" />&nbsp; &nbsp; &nbsp; <input type="reset" value="取 消" class="reg jc yahei18" />
                </form>
            </div>
        </div>
    </div>
</div>

<div id="flash">
    <div class="pos">
        <a id="flash1" style="display:block;"><img src="statics/images/bg6.jpeg"></a>
        <a id="flash2"                       ><img src="statics/images/bg1.jpg"></a>
    </div>
    <div class="flash_bar">
        <div class="dq" id="f1" onclick="changeflash(1)"></div>
        <div class="no" id="f2" onclick="changeflash(2)"></div>
    </div>
</div>

</body>

</html>

