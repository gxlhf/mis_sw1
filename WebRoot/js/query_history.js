var queryHistoryRecord = {
  data: [
    {
      index: 0,
      dataID: "123",
      sex: "男",
      ageArea: "10-15",
      examType: "CT",
      labType: "血常规",
      queryTime: "2017-4-1"
    },
    {
      index: 1,
      dataID: "123",
      sex: "男",
      ageArea: "15-20",
      examType: "CT",
      labType: "血常规",
      queryTime: "2017-4-1"
    }
  ]
};

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
    this.tableContent = queryHistoryRecord;
  },
  methods: {
    jumpToDetail: function (recordID) {
      window.location.href = "query_history_result.html?redordID=" + recordID;
    }
  }
})