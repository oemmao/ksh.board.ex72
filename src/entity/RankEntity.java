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
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); //Ŀ�ؼ� Ǯ �˻�
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
			
			String query = "delete from tb_rankData"; //DB�� score�� ��� ���� delete����
			stmt = con.prepareStatement(query); //��?? �ش� socre�� DB�� �����ϱ� ����
			stmt.executeUpdate();
			
			query = "insert into tb_rankData(sno, name, sub1, sub2, sub3, sum, avg, rank, bigo )"
					        + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(query);
			for (int i=0; i<score.length-1; i++) {	//�迭�� ��� score�� DB�� ����	
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
				vo.setSno(rs.getString("sno").trim()); //DB���� null�� ��, trim()�� ����ϸ� ���� �������� ����.
				vo.setName(rs.getString("name"));
				vo.setSub1(rs.getString("sub1").trim());
				vo.setSub2(rs.getString("sub2").trim());
				vo.setSub3(rs.getString("sub3").trim());
				vo.setSum(rs.getString("sum"));
				vo.setAvg(rs.getString("avg"));
				vo.setRank(rs.getString("rank"));
				vo.setBigo(rs.getString("bigo")); //DB���� vo�� ����
				
				resultList.add(vo); //vo�� resultList�� ����
			}
			list.add(resultList); //resultList�� list�� ��Ƽ� �ѱ�
			
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
	
	//Exception ó��
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
