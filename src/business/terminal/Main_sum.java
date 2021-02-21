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
		 * ��ȡ��ǰ���ڵ�ǰ����֮����������ڵ��ַ�����
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
		 * ����Mainsum_conn()��
		 *  �������ݿ⣬������ȡһ���������,����һ��list1�С�
		 */
		List<String> list1=currentdate();
		List<String> list2 =new ArrayList<String>();//�����е����ݶ�����һ��������
		for(int i=0;i<list1.size();i++){       
				String sql1 = "select sum(main_log),sum(main_conn),sum(main_traff)  from main where date like "+"'"+list1.get(i)+"%"+"'";
				// MySQL��JDBC URL��д��ʽ��jdbc:mysql://�������ƣ����Ӷ˿�/���ݿ������?����=ֵ
				// ������������Ҫָ��useUnicode��characterEncoding
				// ִ�����ݿ����֮ǰҪ�����ݿ����ϵͳ�ϴ���һ�����ݿ⣬�����Լ�����
				// �������֮ǰ��Ҫ�ȴ���javademo���ݿ�
				DBConfig cs=new DBConfig();//ʹ�ú����������ݿ�
				String url =cs.getUrl();
				Connection conn = DriverManager.getConnection(url);
				Class.forName("com.mysql.jdbc.Driver");// ��̬����mysql����
				// һ��Connection����һ�����ݿ�����
				// Statement������кܶ෽��������executeUpdate����ʵ�ֲ��룬���º�ɾ����
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql1);// executeQuery�᷵�ؽ���ļ��ϣ����򷵻ؿ�ֵ
				
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
		 * ��Mainsum()�����ж�ȡ���������������һ�������С�
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
		 * ����Main_conn()��
		 *  ��Mainsum()�����ж�ȡ�������������һ��������
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
		 * ����Main_conn()��
		 *  ��Mainsum()�����ж�ȡ�����log�ļ���С����һ��������
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
