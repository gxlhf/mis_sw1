<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%  
    //String swfFilePath=session.getAttribute("swfpath").toString();  
    String swfFilePath=(String)request.getAttribute("swfpath");
  //  System.out.println(swfFilePath);
    //swfFilePath="preview/ddd.swf";
%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
	<link rel="shortcut icon" href="images/icon.png"></link>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<script type="text/javascript" src="js/jquery.js"></script>  
<script type="text/javascript" src="js/flexpaper_flash.js"></script>  
<script type="text/javascript" src="js/flexpaper_flash_debug.js"></script>  
 <script type="text/javascript" src="js/swfobject.js"></script>  
<style type="text/css" media="screen">   
            html, body  { height:100%; }  
            body { margin:0; padding:0; overflow:auto; }     
            #flashContent { display:none; }  
        </style>   
  
<title>文档在线预览系统</title>  
</head>  
<body>   
        <div style="position:absolute;left:50px;top:10px;">  
            <a id="viewerPlaceHolder" style="width:820px;height:650px;display:block"></a>  
              
            <script type="text/javascript">   
                var fp = new FlexPaperViewer(     
                         'FlexPaperViewer',  
                         'viewerPlaceHolder', { config : {  
                         SwfFile : decodeURI('<%=swfFilePath%>'),  
                         Scale : 0.6,   
                         ZoomTransition : 'easeOut',  
                         ZoomTime : 0.5,  
                         ZoomInterval : 0.2,  
                         FitPageOnLoad : true,  
                         FitWidthOnLoad : false,  
                         FullScreenAsMaxWindow : false,  
                         ProgressiveLoading : false,  
                         MinZoomSize : 0.2,  
                         MaxZoomSize : 5,  
                         SearchMatchAll : false,  
                         InitViewMode : 'SinglePage',  
                           
                         ViewModeToolsVisible : true,  
                         ZoomToolsVisible : true,  
                         NavToolsVisible : true,  
                         CursorToolsVisible : true,  
                         SearchToolsVisible : true,  
                          
                         localeChain: 'en_US'  
                         }});  
            </script>              
        </div>  
</body>  
</html> 
