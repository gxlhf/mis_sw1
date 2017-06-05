<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<link rel="shortcut icon" href="images/icon.png"></link>
  <meta charset="utf-8">
  <title>历史查询结果</title>
  <script src="js/vue.min.js"></script>
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
  <div class="container" id="div-queryHistoryResult">
    <div class="row">
      <div class="col-lg-12">
        <div class="table-responsive">
          <table class="table table-bordered table-hover table-striped">
            <thead>
              <tr>
                <td>性别</td>
                <td>年龄段</td>
                <td>检查指标</td>
                <td>检验指标</td>
                <td>检验指标范围</td>
                <td>查询时间</td>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td>{{queryAbstract.sex}}</td>
                <td>
                  {{queryAbstract.ageFrom}}
                  <span v-if="showingAgeTilde"> ~ </span><span v-else>不限</span>
                  {{queryAbstract.ageTo}}
                </td>
                <td>{{queryAbstract.examType}}</td>
                <td>
                  {{queryAbstract.labType}}
                  <span v-if="showingLabArrow"> → {{queryAbstract.labSubType}}</span>
                </td>
                <td>
                  {{queryAbstract.labValFrom}}
                  <span v-if="showingLabTilde"> ~ </span><span v-else>不限</span>
                  {{queryAbstract.labValTo}}
                </td>
                <td>{{queryAbstract.queryTime}}</td>
              </tr>
            </tbody>
          </table>
        </div>
        <!-- 添加按钮正态分析以及数据导出 -->
        <div>
            <a href="/MIS/normalAnalysis" target="_blank"><input type="button"  value="正态分析" style="width:100px;background:#C7EACD" onmouseover="this.style.background='#77DDFF'" onmouseout="this.style.background='#C7EACD'" title="进行正态分析"></a>
            <a href="/MIS/dataProcess"><input type="button"  value="数据导出"  style="width:100px;background:#C7EACD;float:right" onmouseover="this.style.background='#77DDFF'" onmouseout="this.style.background='#C7EACD'" title="导出数据"></a>
        </div>
        <!-- 添加完毕 -->
        <div class="table-responsive">
          <table class="table table-bordered table-hover table-striped">
            <thead>
              <tr class="text-center">
                <td>姓名</td>
                <td>性别</td>
                <!-- <td>临床诊断</td> -->
                <td>住院号</td>
                <td>住院次数</td>
                <td>详情</td>
              </tr>
            </thead>
            <tbody>
    
              <tr class="text-center" v-for="result in queryResult.result">
                <td>{{ result.name }}</td>
                <td>{{ result.sex }}</td>
                <!-- <td>{{ result.diag }}</td> -->
                <td>{{ result.patientID }}</td>
                <td>{{ result.inHospitalCount }}</td>
                <td><a @click="jumpToDetail(result.patientID)">患者详情</a></td>
              </tr>

            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
  <script src="js/analysis_history_result.js"></script>
</body>
</html>