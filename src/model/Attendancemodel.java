/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Attendancemodel {
    private int workerId;
    private String status;
    private Date attendanceDate;
    private String workername;

    public Attendancemodel(int workerId, String status, Date attendanceDate,String workername) {
        this.workerId = workerId;
        this.status = status;
        this.attendanceDate = attendanceDate;
        this.workername=workername;
    }




public int getWorkerId() {
        return workerId;
    }
public String getworkername(){
    return workername;
}
public void setname(String name){
    this.workername=name;
}
    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(Date attendanceDate) {
        this.attendanceDate = attendanceDate;
    }
}