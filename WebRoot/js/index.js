document.write("<script language=javascript src='js/public_pack.js'></script>");

var option = {
  title: {
    text: '患者数量'
  },
  tooltip: {
    trigger:'axis'
  },
  xAxis: {
    data: [2013,2014,2015,2016,2017]
  },
  yAxis: {},
  series: {
    name: '患者数量',
    type: 'line',
    data: [384,499,560,655,700]
  }
};
var mychart;

Date.prototype.setDateAfter = function(after){
  var beforMilliseconds = this.getTime() + 1000 * 3600 * 24 * after;
  this.setTime(beforMilliseconds);
  return this;
};

function date2string(date) {  
  var y = date.getFullYear();
  var m = ((date.getMonth() + 1) < 10 ? "0" : "") + (date.getMonth() + 1);
  var d = (date.getDate() < 10 ? "0" : "") + date.getDate();
  return y + "-" + m + "-" + d;
};

function selectOption(type){
  mychart = echarts.init(document.getElementById('presentation'));
  var date = new Date();
  var queryParm = {datefrom:"", dateto: ""};
  var minusDate;
  queryParm.dateto = date2string(date);

  switch(type){
    case "week":      
      minusDate = 7;
      break;
    case "month":      
      minusDate = 30;
      break;
    case "year":      
      minusDate = 365;
      break;
  }

  date.setDateAfter(-minusDate + 1);
  queryParm.datefrom = date2string(date);
  option.xAxis.data = [];
  for (var i = 0; i < minusDate; i++) {
    option.xAxis.data.push(date2string(date.setDateAfter(1)));
  }

  ajax_get("patientNumChange", option.series.data, queryParm, function () {
    mychart.clear();
    mychart.setOption(option);
  });
};
window.onload = function () {
  selectOption("week");
}