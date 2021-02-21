package business.terminal;

public class DBConfig {
	String url = "jdbc:mysql://192.168.1.62:3306/hubutraff?"
			+ "user=root&password=rootroot&useUnicode=true&characterEncoding=UTF8";

	public String getUrl() {
		return url;
	}
}
