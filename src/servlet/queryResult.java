package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import entity.QueryResult;
import entity.User;
import net.sf.json.JSONArray;

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
		String sex=request.getParameter("sex");
		String ageFrom=request.getParameter("ageFrom");
		String ageTo=request.getParameter("ageTo");
		String examType=request.getParameter("examType");
		String labType=request.getParameter("labType");
		String labSubType=request.getParameter("labSubType");
		String labValFrom=request.getParameter("labValFrom");
		String labValTo=request.getParameter("labValTo");
		List<QueryResult> queryResult=null;
		List<QueryResult>  queryResults=null;
		if(!(ageFrom.equals("")||ageTo.equals("")))
			queryResult=user.queryPatient(sex,Integer.parseInt(ageFrom) ,Integer.parseInt(ageTo) , examType);
		if(!ageFrom.equals("")&&!ageTo.endsWith("")&&!labValFrom.equals("")&&!labValTo.equals(""))
			queryResults=user.queryPatient(sex, Integer.parseInt(ageFrom) ,Integer.parseInt(ageTo) ,labSubType,Double.parseDouble(labValFrom) , Double.parseDouble(labValTo));
		if(queryResult!=null&&queryResults!=null)
		for(QueryResult q:queryResults){
			queryResult.add(q);
		}
		else if(queryResults!=null)
			queryResult=queryResults;
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		
		if(queryResult!=null){
		try {
			
			for(QueryResult q:queryResult){
				for(Integer in:q.getClinicDiagMap().keySet()){
					JSONObject jsonObject=new JSONObject();
					String string=q.getClinicDiagMap().get(in);
					jsonObject.put("name", q.getPatient().getPatient_name());
					jsonObject.put("sex", q.getPatient().getSex());
					jsonObject.put("diag", string);
					jsonObject.put("patientID", q.getPatient().getPatient_id());
					jsonObject.put("inHospitalCount", q.getHospitalCount());
					jsonArray.add(jsonObject);
				}
			}
			jsonData.put("result", jsonArray);
			
			response.getWriter().println(jsonData);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		} else{
			try {
				jsonData.put("result", jsonArray);
				response.getWriter().println(jsonData);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
