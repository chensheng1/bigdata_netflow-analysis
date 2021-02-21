package business.terminal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class login {
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection conn = null;
	public boolean test(String name,String password )  {
		boolean result=false;
		
		String sql = "select * from user where idcard=? and password=?";
		Connection con = new login_jdbc().getConn();
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if (rs.next()) {//验证成功
				result=true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	public static void main(String[] args) {
		login cs = new login();
		boolean s=cs.test("cs","1234564");
		System.out.println(s);
	}
}
