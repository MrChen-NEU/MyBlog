<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-9">
    <div class="data_list">
        <div class="data_list_title"><span class="glyphicon glyphicon-signal"></span>&nbsp;数据报表 </div>
        <div class="container-fluid">
            <div class="row" style="padding-top: 20px;">
                <div class="col-md-12">
                <%-- 柱状图容器--%>
                <div id="monthChart" style="height: 500px"></div>
                    <%--百度地图加载--%>
                    <h3 align="center">用户地区分布图</h3>
                    <%--加载百度地图容器--%>
                    <div id="baiduMap" style="height: 600px;width: 100%;"></div>
                </div>
            </div>
        </div>
    </div>
</div>
<%----%>

<script type="text/javascript" src="statics/echarts/echarts.min.js"></script>
<%--引用百度地图API文件--%>
<script type="text/javascript" src="https://api.map.baidu.com/api?v=2.0&&type=webgl&ak=3yArCy8oCvYeVn5MliAAiSaomaLExP94"></script>
<script type="text/javascript">
    /**
     * 通过月份查询云记数量
     */
   $.ajax({
       type:"get",
       url:"report",
       data:{
           actionName:"month",
       },
       success:function (result){
                console.log(result)
          if (result.code == 1){
              //通过月份查询数量
              var monthArray=result.result.monthArray;
              var dataArray=result.result.dataArray;
              //加载柱状图
              loadMonthChart(monthArray,dataArray);
          }
       }
   })
            /**
             * 加载柱状图
             */
         function loadMonthChart(monthArray,dataArray){
             // 基于准备好的dom，初始化echarts实例
             var myChart = echarts.init(document.getElementById('monthChart'));

             var dataAxis = monthArray;
             var data = dataArray;
             var yMax = 30;
             var dataShadow = [];

             for (var i = 0; i < data.length; i++) {
                 dataShadow.push(yMax);
             }

             var option = {
                 title: {
                     text: '按月统计',
                     subtext: '通过月份查询对应数量',
                     left:'center'
                 },
                 tooltip:{},

                 xAxis: {
                     data: dataAxis,
                     axisTick: {
                         show: false
                     },
                     axisLine: {
                         show: false
                     }
                 },
                 yAxis: {
                     axisLine: {
                         show: false
                     },
                     axisTick: {
                         show: false
                     },
                     axisLabel: {
                         textStyle: {
                             color: '#999'
                         }
                     }
                 },
                 dataZoom: [
                     {
                         type: 'inside'
                     }
                 ],
                 series: [
                     {
                         type: 'bar',
                         showBackground: true,
                         data: data,
                         itemStyle: {
                             color: new echarts.graphic.LinearGradient(
                                 0, 0, 0, 1,
                                 [
                                     {offset: 0, color: '#83bff6'},
                                     {offset: 0.5, color: '#188df0'},
                                     {offset: 1, color: '#188df0'}
                                 ]
                             )
                         },
                         emphasis: {
                             itemStyle: {
                                 color: new echarts.graphic.LinearGradient(
                                     0, 0, 0, 1,
                                     [
                                         {offset: 0, color: '#2378f7'},
                                         {offset: 0.7, color: '#2378f7'},
                                         {offset: 1, color: '#83bff6'}
                                     ]
                                 )
                             }
                         },

                     }
                 ]
             };
             // 使用刚指定的配置项和数据显示图表。
             myChart.setOption(option);
 }
     /**
     * 通过用户发布的坐标查询数量
     */
 $.ajax({
     type:"get",
     url: "report",
     data:{
         actionName:"location",
     },
     success:function (result){
         if (result.code==1){
             loadBaiduMap(result.result);
         }
     }

 })
    /**
     * 加载百度地图
     */
    function loadBaiduMap(markers){
        var map = new BMapGL.Map("baiduMap");
        var point = new BMapGL.Point(116.404, 39.915);
        map.centerAndZoom(point, 10);
        map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
        var zoomCtrl = new BMapGL.ZoomControl();  // 添加比例尺控件
        map.addControl(zoomCtrl);

        //判断是否有坐标
        if(markers !=null && markers.length>0){ //集合中的第一个坐标，是用户当前所在的位置，其他是用户记录的经纬度
            //将用户所在的位置设置为中心点
            map.centerAndZoom(new BMapGL.Point(markers[0].lon,markers[0].lat), 10);
            //循环在地图上添加点标记
            for (var i=1;i<markers.length;i++){
                // 创建点标记
                var marker = new BMapGL.Marker(new BMapGL.Point(markers[i].lon, markers[i].lat));
                // 在地图上添加点标记
                map.addOverlay(marker);
            }
        }
    }
</script>