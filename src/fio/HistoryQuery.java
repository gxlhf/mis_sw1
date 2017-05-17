package fio;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.json.JSONException;
import org.json.JSONObject;

import entity.HistoryQueryItem;

public class HistoryQuery {
	String directory = "";
	public void writeFile(String filePath, String sets)  
            throws IOException {  
        FileWriter fw = new FileWriter(directory + filePath);  
        PrintWriter out = new PrintWriter(fw);  
        out.write(sets);  
        out.println();  
        fw.close();  
        out.close();  
    } 
	/**
	 * 根据历史查询项类和存有历史查询信息的JSONObject，添加历史查询文件，返回文件名
	 * @throws IOException 
	 */
	public String addHistoryFile(HistoryQueryItem historyQueryItem, JSONObject hqJsonObject) throws IOException
	{
		String string = historyQueryItem.getSex() + historyQueryItem.getMinAge() + historyQueryItem.getMaxAge() + historyQueryItem.getExamClass() + historyQueryItem.getLabSubType() + historyQueryItem.getLabValFrom() + historyQueryItem.getLabValTo();
		int hashCode = string.hashCode();
		String fileName = Integer.toString(hashCode);
		writeFile(fileName + ".json", hqJsonObject.toString());
		return fileName;
	}
	
	/**
	 * 根据文件名，删除历史查询文件，成功返回true，失败返回false
	 */
	public boolean deleteHistoryFile(String fileName) {
		boolean result = false;
		File file = new File(directory + fileName);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        result = true;  
	    }  
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
        HistoryQueryItem historyQueryItem = new HistoryQueryItem("", 0, 1, "", "", "", "", "", "", "");
  
//        new HistoryQuery().writeFile("test.json", jsonObject.toString());
//        new HistoryQuery().deleteHistoryFile("HistoryQueryFiles/test.json");
        new HistoryQuery().addHistoryFile(historyQueryItem,jsonObject);
	}
}
