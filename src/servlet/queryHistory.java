package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import entity.HistoryQueryItem;
import entity.QueryHistory;
import entity.User;
import fio.HistoryQuery;

/**
 * Servlet implementation class queryHistory
 */
@WebServlet("/queryHistory")
public class queryHistory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public queryHistory() {
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
		HistoryQueryItem[] historyQueryItems = user.returnAllHistoryQueryItem();
		JSONObject jsonData=new JSONObject();
		JSONArray jsonArray=new JSONArray();
		for (HistoryQueryItem historyQueryItem : historyQueryItems) {
			JSONObject jsonObject=new JSONObject();
			if(new HistoryQuery().judgeFileExits(historyQueryItem.getFilename()))
			{
				try {
					jsonObject.put("queryID", historyQueryItem.getFilename());
					jsonObject.put("sex", historyQueryItem.getSex());
					if(historyQueryItem.getMinAge() == -1)
						jsonObject.put("ageFrom", "");
					else
						jsonObject.put("ageFrom", historyQueryItem.getMinAge());
					if(historyQueryItem.getMaxAge() == 1000)
						jsonObject.put("ageTo", "");
					else
						jsonObject.put("ageTo", historyQueryItem.getMaxAge());
					jsonObject.put("examType", historyQueryItem.getExamClass());
					jsonObject.put("labType", historyQueryItem.getLabType());
					jsonObject.put("labSubType", historyQueryItem.getLabSubType());
					jsonObject.put("labValFrom", historyQueryItem.getLabValFrom());
					jsonObject.put("labValTo", historyQueryItem.getLabValTo());
					jsonObject.put("queryTime", historyQueryItem.getDate());
					jsonArray.put(jsonObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		try {
			jsonData.put("data", jsonArray);
			response.getWriter().println(jsonData);
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
