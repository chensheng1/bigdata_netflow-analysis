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
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
	//	String url = "jdbc:mysql://localhost:3306/hubu?"
	//			+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		
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

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		
		List<String> list2 =new ArrayList<String>();
		while (rs.next()) {
			value=rs.getString(1);
			list2.add(value);
		}
		conn.close();
		Collections.reverse(list2);
		//�Ե�λ���жϣ�
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