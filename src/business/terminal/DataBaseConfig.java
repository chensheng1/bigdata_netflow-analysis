package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConfig {
	public String ss = "";

	public static void main(String[] args) throws Exception {
		DataBaseConfig dataTest = new DataBaseConfig();
		String s = dataTest.test();
		System.out.println(s);
	}

	public String test() throws SQLException, ClassNotFoundException {
		String sql = "select * from device";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		String url = "jdbc:mysql://localhost:3306/hubu?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			ss = rs.getString(2);
		}

		conn.close();

		return ss;

	}
}