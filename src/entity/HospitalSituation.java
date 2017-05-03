/**
 * 住院情况类：
 * sequence:住院次数
 * exam:检查记录
 * test:测试记录
 * patient_id:患者编号
 * 
 **/
package entity;


public class HospitalSituation {
	int sequence;
	Exam[] exam;
	Test[] test;
	String patient_id;
	public HospitalSituation(int sequence,Exam[] exam, Test[] test, String patient_id) {
		super();
		this.sequence=sequence;
		this.exam = exam;
		this.test = test;
		this.patient_id = patient_id;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Exam[] getExam() {
		return exam;
	}
	public void setExam(Exam[] exam) {
		this.exam = exam;
	}
	public Test[] getTest() {
		return test;
	}
	public void setTest(Test[] test) {
		this.test = test;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	
}
