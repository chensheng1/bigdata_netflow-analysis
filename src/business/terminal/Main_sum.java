package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public class Main_sum {
	
	
	public static void main(String[] args) throws Exception {
		Main_sum cs = new Main_sum();
		List<String> time=cs.currentdate();
		List<String> c=cs.Mainsum();
		List<String> conn=cs.Mainsum_conn();
		List<String> traff=cs.Mainsum_traff();
		List<String> log=cs.Mainsum_log();
		System.out.println(time);
		System.out.println(c);
		System.out.println(conn);
		System.out.println(traff);
		System.out.println(log);
	}
	
	public List<String> currentdate(){
		/*
		 * 获取当前日期到前五天之间的五天日期的字符串。
		 */
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        List<String> datetime=new ArrayList<String>();
        for (int i=0;i<2;i++) {  
        	 datetime.add(sdf.format(new Date(d.getTime() - i * 24 * 60 * 60 * 1000)));
        }  
        Collections.reverse(datetime);
        return datetime;
	}
	
	public List<String> Mainsum() throws SQLException,ClassNotFoundException {
		/*
		 * 函数Mainsum_conn()：
		 *  访问数据库，从中提取一天的连接数,放在一个list1中。
		 */
		List<String> list1=currentdate();
		List<String> list2 =new ArrayList<String>();//将所有的数据都存入一个数组中
		for(int i=0;i<list1.size();i++){       
				String sql1 = "select sum(main_log),sum(main_conn),sum(main_traff)  from main where date like "+"'"+list1.get(i)+"%"+"'";
				// MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
				// 避免中文乱码要指定useUnicode和characterEncoding
				// 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
				// 下面语句之前就要先创建javademo数据库
				DBConfig cs=new DBConfig();//使用函数调用数据库
				String url =cs.getUrl();
				Connection conn = DriverManager.getConnection(url);
				Class.forName("com.mysql.jdbc.Driver");// 动态加载mysql驱动
				// 一个Connection代表一个数据库连接
				// Statement里面带有很多方法，比如executeUpdate可以实现插入，更新和删除等
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);// executeQuery会返回结果的集合，否则返回空值
				
				while (rs.next()) {
					list2.add(rs.getString(1));
					list2.add(rs.getString(2));
					list2.add(rs.getString(3));
				}
				conn.close();
		}
		Collections.reverse(list2);
		return list2;
	}
	
	public List<String> Mainsum_conn() throws ClassNotFoundException, SQLException{
		/*
		 * 从Mainsum()函数中读取五天的连接数放在一个数组中。
		 */
			List<String> conn=Mainsum();
			List<String> connlist =new ArrayList<String>();
			for(int i=1;i<conn.size();i=i+3){
				long l=Long.parseLong(conn.get(i));
				long x=l/100;
				String values = String.valueOf(x);
				connlist.add(values);
			}
			return connlist;
	}
	
	public List<String> Mainsum_traff() throws SQLException,ClassNotFoundException {
		/*
		 * 函数Main_conn()：
		 *  从Mainsum()函数中读取五天的流量放在一个数组中
		 */
		List<String> traff=Mainsum();
		List<String> trafflist =new ArrayList<String>();
		for(int i=0;i<traff.size();i=i+3){
			long l=Long.parseLong(traff.get(i));
			long x=l/8192;
			String values = String.valueOf(x);
			trafflist.add(values);
		}
		return trafflist;
	}
	
	public List<String> Mainsum_log() throws SQLException,ClassNotFoundException {
		/*
		 * 函数Main_conn()：
		 *  从Mainsum()函数中读取五天的log文件大小放在一个数组中
		 */
		List<String> log=Mainsum();
		List<String> loglist =new ArrayList<String>();
		for(int i=2;i<log.size();i=i+3){
			long l=Long.parseLong(log.get(i));
			long x=l/1024;
			String values = String.valueOf(x);
			loglist.add(values);
		}
		
		return loglist;
	}
	
}
