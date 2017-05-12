## 各个页面所用到的 json 

* `index.html`
    * `patientNumChange.json` **json已稳定**
        * 数据展示页图表数据，传入datefrom、dateto作为参数，按天返回从datefrom到dateto每日在院患者数量

* `multi_query.html`
    * `examList.json` **json已稳定**
        * 所有检查指标的名称，如 "B超"
    * `labList.json` **json已稳定**
        * 所有检验一级指标的名称，如 "血常规" "尿常规" 等
    * `subLabList.json` **json已稳定**
        * 传入一级指标 "labType" 作为参数，返回检验二级指标的名称如 "白细胞" "血红蛋白" "红细胞压积" 等
    * `queryResult.json` **json已稳定**
        * 传入"sex"、"ageFrom"、"ageTo"、"examType"、"labType"、"labSubType"、"labValFrom"、"labValTo"作为参数，返回符合条件的查询结果

* `query_history.html`
    * `queryHistory.json` **json已稳定**
        * 历史查询记录

* `patient_detail.html`
    * `inHospitalRecord.json` **json已稳定** 
        * 传入 "patient_id" 作为参数，返回住院号为 "patient_id" 的病人所有住院记录
    * `inHospitalRecord.json` **json已稳定**
        * 传入 "patient_id" 作为参数，返回住院号为 "patient_id" 的病人个人信息
    * `examRecord.json` **json已稳定** **已对接**
        * 传入 "patient_id" 、 "sequence" 作为参数，返回住院号为 "patient_id" 的病人第 "sequence" 次住院的所有检查结果
    * `labRecord.json` **json已稳定**
        * 传入 "patient_id" 、 "sequence" 作为参数，返回住院号为 "patient_id" 的病人第 "sequence" 次住院的所有检验结果
