package servlet;

import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import entity.HistoryQueryItem;
import entity.QueryResult;
import entity.User;


/**
 * Servlet implementation class queryResult
 */
@WebServlet("/queryResult")
public class queryResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryResult() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		User user=new User("", "","");
		String sex=URLDecoder.decode(request.getParameter("sex"), "UTF-8");
//		System.out.println(URLDecoder.decode(request.getParameter("sex"), "UTF-8"));
		if (sex.equals("不限")) 
			sex = "";
		/*String ageFrom=URLDecoder.decode(request.getParameter("ageFrom"), "UTF-8");
		String ageTo=URLDecoder.decode(request.getParameter("ageTo"), "UTF-8");*/
		int ageFrom;
		int ageTo;
		if(URLDecoder.decode(request.getParameter("ageFrom"), "UTF-8").equals(""))
			ageFrom = -1;
		else
			ageFrom = Integer.parseInt(URLDecoder.decode(request.getParameter("ageFrom"), "UTF-8"));
		if(URLDecoder.decode(request.getParameter("ageTo"), "UTF-8").equals(""))
			ageTo = 1000;
		else
			ageTo = Integer.parseInt(URLDecoder.decode(request.getParameter("ageTo"), "UTF-8"));
		String examType=URLDecoder.decode(request.getParameter("examType"), "UTF-8");
		if (examType.equals("不限")) 
			examType = "";
		String labType=URLDecoder.decode(request.getParameter("labType"), "UTF-8");
		if (labType.equals("不限")) 
			labType = "";
		String labSubType=URLDecoder.decode(request.getParameter("labSubType"), "UTF-8");
		if (labSubType.equals("不限")) 
			labSubType = "";
		String labValFrom=URLDecoder.decode(request.getParameter("labValFrom"), "UTF-8");
		String labValTo=URLDecoder.decode(request.getParameter("labValTo"), "UTF-8");
		List<QueryResult> queryResult=null;
		List<QueryResult>  queryResults=null;
		
		queryResult=user.queryPatient(sex, ageFrom, ageTo, examType);
		
		if(!labValFrom.equals("") && !labValTo.equals(""))
			queryResults=user.queryPatient(sex, ageFrom, ageTo, labSubType, Double.parseDouble(labValFrom) , Double.parseDouble(labValTo));
		if(queryResult!=null&&queryResults!=null)
			for(QueryResult q:queryResults)
			{
				queryResult.add(q);
			}
		else if(queryResults!=null)
			queryResult=queryResults;
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		if(queryResult!=null)
		{
			for(QueryResult q:queryResult)
			{
				System.out.println(q.getHospitalCount()+q.getPatient().getPatient_name()+q.getPatient().getSex());
			}
			
			try {
			
				for(QueryResult q:queryResult){
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("name", q.getPatient().getPatient_name());
						jsonObject.put("sex", q.getPatient().getSex());
						jsonObject.put("patientID", q.getPatient().getPatient_id());
						if(q.getClinicDiagMap()!=null)
							jsonObject.put("inHospitalCount",q.getClinicDiagMap().size());
						else
							jsonObject.put("inHospitalCount", 0);
						jsonArray.put(jsonObject);
					
				}
				jsonData.put("result", jsonArray);
				
				response.getWriter().println(jsonData);

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		else
		{
			try {
				jsonData.put("result", jsonArray);
				response.getWriter().println(jsonData);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONObject qAJsonObject = new JSONObject();
		JSONObject hQJsonObject = new JSONObject();
		try {
			qAJsonObject.put("queryID", "");
			qAJsonObject.put("sex", URLDecoder.decode(request.getParameter("sex"), "UTF-8"));
			qAJsonObject.put("ageFrom", URLDecoder.decode(request.getParameter("ageFrom"), "UTF-8"));
			qAJsonObject.put("ageTo", URLDecoder.decode(request.getParameter("ageTo"), "UTF-8"));
			qAJsonObject.put("labType", URLDecoder.decode(request.getParameter("labType"), "UTF-8"));
			qAJsonObject.put("labSubType", URLDecoder.decode(request.getParameter("labSubType"), "UTF-8"));
			qAJsonObject.put("labValFrom", URLDecoder.decode(request.getParameter("labValFrom"), "UTF-8"));
			qAJsonObject.put("labValTo", URLDecoder.decode(request.getParameter("labValTo"), "UTF-8"));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			qAJsonObject.put("queryTime", df.format(new Date()));
			hQJsonObject.put("queryAbstract", qAJsonObject);
			hQJsonObject.put("queryResult", jsonData);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		HistoryQueryItem historyQueryItem = new HistoryQueryItem(URLDecoder.decode(request.getParameter("sex"), "UTF-8"), ageFrom, ageTo, URLDecoder.decode(request.getParameter("examType"), "UTF-8"), testItem, labValFrom, labValTo)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
