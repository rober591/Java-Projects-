/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package robertoandrade.healthyhearts;
import java.util.Scanner ;

/**
 *
 * @author Roberto Andrade
 */
public class HealthyHearts {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String entry;
        int age = 0;
        int hr;
        boolean EntryValidation = false;
        System.out.println("******************************************************");

        while(!EntryValidation){
            System.out.println("What is your Age ? ");
            entry = input.next(); 
            try{
                age = Integer.parseInt(entry);
                if(age > 0 && age < 130){
                    EntryValidation = true;
                }else{
                    System.out.println("Enter an age less than 130 years ");
                }
                
            } catch(NumberFormatException e){
                System.out.println( " Error: Enter an integer as your AGE");
        }
        }
        hr = 220 - age;
        System.out.println( " Your maximum heart rate should be "+hr);
        System.out.println( " Your target HR Zone  is "+(hr*0.5) + " - "+(hr*0.85));
        System.out.println("******************************************************");


        
    }
}
