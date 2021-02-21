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
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		String url ="jdbc:mysql://192.168.1.62:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		
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
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		String url ="jdbc:mysql://192.168.1.62:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		
		List<String> values=new ArrayList<String>();
		while (rs.next()) {
			value=rs.getString(2);
			values.add(value);
		}
		
		
		conn.close();

		return values;
		

	}
	
}