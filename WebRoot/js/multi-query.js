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

function clone(o){
    var k, ret= o, b;
    if(o && ((b = (o instanceof Array)) || o instanceof Object)) {
        ret = b ? [] : {};
        for(k in o){
            if(o.hasOwnProperty(k)){
                ret[k] = clone(o[k]);
            }
        }
    }
    return ret;
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

var searchInfo_vue = new Vue({
    el: "#div-searchInfo",
    data: {
        searchInfo: {
            examType: "", 
            labName: ""
        }
    },
    created: function() {
        var vueObj = this;
        ajax_get("json_test/searchInfo.json", this.searchInfo);
    }
});

var searchRecord_vue = new Vue({
    el: "#tb-searchRecord",
    data:{
        tableContent: {
            patientID: "",
            name: "",
            sex: "",
            birthday: ""
        }
    },
    created: function () {
        var vueObj = this;
        ajax_get("json_test/searchRecord.json", this.tableContent);
    }
});