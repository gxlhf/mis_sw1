// var queryHistoryRecord = {
//   data: [
//     {
//       index: 0,
//       dataID: "123",
//       sex: "男",
//       ageArea: "10-15",
//       examType: "CT",
//       labType: "血常规",
//       queryTime: "2017-4-1"
//     },
//     {
//       index: 1,
//       dataID: "123",
//       sex: "男",
//       ageArea: "15-20",
//       examType: "CT",
//       labType: "血常规",
//       queryTime: "2017-4-1"
//     }
//   ]
// };

document.write("<script language=javascript src='/js/public_pack.js'></script>");

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