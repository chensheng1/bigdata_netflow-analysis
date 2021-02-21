package business.terminal;

import java.sql.Date;

public class ChartTest {

	/**
	 * @Description:
	 * 
	 * @return void
	 * 
	 */
	public static void main(String[] args) {

	}

	private Date datetime;

	public ChartTest(Date datetime, Long main_log, Long main_conn,
			Long main_traff, Long resp_traff, Long orig_traff) {
		super();
		this.datetime = datetime;
		this.main_log = main_log;
		this.main_conn = main_conn;
		this.main_traff = main_traff;
		this.resp_traff = resp_traff;
		this.orig_traff = orig_traff;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public Long getMain_log() {
		return main_log;
	}

	public void setMain_log(Long main_log) {
		this.main_log = main_log;
	}

	public Long getMain_conn() {
		return main_conn;
	}

	public void setMain_conn(Long main_conn) {
		this.main_conn = main_conn;
	}

	public Long getMain_traff() {
		return main_traff;
	}

	public void setMain_traff(Long main_traff) {
		this.main_traff = main_traff;
	}

	public Long getResp_traff() {
		return resp_traff;
	}

	public void setResp_traff(Long resp_traff) {
		this.resp_traff = resp_traff;
	}

	public Long getOrig_traff() {
		return orig_traff;
	}

	public void setOrig_traff(Long orig_traff) {
		this.orig_traff = orig_traff;
	}

	private Long main_log;
	private Long main_conn;
	private Long main_traff;
	private Long resp_traff;
	private Long orig_traff;

}
