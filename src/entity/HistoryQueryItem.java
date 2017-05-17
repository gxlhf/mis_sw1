/**
 * 历史查询项类
 * sex:性别
 * minAge、maxAge:年龄范围
 * examClass:检查指标
 * testItem:检验指标
 * labValFrom、labValTo：检验指标值范围
 * filename：文件名
 */
package entity;

public class HistoryQueryItem {
	String sex;
	int minAge;
	int maxAge;
	String examClass;
	String testItem;
	String labValFrom;
	String labValTo;
	String filename;
	public HistoryQueryItem(String sex, int minAge, int maxAge, String examClass, String testItem, String labValFrom,
			String labValTo, String filename) {
		this.sex = sex;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.examClass = examClass;
		this.testItem = testItem;
		this.labValFrom = labValFrom;
		this.labValTo = labValTo;
		this.filename = filename;
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
	public String getLabValFrom() {
		return labValFrom;
	}
	public void setLabValFrom(String labValFrom) {
		this.labValFrom = labValFrom;
	}
	public String getLabValTo() {
		return labValTo;
	}
	public void setLabValTo(String labValTo) {
		this.labValTo = labValTo;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	
}
