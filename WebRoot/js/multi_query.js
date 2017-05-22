document.write("<script language=javascript src='js/public_pack.js'></script>");

window.onload = function () {
  var query_vue = new Vue({
    el: "#div-query",
    data: {
      queryParm: {
        sex: "不限",
        ageFrom: "",
        ageTo: "",
        examType: "不限",
        labType: "不限",
        labSubType: "不限",
        labValFrom: "",
        labValTo: ""
      },
      formOptions: {
        exam: {
          examClass: []
        },
        lab: {
          labClass: []
        },
        subLab: {
          subLabClass: []
        }
      },
      pageAnimation: {
        result: {
          loading: false,
          showing: false
        }
      },
      queryResult: {
        showing: false,
        loading: false,
        result: [
          {
            name: "",
            sex: "",
            diag: "",
            patientID: "",
            inHospitalCount: ""
          }
        ]
      }
    },
    computed: {
      ageMsg: function () {
        var from = this.queryParm.ageFrom;
        var to = this.queryParm.ageTo;
        var reg = new RegExp("^(0{0,1}|[1-9][0-9]*)$");
        if(!reg.test(from) || !reg.test(to))
          return "年龄范围输入有误";
        if(parseInt(from) > parseInt(to))
          return "年龄范围输入有误";
        else
          return "";
      },
      labMsg: function () {        
        var from = this.queryParm.labValFrom;
        var to = this.queryParm.labValTo;
        var reg = new RegExp("^((0{0,1}|[1-9][0-9]*)|(0|[1-9][0-9]*)+(.[0-9]+))$");
        if(!reg.test(from) || !reg.test(to))
          return "检验指标的值范围输入有误";
        if(parseInt(from) > parseInt(to))
          return "检验指标的值范围输入有误";
        else
          return "";
      },
      valided: function () {
        return this.ageMsg == 0 && this.labMsg == 0;
      },
      showNoResult: function () {
        return this.queryResult.showing && this.queryResult.result.length == 0;
      },
      showResult: function () {
        return this.queryResult.showing && !this.queryResult.loading && this.queryResult.result.length > 0;
      }
    },
    created: function () {
      var vueObj = this;

      // 获取检查列表
      ajax_get("examList", vueObj.formOptions.exam);

      // 获取检验一级指标
      ajax_get("labList", vueObj.formOptions.lab);

    },
    methods: {
      fetchSubLab: function () {
        var vueObj = this;
        vueObj.formOptions.subLab.subLabClass = [];

        if(vueObj.queryParm.labType == "不限"){
          vueObj.queryParm.labSubType = "不限";
          return;
        }

        var fetchParm = {"labType": vueObj.queryParm.labType};
        ajax_get("subLabList", vueObj.formOptions.subLab, fetchParm);
      },
      multiQuery: function () {
        var vueObj = this;
        vueObj.queryResult.showing = true;
        vueObj.queryResult.loading = true;
        ajax_get("queryResult", vueObj.queryResult, vueObj.queryParm, 
          function () {
            vueObj.queryResult.loading = false;
          });
      },
      jumpToDetail: function (paID) {
        // window.location.href = "patient_detail.html?patient_id=" + paID;
        return "patient_detail.html?patient_id=" + paID;
      }
    }
  });
}