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
	
	//��ȡresp_flow_hour_sum���е��������Сʱ������
	public List<String> gethourValue() throws SQLException, ClassNotFoundException{
		String sql = "select value from resp_flow_hour_sum order by date desc limit 5";
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list =new ArrayList<String>();
		while (rs.next()) {
			list.add(rs.getString(1));
		}
		conn.close();
		Collections.reverse(list);
		//�Ե�λ���жϣ�
		List<String> list1=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			long l=Long.parseLong(list.get(i));
			long x=l/8192;
			values = String.valueOf(x);
			list1.add(values);
		}
		return list1 ;
	}
	
	//��ȡ����ǰ5������ݣ��뵱ǰ�������
	public List<String> getsecondValue() throws SQLException, ClassNotFoundException {
		String sql = "select value1,value2 from temp_resp";
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
		
		List<String> list2 =new ArrayList<String>();
		while (rs.next()) {
			list2.add(rs.getString(1));
			list2.add(rs.getString(2));
		}
		conn.close();
		return list2;
	}
	
	//��ȡip_flow_top_10���е����ݣ�ͳ��top10��Ŀ��IP
	public List<String> gettopIP() throws SQLException, ClassNotFoundException{
		String sql = "select ip from ip_flow_top_10";
		DBConfig cs=new DBConfig();
		String url =cs.getUrl();
		Connection conn = DriverManager.getConnection(url);
		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����
		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		List<String> list3 =new ArrayList<String>();
		while (rs.next()) {
			list3.add(rs.getString(1));
		}
		conn.close();
		return list3 ;
	}
	//��ȡip_flow_top_10���е����ݣ�ͳ��top10��Ŀ��IP��flow
		public List<String> gettopFlow() throws SQLException, ClassNotFoundException{
			String sql = "select flow from ip_flow_top_10";
			DBConfig cs=new DBConfig();
			String url =cs.getUrl();
			Connection conn = DriverManager.getConnection(url);
			Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
			// һ��Connection����һ�����ݿ�����
			// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
			List<String> list4 =new ArrayList<String>();
			while (rs.next()) {
				list4.add(rs.getString(1));
			}
			conn.close();
			return list4 ;
		}
}
