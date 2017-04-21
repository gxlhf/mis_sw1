/*
 * 用户类
 * user:用户名
 * password:密码
 * email:邮箱
 */
package entity;

import java.util.List;

public class User {
	String user;
	String password;
	String email;
	public User(String user, String password, String email) {
		this.user = user;
		this.password = password;
		this.email = email;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 多维查询
	 * 区分检查（Exam）和检验（Test）
	 */

	/**
	 * 查询 检查指标，返回字符串数组
	 * 检查指标包括黑白超，内镜，介入，心电图等
	 */
	public String[] queryExamClass(){
		String[] queryExamClassResult = null;
		return queryExamClassResult;
	}

	/**
	 * 查询 检验指标大类,返回字符串数组
	 * 检验指标大类包括 血常规，血脂全套，E4A等
	 */
	public String[] queryTestClass(){
		String[] queryTestClassResult = null;
		return queryTestClassResult;
	}

	/**
	 * 根据检验指标大类查询检验指标具体类别
	 * 如参数血常规，返回所有血常规的具体检验项，如白细胞，血红蛋白等
	 */
	public String[] queryTestItem(String TestClass){
		String[] queryTestItemResult = null;
		return queryTestItemResult;
	}

	/**
	 * 根据性别，年龄段，检查指标查询患者信息，返回患者类列表
	 * 当性别和检验指标为不限的时候，默认为全选，查询全部结果
	 */
	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String examClass){
		List<Patient> queryPatientResult = null;
		return queryPatientResult;
	}

	/**
	 * 根据性别，年龄段，检查指标具体类别，指标值范围查询患者信息，返回患者列表
	 * 当性别和检验指标为不限的时候，默认为全选，查询全部结果
	 */
	public List<Patient> queryPatient(String sex, int minAge, int maxAge, String TestItem, double valueStart, double valueEnd) {
		List<Patient> queryPatientResult = null;
		return queryPatientResult;
	}

	/**
	 * 查询所有历史查询，返回历史查询类列表
	 */
	public List<HistoryQueryItem>  getQueryHistory(){
		List<HistoryQueryItem> getQueryHistoryResult = null;
		return getQueryHistoryResult;
	}


	/**
	 * 查询数据库中患者的数量
	 */
	public int getPatientCount(){
		int getPatientCountResult = 0;
		return getPatientCountResult;
	}

	/**
	 * 查询数据库中检查项的数量
	 */
	public int getExamCount(){
		int getExamCountResult = 0;
		return getExamCountResult;
	}


	/**
	 * 查询数据库中检验项的数量
	 */
	public int getTestCount(){
		int getTestCountResult = 0;
		return getTestCountResult;
	}
	
	
}
