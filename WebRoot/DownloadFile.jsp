<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <meta charset="UTF-8">
        <title>知识库资料下载</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
         <script type="text/javascript" src="js/jquery-3.1.1.min.js"></script>   
	  <script type="text/javascript">
	    function myFunction(param,param2){             //request.getParameter不能接收#@等特殊字符，因此需要先行进行转换
	       var temp=encodeURIComponent(param);         //temp为完整的文件名
	       if(param2=="d")
             url = "${pageContext.request.contextPath }/Filelu?method=down&fileName="+temp;
           else if(param2=="dle")
             url="${pageContext.request.contextPath }/Filelu?method=delete&fileName="+temp;
           else url="${pageContext.request.contextPath }/Filelu?method=converter&fileName="+temp;
            window.location.href=url;
	    }
	  </script>
        <style type="text/css">
        body,td,th {
	font-family: "Avenir Light", "Avenir Medium", "PingFang HK Light";
	color: #555;
	font-size: 15px;
}
        a:link {
	color: #666666;
	text-decoration: none;
}
a:visited {
	text-decoration: none;
}
a:hover {
	text-decoration: none;
}
a:active {
	text-decoration: none;
}
        </style>
    </head>
    <body>
     <%
     String str="123";
     if(str.equals(request.getAttribute("flaag"))){
       
   %>
    <script>
    alert("此文件暂不支持预览");
   </script>
    <%} %>
   <%!int number=1;%>
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
                   
                    <li>
                        <a href="index.html">数据显示</a>
                    </li>
                    <li>
                        <a href="#">统计处分析</a>
                    </li>
                    
                     <li class="dropdown active">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">知识库</a>
                        <ul class="dropdown-menu">
                            <li><a href="UploadFile.jsp">上传资料</a></li>
                            <li><a href="DownloadFile.jsp">下载资料</a></li>
                        </ul>
                    </li>
                    
                </ul>
            </div>
        </div>
    </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                   <hr/>
                  <!--form class="form-inline" role="form">
                        <div class="form-group">
                            <label>起止时间</label>
                            <input type="text" class="form-control" >
                            -
                            <input type="text" class="form-control">
                        </div>
                    </form>
                    <br>
                 
                  <form class="form-inline" role="form">
                        <div class="form-group">
                            <label>类&nbsp;别</label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select class="form-control">
                                <option>所有</option>
                                <option>文本</option>
                                <option>图像</option>
                                
                            </select>
                        </div>
                    </form-->
                    <br>
                      <form class="form-inline" role="form">
                        <div class="form-group">
                            <label>关&nbsp;键&nbsp;字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
                           

                            <input type="text" class="form-control" id="keyw" name="keyw" placeholder="请输入关键字">
                     
                         </div>
                  &nbsp;&nbsp;
                    <button type="button" id="query" class="btn btn-default">查询</button>  
                     <button type="button" id="back" class="btn btn-default" style="margin-left:50px">还原</button>  
                    <button type="button" class="btn btn-default" id="delete" style="margin-left:400px">删除</button> 
                 </form>
                </div>
            </div>

<hr/>
          <div class="row">
                <div class="col-lg-12">
                    <div class="table-responsive">
                        <div align="center" >
                          <table width="68%" height="330"  align="center" class="table table-bordered table-hover table-striped"  >
                            <thead>
                              <tr align="center">
                                <td width="5%">选择</td>
                                <td width="19%">序号</td>
                                <td width="29%">名称</td>
                                <td width="27%">上传时间</td>
                                <td width="20%" colspan="2">操 作</td>
                                
                                
                                </tr>
                              </thead>
                            <tbody>
                       <%Map<String,String> fil = (Map<String,String>)request.getAttribute("filename");
                          Map<String,String> filee = (Map<String,String>)request.getAttribute("fileNames");
			              if(filee==null || filee.size()<1){  
			                 out.print("没有 数据!");  
			              }  
			              else{  
			                for(Map.Entry<String,String> entry : filee.entrySet()){  
			                String s=fil.get(entry.getKey());
                        %>  
                              <tr align="center">
                                <td >
                                  <label class="checkbox-inline">
                                    <input type="checkbox"  name="check" value="<%=entry.getKey() %>" align="middle"></label></td>
                                <td><%=number %></td>
                                <td><a href="javascript:myFunction('<%=entry.getKey() %>','conv');" target="_blank"><%=entry.getValue() %></a></td>
                                <td><%=s %></td>
                                <td>				
								    <a href="javascript:myFunction('<%=entry.getKey() %>','d');">下载</a>					
							    </td>
							    <td>
							        <a href="javascript:myFunction('<%=entry.getKey() %>','dle');">删除</a>
				                </td>
                              </tr>    
                                  <%number++;}} %>                        
                              </tbody>
                              <%number=1; %>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script type="text/javascript">
             $("#back").click(function(){
		      var url="${pageContext.request.contextPath }/Filelu?method=downList";
		       window.location.href=url;
		  })
		  
		     $("#query").click(function(){
		       var keyword=$("#keyw").val().trim();
		       if(keyword=="") alert("请输入要查找的关键字");
		       else{
		       var url="${pageContext.request.contextPath }/Filelu?method=search&keyword="+keyword;
		       window.location.href=url;
		       }
		    })
		    
		     $("#delete").click(function(){
		       var content="";
		       $('input[name="check"]:checked').each(function(){  
		         var change=$(this).val().replace('#','_');  
                content=content+change+"+";  
               })
                 
		       if(content=="")  alert("请选择要删除的文件");		       
		       else{		        
		          var url="${pageContext.request.contextPath }/Filelu?method=plurality&parameter="+content;
		          window.location.href=url;
		       }
		     })
        </script>
    </body>
</html>

