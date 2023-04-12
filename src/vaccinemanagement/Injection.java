/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author anhkhoa
 */
public class Injection extends Vaccine implements Serializable{
    private String InjectionID;
    private String firstPlace;
    private String secondPlace;
    private Date firstDate;
    private Date secondDate;
    private String studentID;    
    private String studentName;

    public String getInjectionID() {
        return InjectionID;
    }

    public void setInjectionID(String InjectionID) {
        this.InjectionID = InjectionID;
    }

    public String getFirstPlace() {
        return firstPlace;
    }

    public void setFirstPlace(String firstPlace) {
        this.firstPlace = firstPlace;
    }

    public String getSecondPlace() {
        return secondPlace;
    }

    public void setSecondPlace(String secondPlace) {
        this.secondPlace = secondPlace;
    }

    public Date getFirstDate() {
        return firstDate;
    }

    public void setFirstDate(Date firstDate) {
        this.firstDate = firstDate;
    }

    public Date getSecondDate() {
        return secondDate;
    }

    public void setSecondDate(Date secondDate) {
        this.secondDate = secondDate;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudenName() {
        return studentName;
    }

    public void setStudenName(String studenName) {
        this.studentName = studenName;
    }
    
    

    public Injection() {
    }

    public Injection(String InjectionID, String studentID, String studentName, String vaccineID, String vaccineName, String firstPlace, String secondPlace, Date firstDate, Date secondDate ) {
        super(vaccineID, vaccineName);
        this.InjectionID = InjectionID;
        this.firstPlace = firstPlace;
        this.secondPlace = secondPlace;
        this.firstDate = firstDate;
        this.secondDate = secondDate;
        this.studentID = studentID;
        this.studentName = studentName;
    }
    
@Override
    public String toString() {
        return  "InjectionID=" + InjectionID + ", firstPlace=" + firstPlace + ", secondPlace=" + secondPlace + ", firstDate=" + firstDate + ", secondDate=" + secondDate + ", studentID=" + studentID +", studentName=" +studentName +", vaccineID=" + getVaccineID() +", vaccineName=" + getVaccineName() + "}\n";
    }
    
    
    public String printDate(Date d ){
    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    if(d==null) return null;
    return df.format(d);
    }

    public void ShowInfo(){        
        System.out.printf("| %-12s | %-15s | %-15s | %-15s | %-20s | %-20s | %-20s | %-20s |\n",InjectionID,studentID,studentName,getVaccineName(),firstPlace,printDate(firstDate),secondPlace,printDate(secondDate));
        
    }
    
}
