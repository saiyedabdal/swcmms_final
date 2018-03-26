package com.example.smahsanabdal.finalfirstlevel;

/**
 * Created by smahsanabdal on 26/03/18.
 */

public class User_Data {
    String uid,type;
    User_Data(){}
    User_Data(String a, String b){
        uid=a;
        type=b;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setuid(String uid) {
        this.uid = uid;
    }

    public String getType() {
        return type;
    }

    public String getUid() {
        return uid;
    }
}
