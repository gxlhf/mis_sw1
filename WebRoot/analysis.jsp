<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="utf-8">
  <title>历史查询</title>
	<link rel="shortcut icon" href="images/icon.png"></link>
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <script src="js/jquery-3.1.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
    .navbar-brand {
      padding-left: 40px;
      padding-right: 20px;
      padding-top: 5px;
      padding-bottom: 5px;
    }
    .navbar-brand>img {
      height: 40px;
    }
    </style>
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
        <a class="navbar-brand" href="index.html"><img src="images/icon.png"></a>
      </div>
      <div class="collapse navbar-collapse" id="navbar-ex-collapse">
        <ul class="nav navbar-nav navbar-left">
          <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">多维查询</a>
            <ul class="dropdown-menu">
              <li><a href="multi_query.html">查询</a></li>
              <li><a href="query_history.html">历史查询</a></li>
            </ul>
          </li>
            <li class="active">
              <a href="analysis.jsp">统计分析</a>
            </li>
          <li>
            <a href="UploadFile.jsp">知识库</a>
          </li>
        </ul>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-lg-12">
        <div class="table-responsive">
          <table class="table table-bordered table-hover table-striped" id="tb-queryHistoryRecord">
            <thead>
              <tr>
                <td>性别</td>
                <td>年龄段</td>
                <td>检查指标</td>
                <td>检验指标</td>
                <td>检验指标范围</td>
                <td>查询时间</td>
                <td>查询结果</td>
              </tr>
            </thead>
            <tbody>
              <tr v-for="(item, index) in tableContent.data">
                <td>{{item.sex}}</td>
                <td>
                  {{item.ageFrom}}
                  <span v-if="showingAgeTilde(index)"> ~ </span><span v-else>不限</span>
                  {{item.ageTo}}
                </td>
                <td>{{item.examType}}</td>
                <td>
                  {{item.labType}}
                  <span v-if="showingLabArrow(index)"> → {{item.labSubType}}</span>                  
                </td>
                <td>
                  {{item.labValFrom}}
                  <span v-if="showingLabTilde(index)"> ~ </span><span v-else>不限</span>
                  {{item.labValTo}}
                </td>
                <td>{{item.queryTime}}</td>
                <td><a @click="jumpToResult(item.queryID)">详情</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <script src="js/vue.min.js"></script>
  <script src="js/analysis.js"></script>
</body>
</html>