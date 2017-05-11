document.write("<script language=javascript src='js/public_pack.js'></script>");

window.onload = function(){
  var queryHistoryRecord_vue = new Vue({
    el: "#tb-queryHistoryRecord",
    data:{
      tableContent: {
        data:[
          {
            index: 0,
            dataID: "",
            sex: "",
            ageArea: "",
            examType: "",
            labType: "",
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
      jumpToDetail: function (recordID) {
        window.location.href = "query_history_result.html?redordID=" + recordID;
      }
    }
  })
}