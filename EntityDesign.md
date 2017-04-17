### 实体规范

* 检查（Exam）

exam_no 主键

patient_id 患者ID

visit_id 住院编号

req_date_time 检查时间

exam_sub_class 检查父类

exam_class 检查子类

clin_symp 临床症状

phys_sign 物理症状

clin_diag 临床诊断

description 描述

impression 结论

* 检验（Test）

test_no 主键

patient_id 患者ID

visit_id 住院编号

execute_date 检验时间

age 年龄

relevant_clinic_diag 相关临床诊断结果

specimen 检验样本

item_name 检验项目名 （String数组）

test_result 检验结果 （TestResult对象数组）


* 检验结果（TestResult）

print_order 打印顺序

report_item_name 指标名称

result 结果

units 单位

abnormal_indicator 正常情况

normal_value 参考值

* 患者（patient）

patient_id 患者ID

patient_name 姓名

sex 性别

date_of_birth 出生日期

* 用户（user）

user 用户名

password 密码

email 邮箱