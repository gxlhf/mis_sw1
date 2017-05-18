package servlet;

import java.io.IOException;
import java.net.URLDecoder;
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
import entity.Exam;
import entity.HospitalSituation;
import entity.InHospitalRecord;
import entity.Patient;
import entity.QueryResult;
import entity.Test;
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
		String id=URLDecoder.decode(request.getParameter("patient_id"), "UTF-8");
		InHospitalRecord[] inHospitalRecords=user.getInHospitalRecord(id);
		JSONObject jsonData=new JSONObject();
		if(inHospitalRecords!=null){
		try {
			jsonData.put("length", inHospitalRecords.length);
			JSONArray data=new JSONArray();
			for(int i=0;i<inHospitalRecords.length;i++)
			{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("index", inHospitalRecords[i].getSequence());
				jsonObject.put("inTime", inHospitalRecords[i].getInTime());
				jsonObject.put("inAge", inHospitalRecords[i].getInAge());
				jsonObject.put("diag", inHospitalRecords[i].getDiag());
				HospitalSituation hospitalSituation=user.getHospitalSituation(inHospitalRecords[i].getPatientId(), inHospitalRecords[i].getSequence());
				if(hospitalSituation!=null)
				{
					if(hospitalSituation.getExam().length!=0){
						jsonObject.put("haveExam", true);
						for(Exam s:hospitalSituation.getExam()){
							System.out.println(s.getExam_class()+s.getExam_sub_class());
						}
					}
					else
						jsonObject.put("haveExam", false);
					if(hospitalSituation.getTest().length!=0){
						jsonObject.put("haveLab", true);
						for(Test t:hospitalSituation.getTest()){
							System.out.println(t.getItem_name()+t.getPatient_id());
						}
					}
					else
						jsonObject.put("haveLab", false);
					data.put(jsonObject);
				}
				
			}
			jsonData.put("data", data);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
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
