/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.rockpaperscissors;
import java.lang.Math;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Roberto Andrade
 */
public class RockPaperScissors {

    public static void main(String[] args) {
        String entry;
        int rounds = 0;
        int Userchoice;
        int PcChoice;
        int pcWins = 0;
        int UserWins = 0;
        int ties = 0;
        Random random =new Random();
        System.out.println("---------------- ROCK PAPAER SCISSORS ----------------");
        Scanner input = new Scanner(System.in);
        System.out.println("How many rounds do you want to play ?   Min = 1      Max = 10");
        entry = input.next(); 
        try{
                rounds = Integer.parseInt(entry);
                if(rounds <= 0 || rounds > 10){
                    System.out.println( " Error: Entry is not Valid");
                    System.exit(0);
                }         
            } catch(NumberFormatException e){
                System.out.println( " Error: Entry is not Valid");
                System.exit(0);
        }
        
        for(int k=0 ; k < rounds ; k++){
      
                System.out.println("                Select your Choice "   );
                System.out.println("----------------------------------------------------"   );
                System.out.println("  Rock = 1   |    Paper = 2   |    Scissors = 3"   );
                System.out.println("----------------------------------------------------"   );

                entry = input.next(); 
             while(Integer.parseInt(entry) > 3 || Integer.parseInt(entry) < 1){
                System.out.println("**   Incorrect Entry - Enter only 1 , 2 or 3  **"   );
                System.out.println("                Select your Choice "   );
                System.out.println("----------------------------------------------------"   );
                System.out.println("  Rock = 1   |    Paper = 2   |    Scissors = 3"   );
                System.out.println("----------------------------------------------------"   );
                entry = input.next();
             }
             Userchoice = Integer.parseInt(entry);
             System.out.println(" Your choice was " +  Userchoice );
             PcChoice = random.nextInt(3)+1;
             if((Userchoice == 1 && PcChoice==3) || (Userchoice == 2 && PcChoice==1) || (Userchoice == 3 && PcChoice==2)){
                 System.out.println(" PC choice was "+  PcChoice );
                 System.out.println(" ****** YOU ARE THE WINNER!! ******" );
                 UserWins ++ ;
             }else if((Userchoice == 1 && PcChoice==1) || (Userchoice == 2 && PcChoice==2) || (Userchoice == 3 && PcChoice==3)){
                 System.out.println(" PC choice was "+  PcChoice );
                 System.out.println(" ****** THIS IS A TIE!! ******" );
                 ties ++ ;
             }else{
                 System.out.println(" PC choice was "+  PcChoice );
                 System.out.println(" ****** PC IS THE WINNER!! ******" );
                 pcWins ++ ;
             }
             
             System.out.println("  Round# " +  (k+1) );
             System.out.println("  User Wins = " +  UserWins );
             System.out.println("  PC Wins = " +  pcWins );
             System.out.println("  ************************* " );

        }
        if(UserWins > pcWins){
           System.out.println("  USER WINS THE GAME " );
           System.out.println("  User Wins = " +  UserWins + " \n  PC Wins = " + pcWins + " \n  ties = "+ties );
        }else if(UserWins == pcWins){
            System.out.println("  GAME WAS A TIE " );
           System.out.println("  User Wins = " +  UserWins + " \n  PC Wins = " + pcWins + " \n  ties = "+ties );
        }else{
            System.out.println("  PC WINS THE GAME " );
           System.out.println("  User Wins = " +  UserWins + " \n  PC Wins = " + pcWins + " \n  ties = "+ties );
        }
        
        
        
        
        
        

    }
}
