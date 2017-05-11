/**
 * 历史查询类
 * sex:性别
 * minAge、maxAge:年龄范围
 * examClass:检查指标
 * TestItem:检验指标
 * valueStart、valueEnd:检验指标值范围
 */
package entity;

import java.util.List;

public class QueryHistory {
	String sex;
	int minAge;
	int maxAge;
	String examClass;
	String TestItem;
	double valueStart;
	double valueEnd;
	List<QueryResult> results;
	public QueryHistory(String sex, int minAge, int maxAge, String examClass, String testItem, double valueStart,
			double valueEnd, List<QueryResult> results) {
		this.sex = sex;
		this.minAge = minAge;
		this.maxAge = maxAge;
		this.examClass = examClass;
		TestItem = testItem;
		this.valueStart = valueStart;
		this.valueEnd = valueEnd;
		this.results = results;
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
		return TestItem;
	}
	public void setTestItem(String testItem) {
		TestItem = testItem;
	}
	public double getValueStart() {
		return valueStart;
	}
	public void setValueStart(double valueStart) {
		this.valueStart = valueStart;
	}
	public double getValueEnd() {
		return valueEnd;
	}
	public void setValueEnd(double valueEnd) {
		this.valueEnd = valueEnd;
	}
	public List<QueryResult> getResults() {
		return results;
	}
	public void setResults(List<QueryResult> results) {
		this.results = results;
	}
	
}
