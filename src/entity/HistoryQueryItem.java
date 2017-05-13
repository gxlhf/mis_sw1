/**
 * 历史查询项类
 * sex:性别
 * minAge、maxAge:年龄范围
 * examClass:检查指标
 * testItem:检验指标
 * patients:符合以上条件的患者
 */
package entity;

public class HistoryQueryItem {
	String sex;
	int minAge;
	int maxAge;
	String examClass;
	String testItem;
	Patient[] patients;
	public HistoryQueryItem(String sex, int minAge, int maxAge, String examClass, String testItem, Patient[] patients) {
		super();
		this.sex = sex;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.examClass = examClass;
		this.testItem = testItem;
		this.patients = patients;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getMinAge() {
		return minAge;
	}
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	public int getMaxAge() {
		return maxAge;
	}
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	public String getExamClass() {
		return examClass;
	}
	public void setExamClass(String examClass) {
		this.examClass = examClass;
	}
	public String getTestItem() {
		return testItem;
	}
	public void setTestItem(String testItem) {
		this.testItem = testItem;
	}
	public Patient[] getPatients() {
		return patients;
	}
	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}
	
}
