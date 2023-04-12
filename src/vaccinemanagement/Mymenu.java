/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vaccinemanagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author anhkhoa
 */
public class Mymenu extends ArrayList {
    Scanner sc = new Scanner(System.in);
    
    public int getChoice() {
        for(int i = 0; i < this.size(); i++)
            System.out.println((i + 1) + "-" + this.get(i));
        System.out.println("----------------------------");
        System.out.println(" Please choose " + "1.." + this.size() + ":");
        return Integer.parseInt(sc.nextLine());
    }
}
