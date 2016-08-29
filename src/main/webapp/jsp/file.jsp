<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>
<base href="">
<style type="text/css">
*{
	margin: 0;
	padding: 0;
}
.center{
	margin-left: 50px;
	margin-top: 50px;;
}
ul ul{
	min-width:25px;
/* 	font-size:15px; */
	color: red;
	background:url(../jsp/img/folder_close.png)  no-repeat;padding-left:25px;
	display: block;
	margin-left: 20px;
}
ul.open{
	background:url(../jsp/img/folder_open.png)  no-repeat;padding-left:25px;
}
ul>li{
	min-width:25px;
	color:blue;
	margin-left: 20px;
	list-style:none;
	background:url(../jsp/img/file.png)  no-repeat;padding-left:25px;
	display: block;
}
</style>
<script type="text/javascript" src="../jsp/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$(function(){
	
	
	
})	
function init(){
$("body ul").on("click","ul",function(event){
		console.log(event.target)
		var node=$(this);
		var type = node.attr("data-type")
		var open =node.attr("op")

		
		if(open=="close"){
			node.attr("op","open");
			node.children().show();
		}else if (type="dir"&&open==undefined){
			node.attr("op","open");
			$.ajax({
				   type: "GET",
				   url: "fileList",
				   data: "path="+node.attr("data-url"),
				   success: function(data){
				     for(x in data){
				    	 if(data[x].type=="file"){
				    		 node.append("<li data-url='"+data[x].path+"' data-type='"+data[x].type+"'>"+data[x].name+"</li>")
				     	}else{
				     		node.append("<ul data-url='"+data[x].path+"' data-type='"+data[x].type+"' >"+data[x].name+"</li>")
				     	}
				     	
				     }
				   }
			});
		}else{
			node.attr("op","close");
			node.children().hide();
// 			node.find("li").remove(); 
// 			node.find("ul").remove();
		}
		console.log(node.attr("data-url"));
// 		event.stopPropagation();
		return false;
	})
	$("body ul").on("click","li",function(event){
		return false;
	})
}

</script>
</head>
<body>
	<div class="center">
		<ul>
			<ul data-url="D:/ahowso" data-typpe='dir'>D:</ul>
		</ul>
	</div>
</body>
<script type="text/javascript">
init();
</script>
</html>