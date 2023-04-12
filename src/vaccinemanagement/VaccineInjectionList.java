/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author anhkhoa
 */
public class VaccineInjectionList {

    List<Student> studentList = new ArrayList<>();
    List<Vaccine> vaccineList = new ArrayList<>();
    List<Injection> InjectionList = new ArrayList<>();

    public VaccineInjectionList() {
        studentList = new ArrayList<>();
        vaccineList = new ArrayList<>();
        InjectionList = new ArrayList<>();
    }

    public VaccineInjectionList(ArrayList<Student> studentList) {
        this.studentList = studentList;
    }

    Scanner sc = new Scanner(System.in);
    Support sp = new Support();

    public void ShowInjectionInfo() {
        if (InjectionList.isEmpty()) {
            System.out.println("The list is EMTPY!!!");
        } else {
            System.out.println("+--------------+-----------------+-----------------+-----------------+----------------------+----------------------+----------------------+----------------------+");
            System.out.printf("| %-12s | %-15s | %-15s | %-15s | %-20s | %-20s | %-20s | %-20s |\n", "InjectionID", "StudentID", "StudentName", "VaccineName", "FirstPlace", "FirstDateInjection", "SecondPlace", "SecondDateInjection");
            System.out.println("+--------------+-----------------+-----------------+-----------------+----------------------+----------------------+----------------------+----------------------+");
            for (Injection x : InjectionList) {
                x.ShowInfo();
            }
        }
    }

    //HÀM THÊM CÁC THÔNG TIN TIÊM CỦA HỌC SINH:    
    public void AddInjectionInfo() {
        int kbv = 0;
        String VaccineName;
        String injectID;
        String studentID;
        String VaccineID;
        String place1;
        Date injectDate1;
        String place2;
        Date injectDate2;
        do {

            System.out.println("Enter the Injection ID:");
            injectID = CheckInjectID();

            System.out.println("Enter Student's ID: ");
            studentID = inputStudentID();

            String studentName = GetStudentName(studentID);

            System.out.println("Enter the VaccineID:");
            VaccineID = GetVaccine();

            VaccineName = GetVaccineName(VaccineID);

            System.out.println("Enter the first Injection place:");
            place1 = sp.fixString();

            System.out.println("Enter the first Injection Date:");
            injectDate1 = sp.getDate();

            place2 = null;

            injectDate2 = null;

            InjectionList.add(new Injection(injectID, studentID, studentName, VaccineID, VaccineName, place1, place2, injectDate1, injectDate2));

            do {
                try {
                    System.out.println("Do you want to continue? (1:Yes/2:No) ");
                    kbv = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid!!!. Please Re-enter.");
                }
            } while (kbv < 1 || kbv > 2);
        } while (kbv == 1);
        System.out.println("Add Success!!!");
    }

    //nhập injection và ktra injection id có bị trùng trong danh sách ko
    private String CheckInjectID() {

        String InjID;
        do {
            int a = 0;
            InjID = sp.fixString();
            for (Injection x : InjectionList) {
                if (x.getInjectionID().equalsIgnoreCase(InjID)) {
                    a = 1;
                    System.out.println("Injection ID already exists.Please input another one: ");
                }
            }
            if (a == 0) {
                break;
            }
        } while (true);
        return InjID;

    }

    //hàm nhập student id (ktra id student mình nhập có trong student list ko và ktra xem id mình nhập có bị trùng vs id student đã có trong injection list ko)
    private String inputStudentID() {
        String ID;
        while (true) {
            ID = sp.fixString();
            // tìm kiếm student id có tồn tại trong Student List ko
            String a = findStudentID(ID);
            //ko tìm thấy trong Student List
            if (a == null) {
                System.out.println("This Student doesn't exist in student list!!! Please input again:");
            } else {
                //tìm kiếm student ID có trùng vs student id trong Injectionlist ko
                String b = findStudentIDInListInjection(ID);
                //ko tìm thấy trong Injectionlist
                if (b == null) {
                    return ID;
                } else {
                    System.out.println("This student is already on the injection list!!! Please input again:");
                }
            }
        }
    }
    
    private String findStudentID(String studentID) {
        for (Student x : studentList) {
            if (x.getStudentID().equalsIgnoreCase(studentID)) {
                return studentID;
            }
        }
        return null;
    }

    private String findStudentIDInListInjection(String studentID) {
        for (Injection x : InjectionList) {
            if (x.getStudentID().equalsIgnoreCase(studentID)) {
                return studentID;
            }
        }
        return null;
    }



    
    
    //lấy tên học sinh trong danh sách theo id
    private String GetStudentName(String studentID) {
        String name = "";
        for (Student x : studentList) {
            if (x.getStudentID().equals(studentID)) {
                name = x.getStudentName();
            }

        }
        return name;
    }

    //tìm vaccine nhập có tồn tại trong danh sách vaccine ko
    private String GetVaccine() {
        String ID = "";
        do {
            int a = 1;
            ID = sp.fixString();
            for (Vaccine x : this.vaccineList) {
                if (x.getVaccineID().equalsIgnoreCase(ID)) {
                    a = 0;
                    System.out.println("vaccine found on the list: " + x.getVaccineName());
                }
            }
            if (a == 1) {
                System.out.println("Vaccine not exist in vaccine list. PLease re-enter a new one !!!");
            }
            if (a == 0) {
                break;
            }
        } while (true);
        return ID;
    }


    //lấy tên vaccine trong danh sách theo id
    private String GetVaccineName(String text) {
        for (Vaccine s : this.vaccineList) {
            if (s.getVaccineID().equalsIgnoreCase(text)) {
                return s.getVaccineName();
            }
        }
        return null;
    }

   

    //HÀM UPDATE THÔNG TIN TIÊM LẦN 2 CỦA HỌC SINH
    public void UpdateInjectionInfo() {
        int a = 0;
        if (InjectionList.isEmpty()) {
            System.out.println("The list is EMTPY!");
        } else {
            System.out.println("Enter Injection's ID: ");
            String InjectionID = sp.fixString();
            for (Injection x : InjectionList) {
                if (x.getInjectionID().equals(InjectionID) && x.getSecondPlace() != null && x.getSecondDate() != null ) {
                    System.out.println("Student has completed 2 injections!");
                    a = 1;
                    break;
                }
                if (x.getInjectionID().equals(InjectionID)) {
                    a = 1;
                    System.out.println("Enter the second Injection place:");
                    x.setSecondPlace(sp.fixString());
                    System.out.println("Enter the second Injection date:");
                    x.setSecondDate(CheckSecondVacineDay(x.getFirstDate()));
                    System.out.println("Update injection infomation success!!!");
                    System.out.println("Student has completed 2 injections!");

                    break;
                }
            }
            if (a == 0) {
                System.out.println("This Injection ID does not exist!!!");
            }
        }
    }
    
    //ktra ngày tiem có hợp lệ ko(ko dưới 4 tuần hay quá 12 tuần)
    private Date CheckSecondVacineDay(Date date) {
        Date InjectDate2 = null;
        {
            while (true) {
                InjectDate2 = sp.getDate();
                Date DayMin = sp.addDays(date, 28);
                Date DayMax = sp.addDays(date, 84);
                if (InjectDate2.before(DayMin)) {
                    System.out.println("The time from the first injection is less than 4 weeks!!! Please Re-Enter: ");
                } else if (InjectDate2.after(DayMax)) {
                    System.out.println("The time from the first injection is more than 12 weeks!!! Please Re-Enter: ");
                } else {
                    break;
                }
            }
        }
        return InjectDate2;
    }


    
    
    //HÀM XÓA THÔNG TIN TIÊM CỦA HỌC SINH
    public void DeleteInjectionInfo() {
        System.out.println("Enter the Student vaccine injection ID you want to remove: ");
        String ID = sp.fixString();
        int a = CheckInjectionID(ID);
        int kbv = 0;

        if (a == -1) {
            System.out.println("Injection ID not found!!!");
        } else {
            do {
                try {
                    System.out.println("Are you sure ?(1.Yes/2:No) ");
                    kbv = Integer.parseInt(sc.nextLine());
                } catch (Exception e) {
                    System.out.println("Invalid!!!. Please Re-enter.");
                }
            } while (kbv < 1 || kbv > 2);
            if (kbv == 1) {
                InjectionList.remove(a);
                System.out.println("Delete student injection information success!!! ");

            } else {
                System.out.println("Delete fail !!!");
            }
        }
    }
    //hàm tìm Injection ID trong danh sách InjectionList    
    public int CheckInjectionID(String ID){
        if(InjectionList.isEmpty()){
            return -1;
        }
        for(int i = 0; i < InjectionList.size(); i++){
            if(InjectionList.get(i).getInjectionID().equalsIgnoreCase(ID)){
                return i;
            }
        }
        return -1;
    }
    
    
    //HÀM TÌM KIẾM THÔNG TIN TIÊM TRONG DANH SÁCH BẰNG CÁCH NHẬP STUDENT ID
    public void SearchInjectionInfoByStudentID() {
        int a = 0;
        if (InjectionList.isEmpty()) {
            System.out.println("The list is EMTPY!");
        } else {
            System.out.println("Enter Student ID: ");
            String ID = sp.fixString();
            for (Injection x : InjectionList) {
                if (x.getStudentID().equalsIgnoreCase(ID)) {
                    System.out.println("Here is the injection information you want to search:");
                    x.ShowInfo();
                    a = 1;
                } else {
                    a = 0;
                }
            }
            if (a != 1) {
                System.out.println("Student ID not exist!");
            }
        }
    }

    
    
    //HÀM ĐỂ LƯU TẤT CẢ THÔNG TIN MÌNH VỪA NHẬP VÀO FILE INJECTION 
    public void SaveToFile() {
        String fileName = "injection.dat";
        try {

            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);

            for (Injection x : InjectionList) {
                oStream.writeObject(x);
            }

            oStream.close();
            file.close();
            System.out.println("Save file success!");
        } catch (Exception e) {
            System.out.println("Save file fail!");
        }
    }
    
    //hàm để đọc file injection.dat
    public void ReadFile() {
        String fileName = "injection.dat";

        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream oStream = new ObjectInputStream(file);

            while (true) {
                Injection read = (Injection) oStream.readObject();
                InjectionList.add(read);
                if (file.available() < 1) {
                    break;
                }
            }
            oStream.close();
            file.close();
            System.out.println("ReadFile " + fileName + " Success!");
        } catch (Exception e) {
            System.out.println("ReadFile " + fileName + " Fail!");
        }
    }
   





    

    public void writeVaccine() {
        String fileName = "vaccine.dat";
        try {

            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);

            vaccineList.add(new Vaccine("Covid-V001", "AstraZeneca"));
            vaccineList.add(new Vaccine("Covid-V002", "SPUTNIK V"));
            vaccineList.add(new Vaccine("Covid-V003", "Vero Cell"));
            vaccineList.add(new Vaccine("Covid-V004", "Pfizer"));
            vaccineList.add(new Vaccine("Covid-V005", "Moderna"));

            for (Vaccine vc : vaccineList) {
                oStream.writeObject(vc);
            }

            oStream.close();
            file.close();
            System.out.println("WriteFile " + fileName + " Success!");
        } catch (Exception e) {
            System.out.println("WriteFile " + fileName + " Fail!");
        }
    }

    public void PrintVaccineFile() {
        System.out.printf("| %-15s | %-15s |\n", "VaccineID", "VaccineName");
        for (Vaccine s : vaccineList) {
            System.out.printf("| %-15s | %-15s |\n", s.getVaccineID(), s.getVaccineName());
        }
    }

    public void writeStudent() {
        String fileName = "student.dat";
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream oStream = new ObjectOutputStream(file);

            studentList.add(new Student("SE15000", "Huy Vu"));
            studentList.add(new Student("SE15001", "Nguyen Thien"));
            studentList.add(new Student("SE15002", "Nhut Minh"));
            studentList.add(new Student("SE15003", "Dang Khoa"));
            studentList.add(new Student("SE15004", "Kim Loan"));
            studentList.add(new Student("SE15005", "Quang Vu"));
            studentList.add(new Student("SE15006", "Dai Quang"));
            studentList.add(new Student("SE15007", "Quang DInh"));
            studentList.add(new Student("SE15008", "Kim Dung"));
            studentList.add(new Student("SE15009", "Thuy Tien"));
            studentList.add(new Student("SE15010", "Gia Bao"));

            for (Student s : studentList) {
                oStream.writeObject(s);
            }
            oStream.close();
            file.close();
            System.out.println("WriteFile " + fileName + " Success!");
        } catch (Exception e) {
            System.out.println("WriteFile " + fileName + " Fail!");
        }
    }

    public void PrintStudentFile() {
        System.out.printf("| %-15s | %-15s |\n", "StudentID", "StudentName");
        for (Student s : studentList) {
            System.out.printf("| %-15s | %-15s |\n", s.getStudentID(), s.getStudentName());
        }
    }
    
    



}
