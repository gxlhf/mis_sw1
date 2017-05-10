// var inHospitalRecord = {
//   length: 3,
//   data:[
//     {
//       index: 0,
//       dataIndex: 0,
//       inTime: "2008-6-12",
//       inAge: 15,
//       diag: "肺炎",
//       haveExam: true,
//       haveLab: true
//     },
//     {
//       index: 1,
//       dataIndex: 1,
//       inTime: "2008-8-12",
//       inAge: 15,
//       diag: "肠炎",
//       haveExam: true,
//       haveLab: true
//     },
//     {
//       index: 2,
//       dataIndex: 2,          
//       inTime: " 2011-6-12",
//       inAge: 17,
//       diag: "骨折",
//       haveExam: true,
//       haveLab: true
//     },
//   ]
// };
var examRecord = {
  type: "exam",
  length: 2,
  data:[
    {
      examNo: "123123",
      examType: "B超",
      examPos: "腹部",
      isNormal: "否",
      description: "右肺中叶大部分不张，呈条片状高密度影，边缘模糊，其内可见少许含气支气管影，右肺下叶可见斑片状高密度实变影，与邻近胸膜粘连，左肺下叶后基底段胸膜下可见少许斑片状模糊影，各叶段支气管通畅；双肺门及纵隔内未见增大淋巴结，双侧未见胸腔积液。\<br\>右膈面明显上抬，所示肝脏体积缩小、肝裂增宽，边缘不规则呈波浪结节样，右肝及左肝内叶可见巨大肿块灶，形态不规则，边界不清晰，密度不均匀，其内可见不规则片状低密度影及多发点片状点油沉积，增强扫描肿块呈不均匀明显强化。门静脉主干及其属支未见增粗，其内未见充盈缺损影。食管下段",
      diag: "1.肝脏肿块较前增大，仍考虑恶性肿瘤（原发性肝癌？）可能。\<br\>2.右肝前叶小囊肿同前。\<br\>3.双肾囊肿，右肾多发小结石可能同前；\<br\>4.左肺下叶炎症较前吸收，左侧少-中量胸腔积液较前基本吸收；心包增厚较前改善；双肺慢性炎症，右侧少量胸水。\<br\>5.双侧甲状腺内多发结节灶，性质待定，建议进一步检查。"
    },
    {
      examNo: "123123",
      examType: "B超",
      examPos: "腹部",
      isNormal: "否",
      description: "右肺中叶大部分不张，呈条片状高密度影，边缘模糊，其内可见少许含气支气管影，右肺下叶可见斑片状高密度实变影，与邻近胸膜粘连，左肺下叶后基底段胸膜下可见少许斑片状模糊影，各叶段支气管通畅；双肺门及纵隔内未见增大淋巴结，双侧未见胸腔积液。\<br\>右膈面明显上抬，所示肝脏体积缩小、肝裂增宽，边缘不规则呈波浪结节样，右肝及左肝内叶可见巨大肿块灶，形态不规则，边界不清晰，密度不均匀，其内可见不规则片状低密度影及多发点片状点油沉积，增强扫描肿块呈不均匀明显强化。门静脉主干及其属支未见增粗，其内未见充盈缺损影。食管下段",
      diag: "1.肝脏肿块较前增大，仍考虑恶性肿瘤（原发性肝癌？）可能。\<br\>2.右肝前叶小囊肿同前。\<br\>3.双肾囊肿，右肾多发小结石可能同前；\<br\>4.左肺下叶炎症较前吸收，左侧少-中量胸腔积液较前基本吸收；心包增厚较前改善；双肺慢性炎症，右侧少量胸水。\<br\>5.双侧甲状腺内多发结节灶，性质待定，建议进一步检查。"
    },
  ]
};
var labRecord = {
  type: "lab",
  length: 3,
  data: [
    {
      labNo: "123123",
      labName: "血常规",
      result:[
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        },
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        },
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        }
      ]
    },
    {
      labNo: "123123",
      labName: "血常规",
      result:[
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        },
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        },
        {
          name: "白细胞",
          resultVal: "2.89",
          unit: "*10^9/L",
          resultIndicate: "L",
          normalRange: "4.0-10.9"
        }
      ]
    }
  ]
};


// /*
//  * 深拷贝
//  */
// function dpCopy(dest, src) {
//   for(item in src){
//     if(typeof item == "object"){
//       dpCopy(dest[item], src[item]);
//     }
//     else{
//       dest[item] = src[item];
//     }
//   }
// }

// /*
//  * get方法获取json
//  * @parm url 
//  * @parm dataToSet 结束ajax时会将该变量设置为json返回的对象
//  * @param dataToSend 可选 随ajax发送的数据
//  * @parm successCbk 可选 成功时的回调函数
//  * @parm errorCbk 可选 失败时回调函数
//  */
// function ajax_get(url, dataToSet, dataToSend, successCbk, errorCbk) {
//   $.ajax({
//     url: url,
//     type: "GET",
//     dataType: "json",
//     data: dataToSend,
//     success: function (data) {
//       dpCopy(dataToSet, data);
//       // console.log(successCbk);
//       if(successCbk != undefined){
//         successCbk();
//       }
//     },
//     error: function (XMLHttpRequest, textStatus, errorThrown) {   
//       if(errorCbk != undefined){
//         errorCbk();
//       }   
//       console.log("ajaxErr");
//       console.log(XMLHttpRequest, textStatus, errorThrown);
//       alert("获取数据出错");
//     }
//   })
// }

document.write("<script language=javascript src='js/public_pack.js'></script>");
window.onload = function(){
  var patientID = getQueryString("patient_id");
  var patientInfo_vue = new Vue({
    el: "#div-patientInfo",
    data: {
      patientInfo: {
        patientID: "",
        name: "",
        sex: "",
        birthday: ""
      }
    },
    created: function function_name(argument) {
      var queryParm = {"patientID": patientID};
      var vueObj = this;
      ajax_get("json_test/patientInfo.json", this.patientInfo, queryParm);
    }
  });

  var inHospitalRecord_vue = new Vue({
    el: "#tb-inHospitalRecord",
    data:{ 
      tableContent: {
        length: 1,
        data:[
          {
            index: 0,
            dataIndex: 0,
            inTime: "",
            inAge: 0,
            diag: "",
            haveExam: true,
            haveLab: true,
            showingDetail: false,
            showingLoading: false,
            detail: {type: "", length: 0, data: []}
          }
        ]
      }
    },
    created: function () {
      inHospitalRecord = {};
      var queryParm = {"patient_id": patientID};
      var vueObj = this;
      ajax_get("json_test/inHospitalRecord.json", inHospitalRecord, queryParm,
        function () {
          for(var i = 0; i < inHospitalRecord.length; i++){
            inHospitalRecord.data[i].showingDetail = false;
            inHospitalRecord.data[i].showingLoading = false;
            inHospitalRecord.data[i].detail = {type: "", length: 0, data: []};
          }
          vueObj.tableContent = inHospitalRecord;
        });
      
    },
    methods: {
      showLoading: function (index) {
        var curData = this.tableContent.data[index];
        this.tableContent.data[index].showingDetail = true;
        curData.showingLoading = true;
      },
      hideLoading: function (index) {
        var curData = this.tableContent.data[index];
        curData.showingDetail = true;
        curData.showingLoading = false;
      },
      hideDetail: function (index) {
        var curData = this.tableContent.data[index];
        curData.showingDetail = false;
      },
      toggleDetail: function(type, index){
        var vueObj = this;
        var sucCbk = function(){
        	vueObj.hideLoading(index);
        };
        var errCbk = function(){
        	vueObj.hideDetail(index);
        };
        var curData = vueObj.tableContent.data[index];
        var queryParm = {"patient_id": patientID, "sequence": index};
        if(curData.showingDetail && curData.detail.type == type)
          vueObj.hideDetail(index);
        else{
          vueObj.showLoading(index);
          switch(type){
            case "exam":
              // curData.detail = examRecord;
              ajax_get("examRecord", curData.detail, queryParm, sucCbk, errCbk);
              break;
            case "lab":
              ajax_get("json_test/labRecord.json", curData.detail, queryParm, sucCbk, errCbk);
              break;
          }
        }
      }
    }
  });
};