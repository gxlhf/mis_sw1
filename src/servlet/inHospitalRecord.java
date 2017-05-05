package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import dao.PatientDao;
import entity.HospitalSituation;
import entity.Patient;

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
		String sex=request.getParameter("sex");
		int maxAge=Integer.parseInt(request.getParameter("maxAge"));
		int minAge=Integer.parseInt(request.getParameter("minAge"));
		String examClass=request.getParameter("exam");
		 String testItem=request.getParameter("testItem");
		 Double valueStart=Double.valueOf(request.getParameter("valueStart"));
		 Double valueEnd=Double.valueOf(request.getParameter("valueEnd"));
		 List<Patient> patients=null;
		 Map<Patient, Integer> result=null;
		 Map<String, Object> jsonData = new HashMap<String, Object>();
		 if(examClass.equals("")){
			 patients=new PatientDao().queryPatient(sex, minAge, maxAge,testItem,valueStart,valueEnd);
			 if(patients!=null){
				 for(int i=0;i<patients.size();i++){
					 	result.put(, value)
				 }
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
