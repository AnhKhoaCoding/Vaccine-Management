/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Support {
    Scanner sc = new Scanner(System.in);
    
    //ktra chuỗi kí tự mình nhập ko đc để trống
    public String fixString(){
        String content; int c;
        do{
            c = 1;
            content = sc.nextLine().toUpperCase();
            if (content.length() < 1){
                c = 0;
                System.out.println("You can't leave this information blank!!!");
                System.out.print("Please Re-Enter: ");
            }
        }while(c == 0);
        return content;
    }
    
    

    
    
    public Date addDays(Date date, int days)
    {
        Calendar x = Calendar.getInstance();
        x.setTime(date);
        x.add(Calendar.DATE, days); 
        return x.getTime();
    }
       

    //ktra ngày có nhập theo đúng định dạng và hợp lệ ko
    public Date getDate() {
        Scanner sc = new Scanner(System.in);
        String inputDate;
        Date date = null;

        while (true) {
            try {
                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                df.setLenient(false);
                inputDate = sc.nextLine();
                date = df.parse(inputDate);
                return date;
            } catch (Exception e) {
                System.out.println("Invalid input date!!!.Please Re-enter:");
            }

        }
    }
}
