## 各个页面所用到的 json 

* `searchInfo.json`
    * 多维查询页面的 get 请求内容
* `searchRecord.json`
    * 多维查询页面返回的搜索结果项的内容
* `examList.json`
    * 所有检查指标的名称，如 "B超"
* `labList.json`
    * 所有检验指标的名称，如 "血常规"
        * 每一项检验指标又对于一串子指标，如 "血常规" → "白细胞"

* `patientNumChange.json` *已完成*
    * 数据展示页 index.html 图表数据，传入datefrom、dateto作为参数，按天返回从datefrom到dateto每日在院患者数量