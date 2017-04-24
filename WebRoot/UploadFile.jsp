<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="utf-8">
        <title>文件上传</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="css/dropzone.css">
    </head>
    <body>
    <div class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#"><span>LOGO</span></a>
            </div>
            <div class="collapse navbar-collapse" id="navbar-ex-collapse">
                <ul class="nav navbar-nav navbar-left">
                    <li class="dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">多维查询</a>
                        <ul class="dropdown-menu">
                            <li><a href="quire.html">查询</a></li>
                            <li><a href="quire_history.html">历史查询</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#">统计分析</a>
                    </li>
                    <li>
                        <a href="#">影像文本分析</a>
                    </li>
                    <li>
                       <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">知识库</a>
                        <ul class="dropdown-menu">
                            <li><a href="UploadFile.jsp">文件上传</a></li>
                            <li><a href="DownloadFile.jsp">文件下载</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </div>
        <div class="container">
           <div class="row clearfix">
		
		<div class="col-md-6 column" style="position:absolute; right:-300px;">
			 <a href="DownloadFile.jsp"><input type="button" class="btn btn-default btn-success" value="下载文件"></a>
		</div>
        <div id="mydropzone" class="dropzone" style="margin:100px 0 0 0px"></div>
		</div>
            <!--table class="table table-striped" style="margin:100px 0 0 0px">
	     
	        <thead>
		       <tr>
					<th>名称</th>
					<th>城市</th>
					<th>邮编</th>
			   </tr>
	       </thead>
		   <tbody>
					<tr>
						<td>Tanmay</td>
						<td>Bangalore</td>
						<td>560001</td>
					</tr>
					<tr>
						<td>Sachin</td>
						<td>Mumbai</td>
						<td>400003</td>
					</tr>
					<tr>
						<td>Uma</td>
						<td>Pune</td>
						<td>411027</td>
					</tr>
		   </tbody>
           </table-->
        </div>
        <script src="js/dropzone.js"></script>
      <script>
        var myDropzone = new Dropzone("div#mydropzone", { 
		url:"${pageContext.request.contextPath }/Filelu?method=upload",
		addRemoveLinks:true,
		paramName: "file",
		maxFilesize: 5, // MB
		maxFiles: 5,
		acceptedFiles: ".jpg,.jpeg,.png,.gif,.doc,.docx,.ppt,.pptx,.txt,.xls,.xlsx,.pdf,.zip"
       });
     </script>
    </body>
</html>

