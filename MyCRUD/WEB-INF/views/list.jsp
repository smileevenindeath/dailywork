<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>员工管理</title>
<!-- 项目相对路径 -->
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!--web 路径 ：
不以 /  开始的相对路径，找资源以当前路径为准，容易出错
以 / 开始的相对路径，找资源，以服务器路径为标准（http://localhost:3306）+项目名
	http://localhost:3306/crud
  -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.css"
	rel="stylesheet">
<script src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
	<!--搭建显示页面  -->
	<div class="container">

		<!--标题 -->
		<div class="row">
			<div class="col-md-12">
				<h2>SSM-CRUD</h2>
			</div>
		</div>

		<!--按钮  -->
		<div class="row">

			<div class="col-md-4 col-md-offset-8">
				<button class="btn btn-primary">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
				</button>
				<button class="btn btn-warning">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>

		<!--显示表格数据  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>Emp_ID</th>
						<th>Emp_Name</th>
						<th>Gender</th>
						<th>Email</th>
						<th>Dept_Name</th>
						<th>操作</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${ emp.empId}</th>
							<th>${ emp.empName}</th>
							<th>${ emp.gender == "M" ? "男": "女"}</th>
							<th>${ emp.email}</th>
							<th>${ emp.department.deptName}</th>
							<th>
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
								</button>
								<button class="btn btn-warning btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
								</button>
							</th>
						</tr>
					</c:forEach>

				</table>
			</div>


		</div>

		<!--显示分页信息  -->
		<div class="row">
			<!-- 分页信息 -->
			<div class="col-md-6">当前第${pageInfo.pageNum }页 --
				总计${pageInfo.pages }页 -- 总计${pageInfo.total}条记录</div>
			<!-- 分页条 -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
					<ul class="pagination">
						<li><a href="${APP_PATH }/emps?pn=1">首页</a></li>
						<c:if test="${pageInfo.hasPreviousPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum-1} "
								aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:if>

						<c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
							<c:if test="${page_Num == pageInfo.pageNum }">
								<li class="active"><a href="#">${page_Num }</a></li>
							</c:if>
							<c:if test="${page_Num != pageInfo.pageNum }">
								<li><a href="${APP_PATH }/emps?pn=${page_Num}">${page_Num }</a></li>
							</c:if>
						</c:forEach>
						<c:if test="${pageInfo.hasNextPage }">
							<li><a href="${APP_PATH }/emps?pn=${pageInfo.pageNum+1}"
								aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							</a></li>
						</c:if>
						<li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">尾页</a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>


</body>
</html>