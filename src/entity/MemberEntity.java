package entity;

import java.sql.*;
import java.util.List;
import vo.MemberVO;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberEntity {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	private Context ctx;
	private DataSource ds;
	public MemberEntity() {
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			ctx = new InitialContext(); //JNDI Subsystem //Ä¿³Ø¼Ç Ç® °Ë»ö
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); //Ä¿³Ø¼Ç Ç® °Ë»ö
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void memberInsert(MemberVO vo) {

		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "insert into tb_userdata(userId, pwd, name, phone, address)" +
					"values(?, ?, ?, ?, ?)";
			stmt = con.prepareStatement(query);

			String userId = vo.getUserID();
			String pwd = vo.getPwd();
			String name = vo.getName();
			String phone = vo.getPhone();
			String address = vo.getAddress();

			stmt.setString(1, userId);
			stmt.setString(2, pwd);
			stmt.setString(3, name);
			stmt.setString(4, phone);
			stmt.setString(5, address);
			stmt.executeUpdate();
			
			if (userId == null || pwd == null || name == null || phone == null || address == null) {
				vo.setResult(0);
			} else {
				vo.setResult(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void checkID(MemberVO vo) {
		String userID = null;
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "select * from tb_userdata where userId=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				userID = rs.getString("userID").trim();
			}
			if (userID == null) {
				vo.setResult(1);
			} else {
				vo.setResult(0);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void login(MemberVO vo) {
		String userID = null;
		String pwd = null;
		String userName = null;
		
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "select * from tb_userdata where userId=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				userID = rs.getString("userID").trim();
				pwd = rs.getString("pwd").trim();
				userName = rs.getString("name").trim();
			}
			if (userID == null) {
				vo.setResult(2);
			} else if (!pwd.equals(vo.getPwd())) {
				vo.setResult(1);
			} else {
				vo.setResult(0);
				vo.setName(userName);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	public void selectAllMember(List<MemberVO> list) {

		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "select * from tb_userdata";
			stmt = con.prepareStatement(query);
			rs = stmt.executeQuery();
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserID(rs.getString("userId").trim());
				vo.setName(rs.getString("name").trim());
				vo.setPhone(rs.getString("phone").trim());
				vo.setAddress(rs.getString("address").trim());
				
				list.add(vo);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void selectMember(MemberVO vo) {
		String userID = null;
		String name = null;
		String phone = null;
		String address = null;

		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "select * from tb_userdata where userId=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getUserID());
			rs = stmt.executeQuery();
			while (rs.next()) {
				userID = rs.getString("userId").trim();
				name = rs.getString("name").trim();
				phone = rs.getString("phone").trim();
				address = rs.getString("address").trim();

			}
			if (userID == null) {
				vo.setResult(1);	
			} else {
				vo.setResult(0);
				vo.setUserID(userID);
				vo.setName(name);
				vo.setPhone(phone);
				vo.setAddress(address);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void updateMember(MemberVO vo) {
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "update tb_userdata set pwd=?, name=?, phone=?, address=? where userId=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getPwd());
			stmt.setString(2, vo.getName());
			stmt.setString(3, vo.getPhone());
			stmt.setString(4, vo.getAddress());
			stmt.setString(5, vo.getUserID());
			stmt.executeUpdate();
				vo.setResult(1);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void deleteMember(MemberVO vo) {		
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "delete from tb_userdata where userId=? and pwd=?";
			stmt = con.prepareStatement(query);
			stmt.setString(1, vo.getUserID());
			stmt.setString(2, vo.getPwd());
			int i = stmt.executeUpdate();
			if (i > 0) {
				vo.setResult(1);
			} else {
				vo.setResult(0);
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
					con.close(); //Ä¿³Ø¼Ç ¹Ý³³
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
