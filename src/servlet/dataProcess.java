package servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.DataExport;

/**
 * Servlet implementation class dataProcess
 */
@WebServlet("/dataProcess")
public class dataProcess extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  DataExport dataExport=new DataExport();
		  ArrayList<String>nameList=new ArrayList<String>();
		  ArrayList<String>sexList=new ArrayList<String>();
		  ArrayList<String>idList=new ArrayList<String>();
		  ArrayList<String>amountList=new ArrayList<String>();
		  String data =(String)request.getSession().getAttribute("anaysisdata");                 //获得字符串
		  response.setContentType("application/vnd.ms-excel;charset=utf-8");
		  response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode("userData.xls","utf-8"));
		// 解析获得住院次数集
		  while(data.indexOf("inHospitalCount")!=-1){                                           
			  int index=data.indexOf("inHospitalCount");
			  index=index+17;
			  String str="";
			  while(data.charAt(index)!='}'){
				  str=str+data.charAt(index);
				  index++;
			  }
			  amountList.add(str);
			  data=data.replaceFirst("inHospitalCount", "null");
		  }
		  
		  data =(String)request.getSession().getAttribute("anaysisdata");                 //获得字符串
		  //解析获得人名集
		  while(data.indexOf("name")!=-1){                                           
			  int index=data.indexOf("name");
			  index=index+7;
			  String str="";
			  while(data.charAt(index)!='"'){
				  str=str+data.charAt(index);
				  index++;
			  }
			  nameList.add(str);
			  data=data.replaceFirst("name", "null");
		  }
		  
		  data =(String)request.getSession().getAttribute("anaysisdata");                 //获得字符串
		  //解析获得性别集
		  while(data.indexOf("sex")!=-1){                                           
			  int index=data.indexOf("sex");
			  index=index+6;
			  String str="";
			  while(data.charAt(index)!='"'){
				  str=str+data.charAt(index);
				  index++;
			  }
			  sexList.add(str);
			  data=data.replaceFirst("sex", "null");
		  }
		  
		  data =(String)request.getSession().getAttribute("anaysisdata");                 //获得字符串
		  //解析获得住院号集
		  while(data.indexOf("patientID")!=-1){                                           
			  int index=data.indexOf("patientID");
			  index=index+12;
			  String str="";
			  while(data.charAt(index)!='"'){
				  str=str+data.charAt(index);
				  index++;
			  }
			  idList.add(str);
			  data=data.replaceFirst("patientID", "null");
		  }

		  dataExport.exportData(nameList,sexList,idList,amountList,response.getOutputStream());
		//  request.setAttribute("flag", "true");
		//  request.getRequestDispatcher("/analysis_history_result.jsp").forward(request, response);
	}

}
