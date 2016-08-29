function init() {

	var listUrl="treeList";
	var addUrl="addDict";
	var editUrl="editDict";
	var delUrl="deleteDict";
	var detailUrl="detailDict";
	
	var pid;
	var id;
	var nowNode;
	var key;
	var ulNode;
	
	
	
	$(".center").on("click","ul",list)
	function list(node) {
				if(node.target && node.clientX && node.clientY){
					node = $(this);
				}
				var open = node.attr("op");
				var r=node.attr("r");
				if (open == undefined||r=='Y') {
					$("ul").removeClass("active")
					node.attr("op", "Y");
					node.addClass("active")
					$.ajax({
						type : "POST",
						url : listUrl,
						data : "pid=" + node.closest("ul").attr("data-id"),
						success : function(data) {
							node.children().remove();
							for (var x in data) {
								if (data[x].pid == -1) {
									node.append("<ul data-id='" + data[x].id
											+ "' class='list-group-item' data-key="+data[x].dictKey+">"
											+ data[x].dictKey + "</ul>")
								} else {
									var text = "<table class='table'><tr>"
											+"<td>ID：" + data[x].id+"</td>" 
											+"<td>中文名：" + data[x].dictCn
											+ "</td>" + "<td>Value："
											+ data[x].dictValue + "</td>"
											+ "<td>排序号：" + data[x].sort
											+ "</td>" + "</tr></table>";
									
									node.append("<li data-id='" + data[x].id
											+ "' class='list-group-item'>"
											+ text + "</li>")
								}
							}
						},
						error:function(){
							alert("error")
						}
					});
				} else if (open == "N") {
					$("ul").removeClass("active")
					node.children().show();
					node.addClass("active")
					node.attr("op", "Y")
				} else {
					node.children().hide();
					$("ul").removeClass("active")
					node.attr("op", "N")
				}
				onMouseDown();
				return false;
			}
	$("body ul").on("click", "li", function(event) {
		onMouseDown();
		return false;
	})

	var menu = $('.menu:first');
	function showMenu(x, y) {
		menu.css({
			"left" : x + "px",
			"top" : y + "px"
		})
		menu.addClass("show-menu")
	}
	function hideMenu() {
		menu.removeClass("show-menu")
	}

	function onContextMenu(e) {
		e.preventDefault();
		showMenu(e.pageX, e.pageY);
		nowNode=$(e.target)
		key = $(e.target).closest("ul").attr("data-key");
//		pid =$(e.target).parent().closest("ul").attr("data-id");
		if(nowNode[0].tagName=="UL"){
			pid =$(e.target).parent().closest("ul").attr("data-id");		
		}else{
			pid =$(e.target).closest("ul").attr("data-id");			
		}
		id =$(e.target).closest("[data-id]").attr("data-id");
		ulNode=$(e.target).closest("ul")
		
		$(function() {
		}).on('click', onMouseDown);
	}
	function findId(node){
		var id=node.attr("data-id");
		if(id=undefined){
			id=findId(node.closest("[data-id]"))
		}
		return id;
	}
	function onMouseDown(e) {
		hideMenu();
		$(function() {}).off('click', onMouseDown);
	}

	$(function() {}).on('contextmenu', onContextMenu);
	
	function showAdd() {
		$("#addDictForm")[0].reset();
		$('#newDict').modal('show')
		if (pid == undefined||pid==-1) {
			$("#addDictForm > div:nth-child(2)").show()
			$("#dictKey").val("")
			$("#addDictForm > div:nth-child(3)").hide()
			$("#addDictForm > div:nth-child(4)").hide()
			$("#pid").val(-1)
		} else {
			$("#addDictForm > div:nth-child(2)").hide()
			$("#dictKey").val(key)
			$("#addDictForm > div:nth-child(3)").show()
			$("#addDictForm > div:nth-child(4)").show()
			$("#pid").val(pid)
		}
	}
	function showEdit() {
		$("#editDictForm")[0].reset();
		console.log(id)
		console.log(pid)
		var data = {
				id : id
			}
		$.ajax({
			type : "POST",
			url : detailUrl,
			async:false, 
			data :data,
			success : function(data) {
				$("#epid").val(data.pid);
				$("#edictKey").val(data.dictKey);
				$("#edictCn").val(data.dictCn);
				$("#edictValue").val(data.dictValue);
				$("#esort").val(data.sort);
				$("#estatus").val(data.status);
			}
		})
		
		$('#editDict').modal('show')
		if (pid == undefined||pid==-1) {
			$("#editDictForm > div:nth-child(2)").show()
			$("#editDictForm > div:nth-child(3)").hide()
			$("#editDictForm > div:nth-child(4)").hide()
			$("#pid").val(-1)
		} else {
			$("#editDictForm > div:nth-child(2)").hide()
			$("#edictKey").val(key)
			$("#editDictForm > div:nth-child(3)").show()
			$("#editDictForm > div:nth-child(4)").show()
			$("#pid").val(pid)
		}
	}
	function deleteDict(){
		var data = {
				id : id
			}
		if(confirm("是否要删除ID为"+id+"的字典元素")){
			$.ajax({
				type : "POST",
				url : delUrl,
				data :data,
				success : function(data) {
					alert(data)
					ulNode.attr("r","Y");
					list(ulNode)
					ulNode.removeAttr("r");
				}
			})
		}
	}
	
	$(function() {
		$("body > menu > li:nth-child(1) ").on("click", showAdd)
		$("body > menu > li:nth-child(2) ").on("click", showEdit)
		$("body > menu > li:nth-child(3)").on("click",deleteDict)
		
		$("#btnEditDict").on("click", function() {
			var data = {
				pid : $("#epid").val(),
				dictKey : key,
				dictCn : $("#edictCn").val(),
				dictValue : $("#edictValue").val(),
				sort : $("#esort").val(),
				status : $("#estatus").val()
			}
			$.ajax({
				type : "POST",
				url : editUrl,
				data : data,
				success : function(data) {
					$('#editDict').modal('hide');
					alert(data)
					ulNode.attr("r","Y");
					list(ulNode)
					ulNode.removeAttr("r");
				}
			})
			
		})
		
		$("#btnAddDict").on("click", function() {
			var data = {
				pid : $("#pid").val(),
				dictKey : $("#dictKey").val(),
				dictCn : $("#dictCn").val(),
				dictValue : $("#dictValue").val(),
				sort : $("#sort").val(),
				status : $("#status").val()
			}
			$.ajax({
				type : "POST",
				url : addUrl,
				data : data,
				success : function(data) {
					$('#newDict').modal('hide');
					alert(data)
					ulNode.attr("r","Y");
					list(ulNode)
					ulNode.removeAttr("r");
				}
			})
		})
	})
}
