package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.HospitalSituation;
import entity.Test;
import entity.TestResult;
import entity.User;


/**
 * Servlet implementation class labRecord
 */
@WebServlet("/labRecord")
public class labRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public labRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		ArrayList<Integer>list=new ArrayList<Integer>();
		User user=new User("", "", "");
		String id=URLDecoder.decode(request.getParameter("patient_id"), "UTF-8");
		String Sequence=URLDecoder.decode(request.getParameter("sequence"), "UTF-8");
		int sequence=1;
		if(Sequence!=null)
			sequence=Integer.valueOf(Sequence);
		HospitalSituation hospitalSituation=user.getHospitalSituation(id, sequence);
		JSONObject jsonData=new JSONObject();
		try {
			
			jsonData.put("type", "lab");
			jsonData.put("length", hospitalSituation.getTest().length);
			
			JSONArray data=new JSONArray();

			
			for(int i=0;i<hospitalSituation.getTest().length;i++){
				Test test=hospitalSituation.getTest()[i];
				JSONObject lab=new JSONObject();
				lab.put("labNo", test.getTest_no());
				lab.put("labName", test.getItem_name());
				JSONArray result=new JSONArray();
				list.add(test.getTest_result().length);
				for(int j=0;j<test.getTest_result().length;j++){
					JSONObject res=new JSONObject();
					TestResult testResult=test.getTest_result()[j];
					res.put("name", testResult.getReport_item_name());
					res.put("resultVal", testResult.getResult());
					res.put("unit", testResult.getUnits());
					res.put("resultIndicate", testResult.getAbnormal_indicator());
					res.put("normalRange", testResult.getNormal_value());
					result.put(res);
				}
				
				lab.put("result",result );
				data.put(lab);
			}
			jsonData.put("data", data);
			System.out.println("检验项目："+data.getJSONObject(0).getString("labNo")+" "+data.getJSONObject(0).get("labName")+" "+((JSONArray)(data.getJSONObject(0).getJSONArray("result"))).getJSONObject(0).getString("name"));
			request.getSession().setAttribute("labData", data);
			request.getSession().setAttribute("sumnum", hospitalSituation.getTest().length);
			request.getSession().setAttribute("arrlist", list);
			response.getWriter().println(jsonData.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
