package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import service.*;
import vo.*;

@WebServlet(name ="/rankController", urlPatterns={"/rankController"})
public class RankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RankService rs;
	private String[][] score;
	private List list;
	
    public RankController() {
    	rs = new RankService();
    }

	protected void rankProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = (String)request.getParameter("cmd");
		if (cmd.equals("doService")) {
			doService(request, response);
		}
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		score = new String[6][8];
		for (int i=0; i<score.length; i++) {
			if (i<score.length-1) {
				score[i][0] = (String)request.getParameter("student"+(i+1));
				score[i][4] = " ";
				score[i][5] = " ";
				score[i][6] = " ";
				score[i][7] = " ";
				for(int j=1; j<score[i].length-4; j++) {
					score[i][j] = (String)request.getParameter("score"+(j+i*3));
				}
			} else {
				for(int j=0; j<score[i].length; j++) {
					score[i][j] = " ";
				}
			}
		}
		
		RankVO vo = new RankVO(score);
		RankMsgVO mvo = new RankMsgVO();
		list  = new ArrayList();
		list.add(vo);
		list.add(mvo);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			rs.doService(list);
			
			List<RankVO> resultList = (ArrayList)list.get(2);
			vo = (RankVO)list.get(0);
			String[][] score1 = vo.getScore();
			
			JSONArray jArray = new JSONArray();
			
			for (int i=0; i<resultList.size(); i++) {
				JSONObject sObject = new JSONObject();
				sObject.put("sum",resultList.get(i).getSum());
				sObject.put("avg",resultList.get(i).getAvg());
				sObject.put("rank",resultList.get(i).getRank());
				sObject.put("bigo",resultList.get(i).getBigo());
				jArray.add(sObject);
			}
			obj.put("rankList", jArray);
			obj.put("subTotal1", score1[5][1]);
			obj.put("subTotal2", score1[5][2]);
			obj.put("subTotal3", score1[5][3]);
			
			
			out.print(obj);	
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		rankProcess(request, response);
	}

}
