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
      ajax_get("patientInfo", this.patientInfo, queryParm);
    }
  });

  var inHospitalRecord_vue = new Vue({
    el: "#tb-inHospitalRecord",
    data:{ 
      tableContent: {
        loading: true,
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
      ajax_get("inHospitalRecord", inHospitalRecord, queryParm,
        function () {
          for (i in inHospitalRecord.data) {
            inHospitalRecord.data[i]["showingDetail"] = false;
            inHospitalRecord.data[i]["showingLoading"] = false;
            inHospitalRecord.data[i]["detail"] = {type: "", length: 0, data: []};
          }
          vueObj.tableContent = inHospitalRecord;
          vueObj.loading = false;
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
        var queryParm = {"patient_id": patientID, "sequence": vueObj.tableContent.data[index].index};
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
              ajax_get("labRecord", curData.detail, queryParm, sucCbk, errCbk);
              break;
          }
        }
      },
      breakLine: function (content) {
        if(content != undefined){
          return content.replace(/\n/g, "<br>");
        }
      }
    }
  });
  
};