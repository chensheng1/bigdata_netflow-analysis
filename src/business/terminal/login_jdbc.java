package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class login_jdbc{
               //public ConnectionDemo02 conn;
	public static final String DBDRIVER="com.mysql.jdbc.Driver";
	public static final String DBURL="jdbc:mysql://192.168.1.62:3306/hubutraff?";
	public static final String DBUSER="root";
	public static final String DBPASS="rootroot";
	public  Connection getConn(){
		Connection conn=null;
		try{
			Class.forName(DBDRIVER);
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		try{
			conn=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return conn;
	}
}