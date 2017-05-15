document.write("<script language=javascript src='js/public_pack.js'></script>");

window.onload = function(){
  var queryHistoryRecord_vue = new Vue({
    el: "#tb-queryHistoryRecord",
    data:{
      tableContent: {
        data:[
          {
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
          }
        ]
      }
    },
    created: function () {
      // this.tableContent = queryHistoryRecord;
      ajax_get("json_test/queryHistory.json", this.tableContent);
    },
    methods: {
      jumpToResult: function (recordID) {
        window.location.href = "query_history_result.html?queryID=" + recordID;
      },
      showingAgeTilde: function (index) {
        var da = this.tableContent.data[index];
        return da.ageFrom.length > 0 || da.ageTo.length > 0;
      },
      showingLabArrow: function (index) {
        var da = this.tableContent.data[index];
        return da.labSubType != "不限" && da.labSubType.length > 0 ;
      },
      showingLabTilde: function (index) {
        var da = this.tableContent.data[index];
        return da.labValFrom.length > 0 || da.labValTo.length > 0;
      }
    }
  })
}