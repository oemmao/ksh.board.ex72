package entity;

import java.sql.*;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import vo.*;

public class BoardEntity {
	private Connection conn;
	private Context ctx;
	private DataSource ds;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public BoardEntity() {
		try {
			ctx = new InitialContext(); //JNDI Subsystem //커넥션 풀 검색
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); //커넥션 풀 검색
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//새 게시글&답글 작성
	public void insertArticle(BoardVO vo) throws Exception {

		int num=vo.getNum(); //새글인지 답글인지 구분하기 위한 값 //num=0 이면 새글
		int ref=vo.getRef(); //게시글에 Reference number를 부여 
		int re_step=vo.getRe_step(); //답글 순서
		int re_level=vo.getRe_level(); //답글 들여쓰기 간격
		int number=0; //게시글 번호
		String sql="";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select max(num) from board"); //
			rs = pstmt.executeQuery();

			if (rs.next())
				number=rs.getInt(1)+1; //table에서 num 가져오는거임
			else
				number=1; 

			if (num!=0)   //
			{  
				sql="update board set re_step=re_step+1 where ref= ? and re_step> ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, re_step);
				pstmt.executeUpdate();
				re_step=re_step+1;
				re_level=re_level+1;
			}else{
				ref=number;
				re_step=0;
				re_level=0;
			}	 
			// DB에 정보 저장
			sql = "insert into board(writer,email,subject,passwd,reg_date,readcount,";
			sql+="ref,re_step,re_level,content,ip) values(?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getWriter());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getSubject());
			pstmt.setString(4, vo.getPasswd());
			pstmt.setTimestamp(5, vo.getReg_date());
			pstmt.setInt(6, vo.getReadcount());
			pstmt.setInt(7, ref);
			pstmt.setInt(8, re_step);
			pstmt.setInt(9, re_level);
			pstmt.setString(10, vo.getContent());
			pstmt.setString(11, vo.getIp());

			pstmt.executeUpdate();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

	//게시판 리스트 출력 시 게시물 수량 가져오는 메소드
	public void getArticleCount(BoardVO vo) throws Exception {

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setCount(rs.getInt(1));
			}

		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}

	}
	
	//게시판 리스트 출력 시 DB에 있는 모든 게시물 가져옴
	public void getArticles(BoardVO vo) throws Exception {
		List<BoardVO> articleList= new ArrayList<BoardVO>();
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(
					"select * from board order by ref desc, re_step asc limit ?,? ");
			//select limit는 지정한 갯수만큼 결과에 나오도록 하기위해 사용
			//select 칼럼명 from 테이블명 limit 반환갯수
			//select 칼럼명 from 테이블명 limit 시작위치, 반환갯수 ::시작위치는 0부터 시작함
			pstmt.setInt(1, vo.getStartRow()-1);
			pstmt.setInt(2, vo.getPageSize());
			rs = pstmt.executeQuery();

			while(rs.next()){
				BoardVO article= new BoardVO();
				article.setNum(rs.getInt("num"));
				article.setWriter(rs.getString("writer"));
				article.setEmail(rs.getString("email"));
				article.setSubject(rs.getString("subject"));
				article.setPasswd(rs.getString("passwd"));
				article.setReg_date(rs.getTimestamp("reg_date"));
				article.setReadcount(rs.getInt("readcount"));
				article.setRef(rs.getInt("ref"));
				article.setRe_step(rs.getInt("re_step"));
				article.setRe_level(rs.getInt("re_level"));
				article.setContent(rs.getString("content"));
				article.setIp(rs.getString("ip")); 

				articleList.add(article);
			};
			vo.setList(articleList);
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//게시글 내용 (Content) 가져옴
	public void getArticle(BoardVO vo) throws Exception {
		int num = vo.getNum();

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(
					"update board set readcount=readcount+1 where num = ?");
					//게시글 조회수 update해주는거~
					// Content에 들어올때마다 조회수 +1 해준다 
			pstmt.setInt(1, num);
			pstmt.executeUpdate();

			pstmt = conn.prepareStatement(
					"select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setSubject(rs.getString("subject"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRef(rs.getInt("ref"));
				vo.setRe_step(rs.getInt("re_step"));
				vo.setRe_level(rs.getInt("re_level"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));     
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	//Content에서 글수정 클릭 시 비밀번호를 제외한 정보 가져옴
	public void updateGetArticle(BoardVO vo) throws Exception {	
		int num = vo.getNum();
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(
					"select * from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				vo.setNum(rs.getInt("num"));
				vo.setWriter(rs.getString("writer"));
				vo.setEmail(rs.getString("email"));
				vo.setSubject(rs.getString("subject"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setReg_date(rs.getTimestamp("reg_date"));
				vo.setReadcount(rs.getInt("readcount"));
				vo.setRef(rs.getInt("ref"));
				vo.setRe_step(rs.getInt("re_step"));
				vo.setRe_level(rs.getInt("re_level"));
				vo.setContent(rs.getString("content"));
				vo.setIp(rs.getString("ip"));     
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}

	//게시글 수정 시 비밀번호 확인하여 일치하면 DB에 정보 update
	public void updateArticle(BoardVO vo) throws Exception {

		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(
					"select passwd from board where num = ?");
			pstmt.setInt(1, vo.getNum());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String dbpasswd= rs.getString("passwd"); //DB에 있는 비밀번호 정보 가져옴
//				System.out.println(dbpasswd+" vs "+vo.getPasswd());
				if(dbpasswd.equals(vo.getPasswd())){ //BD에 있는 비밀번호와 게시글에 작성한 비빌번호 일치 여부 확인
					String sql="update board set writer=?,email=?,subject=?,passwd=?";
					sql+=",content=? where num=?";
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, vo.getWriter());
					pstmt.setString(2, vo.getEmail());
					pstmt.setString(3, vo.getSubject());
					pstmt.setString(4, vo.getPasswd());
					pstmt.setString(5, vo.getContent());
					pstmt.setInt(6, vo.getNum());
					pstmt.executeUpdate();
					vo.setPwCheck(1);
				}else{
					vo.setPwCheck(0);
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
	
	public void deleteArticle(BoardVO vo) throws Exception {
		int num = vo.getNum();
		String passwd = vo.getPasswd();
		
		try {
			conn = ds.getConnection();

			pstmt = conn.prepareStatement(
					"select passwd from board where num = ?");
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String dbpasswd= rs.getString("passwd"); 
				if (dbpasswd.equals(passwd)) {
					pstmt = conn.prepareStatement("delete from board where num=?");
					pstmt.setInt(1, num);
					pstmt.executeUpdate();
					vo.setPwCheck(1);
				} else {
					vo.setPwCheck(0);
				}
			}	
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) try { rs.close(); } catch(SQLException ex) {}
			if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
			if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		}
	}
}