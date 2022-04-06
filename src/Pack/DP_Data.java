package Pack;
public class DP_Data {
    String roomNumb, dept, sid, name, pNumb, sub,pDate,state;

    public DP_Data(String roomNumb,String dept,String sid,String name,String pNumb,String pDate,String sub,String state) {
        this.roomNumb=roomNumb;
        this.dept=dept;
        this.sid=sid;
        this.name=name;
        this.pNumb=pNumb;
        this.pDate=pDate;
        this.sub=sub;
        this.state=state;

    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSub() {
        return sub;
    }



    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getRoomNumb () {
        return roomNumb;
    }

    public void setRoomNumb (String roomNumb){
        this.roomNumb = roomNumb;
    }

    public String getDept () {
        return dept;
    }

    public void setDept (String dept){
        this.dept = dept;
    }

    public String getSid () {
        return sid;
    }

    public void setSid (String sid){
        this.sid = sid;
    }

    public String getName () {
        return name;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getpNumb () {
        return pNumb;
    }

    public void setpNumb (String pNumb){
        this.pNumb = pNumb;
    }

    public String getpDate () {
        return pDate;
    }

    public void setpDate(String pDate){
        this.pDate = pDate;
    }


    @Override
    public String toString() {
        return roomNumb+ dept+ sid+ name+ pNumb+ pDate+ sub;
    }




}
