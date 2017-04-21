/**
 * 检验类
 * test_no:检验编号
 * patient_id:患者编号
 * visit_id:住院编号
 * execute_date:检验时间
 * age:年龄
 * relevant_clinic_diag:相关临床诊断结果
 * specimen:检验样本
 * item_name:检验项目名
 * test_result:检验结果
 */
package entity;

public class Test {
	String test_no;
	String patient_id;
	String visit_id;
	String execute_date;
	int age;
	String relevant_clinic_diag;
	String specimen;
	String item_name;
	TestResult[] test_result;
	public Test(String test_no, String patient_id, String visit_id, String execute_date, int age,
			String relevant_clinic_diag, String specimen, String item_name, TestResult[] test_result) {
		this.test_no = test_no;
		this.patient_id = patient_id;
		this.visit_id = visit_id;
		this.execute_date = execute_date;
		this.age = age;
		this.relevant_clinic_diag = relevant_clinic_diag;
		this.specimen = specimen;
		this.item_name = item_name;
		this.test_result = test_result;
	}
	public String getTest_no() {
		return test_no;
	}
	public void setTest_no(String test_no) {
		this.test_no = test_no;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getVisit_id() {
		return visit_id;
	}
	public void setVisit_id(String visit_id) {
		this.visit_id = visit_id;
	}
	public String getExecute_date() {
		return execute_date;
	}
	public void setExecute_date(String execute_date) {
		this.execute_date = execute_date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getRelevant_clinic_diag() {
		return relevant_clinic_diag;
	}
	public void setRelevant_clinic_diag(String relevant_clinic_diag) {
		this.relevant_clinic_diag = relevant_clinic_diag;
	}
	public String getSpecimen() {
		return specimen;
	}
	public void setSpecimen(String specimen) {
		this.specimen = specimen;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public TestResult[] getTest_result() {
		return test_result;
	}
	public void setTest_result(TestResult[] test_result) {
		this.test_result = test_result;
	}
	
}
