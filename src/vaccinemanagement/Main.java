/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

/**
 *
 * @author anhkhoa
 */
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException{
        Support sp = new Support();
        VaccineInjectionList menu = new VaccineInjectionList();    
        menu.ReadFile();
        menu.writeStudent();
        menu.writeVaccine();
       
            Scanner sc = new Scanner(System.in);
            Mymenu strList = new Mymenu();
            System.out.println("Welcome to Vaccine Management - @ 2021 by <SE150176 - Nguyen Anh Khoa >");
            strList.add(" Show all student List and vaccine List");
            strList.add(" Show information all students have been injected");
            strList.add(" Add student's vaccine injection information ");
            strList.add(" Updating information of students' for second injection");
            strList.add(" Delete student vaccine injection information");
            strList.add(" Search for injection information by studentID");
            strList.add(" Save Injection List To File");         
            strList.add(" Exit Program");
        
            int Choice;
        do {
            Choice = strList.getChoice();
            switch (Choice) {
                case 1:
                    System.out.println("+-----------------------------------+");
                    menu.PrintStudentFile();
                    System.out.println("+-----------------------------------+");
                    menu.PrintVaccineFile();
                    System.out.println("+-----------------------------------+");
                    break;
                case 2:
                    menu.ShowInjectionInfo();
                    break;                    
                case 3:                    
                    menu.AddInjectionInfo();
                  break;
                case 4:
                    menu.UpdateInjectionInfo();
                    break;
                case 5:
                    menu.DeleteInjectionInfo();
                    break;
                case 6:
                    menu.SearchInjectionInfoByStudentID();
                    break;
                case 7:
                    menu.SaveToFile();
                    break;
            }

        } while (Choice != 8);
      

    }
}
