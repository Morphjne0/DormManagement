package Pack;
public class Data {
    String roomNumb, dept, sid, name, pNumb, gender;

    public Data(String roomNumb,String dept,String sid,String name,String pNumb,String gender) {
        this.roomNumb=roomNumb;
        this.dept=dept;
        this.sid=sid;
        this.name=name;
        this.pNumb=pNumb;
        this.gender=gender;
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

        public String getGender () {
            return gender;
        }

        public void setGender (String gender){
            this.gender = gender;
        }

    @Override
    public String toString() {
        return roomNumb+ dept+ sid+ name+ pNumb+ gender;
    }

}

