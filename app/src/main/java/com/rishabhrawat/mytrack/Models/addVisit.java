package com.rishabhrawat.mytrack.Models;

import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.ServerTimestamp;
import java.util.Date;


public class addVisit {
    private String Client_name;
    private String Client_contactno;
    private String Person_Intracted;
    private String Remark;
    private String Status;
    private @ServerTimestamp Date DateofVisit;
    private GeoPoint geopoint;
    private String visited_by;

    public addVisit(){
        }

    public addVisit(String client_name, String client_contactno, String person_Intracted, String remark, String status, Date dateofVisit, GeoPoint geopoint, String visited_by) {
        Client_name = client_name;
        Client_contactno = client_contactno;
        Person_Intracted = person_Intracted;
        Remark = remark;
        Status = status;
        DateofVisit = dateofVisit;
        this.geopoint = geopoint;
        this.visited_by = visited_by;
    }

    public String getClient_name() {
        return Client_name;
    }

    public void setClient_name(String client_name) {
        Client_name = client_name;
    }

    public String getClient_contactno() {
        return Client_contactno;
    }

    public void setClient_contactno(String client_contactno) {
        Client_contactno = client_contactno;
    }

    public String getPerson_Intracted() {
        return Person_Intracted;
    }

    public void setPerson_Intracted(String person_Intracted) {
        Person_Intracted = person_Intracted;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public Date getDateofVisit() {
        return DateofVisit;
    }

    public void setDateofVisit(Date dateofVisit) {
        DateofVisit = dateofVisit;
    }

    public GeoPoint getGeopoint() {
        return geopoint;
    }

    public void setGeopoint(GeoPoint geopoint) {
        this.geopoint = geopoint;
    }

    public String getVisited_by() {
        return visited_by;
    }

    public void setVisited_by(String visited_by) {
        this.visited_by = visited_by;
    }

    @Override
    public String toString() {
        return "addVisit{" +
                "Client_name='" + Client_name + '\'' +
                ", Client_contactno='" + Client_contactno + '\'' +
                ", Person_Intracted='" + Person_Intracted + '\'' +
                ", Remark='" + Remark + '\'' +
                ", Status='" + Status + '\'' +
                ", DateofVisit=" + DateofVisit +
                ", geopoint=" + geopoint +
                ", visited_by='" + visited_by + '\'' +
                '}';
    }
}
