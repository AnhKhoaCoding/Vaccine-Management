/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.Serializable;


/**
 *
 * @author anhkhoa
 */
public class Student implements Serializable{
    private String studentID;
    private String studentName;

    public String getStudentID() {
        return studentID;
    }
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

   
    
    public Student(){}

    public Student(String studentID, String studentName) {
        this.studentID = studentID;
        this.studentName = studentName;
    }

    @Override
    public String toString() {
        return "Student{" + "studentID=" + studentID + ", studentName=" + studentName + "}";
    }


    

    
    
    
}
