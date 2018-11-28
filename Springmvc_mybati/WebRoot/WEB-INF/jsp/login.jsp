<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<div>
	<form  id="login" action="${pageContext.request.contextPath }/login.action" method="post" ></form>
		<table>

			<tr>
				<td>username:</td>
				<td><input type="text" name="username"   /></td>
				<td>提示信息</td>
			</tr>
			<tr>
				<td>password</td>
				<td><input type="password" name="password"  /></td>
				<td>提示信息</td>
			</tr>
			<tr >
				<td colspan="2"><input type="submit" value="submit"/></td>
				<td><input type="reset" value="reset"/></td>
				
			</tr>


		</table>



	</div>

</body>
</html>