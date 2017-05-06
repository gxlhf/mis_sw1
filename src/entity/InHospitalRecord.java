/**
 * 住院记录类
 * sequence:住院号
 * inTime:入院时间
 * inAge:入院年龄
 * diag:临床诊断结果
 */
package entity;

public class InHospitalRecord {
	int sequence;
	String inTime;
	String inAge;
	String diag;
	public InHospitalRecord(int sequence, String inTime, String inAge, String diag) {
		this.sequence = sequence;
		this.inTime = inTime;
		this.inAge = inAge;
		this.diag = diag;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getInAge() {
		return inAge;
	}
	public void setInAge(String inAge) {
		this.inAge = inAge;
	}
	public String getDiag() {
		return diag;
	}
	public void setDiag(String diag) {
		this.diag = diag;
	}
	
}
