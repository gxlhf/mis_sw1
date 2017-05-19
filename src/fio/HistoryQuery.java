package fio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import org.json.JSONException;
import org.json.JSONObject;

import entity.HistoryQueryItem;

public class HistoryQuery {
	String classPath = this.getClass().getClassLoader().getResource("").getPath();
	String path = classPath.substring(0, classPath.indexOf("WEB-INF"));
	String directory = path + "HistoryFiles";
	public HistoryQuery() throws IOException {
//		System.out.println(path);
		File dir = new File(directory);
		judgeDirExists(dir);
	}
	
	/**
	 * 判断目录是否存在，不存在则创建目录
	 */
	public void judgeDirExists(File file) {	
		if (file.exists()) 
		{
			if (file.isDirectory()) 
			{
//				System.out.println("dir exists");
	        } else 
	        {
//	        	System.out.println("the same name file exists, can not create dir");
		    }
		} else {
//		    System.out.println("dir not exists, create it ...");
		    file.mkdir();
		}	
	}
	
	/**
	 * 判断json文件是否存在
	 */
	public boolean judgeFileExits(String fileName) {
		File file = new File(directory + "/" + fileName);
        if (file.exists())
        	return true;
        else
        	return false;
	}
	
	/**
	 * 写入文件
	 */
	public void writeFile(String fileName, String sets)  
            throws IOException {  
		OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(directory + "/" + fileName),"UTF-8"); 
		out.write(sets);
		out.close();
    } 
	
	/**
	 * 读取文件
	 */
	public String ReadFile(String fileName) {   
        BufferedReader reader = null;  
        String laststr = "";  
        File file = new File(directory + "/" + fileName);
        if (!file.exists())
        	return null;
        try {  
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(directory + "/" + fileName), "UTF-8"));  
            String tempString = null;  
            while ((tempString = reader.readLine()) != null) {  
                laststr = laststr + tempString;  
            }  
            reader.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (reader != null) {  
                try {  
                    reader.close();  
                } catch (IOException e1) {  
                }  
            }  
        }  
        return laststr;  
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
		return fileName + ".json";
	}
	
	/**
	 * 根据文件名，删除历史查询文件，成功返回true，失败返回false
	 */
	public boolean deleteHistoryFile(String fileName) {
		boolean result = false;
		File file = new File(directory + "/" + fileName);  
	    // 路径为文件且不为空则进行删除  
		if(!file.exists())
			return true;
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
//        new HistoryQuery().addHistoryFile(historyQueryItem,jsonObject);
	}
}
