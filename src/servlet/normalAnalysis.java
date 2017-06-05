package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.DataCompute;


/**
 * Servlet implementation class normalAnalysis
 */
@WebServlet("/normalAnalysis")
public class normalAnalysis extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  ArrayList<Double>list=new ArrayList<Double>();
		  String data =(String)request.getSession().getAttribute("anaysisdata");                 //获得字符串
		//  System.out.print("解析得到的数字：");
		  while(data.indexOf("inHospitalCount")!=-1){                                            //解析字符串，获得住院次数集
			  int index=data.indexOf("inHospitalCount");
			  index=index+17;
			  String str="";
			  while(data.charAt(index)!='}'){
				  str=str+data.charAt(index);
				  index++;
			  }
			  list.add(Double.parseDouble(str));
	//		  System.out.print(Double.parseDouble(str)+" ");
			  data=data.replaceFirst("inHospitalCount", "null");
		  }
    
		  DataCompute dc=new DataCompute();
		  double ave=dc.getAve(list);
		  double fangcha=dc.getFangcha(list);
		  request.setAttribute("ave", ave);
		  request.setAttribute("fangcha", fangcha);
		  request.setAttribute("flag", "true");
		  request.getRequestDispatcher("/normal_analysis.jsp").forward(request, response);
	}

}
