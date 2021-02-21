package business.terminal;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class resp_ip {
	public String values="";
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		resp_ip cs = new resp_ip();
		List<String> list1=cs.gethourValue();
		List<String> list2=cs.getsecondValue();
		List<String> list3=cs.gettopIP();
		List<String> list4=cs.gettopFlow();
		System.out.println(list1);
		System.out.println(list2);
		System.out.println(list3);
		System.out.println(list4);
		}
	
	//读取resp_flow_hour_sum表中的最新五个小时的数据
	public List<String> gethourValue() throws SQLException, ClassNotFoundException{
		String sql = "select value from resp_flow_hour_sum order by date desc limit 5";
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		conn.close();
		Collections.reverse(list);
		//对单位的判断！
		List<String> list1=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			long l=Long.parseLong(list.get(i));
			long x=l/8192;
			values = String.valueOf(x);
			list1.add(values);
		}
		return list1 ;
	}
	
	//读取表中前5秒的数据，与当前数据相减
	public List<String> getsecondValue() throws SQLException, ClassNotFoundException {
		String sql = "select value1,value2 from temp_resp";
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
		
		List<String> list2 =new ArrayList<String>();
		while (rs.next()) {
			list2.add(rs.getString(1));
			list2.add(rs.getString(2));
		}
		conn.close();
		return list2;
	}
	
	//读取ip_flow_top_10表中的数据，统计top10的目标IP
	public List<String> gettopIP() throws SQLException, ClassNotFoundException{
		String sql = "select ip from ip_flow_top_10";
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
		// 一个Connection代表一个数据库连接
		// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
		List<String> list3 =new ArrayList<String>();
		while (rs.next()) {
			list3.add(rs.getString(1));
		}
		conn.close();
		return list3 ;
	}
	//读取ip_flow_top_10表中的数据，统计top10的目标IP的flow
		public List<String> gettopFlow() throws SQLException, ClassNotFoundException{
			String sql = "select flow from ip_flow_top_10";
			DBConfig cs=new DBConfig();
			String url =cs.getUrl();
			Connection conn = DriverManager.getConnection(url);
			Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
			// 一个Connection代表一个数据库连接
			// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
			List<String> list4 =new ArrayList<String>();
			while (rs.next()) {
				list4.add(rs.getString(1));
			}
			conn.close();
			return list4 ;
		}
}
