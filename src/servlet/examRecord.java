package servlet;

import java.io.IOException;
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
		String id=request.getParameter("patient_id");
		String Sequence=request.getParameter("sequence");
		int sequence=1;
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
				exa.put("examType", exam.getExam_sub_class());
				exa.put("examPos", exam.getExam_class());
				exa.put("isNormal", "");
				exa.put("description", exam.getDescription());
				exa.put("diag", exam.getImpression());
				data.put(exa);
			}
			jsonData.put("data", data);
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
