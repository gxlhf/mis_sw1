package servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Exam;
import entity.HospitalSituation;
import entity.User;

/**
 * Servlet implementation class examRecord
 */
@WebServlet("/examRecord")
public class examRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public examRecord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		User user=new User("", "", "");
		String id=URLDecoder.decode(request.getParameter("patient_id"), "UTF-8");
		String Sequence=URLDecoder.decode(request.getParameter("sequence"), "UTF-8");
		int sequence=1;
//		System.out.println(id+Sequence);
		if(Sequence!=null)
			sequence=Integer.valueOf(Sequence);
		HospitalSituation hospitalSituation=user.getHospitalSituation(id, sequence);
		JSONObject jsonData=new JSONObject();
		
		try {
			
			jsonData.put("type", "exam");
			jsonData.put("length", hospitalSituation.getExam().length);
			JSONArray data=new JSONArray();
			for(int i=0;i<hospitalSituation.getExam().length;i++){
				Exam exam=hospitalSituation.getExam()[i];
				JSONObject exa=new JSONObject();
				exa.put("examNo", exam.getExam_no());
				exa.put("examPos", exam.getExam_sub_class());
				exa.put("examType", exam.getExam_class());
				exa.put("isNormal", exam.getIsAbnormal()==1?"否":"是");
				exa.put("description", exam.getDescription());
				exa.put("diag", exam.getImpression());
				data.put(exa);
			}
			request.getSession().setAttribute("examData", data);
			jsonData.put("data", data);
		//	System.out.println("检验项："+data.getJSONObject(0).getString("examNo"));
			
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
