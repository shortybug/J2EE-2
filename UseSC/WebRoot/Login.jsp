<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
		<h3>请登录：</h3>
		<form action ="login.sc" name="loginForm">
			<table>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input type="text" name="password"></td>
				</tr>
				<tr>
					<td><input type="submit" name="login" value="登录"/></td>
				</tr>
			</table>
		</form>
		<div>
             <font color="black">${sessionScope.loginMessage }</font> 
        </div>
         <br>
            <div>
                	如果您还不是我们的用户，请<a href="/UseSC/Regist.jsp">点击注册</a>
            </div>
            <%
                session.removeAttribute("loginMessage");
            %>
  </body>
</html>
