package fio;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

public class HistoryQuery {
	public static void writeFile(String filePath, String sets)  
            throws IOException {  
        FileWriter fw = new FileWriter(filePath);  
        PrintWriter out = new PrintWriter(fw);  
        out.write(sets);  
        out.println();  
        fw.close();  
        out.close();  
    } 
	/**
	 * 根据查询条件，添加历史查询文件，返回文件名
	 */
	public String addHistoryFile(String sex, int minAge, int maxAge, String examClass, String testItem, double valueStart, double valueEnd)
	{
		String fileName = "";
		return fileName;
	}
	
	/**
	 * 根据文件名，删除历史查询文件，成功返回true，失败返回false
	 */
	public boolean deleteHistoryFile(String fileName) {
		boolean result = false;
		return result;
	}
	public static void main(String[] args) throws JSONException, IOException{
		JSONObject jsonObject = new JSONObject();  
        jsonObject.put("1", "一");  
        jsonObject.put("2", "二");  
        jsonObject.put("3", "三");  
        jsonObject.put("4", "四");  
        jsonObject.put("5", "五");  
        jsonObject.put("6", "六");  
        jsonObject.put("7", "⑦");  
        System.out.println(jsonObject);  
  
        writeFile("test.json", jsonObject.toString()); 
	}
}
