<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="images/icon.png"></link>
  <link href="css/mms_login.min.css" rel="stylesheet" type="text/css"></link>
  <link rel="stylesheet" type="text/css" href="css/buttonStyle.css"></link>
    <script>try{Typekit.load({ async: true });}catch(e){}</script>
    

<script>
var name = '<%=request.getAttribute("error")%>'; //将输入的内容赋给变量 name ，  
        //这里需要注意的是，prompt有两个参数，前面是提示的话，后面是当对话框出来后，在对话框里的默认值  
 if (!name.match("null"))//如果返回的有内容  
    alert(name);
</script>

	<style type="text/css">
body{background:#f8f8f8;
        background-image: url(images/background2.jpg);      
        background-size:cover;   
}
    #footer {
        width: 100%;
        height: 50px;
        position: fixed;
        bottom: 0;
    }
</style> 
</head>

<body>
    <div>
    <h1 class="threed" style="font-size:70px" align="center">医疗信息课程项目</h1>
        <div class="card card-container">
        <h2 class='login_title text-center' style="color:gray">Login</h2>
        <hr/>
            <form class="form-signin" action="<%=path %>/LoginServlet" method="post" >
                <span id="reauth-email" class="reauth-email"></span>
                <p class="input_title">用户名</p>
                <input type="text" id="inputEmail" name="inputUser" class="login_box" placeholder="userString" required autofocus/>
                <p class="input_title">密码</p>
                <input type="password" id="inputPassword" name="inputPassword"  class="login_box" placeholder="Nothing Here" required/>
                <button class="a_demo_four" style="border-width:0px" type="submit">登录</button>
            </form><!-- /form -->
        </div><!-- /card-container -->
    </div><!-- /container -->
<div id="footer">
	<p align="center"  style="color:white;font-size:14px;">     Copyright - 2017湖南大学软件工程课程项目 - 软件1401</p>
</div>
	</body>
</html>