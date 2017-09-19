package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

import exception.*;
import vo.CalExcMsgVO;
import vo.CalVO;
import service.CalService;

@WebServlet(name = "/calController", urlPatterns = { "/calController" })
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CalService cs;  
	private List list;
	
    public CalController() {
    	cs = new CalService();
    }
    
    
    protected void memberProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cmd = (String)request.getParameter("cmd");
    	if (cmd.equals("calInput")) {
    		calInput(request, response);
    	}
	}
    
    protected void calInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
    	String op1 = (String)request.getParameter("op1");
    	String op = (String)request.getParameter("op");
    	String op2 = (String)request.getParameter("op2");

    	CalVO vo = new CalVO(op1, op, op2);
    	CalExcMsgVO emvo = new CalExcMsgVO();
    	list = new ArrayList();
    	list.add(vo);
    	list.add(emvo);

    	PrintWriter out = response.getWriter();
    	JSONObject obj = new JSONObject();
    	try {
    		cs.doService(list);
    		obj.put("result", vo.getResult());
    		out.println(obj);
    	} catch (AddZeroException e) {
    		obj.put("errResult", e.getMessage());
    		out.println(obj);
    	} catch (SubZeroException e) {
    		obj.put("errResult", e.getMessage());
    		out.println(obj);
    	} catch (MulOneException e) {
    		obj.put("errResult", e.getMessage());
    		out.println(obj);
    	} catch (DivOneException e) {
    		obj.put("errResult", e.getMessage());
    		out.println(obj);
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		out.close();
    	}
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		memberProcess(request, response);
	}

}
