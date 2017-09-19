package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import vo.*;

public class RankEntity {
	private Context ctx;
	private DataSource ds;
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	private RankVO vo;
	private List list;
	private List<RankVO> resultList;
	
	public RankEntity() {
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); //커넥션 풀 검색
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void store(List list) {
		this.list = list;
		insertRank(list);
		selectRank(list);
	}
	
	public void insertRank(List list) {
		vo = (RankVO)list.get(0);
		String[][] score = vo.getScore();
//		System.out.println(Arrays.deepToString(score));
		try {
			con = ds.getConnection();
			
			String query = "delete from tb_rankData"; //DB에 score를 담기 전에 delete해줌
			stmt = con.prepareStatement(query); //왜?? 해당 socre만 DB에 저장하기 위해
			stmt.executeUpdate();
			
			query = "insert into tb_rankData(sno, name, sub1, sub2, sub3, sum, avg, rank, bigo )"
					        + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(query);
			for (int i=0; i<score.length-1; i++) {	//배열에 담긴 score를 DB에 저장	
				stmt.setInt(1, (i+1));
				stmt.setString(2, score[i][0]);
				stmt.setString(3, score[i][1]);
				stmt.setString(4, score[i][2]);
				stmt.setString(5, score[i][3]);
				stmt.setString(6, score[i][4]);
				stmt.setString(7, score[i][5]);
				stmt.setString(8, score[i][6]);
				stmt.setString(9, score[i][7]);
				stmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void selectRank(List list) {
		resultList = new ArrayList<>();
		try {
			con = ds.getConnection();
			String query = "select * from tb_rankData order by sno";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				RankVO vo = new RankVO();
				vo.setSno(rs.getString("sno").trim()); //DB값이 null일 때, trim()을 사용하면 값을 가져오지 못함.
				vo.setName(rs.getString("name"));
				vo.setSub1(rs.getString("sub1").trim());
				vo.setSub2(rs.getString("sub2").trim());
				vo.setSub3(rs.getString("sub3").trim());
				vo.setSum(rs.getString("sum"));
				vo.setAvg(rs.getString("avg"));
				vo.setRank(rs.getString("rank"));
				vo.setBigo(rs.getString("bigo")); //DB값을 vo에 담음
				
				resultList.add(vo); //vo를 resultList에 담음
			}
			list.add(resultList); //resultList를 list에 담아서 넘김
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			if (stmt != null) {
				try {
				stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			if (con != null) {
				try {
				con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
	}
	
	//Exception 처리
	public String RankExcMsg(int excCode) {
		String excMsg = null;
		try {
			con = ds.getConnection();
			String query = "select * from tb_rankMsg where code=?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, excCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				excMsg = rs.getString("msg").trim();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			if (stmt != null) {
				try {
				stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
			if (con != null) {
				try {
				con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		}
		return excMsg;
	}
	
	public String getMsgScoreFail() {
		return RankExcMsg(181);
	}
	public String getMsgavgFail() {
		return RankExcMsg(182);
	}

}
