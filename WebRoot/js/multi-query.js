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

/**
 * 所有检查指标的名称，从 examList.json 里得来
 * @type {Vue}
 */
var examList_vue = new Vue({
    el: "#div-examInfo",
    data:{
        examContent: {
            length: "",
            examClass: [
                {
                    name: "",
                    detail: {length: "", examClass: []}
                }
            ]
        }
    },
    created: function () {
        ajax_get("json_test/examList.json",this.examContent);
    }
});

/**
 * 所有检验指标以及子指标的名称，从 labList.json 里得来
 * @type {Vue}
 */
var labList_vue = new Vue({
    el: "#div-labInfo",
    data:{
        labContent: {
            length: "",
            labs: [
                {
                    labName: "",
                    subLength: "",
                    subLab: [
                        { subName: "" }
                    ],
                    detail: {length: "", labs: [] }
                }
            ]
        }
    },
    created: function () {
        ajax_get("json_test/labList.json", this.labContent);
    }
});

/**
 * 搜索结果，从 searchRecord.json 里得来
 * @type {Vue}
 */
var searchRecord_vue = new Vue({
    el: "#tb-searchRecord",
    data:{
        tableContent: {
            length: "",
            result: [
                {
                    name: "",
                    sex: "",
                    diag: "",
                    testNO: "",
                    patientID: "",
                    visitTime: "",
                    detail: {length: "", result: []}
                }
            ]
        }
    },
    created: function () {
        ajax_get("json_test/searchRecord.json", this.tableContent);
    }
});