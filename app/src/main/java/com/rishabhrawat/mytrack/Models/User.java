package com.rishabhrawat.mytrack.Models;

import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class User {
    private String Pname;
    private String Pemail;
    private String Phoneno;
    private @ServerTimestamp Date lastLogin;


    public User(String pname, String pemail, String phoneno, Date lastLogin) {
        Pname = pname;
        Pemail = pemail;
        Phoneno = phoneno;
        this.lastLogin = lastLogin;
    }

    public User() {
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getPemail(String emailpp) {
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

    @Override
    public String toString() {
        return "User{" +
                "Pname='" + Pname + '\'' +
                ", Pemail='" + Pemail + '\'' +
                ", Phoneno='" + Phoneno + '\'' +
                ", lastLogin=" + lastLogin +
                '}';
    }
}
