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
				"	originIP_sum.`value` DESC LIMIT 10";//��ȡ���ݿ���������10������
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
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
				"	originIP_sum.`value` DESC LIMIT 10";//��ȡ���ݿ���������10������
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql1);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			value = rs.getString(3);
			list.add(value);
			
		}
		conn.close();
		return list;
	}
	public long Total() throws SQLException,ClassNotFoundException {
		String sql2="SELECT sum(`value`) as origin_total FROM originIP_sum";//��ȡ���ݿ������������
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql2);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (total.next()) {
			values_total=total.getInt(1);
		}
		conn.close();
		return values_total;
	}
	public long Portion() throws SQLException,ClassNotFoundException {
		String sql3="select sum(value) as origin_portion from (select `value` from originIP_sum order by `value` desc limit 10) as A";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet total=stmt.executeQuery(sql3);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (total.next()) {
			values_portion=total.getInt(1);
		}
		conn.close();
		return values_portion;
	}
	
	
}