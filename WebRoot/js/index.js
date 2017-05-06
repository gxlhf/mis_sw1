/*
 * 深拷贝
 */
function dpCopy(dest, src) {
  for(item in src){
    if(typeof item == "object"){
      dpCopy(dest[item], src[item]);
    }
    else{
      dest[item] = src[item];
    }
  }
}
/*
 * get方法获取json
 * @parm url 
 * @parm dataToSet 结束ajax时会将该变量设置为json返回的对象
 * @param dataToSend 可选 随ajax发送的数据
 * @parm successCbk 可选 成功时的回调函数
 * @parm errorCbk 可选 失败时回调函数
 */
function ajax_get(url, dataToSet, dataToSend, successCbk, errorCbk) {
  $.ajax({
    url: url,
    type: "GET",
    dataType: "json",
    data: dataToSend,
    success: function (data) {
      dpCopy(dataToSet, data);      
      if(successCbk != undefined){
        successCbk();
      }
    },
    error: function (XMLHttpRequest, textStatus, errorThrown) {   
      if(errorCbk != undefined){
        errorCbk();
      }   
      console.log("ajaxErr");
      console.log(XMLHttpRequest, textStatus, errorThrown);
      alert("获取数据出错");
    }
  })
}

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
}

function date2string(date) {
  
  var y = date.getFullYear();
  var m = (date.getMonth() < 10 ? "0" : "") + date.getMonth();
  var d = (date.getDate() < 10 ? "0" : "") + date.getDate();
  return y + "-" + m + "-" + d;
}

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

  date.setDateAfter(-minusDate);
  queryParm.datefrom = date2string(date);
  option.xAxis.data = [];
  for (var i = 0; i < minusDate; i++) {
    option.xAxis.data.push(date2string(date.setDateAfter(1)));
  }

  ajax_get("json_test/patientNumChange.json", option.series, queryParm, function () {
    mychart.clear();
    mychart.setOption(option);
  })


  
  
}