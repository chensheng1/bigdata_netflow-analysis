package business.terminal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Device {
	public String nonHandheldDeviceConn = "";
	public String handheldDeviceConn = "";

	public static void main(String[] args) throws Exception {
		Device dataTest = new Device();
		String s = dataTest.handheldDevice();
		System.out.println(s);
	}

	private String getDate() {
		Date dt = new Date();
		SimpleDateFormat matter1 = new SimpleDateFormat("yyyy-MM-dd");
		return matter1.format(dt);
	}

	public String nonHandheldDevice() throws SQLException,
			ClassNotFoundException {
		String today_date = getDate();
		String sql = "select sum(nonHandheldDeviceC) from device_c where date like '"
				+ today_date + "%'";
		// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
		// ������������Ҫָ��useUnicode��characterEncoding
		// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
		// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
		String url = "jdbc:mysql://localhost:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			nonHandheldDeviceConn = rs.getString(1);
		}

		conn.close();

		return nonHandheldDeviceConn;

	}

	public String handheldDevice() throws SQLException, ClassNotFoundException {
		String today_date = getDate();
		String sql = "select sum(handheldDeviceC) from device_c where date like '"
				+ today_date + "%'";
		String url = "jdbc:mysql://localhost:3306/hubutraff?"
				+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";
		Connection conn = DriverManager.getConnection(url);

		Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
		// һ��Connection����һ�����ݿ�����

		// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
		while (rs.next()) {
			handheldDeviceConn = rs.getString(1);
		}

		conn.close();

		return handheldDeviceConn;

	}
}