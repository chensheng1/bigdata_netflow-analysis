package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OriginIPtop {
	public String value= "";
	public long values_total;
	public long values_portion;
	public static void main(String[] args) throws Exception {
		OriginIPtop cs = new OriginIPtop();
		List<String> c=cs.sum();
		List<String> s=cs.Origin_IP();
		System.out.println(s);
		System.out.println(c);
		
	}
	public List<String> sum() throws ClassNotFoundException, SQLException{
		OriginIPtop cs = new OriginIPtop();
		List<String> c=cs.Origin_Values();
		long ip_value=cs.Total()-cs.Portion();
		String value_string=String.valueOf(ip_value);
		c.add(value_string);
		return c;
	}

	public List<String> Origin_IP() throws SQLException,ClassNotFoundException {
		String sql1 = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	originIP_sum\n" +
				"ORDER BY\n" +
				"	originIP_sum.`value` DESC LIMIT 10";//读取数据库里面最大的10条数据
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			value = rs.getString(1);
			list.add(value);
			
		}
		conn.close();
		list.add("else");
		return list;
	}
	public List<String> Origin_Values() throws SQLException,ClassNotFoundException {
		String sql1 = "SELECT\n" +
				"	*\n" +
				"FROM\n" +
				"	originIP_sum\n" +
				"ORDER BY\n" +
				"	originIP_sum.`value` DESC LIMIT 10";//读取数据库里面最大的10条数据
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			value = rs.getString(3);
			list.add(value);
			
		}
		conn.close();
		return list;
	}
	public long Total() throws SQLException,ClassNotFoundException {
		String sql2="SELECT sum(`value`) as origin_total FROM originIP_sum";//读取数据库里面的总数据
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql2);// executeQuery会返回结果的集合，否则返回空值
		while (total.next()) {
			values_total=total.getInt(1);
		}
		conn.close();
		return values_total;
	}
	public long Portion() throws SQLException,ClassNotFoundException {
		String sql3="select sum(value) as origin_portion from (select `value` from originIP_sum order by `value` desc limit 10) as A";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql3);// executeQuery会返回结果的集合，否则返回空值
		while (total.next()) {
			values_portion=total.getInt(1);
		}
		conn.close();
		return values_portion;
	}
	
	
}