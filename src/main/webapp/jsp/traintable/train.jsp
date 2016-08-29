<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<title>火车余票查询</title>
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.external-png.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.icons.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.inline-png.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.inline-svg.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.structure.min.css" rel="stylesheet">
<link href="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.min.css" rel="stylesheet">
<style type="text/css">
	*{
		font-family:"Microsoft YaHei",微软雅黑,"MicrosoftJhengHei",华文细黑,STHeiti,MingLiu;
		margin: 0;
		padding: 0;
	}
	li div{
		display: inline-block;
	}
	div.left{
		width: 20%;
		height: 7rem;
		float: left;
		text-align: center;
		overflow: hidden;	
	}
	div.right{
		width: 80%;
		height: 7rem;
		float: left;
	}
 	div.text{ 
 		width: 50%; 
 		display: inline-block;
 	} 
  	.i{ 
  		display:table-cell;
		vertical-align: middle;
  	} 
</style>
</head>
<body>

	<div data-role="page" id="pageone">
		<div data-role="header">
			<h1>火车余票列表</h1>
		</div>
		<div data-role="content">
			<ul data-role="listview" data-inset="true" id="ulOne">
				
			</ul>
		</div>
	</div>

</body>
<script src="//cdn.bootcss.com/jquery/2.2.4/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.min.js"></script>
<script type="text/javascript">
function trainCheck(s){
	if(s=='--'||s=='无'||s==undefined){
		return 0;
	}else{
		return 1;
	}
}
var data={
		date:'${date}',
		startStation:'${startStation}',
		endStation:'${endStation}'
}
$.ajax({
	   type: "POST",
	   url: "traintable.json",
	   data: data,
	   async: false,
	   success: function(data){
	     console.log(data )
	     var ul=$("#ulOne");
		 var num=1; 
	    	
	     
	     for(i in data){
	    	 if(trainCheck(data[i].gg_num)||trainCheck(data[i].gr_num)||trainCheck(data[i].qt_num)||trainCheck(data[i].rw_num)
	    		||trainCheck(data[i].rz_num)||trainCheck(data[i].tz_num)||trainCheck(data[i].wz_num)||trainCheck(data[i].yb_num)
	    		||trainCheck(data[i].yw_num)||trainCheck(data[i].yz_num)||trainCheck(data[i].ze_num)||trainCheck(data[i].zy_num)
	    		||trainCheck(data[i].swz_num)){

	    	 }else{
	    		 num+=1;
		    	 if(num==data.length){
		    		 console.log("111")
		    		 ul.append("<li style='text-align: center;'>没有可买的票</li>");
		    		 return false;
		    	 }
	    		 continue;	    		 
	    	 }
	    	 
    		var li=$("<li>")
    	 	var divLeft=$("<div>");
			divLeft.addClass("left");   	 
			var divRight=$("<div>");
			divRight.addClass("right");
			var img=$("<img>");
			img.attr("src","../jsp/img/train.png");
			img.addClass("i")
			var p1=$("<div>");
			var p2l=$("<div>");
			var p2r=$("<div>");
			var p3l=$("<div>");
			var p3r=$("<div>");
			var p4l=$("<div>");
			var p4r=$("<div>");
			var p5=$("<div>");
			var m5=$("<marquee>");
			var text;
			var yp="";
			p1.append(data[i].train_class_name+" "+data[i].station_train_code)
			p1.css({"font-size":"17px","font-weight":"bold","margin-left":"10","margin-right":"auto"})
			p2l.append("出发时间："+data[i].start_time);
			p2l.addClass("text");
			p2r.append("历时："+data[i].lishi+"小时");
			p2r.addClass("text");
			p3l.append("出发站："+data[i].from_station_name)
			p3l.addClass("text");
			p3r.append("到达站："+data[i].to_station_name)
			p3r.addClass("text");			
			p4l.append("起点站："+data[i].start_station_name)
			p4l.addClass("text");	
			p4r.append("终点站："+data[i].end_station_name)
			p4r.addClass("text");	
			

			if(trainCheck(data[i].gr_num)){
				yp+="高软："+data[i].gg_num+"张 ";
			}
			if(trainCheck(data[i].qt_num)){
				yp+="其他："+data[i].qt_num+"张 "
			}
			if(trainCheck(data[i].rw_num)){
				yp+="软卧："+data[i].rw_num+"张 "
			}
			if(trainCheck(data[i].rz_num)){
				yp+="软座："+data[i].rz_num+"张 "
			}
			if(trainCheck(data[i].tz_num)){
				yp+="特等座："+data[i].tz_num+"张 "
			}
			if(trainCheck(data[i].yw_num)){
				yp+="硬卧："+data[i].yw_num+"张 "
			}
			if(trainCheck(data[i].yz_num)){
				yp+="硬座："+data[i].yz_num+"张 "
			}
			if(trainCheck(data[i].ze_num)){
				yp+="二等座："+data[i].ze_num+"张 "
			}
			if(trainCheck(data[i].zy_num)){
				yp+="一等座："+data[i].zy_num+"张 "
			}
			if(trainCheck(data[i].wz_num)){
				yp+="无座："+data[i].wz_num+"张 "
			}
			if(yp.length>25){
				text=m5
				text.attr("scrollamount","3px")
			}else{
				text=p5
			}
			text.append(yp);
			text.addClass("text");	
			
			li.append(divLeft);
			divLeft.append(img);
			li.append(divRight);
			divRight.append(p1);
			divRight.append("</br>");
			divRight.append(p2l);
			divRight.append(p2r);
			divRight.append("</br>");
			divRight.append(p3l);
			divRight.append(p3r);
			divRight.append("</br>");
			divRight.append(p4l)
			divRight.append(p4r)
			divRight.append("</br>");
			divRight.append(text)
			ul.append(li);
	     }
// 	     $('#ulOne').listview('refresh')
	   }
	}); 
</script>
</html>