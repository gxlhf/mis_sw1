/**
 * 检查类
 * exam_no:检查编号
 * patient_id:患者编号
 * visit_id:住院序号
 * req_date_time:检查时间
 * exam_sub_class:检查父类
 * exam_class:检查子类
 * clin_symp:临床症状
 * phys_sign:物理症状
 * description:描述
 * impression:结论
 * isAbnormal:正常情况
 */
package entity;

public class Exam {
	String exam_no;
	String patient_id;
	String visit_id;
	String req_date_time;
	String exam_sub_class;
	String exam_class;
	String clin_symp;
	String phys_sign;
	String clin_diag;
	String description;
	String impression;
	int isAbnormal;
	public Exam(String exam_no, String patient_id, String visit_id, String req_date_time, String exam_sub_class,
			String exam_class, String clin_symp, String phys_sign, String clin_diag, String description,
			String impression, int isAbnormal) {
		this.exam_no = exam_no;
		this.patient_id = patient_id;
		this.visit_id = visit_id;
		this.req_date_time = req_date_time;
		this.exam_sub_class = exam_sub_class;
		this.exam_class = exam_class;
		this.clin_symp = clin_symp;
		this.phys_sign = phys_sign;
		this.clin_diag = clin_diag;
		this.description = description;
		this.impression = impression;
		this.isAbnormal = isAbnormal;
	}
	public String getExam_no() {
		return exam_no;
	}
	public void setExam_no(String exam_no) {
		this.exam_no = exam_no;
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
	public String getReq_date_time() {
		return req_date_time;
	}
	public void setReq_date_time(String req_date_time) {
		this.req_date_time = req_date_time;
	}
	public String getExam_sub_class() {
		return exam_sub_class;
	}
	public void setExam_sub_class(String exam_sub_class) {
		this.exam_sub_class = exam_sub_class;
	}
	public String getExam_class() {
		return exam_class;
	}
	public void setExam_class(String exam_class) {
		this.exam_class = exam_class;
	}
	public String getClin_symp() {
		return clin_symp;
	}
	public void setClin_symp(String clin_symp) {
		this.clin_symp = clin_symp;
	}
	public String getPhys_sign() {
		return phys_sign;
	}
	public void setPhys_sign(String phys_sign) {
		this.phys_sign = phys_sign;
	}
	public String getClin_diag() {
		return clin_diag;
	}
	public void setClin_diag(String clin_diag) {
		this.clin_diag = clin_diag;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImpression() {
		return impression;
	}
	public void setImpression(String impression) {
		this.impression = impression;
	}
	public int getIsAbnormal() {
		return isAbnormal;
	}
	public void setIsAbnormal(int isAbnormal) {
		this.isAbnormal = isAbnormal;
	}
	
	
}
