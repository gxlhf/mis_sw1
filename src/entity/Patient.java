/**
 * 患者类
 * patient_id:患者编号
 * patient_name:患者姓名
 * sex:性别
 */
package com.entity;

public class Patient {
	String patient_id;
	String patient_name;
	String sex;
	public Patient(String patient_id, String patient_name, String sex) {
		this.patient_id = patient_id;
		this.patient_name = patient_name;
		this.sex = sex;
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
	
}
