/**
 * 历史查询项类
 * sex:性别
 * minAge、maxAge:年龄范围
 * examClass:检查指标
 * labType:检验大类
 * labSubType:检验子类
 * labValFrom、labValTo:检验指标值范围
 * filename:文件名
 * date:查询日期
 */
package entity;

public class HistoryQueryItem {
	String sex;
	int minAge;
	int maxAge;
	String examClass;
	String labType;
	String labSubType;
	String labValFrom;
	String labValTo;
	String filename;
	String date;
	//Constructor : filename and date are not required
	public HistoryQueryItem(String sex, int minAge, int maxAge, String examClass, String labType, String labSubType,
			String labValFrom, String labValTo) {
		this.sex = sex;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.examClass = examClass;
		this.labType = labType;
		this.labSubType = labSubType;
		this.labValFrom = labValFrom;
		this.labValTo = labValTo;
	}
	
	public HistoryQueryItem(String sex, int minAge, int maxAge, String examClass, String labType, String labSubType,
			String labValFrom, String labValTo, String filename, String date) {
		this.sex = sex;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.examClass = examClass;
		this.labType = labType;
		this.labSubType = labSubType;
		this.labValFrom = labValFrom;
		this.labValTo = labValTo;
		this.filename = filename;
		this.date = date;
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
	public String getLabType() {
		return labType;
	}
	public void setLabType(String labType) {
		this.labType = labType;
	}
	public String getLabSubType() {
		return labSubType;
	}
	public void setLabSubType(String labSubType) {
		this.labSubType = labSubType;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
}
