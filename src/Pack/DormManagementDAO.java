package Pack;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DormManagementDAO {
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	DormManager DM;
	StdMenu stdMenu;
	Login login;
	Data data;
	String nameValue, sidValue, deptValue, rnValue, genderValue, pnValue, enValue, milValue, chValue, rlgnValue,
			addrValue, rDateValue, transValue, tempValue, rtrnValue, pDateValue, subValue;
	String solveValue, state, temp, trans, name, sid, dept, roomNumb, pNumb, eNumb, addr, gender, mil, chis, rlgn, pic,
			pDate, com, ddate, dest, rdate, rtrn, sub, rsn, date;
	int pnt, sum;
	static DormManagementDAO instance = null;

	String url = "jdbc:mysql://localhost:3306/Dormdb?serverTimezone=Asia/Seoul";
	String id = "root";
	String pwd = "1234";

	public DormManagementDAO() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩됨/requestRead");
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 완료/requestWrite");
			stmt = conn.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Data> getList() {
		ArrayList<Data> Datalist = new ArrayList<Data>();
		System.out.println("1111");
		try {
			System.out.println("1111");
			String sql = "select * from studentTBl";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

				Data d = new Data(roomNumb, dept, sid, name, pNumb, gender);
				Datalist.add(d);
			}
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}

		return Datalist;
	}

	public ArrayList<DP_Data> getDPList() {
		ArrayList<DP_Data> DP_Datalist = new ArrayList<DP_Data>();
		System.out.println("1111");
		try {
			System.out.println("1111");
			String sql = "select * from DPTbL";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pDate = rs.getString("P_date");
				com = rs.getString("com");
				sub = rs.getString("com_sub");
				state = rs.getString("state");

				DP_Data e = new DP_Data(roomNumb, dept, sid, name, pNumb, sub, pDate, state);
				DP_Datalist.add(e);

			}

		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}

		return DP_Datalist;
	}

	void ONReturn() {
		try {
			String sql = "update OnTBL set rDate='" + rDateValue + "', trans='" + transValue + "' , temp='" + tempValue
					+ "' , ret ='" + rtrnValue + "'  where s_name='" + nameValue + "'  and s_id = '" + sidValue + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
	}

	public ArrayList<ON_Data> getONList() {
		ArrayList<ON_Data> ON_Datalist = new ArrayList<ON_Data>();
		System.out.println("1111");
		try {
			System.out.println("1111");
			String sql = "select * from OnTbL";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				ddate = rs.getString("DDate");
				dest = rs.getString("desti");
				rdate = rs.getString("RDate");
				temp = rs.getString("temp");
				rtrn = rs.getString("Ret");
				trans = rs.getString("trans");

				ON_Data on_data = new ON_Data(roomNumb, dept, sid, name, pNumb, ddate, dest, rdate, temp, rtrn, trans);
				ON_Datalist.add(on_data);
			}
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}

		return ON_Datalist;
	}

	void ONInsert() {

	}

	public ArrayList<RnP_Data> getRnP_List() {
		ArrayList<RnP_Data> RnP_DataList = new ArrayList<RnP_Data>();
		try {
			String sql = "select s_name,s_id,dept,r_number,p_number,pnt from studenttbl";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pnt = rs.getInt("pnt");

				RnP_Data r = new RnP_Data(roomNumb, dept, sid, name, pNumb, pnt);
				RnP_DataList.add(r);
			}
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
		return RnP_DataList;
	}

	public ArrayList<RnP_Log> getRnP_LogList() {
		ArrayList<RnP_Log> RnP_LogList = new ArrayList<RnP_Log>();
		try {
			String sql = "select * from RnPLogTbl where s_name = '" + nameValue + "' and s_id = '" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				rsn = rs.getString("rsn");
				pnt = rs.getInt("pnt");
				date = rs.getString("dat");

				RnP_Log log = new RnP_Log(sid, name, pnt, rsn, date);
				RnP_LogList.add(log);
			}
		} catch (SQLException e) {
			System.out.println("DB 연결 오류");
			e.printStackTrace();
		}
		return RnP_LogList;
	}

	// 애로사항테이블 이름검색
	public ArrayList<DP_Data> getDP_NameSearch() {
		ArrayList<DP_Data> DPNList = new ArrayList<DP_Data>();
		try {
			String sql = "select * from DPTbL where s_name ='" + nameValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pDate = rs.getString("P_date");
				com = rs.getString("com");
				sub = rs.getString("com_sub");

				DP_Data e = new DP_Data(roomNumb, dept, sid, name, pNumb, sub, pDate, state);
				DPNList.add(e);

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DPNList;
	}

	public ArrayList<DP_Data> getDP_SidSearch() {
		ArrayList<DP_Data> DPSList = new ArrayList<DP_Data>();
		try {
			String sql = "select * from DPTbL where s_id ='" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pDate = rs.getString("P_date");
				com = rs.getString("com");
				sub = rs.getString("com_sub");

				DP_Data e = new DP_Data(roomNumb, dept, sid, name, pNumb, sub, pDate, state);
				DPSList.add(e);

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DPSList;
	}

	public ArrayList<DP_Data> getDP_PNSearch() {
		ArrayList<DP_Data> DPPNList = new ArrayList<DP_Data>();
		try {
			String sql = "select * from DPTbL where p_Number ='" + pnValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pDate = rs.getString("P_date");
				com = rs.getString("com");
				sub = rs.getString("com_sub");

				DP_Data e = new DP_Data(roomNumb, dept, sid, name, pNumb, sub, pDate, state);
				DPPNList.add(e);

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DPPNList;
	}

	public ArrayList<DP_Data> getDP_solveSearch() {
		ArrayList<DP_Data> DPrList = new ArrayList<DP_Data>();
		try {
			String sql = "select * from DPTbL where state ='" + solveValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pDate = rs.getString("P_date");
				com = rs.getString("com");
				sub = rs.getString("com_sub");

				DP_Data e = new DP_Data(roomNumb, dept, sid, name, pNumb, sub, pDate, state);
				DPrList.add(e);

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DPrList;
	}

	// 벌점테이블 이름검색
	public ArrayList<RnP_Data> getRnP_NameSearch() {
		ArrayList pntNList = new ArrayList();
		try {
			String sql = "select * from studenttbl where s_name = '" + nameValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pnt = rs.getInt("pnt");

				RnP_Data r = new RnP_Data(roomNumb, dept, sid, name, pNumb, pnt);
				pntNList.add(r);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return pntNList;
	}

	public ArrayList<RnP_Data> getRnP_SidSearch() {
		ArrayList pntSList = new ArrayList();
		try {
			String sql = "select * from studenttbl where s_name = '" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pnt = rs.getInt("pnt");

				RnP_Data r = new RnP_Data(roomNumb, dept, sid, name, pNumb, pnt);
				pntSList.add(r);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return pntSList;
	}

	public ArrayList<RnP_Data> getRnP_PNSearch() {
		ArrayList pntPNList = new ArrayList();
		try {
			String sql = "select * from studenttbl where p_Number = '" + pnValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				pnt = rs.getInt("pnt");

				RnP_Data r = new RnP_Data(roomNumb, dept, sid, name, pNumb, pnt);
				pntPNList.add(r);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return pntPNList;
	}

	// 외박테이블 이름검색
	public ArrayList<ON_Data> getON_NameSearch() {
		ArrayList<ON_Data> ONNList = new ArrayList<ON_Data>();
		try {
			String sql = "select * from OnTbL where s_name ='" + nameValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				ddate = rs.getString("DDate");
				dest = rs.getString("desti");
				rdate = rs.getString("RDate");
				trans = rs.getString("trans");
				temp = rs.getString("temp");
				rtrn = rs.getString("Ret");

				ON_Data on_data = new ON_Data(roomNumb, dept, sid, name, pNumb, ddate, dest, rdate, trans, temp, rtrn);
				ONNList.add(on_data);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return ONNList;
	}

	public ArrayList<ON_Data> getON_SidSearch() {
		ArrayList<ON_Data> ONSList = new ArrayList<ON_Data>();
		try {
			String sql = "select * from OnTbL where s_id ='" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				ddate = rs.getString("DDate");
				dest = rs.getString("desti");
				rdate = rs.getString("RDate");
				trans = rs.getString("trans");
				temp = rs.getString("temp");
				rtrn = rs.getString("Ret");

				ON_Data on_data = new ON_Data(roomNumb, dept, sid, name, pNumb, ddate, dest, rdate, trans, temp, rtrn);
				ONSList.add(on_data);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return ONSList;
	}

	public ArrayList<ON_Data> getON_returnSearch() {
		ArrayList<ON_Data> ONRList = new ArrayList<ON_Data>();
		try {
			String sql = "select * from OnTbL where ret ='" + rtrnValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				ddate = rs.getString("DDate");
				dest = rs.getString("desti");
				rdate = rs.getString("RDate");
				trans = rs.getString("trans");
				temp = rs.getString("temp");
				rtrn = rs.getString("Ret");

				ON_Data on_data = new ON_Data(roomNumb, dept, sid, name, pNumb, ddate, dest, rdate, temp, rtrn, trans);
				ONRList.add(on_data);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return ONRList;
	}

	public ArrayList<ON_Data> getON_PNSearch() {
		ArrayList<ON_Data> ONPNList = new ArrayList<ON_Data>();
		try {
			String sql = "select * from OnTbL where p_number ='" + pnValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				ddate = rs.getString("DDate");
				dest = rs.getString("desti");
				rdate = rs.getString("RDate");
				trans = rs.getString("trans");
				temp = rs.getString("temp");
				rtrn = rs.getString("Ret");

				ON_Data on_data = new ON_Data(roomNumb, dept, sid, name, pNumb, ddate, dest, rdate, trans, temp, rtrn);
				ONPNList.add(on_data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ONPNList;
	}

	// 현재원관리테이블 이름검색
	public ArrayList<Data> getMNG_NameSearch() {
		ArrayList<Data> DataNlist = new ArrayList<Data>();
		try {
			String sql = "select * from studentTBl where s_name = '" + nameValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

				Data d = new Data(roomNumb, dept, sid, name, pNumb, gender);
				DataNlist.add(d);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DataNlist;
	}

	public ArrayList<Data> getMNG_SidSearch() {
		ArrayList<Data> DataSlist = new ArrayList<Data>();
		try {
			String sql = "select * from studentTBl where s_id = '" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

				Data d = new Data(roomNumb, dept, sid, name, pNumb, gender);
				DataSlist.add(d);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DataSlist;
	}

	public ArrayList<Data> getMNG_PNSearch() {
		ArrayList<Data> DataPlist = new ArrayList<Data>();
		try {
			String sql = "select * from studentTBl where p_Number = '" + pnValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

				Data d = new Data(roomNumb, dept, sid, name, pNumb, gender);
				DataPlist.add(d);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DataPlist;
	}

	public ArrayList<Data> getMNG_DeptSearch() {
		ArrayList<Data> DataDlist = new ArrayList<Data>();
		try {
			String sql = "select * from studentTBl where dept = '" + deptValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

				Data d = new Data(roomNumb, dept, sid, name, pNumb, gender);
				DataDlist.add(d);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return DataDlist;
	}

	void DPSolve() {
		try {
			String sql = "update DPTbl set state='해결'  where s_name='" + nameValue + "'  and s_id = '" + sidValue
					+ "' and com_sub='" + subValue + "' and P_date ='" + pDateValue + "' ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void MngAdd() {
		try {
			String sql = "\n" + "insert into studentTbl values ('" + nameValue + "', '" + sidValue + "', '" + deptValue
					+ "','" + genderValue + "', '" + milValue + "', '" + pnValue + "','" + addrValue + "','" + rnValue
					+ "', '" + enValue + "','" + chValue + "','" + rlgnValue + "','" + pic + "',0);";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void MngEdit() {
		try {
			String sql = "update studenttbl set s_name='" + nameValue + "' , s_id ='" + sidValue + "', dept='"
					+ deptValue + "', gender='" + genderValue + "', mil='" + milValue + "', P_number='" + pnValue
					+ "', addr='" + addrValue + "', r_Number='" + rnValue + "', e_Number='" + enValue + "', C_History='"
					+ chValue + "', Religion='" + rlgnValue + "', pic='" + pic + "' where s_name='" + name
					+ "'and s_id = '" + sid + "' ";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void MngDelete() {
		try {
			String sql = "delete from studenttbl  where s_name='" + nameValue + "'and s_id = '" + sidValue + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void RnPInfo() {
		try {
			String sql = "select * from studenttbl where s_name = '" + nameValue + "' and s_id = '" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				name = rs.getString("s_name");
				sid = rs.getString("s_ID");
				pNumb = rs.getString("P_number");
				pic = rs.getString("pic");
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	public ArrayList getPntList() {
		ArrayList pntList = new ArrayList();
		try {
			String sql = "select pnt from rnplogtbl where s_name = '" + nameValue + "' and s_id = '" + sidValue + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pnt = rs.getInt("pnt");
				pntList.add(pnt);
			}
			sum = (int) pntList.get(0);
			for (int i = 1; i < pntList.size(); i++) {
				sum = sum + (int) pntList.get(i);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return pntList;
	}

	void RnPUpdate() {
		try {
			String sql = "update studenttbl set pnt='" + sum + "' where s_name='" + nameValue + "'  and s_id = '"
					+ sidValue + "'";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void RnPAdd() {
		try {
			String sql = "insert into RnplogTbL " + "values ('0','" + nameValue + "','" + sidValue + "' ," + pnt + ",'"
					+ rsn + "','" + date + "')";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void DPRequest() {
		try {
			String sql = "insert into DpTbL values ('0','" + nameValue + "','" + sidValue + "' ,'" + deptValue + "','"
					+ rnValue + "','" + pnValue + "','" + pDateValue + "','" + com + "','" + subValue + "','진행중')";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void OnRequest() {
		try {
			String sql = "insert into ONTbL values ('0','" + nameValue + "','" + sidValue + "' ,'" + dept + "','"
					+ roomNumb + "','" + pNumb + "','" + ddate + "','','','','','X')";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	void DormManage() {
		try {
			String sql = "select r_Number,dept,s_ID,s_name,P_number,gender from studentTBl";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				roomNumb = rs.getString("r_Number");
				dept = rs.getString("dept");
				sid = rs.getString("s_iD");
				name = rs.getString("s_name");
				pNumb = rs.getString("P_number");
				gender = rs.getString("gender");

			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void Picture() {
		try {
			String sql = "select pic from studentTBL where s_name = '" + id + "' and s_id = '" + sid + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pic = rs.getString("pic");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	void Inspect() {
		try {
			String sql = "select * from studentTBL where s_name ='" + name + "' and s_id = '" + sid + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString("s_name");
				sid = rs.getString("s_ID");
				dept = rs.getString("dept");
				roomNumb = rs.getString("r_Number");
				pNumb = rs.getString("P_Number");
				eNumb = rs.getString("E_Number");
				addr = rs.getString("addr");
				gender = rs.getString("gender");
				mil = rs.getString("mil");
				chis = rs.getString("C_history");
				rlgn = rs.getString("Religion");
				pic = rs.getString("pic");
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	void StdManagement() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("=========================================================");
			System.out.println("드라이버 로딩됨");

		} catch (ClassNotFoundException e) {
			System.out.println("=========================================================");
			System.out.println("드라이버 로딩 실패");

			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("=========================================================");
			System.out.println("DB 연결 완료");

			String sql = "select * from stdtbl";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				// db내용 불러오기작성
			}

		} catch (SQLException e) {
			System.out.println("=========================================================");
			System.out.println("DB 연결 오류");

			e.printStackTrace();
		}
	}

	void requestWrite() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩됨/requestWrite");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패/requestWrite");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 완료/requestWrite");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류/requestWrite");
			e.printStackTrace();
		}
	}

	void requestManage() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 로딩됨/requestRead");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패/requestRead");
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, id, pwd);
			System.out.println("DB 연결 완료/requestWrite");
		} catch (SQLException e) {
			System.out.println("드라이버 로딩 실패/requestRead");
			e.printStackTrace();
		}
	}

	boolean login(String id, String pw) {
		try {
			String sql = "select s_name, s_id from studentTBl where s_name = '" + id + "' and s_id = '" + pw + "'";

			rs = stmt.executeQuery(sql);
			rs.next();
			if (rs.getString("s_name").equals(null)) {
				return false;
			} else {
				return true;
			}

//                if (name&&sid != null) {
//                    CenterP.LoginBtn.setVisible(false);
//                    DM.stdMenu.setVisible(true);
//                }else{
//                JOptionPane.showMessageDialog(CenterP, "로그인 실패", "경고", JOptionPane.WARNING_MESSAGE);
//            }

		} catch (SQLException e) {
			System.out.println("드라이/버 로딩 실패/requestRead");
			e.printStackTrace();
			return false;
		}
	}

	void disconnect() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("=========================================================");
		System.out.println("DB 연결 해제");
	}

	static DormManagementDAO getInstance() throws NullPointerException {
		if (instance == null) {
			instance = new DormManagementDAO();
		}
		return instance;
	}

}
