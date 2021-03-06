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
  var dataToSendTemp = {};
  for(item in dataToSend){
    dataToSendTemp[item] = encodeURIComponent(dataToSend[item]);
  }
  $.ajax({
    url: url,
    type: "GET",
    dataType: "json",
    data: dataToSendTemp,
    success: function (data) {
      dpCopy(dataToSet, data);
      // console.log(successCbk);
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

/*
 * 获取地址栏参数
 */
function getQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
