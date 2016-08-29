<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>
<base href="">
<link rel="stylesheet" type="text/css"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.css">
<link rel="stylesheet" type="text/css"
	href="http://fonts.useso.com/css?family=Roboto:400,100,300,500">
<link href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="//cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	rel="stylesheet">
<link href="../jsp/css/tree.css" rel="stylesheet">
<link href="../jsp/css/right/styles.css" rel="stylesheet">

<script type="text/javascript" src="../jsp/js/jquery-2.1.1.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="../jsp/js/tree.js"></script>
</head>
<body>
	<div class="center">
		<ul class="list-group" data-id="-1">根
		</ul>
	</div>

	<menu class="menu">
		<li class="menu-item">
			<button type="button" class="menu-btn">
				<i class="fa fa-folder-open"></i> <span class="menu-text">新增</span>
			</button>
		</li>
		<li class="menu-item">
			<button type="button" class="menu-btn">
				<i class="fa fa-folder-open"></i> <span class="menu-text">编辑</span>
			</button>
		</li>
		<li class="menu-item">
			<button type="button" class="menu-btn">
				<i class="fa fa-trash"></i> <span class="menu-text">删除</span>
			</button>
		</li>
	</menu>


	<!-- 新增 模态框（Modal） -->
	<div class="modal fade" id="newDict" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">新增字典</h4>
				</div>
				<div class="modal-body">
					<form class="form-signin" id="addDictForm" action="addDict">
						<input type="hidden" class="form-control" id="pid" name="pid"
							placeholder="请输入字典Key">
						<div class="form-group">
							<label for="dictKey" class="from-label">字典Key：</label> <input
								type="text" class="form-control" id="dictKey" name="dictKey"
								placeholder="请输入字典Key">
						</div>
						<div class="form-group">
							<label for="dictValue" class="from-label">字典Value：</label> <input
								type="text" class="form-control" id="dictValue" name="dictValue"
								placeholder="请输入字典Value">
						</div>
						<div class="form-group">
							<label for="dictCn" class="from-label">字典Cn：</label> <input
								type="text" class="form-control" id="dictCn" name="dictCn"
								placeholder="请输入字典Cn">
						</div>
						<div class="form-group">
							<label for="sort" class="from-label">排序sort：</label> <input
								type="text" class="form-control" id="sort" name="sort"
								placeholder="请输入排序sort">
						</div>
						<div class="form-group">
							<label for="status" class="from-label">状态：</label> <input
								type="text" class="form-control" id="status" name="status"
								placeholder="请输入状态status">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="btnAddDict">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
	<!-- 编辑 模态框（Modal） -->
	<div class="modal fade" id="editDict" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">编辑字典</h4>
				</div>
				<div class="modal-body">
					<form class="form-signin" id="editDictForm" action="addDict">
						<input type="hidden" class="form-control" id="epid" name="pid">
						<div class="form-group">
							<label for="edictKey" class="from-label">字典Key：</label> <input
								type="text" class="form-control" id="edictKey" name="dictKey"
								placeholder="请输入字典Key">
						</div>
						<div class="form-group">
							<label for="edictValue" class="from-label">字典Value：</label> <input
								type="text" class="form-control" id="edictValue"
								name="dictValue" placeholder="请输入字典Value">
						</div>
						<div class="form-group">
							<label for="edictCn" class="from-label">字典Cn：</label> <input
								type="text" class="form-control"  id="edictCn" name="dictCn"
								placeholder="请输入字典Cn">
						</div>
						<div class="form-group">
							<label for="esort" class="from-label">排序sort：</label> <input
								type="text" class="form-control" id="esort" name="sort"
								placeholder="请输入排序sort">
						</div>
						<div class="form-group">
							<label for="estatus" class="from-label">状态：</label> <input
								type="text" class="form-control" id="estatus" name="status"
								placeholder="请输入状态status">
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
					<button type="button" class="btn btn-primary" id="btnEditDict">提交</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
	</div>
	<!-- /.modal -->
	<script type="text/javascript">
		init();
	</script>
</body>
</html>