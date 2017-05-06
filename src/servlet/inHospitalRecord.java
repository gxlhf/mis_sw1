package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.UpdateEmbeddedDoc;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import dao.PatientDao;
import dao.UserDao;
import entity.HospitalSituation;
import entity.Patient;
import entity.QueryResult;
import entity.User;
import jdk.nashorn.internal.scripts.JS;

/**
 * Servlet implementation class inHospitalRecord
 */
@WebServlet("/inHospitalRecord")
public class inHospitalRecord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public inHospitalRecord() {
        super();
        // TODO Auto-generated constructor stub
    }
//excam 检查
  // test检验
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
		
		

		
		
		response.getWriter().println(jsonData.toString());

			
		}
		 
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
