/**
 * 检查结果类
 * print_order:打印顺序
 * report_item_name:指标名称
 * result:结果
 * units:单位
 * abnormal_indicator:正常情况
 * normal_value:参考值
 */
package com.entity;

public class TestResult {
	int print_order;
	String report_item_name;
	String result;
	String units;
	String abnormal_indicator;
	String normal_value;
	public TestResult(int print_order, String report_item_name, String result, String units, String abnormal_indicator,
			String normal_value) {
		this.print_order = print_order;
		this.report_item_name = report_item_name;
		this.result = result;
		this.units = units;
		this.abnormal_indicator = abnormal_indicator;
		this.normal_value = normal_value;
	}
	public int getPrint_order() {
		return print_order;
	}
	public void setPrint_order(int print_order) {
		this.print_order = print_order;
	}
	public String getReport_item_name() {
		return report_item_name;
	}
	public void setReport_item_name(String report_item_name) {
		this.report_item_name = report_item_name;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getAbnormal_indicator() {
		return abnormal_indicator;
	}
	public void setAbnormal_indicator(String abnormal_indicator) {
		this.abnormal_indicator = abnormal_indicator;
	}
	public String getNormal_value() {
		return normal_value;
	}
	public void setNormal_value(String normal_value) {
		this.normal_value = normal_value;
	}
	
}
