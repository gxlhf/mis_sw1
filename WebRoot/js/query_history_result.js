document.write("<script language=javascript src='js/public_pack.js'></script>");

window.onload = function () {
  var queryID = getQueryString("queryID");
  var queryHistoryResult_vue = new Vue({
    el: "#div-queryHistoryResult",
    data: {
      queryAbstract: {
        queryID: "",
        sex: "",
        ageFrom: "",
        ageTo: "",
        examType: "",
        labType: "",
        labSubType: "",
        labValFrom: "",
        labValTo: "",
        queryTime: ""
      },
      queryResult: {
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
      showingAgeTilde: function () {
        return this.queryAbstract.ageFrom.length > 0 || this.queryAbstract.ageTo.length > 0;
      },
      showingLabArrow: function () {
        return this.queryAbstract.labSubType != "不限" && this.queryAbstract.labSubType.length > 0 ;
      },
      showingLabTilde: function () {
        return this.queryAbstract.labValFrom.length > 0 || this.queryAbstract.labValTo.length > 0;
      }
    },
    created: function () {
      var qp = {"queryID": queryID};
      ajax_get("queryHistoryResult", this, qp);
    },
    methods: {      
      jumpToDetail: function (paID) {
        // window.location.href = "patient_detail.html?patient_id=" + paID;
        return "patient_detail.html?patient_id=" + paID;
      }
    }
  })
}