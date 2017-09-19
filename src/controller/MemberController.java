package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

//import javax.naming.NamingException;
//import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import service.*;
import vo.*;

@WebServlet(name = "/memberController", urlPatterns = { "/memberController" })
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService ms;

	public MemberController() {
		ms = new MemberService();
		//객체 생성은 다 생성자에 추가하쟈~~ 
	}

	protected void memberProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		//		request.setCharacterEncoding("EUC-KR");

		String cmd = (String)request.getParameter("cmd");
		if (cmd.equals("memberInsert")) {
			memberInsert(request, response);
		} else if (cmd.equals("checkID")) {
			checkID(request, response);
		} else if (cmd.equals("login")) {
			login(request, response);
		} else if (cmd.equals("selectAllMember")) {
			selectAllMember(request, response);
		} else if (cmd.equals("selectMember")) {
			selectMember(request, response);
		} else if (cmd.equals("updateMember")) {
			updateMember(request, response);
		} else if (cmd.equals("deleteMember")) {
			deleteMember(request, response);
		} else if (cmd.equals("isLogin")) {
			isLogin(request, response);
		} else if (cmd.equals("logout")) {
			logout(request, response);
		} 
	}

	protected void memberInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userID = (String)request.getParameter("userID");
		String pwd = (String)request.getParameter("pwd");
		String name = (String)request.getParameter("name");
		String phone = (String)request.getParameter("phone");
		String address = (String)request.getParameter("address");

		MemberVO vo = new MemberVO(userID, pwd, name, phone, address);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			ms.memberInsert(vo);
			obj.put("result", vo.getResult());
			out.println(obj);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void checkID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userID = (String)request.getParameter("userID");
		MemberVO vo = new MemberVO(userID);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			ms.checkID(vo);
			obj.put("result", vo.getResult());
			out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userID = (String)request.getParameter("userID1");
		String pwd = (String)request.getParameter("pwd1");
		MemberVO vo = new MemberVO(userID, pwd);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			ms.login(vo);
			if (vo.getResult() == 0) {
			request.getSession().setAttribute("userID", vo.getUserID());
			request.getSession().setAttribute("userName", vo.getName());
			obj.put("userID", vo.getUserID());
			obj.put("result", vo.getResult());
			} 
			out.print(obj);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void isLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userID = (String)request.getSession().getAttribute("userID");
		boolean login = userID == null ? false : true;
		PrintWriter out = response.getWriter();
		try {
			if (login) {
				out.println("1");
			} else {
				out.println("0");
			} 
		} finally {
			out.close();
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getSession().invalidate();
	}

	protected void selectAllMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
//		String userID = (String)request.getParameter("userID3");
		MemberVO vo = new MemberVO();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			List<MemberVO> list = new ArrayList();
			ms.selectAllMember(list);
			JSONArray jArray = new JSONArray();
			for (int i = 0; i < list.size(); i++)//배열
			{
				JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
				sObject.put("ID", list.get(i).getUserID());
				sObject.put("NAME", list.get(i).getName());
				sObject.put("PHONE", list.get(i).getPhone());
				sObject.put("ADDRESS", list.get(i).getAddress());
				jArray.add(sObject);
			}
			obj.put("memberlist", jArray);//배열을 넣음
			out.print(obj);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void selectMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		response.setCharacterEncoding("UTF-8");
		String userID = (String)request.getParameter("userID2");
		MemberVO vo = new MemberVO(userID);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			ms.selectMember(vo);
			obj.put("ID", "ID");
			obj.put("NAME", "NAME");
			obj.put("PHONE", "PHONE");
			obj.put("ADDRESS", "ADDRESS");
			obj.put("userIDValue", vo.getUserID());
			obj.put("nameValue", vo.getName());
			obj.put("phoneValue", vo.getPhone());
			obj.put("addressValue", vo.getAddress());
			obj.put("result1", vo.getResult());
			out.print(obj);	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void updateMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String userID = (String)request.getParameter("userID");
		String pwd = (String)request.getParameter("pwd");
		String name = (String)request.getParameter("name");
		String phone = (String)request.getParameter("phone");
		String address = (String)request.getParameter("address");	
		MemberVO vo = new MemberVO(userID, pwd, name, phone, address);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		try {
			ms.updateMember(vo);
			obj.put("result", vo.getResult());
			out.print(obj);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void deleteMember(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		request.getSession().invalidate();
		String userID = (String)request.getParameter("userID");
		String pwd = (String)request.getParameter("pwd2");
		MemberVO vo = new MemberVO(userID, pwd);
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		System.out.println(pwd);
		try {
			ms.deleteMember(vo);
			obj.put("result", vo.getResult());
			out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//	memberProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberProcess(request, response);
	}

}

