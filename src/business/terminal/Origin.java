package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Origin {
	public String value="";
	public String values="";
	public static void main(String[] args) throws Exception {
		Origin cs = new Origin();
		List<String> s=cs.test();
		List<String> c=cs.originIP();
		System.out.println(s);
		System.out.println(c);
		
	}

	public List<String> test() throws SQLException, ClassNotFoundException {
		String sql = "select value1,value2 from temp_origin";
		// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
		// 避免中文乱码要指定useUnicode和characterEncoding
		// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
		// 下面语句之前就要先创建javademo数据库
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
	//	String url = "jdbc:mysql://localhost:3306/hubu?"
	//			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
			list.add(rs.getString(2));
		}

		conn.close();

		return list;

	}
	
	public List<String> originIP() throws SQLException, ClassNotFoundException {
		String sql = "select value from originIP order by date desc limit 5";
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接

		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		
		List<String> list2 =new ArrayList<String>();
		while (rs.next()) {
			value=rs.getString(1);
			list2.add(value);
		}
		conn.close();
		Collections.reverse(list2);
		//对单位的判断！
		List<String> list3=new ArrayList<String>();
		for(int i=0;i<list2.size();i++){
			long l=Long.parseLong(list2.get(i));
			long x=l/8388608;
			values = String.valueOf(x);
			list3.add(values);
		}
      	
		return list3;

	}
	 
	 
}