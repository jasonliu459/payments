<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta charset="utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
<script type="text/javascript" src="page/js/jquery-2.0.3.min.js"></script>
<script type="text/javascript">  
	$.ajax({
	   type: "GET",
	   url: "user/single/1",
	   success: function(msg){
	     console.log(msg.userName );
	   }
	});
    </script>
  </body>
</html>
