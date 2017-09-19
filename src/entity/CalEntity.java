package entity;

import java.io.*;

import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vo.*;

public class CalEntity {
	private Context ctx;
	private DataSource ds;
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	private CalVO vo;
	private CalExcMsgVO emvo;
	
	public CalEntity() {
		try {
			ctx = new InitialContext(); //JNDI Subsystem //Ä¿³Ø¼Ç Ç® °Ë»ö
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql"); //Ä¿³Ø¼Ç Ç® °Ë»ö
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void doService(List list) {
		vo = (CalVO)list.get(0);
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "insert into tb_calData(op1, op, op2, result)" +
							"values(?, ?, ?, ?)";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, Integer.parseInt(vo.getOp1()));
			stmt.setString(2, vo.getOp());
			stmt.setInt(3, Integer.parseInt(vo.getOp2()));
			stmt.setInt(4, vo.getResult());
			stmt.executeUpdate();
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
	
	public String calExcMsg(int excCode) {
		String excMsg = null;
		try {
			con = ds.getConnection(); //Ä¿³Ø¼Ç È¹µæ(ºô·Á¿À±â)
			String query = "select * from tb_excMsg where code=?";
			stmt = con.prepareStatement(query);
			stmt.setInt(1, excCode);
			rs = stmt.executeQuery();
			while (rs.next()) {
				excMsg = rs.getString("message").trim();	
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
	
	public String getMsgAddZeroExc() {
		return calExcMsg(181);
	}
	public String getMsgSubZeroExc() {
		return calExcMsg(182);
	}
	public String getMsgMulOneExc() {
		return calExcMsg(183);
	}
	public String getMsgDivOneExc() {
		return calExcMsg(184);
	}
}
