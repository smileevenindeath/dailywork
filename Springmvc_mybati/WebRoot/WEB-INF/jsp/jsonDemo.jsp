<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Json demo</title>
<!-- 引入js文件 -->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.2.2.min.js"></script>
<script type="text/javascript">
	//请求的是json 输出的是 json
	function requestJson() {
		
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/request.action',
			contentType:'application/json;charset=utf-8',
			//数据格式是json 串 商品信息
			data:'{"name":"phone","price":"999"}',
			success:function(data){//返回j'son  结果
				alert(data.name);
			}
		});
	}

	//请求的是 key/value  输出的是 json 
	function responseJson() {
		$.ajax({
			type:'post',
			url:'${pageContext.request.contextPath}/responJson.action',
			
			//默认key/type提交格式  所以不用设置下面的 contentType:
			//contentType:'application/xx-mm;charset=utf-8',
			
			//数据格式是key/value 串 商品信息
			data:'namae=phone&price=999',
			success:function(data){//返回j'son  结果
				alert(data.name);
			}
		});
		
		

	}
</script>
</head>
<body>
	<input type="button" onclick="requestJson()" value="请求的是json 输出的是 json"/>
	<input type="button" onclick="responseJson()" value="请求的是 key/value  输出的是 json " />
</body>
</html>