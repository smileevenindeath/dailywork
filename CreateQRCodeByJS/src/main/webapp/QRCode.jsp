<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>生成二维码</title>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery.qrcode.min.js"></script>

</head>
<body>
	<h3>生成的二维码如下 :</h3>
	<br>
	<div id="qrcode"></div>
	<script type="text/javascript">
		jQuery('#qrcode').qrcode("www.666.com");
	</script>
</body>
</html>