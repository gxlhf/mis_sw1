<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@page import="entity.Chart"%>
<%@ page import="org.jfree.data.general.DefaultPieDataset,org.jfree.chart.ChartFactory
,org.jfree.chart.JFreeChart,org.jfree.chart.servlet.*" %>

<html>
    <head>
        <meta charset="utf-8">
    </head>
    
    <body>    
       <br><hr>
       <%    
             
          double miu = Double.parseDouble(request.getAttribute("ave").toString());
          double sigma = Double.parseDouble(request.getAttribute("fangcha").toString());

         Chart test = new Chart("123");
          JFreeChart chart = test.paint(miu,sigma);
          String fileName = ServletUtilities.saveChartAsJPEG(chart, 800,600, session);       
            String url = request.getContextPath()+"/DisplayChart?filename="+fileName;                                            
     %>
     
    <img src="<%=url %>" width="800"  height="600"> 
    </body>   
</html>