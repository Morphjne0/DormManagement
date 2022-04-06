package Pack;
public class ON_Data {
    String roomNumb, dept, sid, name,pNumb, ddate, desti,rdate,temp,ret,trans;



    public ON_Data(String roomNumb, String dept, String sid, String name, String Pnumb, String ddate, String desti, String rdate, String temp, String ret, String trans) {
        this.roomNumb=roomNumb;

        this.dept=dept;
        this.sid=sid;
        this.name=name;
        this.pNumb=Pnumb;
        this.ddate=ddate;
        this.desti=desti;
        this.rdate=rdate;
        this.ret=ret;
        this.temp=temp;
        this.trans=trans;

    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }
    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
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



    public String getDdate() {
        return ddate;
    }



    public void setDdate(String ddate) {
        this.ddate = ddate;
    }



    public String getDesti() {
        return desti;
    }



    public void setDesti(String desti) {
        this.desti = desti;
    }



    public String getRdate() {
        return rdate;
    }



    public void setRdate(String rdate) {
        this.rdate = rdate;
    }



    public String getRet() {
        return ret;
    }



    public void setRet(String ret) {
        this.ret = ret;
    }



    @Override
    public String toString() {
        return "Datall [roomNumb=" + roomNumb + ", dept=" + dept + ", sid=" + sid + ", name=" + name + ", pnum=" + pNumb
                + ", ddate=" + ddate + ", desti=" + desti + ", rdate=" + rdate + ", ret=" + ret + "]";
    }






}
