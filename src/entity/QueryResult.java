/**
 * 查询结果类
 * patient:Patient对象
 * hospitalCount:患者住院总次数
 * clinicDiagMap:患者每次住院的临床诊断结果，键为住院号，值为临床诊断结果
 */
package entity;

import java.util.Map;

public class QueryResult {
	Patient patient;
	int hospitalCount;
	Map<Integer, String> clinicDiagMap;
	public QueryResult(Patient patient, int hospitalCount, Map<Integer, String> clinicDiagMap) {
		this.patient = patient;
		this.hospitalCount = hospitalCount;
		this.clinicDiagMap = clinicDiagMap;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public int getHospitalCount() {
		return hospitalCount;
	}
	public void setHospitalCount(int hospitalCount) {
		this.hospitalCount = hospitalCount;
	}
	public Map<Integer, String> getClinicDiagMap() {
		return clinicDiagMap;
	}
	public void setClinicDiagMap(Map<Integer, String> clinicDiagMap) {
		this.clinicDiagMap = clinicDiagMap;
	}
	
}
