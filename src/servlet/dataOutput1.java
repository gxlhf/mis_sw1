package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.DataExport;

/**
 * Servlet implementation class dataOutput1
 */
@WebServlet("/dataOutput1")
public class dataOutput1 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String id=request.getParameter("id");
		  int index=Integer.parseInt(id);
		  DataExport dataExport=new DataExport();
		  ArrayList<String>list=new ArrayList<String>();
		  ArrayList<String>exList=new ArrayList<String>();
		  JSONObject jsonData= (JSONObject) request.getSession().getAttribute("inHospitalData");
		  JSONArray data=(JSONArray) request.getSession().getAttribute("examData");
		  JSONArray labdata=(JSONArray) request.getSession().getAttribute("labData");
		  ArrayList<Integer>arrlist=(ArrayList<Integer>) request.getSession().getAttribute("arrlist");
		  int sumnum=(Integer)request.getSession().getAttribute("sumnum");
		  response.setContentType("application/vnd.ms-excel;charset=utf-8");
		  response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode("userData.xls","utf-8"));
		  
		  /*检查项*/
		  try {
			list.add(((JSONArray)jsonData.get("data")).getJSONObject(index).getString("index"));
			list.add(((JSONArray)jsonData.get("data")).getJSONObject(index).getString("inTime"));
			list.add(((JSONArray)jsonData.get("data")).getJSONObject(index).getString("inAge"));
			list.add(((JSONArray)jsonData.get("data")).getJSONObject(index).getString("diag"));
		//	if(data.getJSONObject(index).getString("examNo")==null||data.getJSONObject(index).getString("examNo")=="")
		//		list.add("noItem");
		//	else
			list.add(data.getJSONObject(index).getString("examNo"));
			list.add(data.getJSONObject(index).getString("examPos"));
			list.add(data.getJSONObject(index).getString("examType"));
			list.add(data.getJSONObject(index).getString("isNormal"));
		//	if(data.getJSONObject(index).getString("description")==null||data.getJSONObject(index).getString("description")=="")
			list.add("noItem");
		//	else list.add(data.getJSONObject(index).getString("description"));
		//	if(data.getJSONObject(index).getString("diag")==null||data.getJSONObject(index).getString("diag")=="")
			list.add("noItem");
	//		else list.add(data.getJSONObject(index).getString("diag"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*检验项*/
		  for(int i=0;i<sumnum;i++){
			  for(int j=0;j<arrlist.get(i);j++){
				  try {
					exList.add(labdata.getJSONObject(i).getString("labNo"));
					exList.add(labdata.getJSONObject(i).getString("labName"));
					exList.add(((JSONArray)(labdata.getJSONObject(i).getJSONArray("result"))).getJSONObject(j).getString("name"));
					exList.add(((JSONArray)(labdata.getJSONObject(i).getJSONArray("result"))).getJSONObject(j).getString("resultVal"));
			//		exList.add(((JSONArray)(data.getJSONObject(i).getJSONArray("result"))).getJSONObject(j).getString("unit"));
			//		exList.add(((JSONArray)(data.getJSONObject(i).getJSONArray("result"))).getJSONObject(j).getString("resultIndicate"));
			//		exList.add(((JSONArray)(data.getJSONObject(i).getJSONArray("result"))).getJSONObject(j).getString("normalRange"));
			  }catch (JSONException e) {
					// TODO Auto-generated catch block
					continue;
				}
		  }
		  } 
		  //System.out.println("检验项目："+data.getJSONObject(0).get("labNo")+" "+data.getJSONObject(0).get("labName")+" "+((JSONArray)(data.getJSONObject(0).getJSONArray("result"))).getJSONObject(0).getString("name"));
		//  dataExport.exportCheckData(list,response.getOutputStream());
		  dataExport.exportExamineData(exList,response.getOutputStream());
		//  request.getRequestDispatcher("/analysis_history_result.jsp").forward(request, response);
		//  request.getRequestDispatcher("/dataOutput1").forward(request, response);
	}

}
