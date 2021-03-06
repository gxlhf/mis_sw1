/*
 * 用户类
 * user:用户名
 * password:密码
 * email:邮箱
 */
package entity;

import java.io.IOException;
import java.util.List;

import org.json.JSONObject;

import dao.ExamDao;
import dao.HistoryRecordDao;
import dao.PatientDao;
import dao.TestDao;
import fio.HistoryQuery;

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
	public String[] queryExamClass() {
		String[] queryExamClassResult = null;
		queryExamClassResult = new ExamDao().queryExamClass();
		return queryExamClassResult;
	}

	/**
	 * 查询 检验指标大类,返回字符串数组
	 * 检验指标大类包括 血常规，血脂全套，E4A等
	 */
	public String[] queryTestClass(){
		String[] queryTestClassResult = new  TestDao().quryTestResult();
		return queryTestClassResult;
	}

	/**
	 * 根据检验指标大类查询检验指标具体类别
	 * 如参数血常规，返回所有血常规的具体检验项，如白细胞，血红蛋白等
	 */
	public String[] queryTestItem(String TestClass){
		String[] queryTestItemResult = (new TestDao()).queryTestItem(TestClass);
		return queryTestItemResult;
	}

	/**
	 * 根据性别，年龄段，检查指标查询患者信息，返回患者类列表
	 * 当性别和检验指标为不限的时候，默认为全选，查询全部结果
	 */
	public List<QueryResult> queryPatient(String sex, int minAge, int maxAge, String examClass){
		List<QueryResult> queryPatientResult = new PatientDao().queryPatient(sex, minAge, maxAge, examClass)
;		return queryPatientResult;
	}

	/**
	 * 根据性别，年龄段，检查指标具体类别，指标值范围查询患者信息，返回患者列表
	 * 当性别和检验指标为不限的时候，默认为全选，查询全部结果
	 */
	public List<QueryResult> queryPatient(String sex, int minAge, int maxAge, String testItem, double valueStart, double valueEnd) {
		List<QueryResult> queryPatientResult = new PatientDao().queryPatient(sex, minAge, maxAge, testItem, valueStart, valueEnd);
		return queryPatientResult;
	}

	/**
	 * 查询数据库中患者的数量
	 */
	public int getPatientCount(){
		int getPatientCountResult = new PatientDao().getPatientCount();
		return getPatientCountResult;
	}

	/**
	 * 查询数据库中检查项的数量
	 */
	public int getExamCount() {
		int getExamCountResult = new ExamDao().getExamCount();
		return getExamCountResult;
	}


	/**
	 * 查询数据库中检验项的数量
	 */
	public int getTestCount(){
		int getTestCountResult = new TestDao().getTestCount();
		return getTestCountResult;
	}
	/**
	 * 查询病人一次住院的所有检查记录和测试记录
	 */
	public HospitalSituation getHospitalSituation(String patient_id,int sequence){
		HospitalSituation hospitalSituation=new PatientDao().getHospitalSituation(patient_id, sequence);
		return hospitalSituation;
	}
	
	/**
	 * 根据患者编号，查询患者信息
	 */
	public Patient queryPatientInfo(String patient_id) 
	{
		Patient patient = new PatientDao().queryPatientInfo(patient_id);
		return patient;
	}
	
	/**
	 * 根据患者编号，查询患者所有住院记录
	 */
	public InHospitalRecord[] getInHospitalRecord(String patient_id) {
		InHospitalRecord[] inHospitalRecords = new ExamDao().getInHospitalRecord(patient_id);
		return inHospitalRecords;
	}
	
	/**
	 * 根据时间段，按天返回从datefrom到dateto每日在院患者数量
	 */
	public Integer[] getPatientNumChange(String datefrom,String dateto){
		Integer[] data=new PatientDao().getPatientNumChange(datefrom, dateto);
		return data;
	}
	
	/**
	 * 从数据库中查询该查询条件记录是否已存在于数据库中，若存在返回文件名，若不存在返回null
	 */
	public String historyRecordExistJudge(HistoryQueryItem historyQueryItem) {
		String result = null;
		result=new HistoryRecordDao().historyRecordExistJudge(historyQueryItem);
		return result;
	}
	
	/**
	 * 向数据库中插入该条历史查询记录，成功返回true，失败返回false
	 */
	public boolean addHistoryRecord(HistoryQueryItem historyQueryItem) {
		boolean result = false;
		result=new HistoryRecordDao().addHistoryRecord(historyQueryItem);
		return result;
	}
	
	/**
	 * 向数据库中更新该条历史查询记录，成功返回true，失败返回false
	 */
	public boolean updateHistoryRecord(HistoryQueryItem historyQueryItem) {
		boolean result = false;
		result=new HistoryRecordDao().updateHistoryRecord(historyQueryItem);
		return result;
	}
	
	/**
	 * 返回所有历史查询记录
	 */
	public HistoryQueryItem[] returnAllHistoryQueryItem() {
		HistoryQueryItem[] historyQueryItems = null;
		historyQueryItems=new HistoryRecordDao().returnAllHistoryQueryItem();
		return historyQueryItems;
	}
	
	/**
	 * 存储历史查询记录操作
	 * @throws IOException 
	 */
	public void storeHistoryQuery(HistoryQueryItem historyQueryItem, JSONObject jsonObject) throws IOException {
		String path = historyRecordExistJudge(historyQueryItem);
		if(path == null)
		{
			path = new HistoryQuery().addHistoryFile(historyQueryItem, jsonObject);
			historyQueryItem.setFilename(path);
			addHistoryRecord(historyQueryItem);
		}
		else
		{
			HistoryQuery historyQuery = new HistoryQuery();
			if(historyQuery.deleteHistoryFile(path))
			{
				path = historyQuery.addHistoryFile(historyQueryItem, jsonObject);
				historyQueryItem.setFilename(path);
				updateHistoryRecord(historyQueryItem);
			}
		}
	}
	
	public static void main(String[] args){
		InHospitalRecord[] inHospitalRecords=new User("", "", "").getInHospitalRecord("123141");
		System.out.println(inHospitalRecords[1].getDiag());
	}
	
}
