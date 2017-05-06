/**
 * 患者类
 * patient_id:患者编号
 * patient_name:患者姓名
 * sex:性别
 */
package entity;

public class Patient {
	String patient_id;
	String patient_name;
	String sex;
	String birthday;
	public Patient(String patient_id, String patient_name, String sex, String birthday) {
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.sex = sex;
		this.birthday = birthday;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getPatient_name() {
		return patient_name;
	}
	public void setPatient_name(String patient_name) {
		this.patient_name = patient_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	
}
