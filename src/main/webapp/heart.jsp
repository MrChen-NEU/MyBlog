<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>MyBlog 心动花园</title>
	<link href="./statics/css/note.css" rel="stylesheet">
	<link href="./statics/bootstrap/css/bootstrap.css" rel="stylesheet">
	<link href="./statics/sweetalert/sweetalert2.min.css" rel="stylesheet">
	<script src="./statics/js/jquery-1.11.3.js"></script>
	<script src="./statics/bootstrap/js/bootstrap.js"></script>
	<script src="./statics/sweetalert/sweetalert2.min.js"></script>
	<!-- 配置文件 -->
	<script type="text/javascript" src="./statics/js/config.js"></script>
	<!-- 编辑器源码文件 -->
	<script type="text/javascript" src="./statics/ueditor/ueditor.all.js"></script>
	<link rel="icon" href="./statics/金木研.ico">
	<link rel="stylesheet" type="text/css" href="./live2d/stylesheets/live2d.css">

	
	<link rel="stylesheet" href="./css-3d/css/hovertree.css" />
	<style type="text/css">
	  body {
		   padding-top: 60px;
		   padding-bottom: 40px;
		   background: url(./statics/images/背景图片03.jpeg);
		   opacity: 0.9;
		 }
	</style>
	</head>
	<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
	  <div class="container-fluid">
	
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
			<span class="sr-only">Toggle navigation</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </button>
		  <a class="navbar-brand" style="font-size:25px" href="./主页.html">MyBlog</a>
		</div>
	
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		  <ul class="nav navbar-nav">
			<li><a href="./index.jsp"><i class="glyphicon glyphicon-cloud"></i>&nbsp;主&nbsp;&nbsp;页</a></li>
			<li><a href="./index.jsp"><i class="glyphicon glyphicon-pencil"></i>&nbsp;发布Blog</a></li>
			<li><a href="./index.jsp"><i class="glyphicon glyphicon-list"></i>&nbsp;类别管理</a></li>
			<li><a href="./index.jsp"><i class="glyphicon glyphicon-user"></i>&nbsp;个人中心</a>
			<li class="active"><a href="./心动花园.html"><i class="glyphicon glyphicon-heart"></i>&nbsp;心动花园</a></li>
			
		   
		  </ul>    
		</div>
	  </div>
	</nav>


	<!--/*外层最大容器*/-->
	<div class="wrap">
		<!--	/*包裹所有元素的容器*/-->
		<div class="cube">
			<!--前面图片 -->
			<div class="out_front">
				<img src="./css-3d/3d-picture/01.jpg" class="pic" />
			</div>
			<!--后面图片 -->
			<div class="out_back">
				<img src="./css-3d/3d-picture/02.jpg" class="pic"/>
			</div>
			<!--左图片 -->
			<div class="out_left">
				<img src="./css-3d/3d-picture/03.jpg" class="pic" />
			</div>
			<div class="out_right">
				<img src="./css-3d/3d-picture/04.jpg" class="pic" />
			</div>
			<div class="out_top">
				<img src="./css-3d/3d-picture/05.jpg" class="pic" />
			</div>
			<div class="out_bottom">
				<img src="./css-3d/3d-picture/06.jpg" class="pic" />
			</div>
			<!--小正方体 -->
			<span class="in_front">
				<img src="./css-3d/3d-picture/07.png" class="in_pic" />
			</span>
			<span class="in_back">
				<img src="./css-3d/3d-picture/08.jpg" class="in_pic" />
			</span>
			<span class="in_left">
				<img src="./css-3d/3d-picture/09.jpg" class="in_pic" />
			</span>
			<span class="in_right">
				<img src="./css-3d/3d-picture/10.jpg" class="in_pic" />
			</span>
			<span class="in_top">
				<img src="./css-3d/3d-picture/11.jpg" class="in_pic" />
			</span>
			<span class="in_bottom">
				<img src="./css-3d/3d-picture/12.jpg" class="in_pic" />
			</span>
		</div>
	</div>

	<!-- 本地音乐，双击index.html -->
	<div style="position:absolute;left:0px;bottom:0px; float: left;">
		<!-- 隐藏播放条：删除ontrols="controls"部分 -->
		<audio autoplay="autopaly" loop="loop" controls="controls" id="audios">
			<source src="./css-3d/music/浪子本初 - Rain (伴奏).flac" type="audio/mp3" />
		</audio>
	</div>
	<script>
		var music = document.getElementById('video');
		var state = 0;
		document.addEventListener('touchstart', function(){    
			  if(state==0){        
				  music.play();        
				  state=1;    
			  }  
		  }, false);
		document.addEventListener("WeixinJSBridgeReady", function () {    
			  music.play();
		  }, false);  
		//循环播放
		music.onended = function () {    
			music.load();    
			music.play();
		}    
	</script>



	<div id="landlord" class="lock">
        <div id="l2d-message"></div>
        <div id="l2d-control">
            <button id="l2d-drag">
                <img src="./live2d/images/drag.svg" alt="" draggable="false"/>
            </button>
        </div>
        <canvas id="live2d" width="280" height="250" class="live2d"></canvas>
    </div>

    <script type="text/javascript" src="./live2d/javascripts/jquery.min.js"></script>
    <script type="text/javascript" src="./live2d/javascripts/live2d.js"></script>
    <script type="text/javascript" src="./live2d/javascripts/message.js"></script>
    <script type="text/javascript" src="./live2d/javascripts/extension.js"></script>
    <script type="text/javascript">
        loadlive2d("live2d", "./live2d/models/illyasviel/illyasviel.model.json");
    </script>
	
		

</body>
</html>