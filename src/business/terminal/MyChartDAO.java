package business.terminal;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

public class MyChartDAO {

	/**
	 * @Description:
	 * 
	 * @return void
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		MyChartDAO mych = new MyChartDAO();
		List test = mych.readFirstTitle();
		ChartTest ct = (ChartTest) test.get(2);
		System.out.println(ct.getDatetime());
		System.out.println(ct.getMain_log());
		System.out.println(ct.getMain_conn());
		System.out.println(ct.getMain_traff());
		System.out.println(ct.getOrig_traff());
		System.out.println(ct.getResp_traff());

	}

	@SuppressWarnings("rawtypes")
	public List readFirstTitle() {
		List<ChartTest> list = new ArrayList<ChartTest>();
		Connection con = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			con = (Connection) DriverManager
					.getConnection("jdbc:mysql://192.168.1.62:3306/hubutraff?"
							+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8");
			String sql = "select * from main";
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Date dateTime = rs.getDate("date");
				Long mainLog = rs.getLong("main_log");
				Long mainConn = rs.getLong("main_conn");
				Long mainTraff = rs.getLong("main_traff");
				Long respTraff = rs.getLong("resp_traff");
				Long origTraff = rs.getLong("orig_traff");
				ChartTest tl = new ChartTest(dateTime, mainLog, mainConn,
						mainTraff, respTraff, origTraff);
				list.add(tl);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}

				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
