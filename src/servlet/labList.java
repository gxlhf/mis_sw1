package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.LineUnavailableException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;

/**
 * Servlet implementation class labList
 */
@WebServlet("/labList")
public class labList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public labList() {
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
		String[] labList=user.queryTestClass();
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		try {
			for(int i=0;i<labList.length;i++){
				JSONObject jsonData1=new JSONObject();
				String[] list=user.queryTestItem(labList[i]);
				JSONArray subLab=new JSONArray();
				if(list!=null){
				for(int j=0;j<list.length;j++){
					JSONObject subname=new JSONObject();
					subname.put("subName", list[j]);
					subLab.put(subname);
				}
				jsonData1.put("subLab", subLab);
				jsonArray.put(jsonData1);
			}
			jsonData1.put("labName", labList[i]);
			jsonData.put("labs", jsonArray);
			
			}
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
