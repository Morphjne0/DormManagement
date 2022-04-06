package Pack;
public class RnP_Data {
	String roomNumb;
	String dept;
	String sid;
	String name;
	String pNumb;
	int pnt;

	public RnP_Data(String roomNumb, String dept, String sid, String name, String Pnumb, int pnt) {
		this.roomNumb = roomNumb;
		this.dept = dept;
		this.sid = sid;
		this.name = name;
		this.pNumb = Pnumb;
		this.pnt = pnt;

	}

	public String getPnum() {
		return pNumb;
	}

	public void setPnum(String pnum) {
		this.pNumb = pnum;
	}

	public String getRoomNumb() {
		return roomNumb;
	}

	public void setRoomNumb(String roomNumb) {
		this.roomNumb = roomNumb;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
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

	@Override
	public String toString() {
		return "RnPData [roomNumb=" + roomNumb + ", dept=" + dept + ", sid=" + sid + ", name=" + name + ", pNumb="
				+ pNumb + ", pnt=" + pnt + "]";
	}
}
