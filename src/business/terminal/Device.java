package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Device {
	public String nonHandheldDeviceConn = "";
	public String handheldDeviceConn = "";

	public static void main(String[] args) throws Exception {
		Device dataTest = new Device();
		String s = dataTest.handheldDevice();
		System.out.println(s);
	}

	private String getDate() {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		return matter1.format(dt);
	}

	public String nonHandheldDevice() throws SQLException,
			ClassNotFoundException {
		String today_date = getDate();
		String sql = "select sum(nonHandheldDeviceC) from device_c where date like '"
				+ today_date + "%'";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url = "jdbc:mysql://localhost:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		while (rs.next()) {
			nonHandheldDeviceConn = rs.getString(1);
		}

		conn.close();

		return nonHandheldDeviceConn;

	}

	public String handheldDevice() throws SQLException, ClassNotFoundException {
		String today_date = getDate();
		String sql = "select sum(handheldDeviceC) from device_c where date like '"
				+ today_date + "%'";
		String url = "jdbc:mysql://localhost:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		while (rs.next()) {
			handheldDeviceConn = rs.getString(1);
		}

		conn.close();

		return handheldDeviceConn;

	}
}