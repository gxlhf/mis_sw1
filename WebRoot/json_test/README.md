## 各个页面所用到的 json 

* `index.html`
    * `patientNumChange.json` **json已稳定**
        * 数据展示页图表数据，传入datefrom、dateto作为参数，按天返回从datefrom到dateto每日在院患者数量

* `multi_query.html`
    * `searchInfo.json`
        * 多维查询页面的 get 请求内容
    * `searchRecord.json`
        * 多维查询页面返回的搜索结果项的内容
    * `examList.json`
        * 所有检查指标的名称，如 "B超"
    * `labList.json`
        * 所有检验指标的名称，如 "血常规"
            * 每一项检验指标又对于一串子指标，如 "血常规" → "白细胞"

* `query_history.html`
    * `queryHistory.json` **json已稳定**
        * 历史查询记录

* `patient_detail.html`
    * `inHospitalRecord.json` **json已稳定** 
        * 传入patient_id作为参数，返回住院号为patient_id的病人所有住院记录
    * `inHospitalRecord.json` **json已稳定**
        * 传入patient_id作为参数，返回住院号为patient_id的病人个人信息
    * `examRecord.json` **json已稳定** **已对接**
        * 传入patient_id、sequence作为参数，返回住院号为patient_id的病人第sequence次住院的所有检查结果
    * `labRecord.json` **json已稳定**
        * 传入patient_id、sequence作为参数，返回住院号为patient_id的病人第sequence次住院的所有检验结果