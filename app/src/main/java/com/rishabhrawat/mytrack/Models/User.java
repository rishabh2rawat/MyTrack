package com.rishabhrawat.mytrack.Models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;


public class User {
    private String Pname;
    private String Pemail;
    private String Phoneno;
    private @ServerTimestamp
    Date lastLogin;
    private String Authid;

    public User() {
    }

    public User(String pname, String pemail, String phoneno, Date lastLogin, String authid) {
        Pname = pname;
        Pemail = pemail;
        Phoneno = phoneno;
        this.lastLogin = lastLogin;
        Authid = authid;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPemail() {
        return Pemail;
    }

    public void setPemail(String pemail) {
        Pemail = pemail;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getAuthid() {
        return Authid;
    }

    public void setAuthid(String authid) {
        Authid = authid;
    }

    @Override
    public String toString() {
        return "User{" +
                "Pname='" + Pname + '\'' +
                ", Pemail='" + Pemail + '\'' +
                ", Phoneno='" + Phoneno + '\'' +
                ", lastLogin=" + lastLogin +
                ", Authid='" + Authid + '\'' +
                '}';
    }
}
