package Pack;
public class RnP_Log {

	String sid;
	String name;
	int pnt;
	String rsn;

	String date;

	public RnP_Log(String sid, String name, int pnt, String rsn, String date) {
		this.sid = sid;
		this.name = name;
		this.pnt = pnt;
		this.rsn = rsn;
		this.date = date;

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPnt() {
		return pnt;
	}

	public void setPnt(int pnt) {
		this.pnt = pnt;
	}

	public String getRsn() {
		return rsn;
	}

	public void setRsn(String rsn) {
		this.rsn = rsn;
	}

	@Override
	public String toString() {
		return "RnPLog [sid=" + sid + ", name=" + name + ", pNumb=" + ", pnt=" + pnt + ", rsn=" + rsn + "]";
	}
}
