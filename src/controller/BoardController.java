package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.List;
import service.*;
import vo.*;

@WebServlet("/BoardController")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BoardService bs;
	private SimpleDateFormat sdf;

	public BoardController() {
		bs = new BoardService();
	}

	protected void boardProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //getParameter 쓰기 전에 써줘라!!!!!!
		
		String cmd = (String)request.getParameter("cmd");
		System.out.println("BoardController:: boardProcess() cmd= "+cmd);
		if (cmd.equals("insertBoard")) { //insertBoard //boardWrite //글쓰기
			insertBoard(request, response);
		} else if (cmd.equals("getAllBoards")) { //getAllBoards //boardList //게시글 리스트
			getAllBoards(request, response);
		} else if (cmd.equals("getBoard")) { //getBoard //boardContent //게시글 내용
			getBoard(request, response);
		} else if (cmd.equals("updateBoard")) { //updateBoard //boardUpdate //게시글 업데이트 (DB)
			updateBoard(request, response);
		} else if (cmd.equals("updateGetBoard")) { //updateGetBoard //boardUpdateCompletion //게시글 업데이트
			updateGetBoard(request, response);
		}  else if (cmd.equals("deleteBoard")) { //deleteBoard //boardDelete //게시글 삭제
			deleteBoard(request, response);
		}
	}

	protected void insertBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("insertBoard :: 성공");
//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		String page = "/BoardController?cmd=getAllBoards";
		int num=0,ref=1,re_step=0,re_level=0;
		try{  
			if (request.getParameter("num")!=null) {
				num=Integer.parseInt(request.getParameter("num"));
				ref=Integer.parseInt(request.getParameter("ref"));
				re_step=Integer.parseInt(request.getParameter("re_step"));
				re_level=Integer.parseInt(request.getParameter("re_level"));
			}
			
			vo.setNum(num);
			vo.setRef(ref);
			vo.setRe_step(re_step);
			vo.setRe_level(re_level);
			vo.setReg_date(new Timestamp(System.currentTimeMillis()) );
			vo.setIp(request.getRemoteAddr());
			
			vo.setWriter(request.getParameter("writer"));
//			System.out.println(request.getCharacterEncoding());
//			System.out.println(request.getParameter("writer"));
			vo.setEmail(request.getParameter("email"));
			vo.setSubject(request.getParameter("subject"));
			vo.setPasswd(request.getParameter("passwd"));
			vo.setContent(request.getParameter("content"));
//			vo.setContent(new String(vo.getContent().getBytes("iso-8859-1"),"utf-8"));
			
			bs.insertBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		request.setAttribute("vo", vo);
		dispatcher.forward(request, response);
	}
	
	protected void getAllBoards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("boardList:: 성공");
//		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		String page = "/board/list.jsp";
		vo.setPageSize(10);

		String pageNum = request.getParameter("pageNum");
		if (pageNum == null) {
			pageNum = "1";
		}
		try {
			vo.setPageNum(pageNum);
			vo.setCurrentPage(Integer.parseInt(vo.getPageNum()));
			vo.setStartRow((vo.getCurrentPage() - 1) * vo.getPageSize() + 1);
			vo.setEndRow(vo.getCurrentPage() * vo.getPageSize());
			int count = 0;

			bs.getAllBoards(vo);

			count = vo.getCount();
			//	        if (count > 0) {
			//	            articleList = bs.getArticles(startRow, pageSize);
			//	        }
			vo.setNumber(count-(vo.getCurrentPage()-1)*vo.getPageSize());
		} catch (Exception e) {
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		request.setAttribute("vo", vo);
		dispatcher.forward(request, response);
	}

	protected void getBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("boardContent:: 성공");
//		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		String page = "/board/content.jsp";
		
		int num = Integer.parseInt(request.getParameter("num"));
		String PageNum = request.getParameter("pageNum");
		try {
			vo.setNum(num);
			vo.setPageNum(PageNum);
			
			bs.getBoard(vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		request.setAttribute("vo", vo);
		dispatcher.forward(request, response);
	}

	protected void updateBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("boardUpdate:: 성공");
//		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		String page = "/board/updateForm.jsp";
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		try {
			vo.setNum(num);
			vo.setPageNum(pageNum);
			
			bs.updateBoard(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		request.setAttribute("vo", vo);
		dispatcher.forward(request, response);
	}

	protected void updateGetBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("boardUpdateCompletion:: 성공");
//		response.setCharacterEncoding("UTF-8");
		BoardVO vo = new BoardVO();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		
		try {
			vo.setPageNum(pageNum);
			vo.setNum(num);
			vo.setWriter(request.getParameter("writer"));
			vo.setSubject(request.getParameter("subject"));
			vo.setEmail(request.getParameter("email"));
			vo.setContent(request.getParameter("content"));
			vo.setPasswd(request.getParameter("passwd"));
			bs.updateGetBoard(vo);
			
			if (vo.getPwCheck() == 1) {
				obj.put("check", vo.getPwCheck());
			} else {
				obj.put("check", vo.getPwCheck());
			}
			out.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
		
	}
	
	protected void deleteBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("boardDelete:: 성공");
		BoardVO vo = new BoardVO();
		PrintWriter out = response.getWriter();
		JSONObject obj = new JSONObject();
		
		int num = Integer.parseInt(request.getParameter("num"));
		String passwd = request.getParameter("passwd");
		String pageNum = request.getParameter("pageNum");
		
		try {
			vo.setNum(num);
			vo.setPasswd(passwd);
			vo.setPageNum(pageNum);
			
			bs.deleteBoard(vo);
			
			if (vo.getPwCheck() == 1) {
				obj.put("result", vo.getPwCheck());
			} else {
				obj.put("result", vo.getPwCheck());
			}
			out.print(obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boardProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boardProcess(request, response);
	}

}
