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
	<!-- 员工添加的 Modal -->
	<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">员工添加</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="empName_add_input" class="col-sm-2 control-label">EmpName</label>
							<div class="col-sm-10">
								<input type="text" name="empName" class="form-control"
									id="empName_add_input" placeholder="empName"><span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label for="eamil_add_input" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_add_input" placeholder="email@west.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_add_input" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2 _add_input" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="eamil_add_input" class="col-sm-2 control-label">DeptName</label>
							<div class="col-sm-4">
								<!--部门提交部门Id即可  -->
								<select class="form-control" name="dId" id="depts_select">
								</select>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
					<button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
				</div>
			</div>
		</div>
	</div>

	<!--员工信息编辑 modal  -->
	<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">员工修改</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal">
						<div class="form-group">
							<label for="empName_update_input" class="col-sm-2 control-label">EmpName</label>
							<div class="col-sm-10">
								<p class="form-control-static" id="empName_update_static"></p>
							</div>
						</div>
						<div class="form-group">
							<label for="eamil_update_input" class="col-sm-2 control-label">Email</label>
							<div class="col-sm-10">
								<input type="text" name="email" class="form-control"
									id="email_update_input" placeholder="email@west.com"> <span
									class="help-block"></span>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">Gender</label>
							<div class="col-sm-10">
								<label class="radio-inline"> <input type="radio"
									name="gender" id="gender1_update_input" value="M"
									checked="checked"> 男
								</label> <label class="radio-inline"> <input type="radio"
									name="gender" id="gender2 _update_input" value="F"> 女
								</label>
							</div>
						</div>
						<div class="form-group">
							<label for="eamil_update_input" class="col-sm-2 control-label">DeptName</label>
							<div class="col-sm-4">
								<!--部门提交部门Id即可  -->
								<select class="form-control" name="dId" id="depts_select">
								</select>
							</div>
						</div>
					</form>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">退出</button>
					<button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
				</div>
			</div>
		</div>
	</div>

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
				<button class="btn btn-primary" id="emp_add_modal_btn">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
				</button>
				<button class="btn btn-warning" id="emp_delete_all_btn">
					<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
				</button>
			</div>
		</div>

		<!--显示表格数据  -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th><input type="checkbox" id="check_all" /></th>
							<th>Emp_ID</th>
							<th>Emp_Name</th>
							<th>Gender</th>
							<th>Email</th>
							<th>Dept_Name</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

					</tbody>

				</table>
			</div>


		</div>

		<!--显示分页信息  -->
		<div class="row">
			<!-- 分页信息 -->
			<div class="col-md-6" id="page_info_area"></div>
			<div class="col-md-6" id="page_nav_area"></div>

		</div>
	</div>



	<script type="text/javascript">
		//保存总记录数
		var totalRecord, currentPage;

		//1.页面加载完以后，直接去发送一个ajax请求，要到分页数据  
		$(function() {
			//去首页
			to_page(1);
		});

		function to_page(pn) {
			$.ajax({
				url : "${APP_PATH}/emps",
				data : "pn=" + pn,
				type : "get",
				success : function(result) {
					//console.log(result)
					//1.解析显示员工数据
					build_emps_table(result);
					//2.解析显示分页信息
					build_page_info(result);
					//3.解析显示分页条数据
					build_page_nav(result);
				}
			});

		}

		//解析员工数据 并生成  <tr><td></td></tr>  添加到 <tbody></tbody>中
		function build_emps_table(result) {
			//构造前先清空 table 数据
			$("#emps_table tbody").empty();

			var emps = result.extend.pageInfo.list;
			$
					.each(
							emps,
							function(index, items) {
								var checkBoxTd = $("<td><input type='checkbox' class='check_item'/></td>");
								var empIdTD = $("<td></td>")
										.append(items.empId);
								var empNameTD = $("<td></td>").append(
										items.empName);
								var genderTD = $("<td></td>").append(
										items.gender == "M" ? "男" : "女");
								var emailTD = $("<td></td>")
										.append(items.email);
								var deptNameTD = $("<td></td>").append(
										items.department.deptName);
								/* 
								<button class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>编辑
								</button>
								<button class="btn btn-warning btn-sm">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>删除
								</button>
								 */
								var editbtn = $("<button></button>")
										.addClass(
												"btn btn-primary btn-sm edit_btn")
										.append(
												$("<span></span>")
														.addClass(
																"glyphicon glyphicon-pencil"))
										.append("编辑");
								//为编辑按钮添加一个自定义的属性 来表示 当前员工id
								editbtn.attr("edit_id", items.empId);

								var deleteBtn = $("<button></button>")
										.addClass(
												"btn btn-warning btn-sm delete_btn")
										.append(
												$("<span></span>")
														.addClass(
																"glyphicon glyphicon-trash"))
										.append("删除");
								//为删除按钮添加一个自定义的属性 来表示 当前员工id
								deleteBtn.attr("del_id", items.empId);

								var btnTD = $("<td></td>").append(editbtn)
										.append(" ").append(deleteBtn);
								//append 方法执行完后还是返回原来的元素
								$("<tr></tr>").append(checkBoxTd).append(
										empIdTD).append(empNameTD).append(
										genderTD).append(emailTD).append(
										deptNameTD).append(btnTD).appendTo(
										"#emps_table tbody");

							});

		}
		//解析显示 分页 信息
		function build_page_info(result) {
			$("#page_info_area").empty();
			$("#page_info_area").append(
					"当前第" + result.extend.pageInfo.pageNum + " 页 -- 总计"
							+ result.extend.pageInfo.pages + "页 -- 总计"
							+ result.extend.pageInfo.total + "  条记录");
			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}
		/**
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
		 */
		//解析显示 分页条 信息,点击 跳转。。。。
		function build_page_nav(result) {
			$("#page_nav_area").empty();

			var ul = $("<ul></ul>").addClass("pagination");
			//构建元素
			var fristPageLi = $("<li></li>").append(
					$("<a></a>").append("首页").attr("href", "#"));

			var prePageLi = $("<li></li>").append(
					$("<a></a>").append("&laquo;"));

			if (result.extend.pageInfo.hasPreviousPage == false) {
				fristPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
			} else {
				//为元素添加 点击翻页事件
				fristPageLi.click(function() {
					to_page(1);
				});
				prePageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum - 1);
				});
			}

			var nextPageLi = $("<li></li>").append(
					$("<a></a>").append("&raquo;"));

			var lastPageList = $("<li></li>").append(
					$("<a></a>").append("尾页").attr("href", "#"));

			if (result.extend.pageInfo.hasNextPage == false) {
				nextPageLi.addClass("disabled");
				lastPageList.addClass("disabled");
			} else {
				//

				nextPageLi.click(function() {
					to_page(result.extend.pageInfo.pageNum + 1);
				});
				lastPageList.click(function() {
					to_page(result.extend.pageInfo.pages);
				});
			}

			//添加首页 和 前一页
			ul.append(fristPageLi).append(prePageLi);
			//1,2,3,4,5 便利给ul 中添加页码
			$.each(result.extend.pageInfo.navigatepageNums, function(index,
					items) {
				var numLi = $("<li></li>").append($("<a></a>").append(items));
				if (result.extend.pageInfo.pageNum == items) {
					numLi.addClass("active");
				}
				numLi.click(function() {
					to_page(items)
				});
				ul.append(numLi);
			});
			//添加下一页 和 尾页
			ul.append(nextPageLi).append(lastPageList);

			var nav = $("<nav></nav>").append(ul);
			nav.appendTo("#page_nav_area");

		};

		//完整重置（表单数据 和 表单）
		function reset_form(element) {
			//清空表单内容
			$(element)[0].reset();
			//清空表单样式
			$(element).find("*").removeClass("has-error has-success");
			$(element).find(".help-block").text("");

		}

		//点击新增按钮  弹出模态框
		$("#emp_add_modal_btn").click(function() {
			//清除表单数据  重置 (应该完整重置（表单数据 和 表单样式）)
			//$("#empAddModal form")[0].reset();
			reset_form("#empAddModal form");
			//发送ajax请求，查处部门信息，显示在下拉列表中
			//查询部门信息
			getDepts("#empAddModal select");
			//弹出模态框
			$("#empAddModal").modal({
				backdrop : "static"
			});

		});

		//查出所有部门信息 并显示在下拉列表
		function getDepts(element) {
			//清空之前下拉列表的值
			$(element).empty();
			$.ajax({
				url : "${APP_PATH}/depts",
				type : "GET",
				success : function(result) {
					//console.log(result);
					//显示部门信息在下拉列表

					$.each(result.extend.depts, function() {
						var optionEle = $("<option></option>").append(
								this.deptName).attr("value", this.deptId);
						optionEle.appendTo(element);
					});
				}
			});
		}
		//校验方法  校验表单数据 名字 邮箱
		function validate_add_form() {
			//1. 拿到要校验的数据  使用正则表达式
			//校验用户名
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			//alert(regname.test(empName));
			if (!regName.test(empName)) {
				//清空之前的样式
				//alert("用户名可以是6-16位英文字符或2-5位中文字符");

				show_validate_msg("#empName_add_input", "error",
						"用户名可以是6-16位英文字符或2-5位中文字符");
				return false;

				// 	$("#empName_add_input").parent().addClass("has-error");
				//$("#empName_add_input").next("span").text(
				//"用户名可以是6-16位英文字符或2-5位中文字符"); 

			} else {
				show_validate_msg("#empName_add_input", "success", "");
				//$("#empName_add_input").parent().addClass("has-success");
				//$("#empName_add_input").next("span").text(""); 
			}
			//校验邮箱
			var email = $("#email_add_input").val();
			regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if (!regEmail.test(email)) {
				//清空之前的样式
				//alert("Email 格式不正确 ");
				show_validate_msg("#email_add_input", "error", "Email 格式不正确!");
				return false;
				// $("#email_add_input").parent().addClass("has-errror");
				//$("#email_add_input").next("span").text("Email 格式不正确 "); 

			} else {
				show_validate_msg("#email_add_input", "success", "");
				// $("#email_add_input").parent().addClass("has-success");
				//$("#email_add_input").next("span").text("");
			}
			return true;

		}

		//抽取成函数  
		function show_validate_msg(element, status, msg) {
			//清除当前元素校验状态
			$(element).parent().removeClass("has-success has-error");
			$(element).next("span").text("");

			if ("success" == status) {
				$(element).parent().addClass("has-success");
				$(element).next("span").text(msg);
			} else if ("error" == status) {
				$(element).parent().addClass("has-error");
				$(element).next("span").text(msg);
			}

		}
		//校验用户名是否可用
		$("#empName_add_input").change(
				function() {
					//发送Ajax 请求 校验用户名是否可用
					var empName = this.value;
					$.ajax({
						url : "${APP_PATH}/checkuser",
						data : "empName=" + empName,
						type : "POST",
						success : function(result) {
							if (result.code == 100) {
								show_validate_msg("#empName_add_input",
										"success", "用户名可用");
								$("#emp_save_btn").attr("ajax-va", "success");
							} else {
								show_validate_msg("#empName_add_input",
										"error", result.extend.va_msg);
								$("#emp_save_btn").attr("ajax-va", "error");
							}
						}
					});
				});

		//点击保存按钮  保存员工信息
		$("#emp_save_btn")
				.click(
						function() {
							//1. 模态框中的数据保存的数据库
							//1. 先对要提交的数据进行校验
							//判断 之前的ajax 用户名校验是否成功，如果成功 可以点击保存按钮
							if (!validate_add_form()) {
								return false;
							}
							;
							if ($(this).attr("ajax-va") == "error") {
								return false;
							}
							//2. 发送Ajax 请求 保存员工
							//alert($("#empAddModal form").serialize());

							$
									.ajax({
										url : "${APP_PATH}/emps",
										type : "POST",
										/*表单数据序列化  */
										data : $("#empAddModal form")
												.serialize(),
										success : function(result) {
											//alert(result.msg);
											//员工保存成功后：
											//1. 需要关闭模态框 ， 状态码： 100 成功 200 失败
											if (result.code == 100) {
												$("#empAddModal").modal("hide");
												//2.前往最后一页 显示数据
												//发送Ajax请求 显示最后一页数据即可
												//传入一个大于总页数的数字即可，自动显示最后一页
												//totalRecord为前面声明的总记录数  肯定比总页数大
												to_page(totalRecord);
											} else {
												//显示失败信息
												//console.log();
												//有哪个字段的错误信息就显示那个字段的
												if (undefine != result.extend.errorFields.email) {
													//显示邮箱错误信息
													show_validate_msg(
															"#email_add_input",
															"error",
															result.extend.errorFields.email);
												}
											}
											if (undefine != result.extend.errorFields.empName) {
												//显示用户名错误信息
												show_validate_msg(
														"#empName_add_input",
														"error",
														result.extend.errorFields.empName);
											}

										}
									});

						});
		//按钮创建之前绑定了click  绑定不上
		//1。 可以在创建按钮的时候绑定点击时间  2. 绑定 .live ()
		//jquery  新版没有 live 方法  使用 on进行绑定
		$(document).on("click", ".edit_btn", function() {
			//查出部门信息  显示在列表
			getDepts("#empUpdateModal select");
			//查出员工信息，显示员工信息
			getEmp($(this).attr("edit_id"));

			//把编辑的员工id 传到更新按钮
			$("#emp_update_btn").attr("edit_id", $(this).attr("edit_id"));

			//弹出模态框
			$("#empUpdateModal").modal({
				backdrop : "static"
			});
		});
		function getEmp(id) {
			$.ajax({
				url : "${APP_PATH}/emp/" + id,
				type : "GET",
				success : function(result) {
					var empData = result.extend.emp;
					$("#empName_update_static").text(empData.empName);
					$("#email_update_input").val(empData.email);
					$("#gender_update_radio").val(empData.email);
					$("#empUpdateModal input[name=gender]").val(
							[ empData.gender ]);
					$("#empUpdateModal select").val([ empData.dId ]);
				}
			});
		}
		function checkEmail(element) {
			//邮箱验证  是否合法
			var email = $(element).val();
			regEmail = /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/;
			if (!regEmail.test(email)) {
				//清空之前的样式
				//alert("Email 格式不正确 ");
				show_validate_msg(element, "error", "Email 格式不正确!");
				return false;
			} else {
				show_validate_msg(element, "success", "");
				return true;
			}
		}

		//点击更新按钮  更新员工信息
		$("#emp_update_btn").click(function() {

			if (!checkEmail("#email_update_input")) {
				return false;
			}
			//发送Ajax请求 请求保存员工信息
			$.ajax({
				url : "${APP_PATH}/emp/" + $(this).attr("edit_id"),
				type : "PUT",
				data : $("#empUpdateModal form").serialize(),
				success : function(result) {
					alert(result.msg);
					$("#empUpdateModal").modal("hide");
					//2.前往最后一页 显示数据
					//发送Ajax请求 显示最后一页数据即可
					//传入一个大于总页数的数字即可，自动显示最后一页
					//totalRecord为前面声明的总记录数  肯定比总页数大
					to_page(currentPage);
				}
			});
		});
		//单个删除
		$(document).on("click", ".delete_btn", function() {
			//弹出提示框
			var empName = $(this).parents("tr").find("td:eq(2)").text();
			var empId = $(this).attr("del_id");
			if (confirm("确认删除【" + empName + "】吗?")) {
				//确认 发送 ajax
				$.ajax({
					url : "${APP_PATH}/emp/" + empId,
					type : "DELETE",
					success : function(result) {
						alert(result.msg);
						//回到本页
						to_page(currentPage);
					}
				});
			}
		});

		//全选  全部选
		$("#check_all").click(function() {
			//	attr获取 checked  时， undefined
			//dom原生 属性  ,attr 获取 自定义属性值
			//prop 修改和读取 daom 原生属性
			//alert($(this) .prop("checked"));
			$(".check_item").prop("checked", $(this).prop("checked"));
		});

		//check_item  当选中页面所有元素 时  全选按钮自动选中
		$(document)
				.on(
						"click",
						".check_item",
						function() {
							//判断当前选择的元素是否是5个
							var flag = $(".check_item:checked").length == $(".check_item").length;

							$("#check_all").prop("checked", flag);
						});
		//批量删除
		$("#emp_delete_all_btn").click(
				function() {
					var empNames = "";
					var del_idstr = "";
					$.each($(".check_item:checked"), function() {
						//this
						empNames += $(this).parents("tr").find("td:eq(2)")
								.text()
								+ "、";
						del_idstr += $(this).parents("tr").find("td:eq(1)")
								.text()
								+ "-";

					});
					//去除empNames 多余的 、号
					empNames = empNames.substring(0, empNames.length - 1);
					//去除empNames 多余的 、号
					del_idstr = del_idstr.substring(0, del_idstr.length - 1);
					if (confirm("确认删除【" + empNames + "】吗?")) {
						//确认 发送 ajax
						$.ajax({
							url : "${APP_PATH}/emp/" + del_idstr,
							type : "DELETE",
							success : function(result) {
								alert(result.msg);
								//回到本页
								to_page(currentPage);
							}
						});
					}
				});
	</script>

</body>
</html>
