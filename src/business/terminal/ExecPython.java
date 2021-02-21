package business.terminal;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 
 * 
 * @ClassName: ExecPython
 * 
 * @Description: ִ��python�������
 * 
 * @author Lucas szkwyh@live.com
 * 
 * @date 2017-1-16 ����9:55:02
 * 
 * 
 */
public class ExecPython {
	private String results;

	public void execsavefig(String filename) {
		try {
			Runtime.getRuntime().exec(filename);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @Description: ִ��python����
	 * 
	 * @return void
	 * 
	 */
	public void exec(String filename) {
		try {
			Process pr = Runtime.getRuntime().exec(filename);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					pr.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				results = results + line;
			}
			in.close();
			pr.waitFor();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 
	 * @Description:getResults
	 * 
	 * @return String
	 * 
	 */
	public String getResults() {
		return results;
	}

	/**
	 * 
	 * @Description: setResults
	 * 
	 * @return void
	 * 
	 */
	public void setResults(String results) {
		this.results = results;
	}

}
