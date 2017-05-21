package entity;

import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class DataExport {
	/**
	 * 
	 * @param name
	 * @param sex
	 * @param id
	 * @param amount
	 * @param out
	 * 导出历史记录
	 */
	public void exportData(ArrayList<String> name,ArrayList<String> sex,ArrayList<String> id,ArrayList<String> amount,OutputStream out){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("patientData");
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("姓名");
		cell=row.createCell(1);
		cell.setCellValue("性别");
		cell=row.createCell(2);
		cell.setCellValue("住院号");
		cell=row.createCell(3);
		cell.setCellValue("住院次数");
		int nRow=1;	
	    int len=name.size();
	    int i=0;
	    for(i=0;i<len;i++){
			    row=sheet.createRow(nRow++);
			    cell=row.createCell(0);
			    cell.setCellValue(name.get(i).toString());
			    
			    cell=row.createCell(1);
			    cell.setCellValue(sex.get(i).toString()); 
			    
			    cell=row.createCell(2);
			    cell.setCellValue(id.get(i).toString()); 
			    
			    cell=row.createCell(3);
			    cell.setCellValue(amount.get(i).toString()); 
	    }
		try{
			   wb.write(out);
			   wb.close();
			   out.close();
			  }catch(Exception e){
				  e.printStackTrace();
			  }
	}
	
	/**
	 * 导出检查项记录
	 */
	public void exportCheckData(ArrayList<String> list,OutputStream out){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("checkData");
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("住院次号");
		cell=row.createCell(1);
		cell.setCellValue("住院时间");
		cell=row.createCell(2);
		cell.setCellValue("入院时年龄");
		cell=row.createCell(3);
		cell.setCellValue("诊断结果");
		cell=row.createCell(4);
		cell.setCellValue("检查号");
		cell=row.createCell(5);
		cell.setCellValue("检查类别");
		cell=row.createCell(6);
		cell.setCellValue("检查部位");
		cell=row.createCell(7);
		cell.setCellValue("正常与否");
		cell=row.createCell(8);
		cell.setCellValue("描述");
		cell=row.createCell(9);
		cell.setCellValue("诊断");
		int nRow=1;	
	    int len=list.size();
	    int i=0;
			    row=sheet.createRow(nRow++);
			    cell=row.createCell(0);
			    cell.setCellValue(list.get(i++).toString());
			    
			    cell=row.createCell(1);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(2);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(3);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(4);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(5);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(6);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(7);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(8);
			    cell.setCellValue(list.get(i++).toString()); 
			    
			    cell=row.createCell(9);
			    cell.setCellValue(list.get(i++).toString()); 
		try{
			   wb.write(out);
			   wb.close();
			   out.close();
			  }catch(Exception e){
				  e.printStackTrace();
			  }
	}
	
	/**
	 * 导出检验项记录
	 */
	public void exportExamineData(ArrayList<String>list,OutputStream out){
		HSSFWorkbook wb=new HSSFWorkbook();
		HSSFSheet sheet=wb.createSheet("examineData");
		HSSFRow row=sheet.createRow(0);
		HSSFCell cell=row.createCell(0);
		cell.setCellValue("检验号");
		cell=row.createCell(1);
		cell.setCellValue("检验名称");
		cell=row.createCell(2);
		cell.setCellValue("指标名称");
		cell=row.createCell(3);
		cell.setCellValue("结果");
		cell=row.createCell(4);
	/*	cell.setCellValue("单位");
		cell=row.createCell(5);
		cell.setCellValue("结果指示");
		cell=row.createCell(6);
		cell.setCellValue("正常区间");    */
		int nRow=1,k=0;	
	    int len=list.size();
	    int i=0;
	    for(i=0;i<len;i=i+4){
	    	try{
			    row=sheet.createRow(nRow++);
			    cell=row.createCell(0);
			    cell.setCellValue(list.get(k++).toString());
			    
			    cell=row.createCell(1);
			    cell.setCellValue(list.get(k++).toString()); 
			    
			    cell=row.createCell(2);
			    cell.setCellValue(list.get(k++).toString()); 
			    
			    cell=row.createCell(3);
			    cell.setCellValue(list.get(k++).toString()); 
			    
		/*	    cell=row.createCell(4);
			    cell.setCellValue(list.get(k++).toString()); 
			    
			    cell=row.createCell(5);
			    cell.setCellValue(list.get(k++).toString()); 
			    
			    cell=row.createCell(6);
			    cell.setCellValue(list.get(k++).toString());   */
	    	}catch(Exception e){
	    		continue;
	    	}
	    }
		try{
			   wb.write(out);
		//	   wb.close();
		//	   out.close();
			  }catch(Exception e){
				  e.printStackTrace();
			  }
	}
	
}
