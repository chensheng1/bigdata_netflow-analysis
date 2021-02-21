package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Host {
	public String host= "";
	public String value="";
	
	public static void main(String[] args) throws Exception {
		Host cs = new Host();
		List<String> s=cs.host();
		System.out.println(s);
		List<String> c=cs.Values();
		System.out.println(c);
		
	}

	public List<String> host() throws SQLException,ClassNotFoundException {
		String sql = "select * from temp_host";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url ="jdbc:mysql://192.168.1.62:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			host = rs.getString(1);
			list.add(host);
			
		}
		
		
		conn.close();

		return list;
		

	}
	public List<String> Values() throws SQLException,ClassNotFoundException {
		String sql = "select * from temp_host";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		String url ="jdbc:mysql://192.168.1.62:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		
		List<String> values=new ArrayList<String>();
		while (rs.next()) {
			value=rs.getString(2);
			values.add(value);
		}
		
		
		conn.close();

		return values;
		

	}
	
}