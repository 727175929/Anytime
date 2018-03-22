package zx31401425.zucc.anytime.RegiserActivity;

/**
 * Created by 赵轩 on 2018/3/22.
 */

public class Register {
    private String Uid;

    private String Upass;

    private String Uname;

    private String Uname_longitude;

    private String Uname_latitude;

    private String State;

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getUname() {
        return Uname;
    }

    public String getUpass() {
        return Upass;
    }

    public void setUname(String uname) {
        Uname = uname;
    }

    public void setUpass(String upass) {
        Upass = upass;
    }

    public String getUid() {
        return Uid;
    }

    public String getUname_longitude() {
        return Uname_longitude;
    }

    public String getUname_latitude() {
        return Uname_latitude;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public void setUname_longitude(String uname_longitude) {
        Uname_longitude = uname_longitude;
    }

    public void setUname_latitude(String uname_latitude) {
        Uname_latitude = uname_latitude;
    }
}
