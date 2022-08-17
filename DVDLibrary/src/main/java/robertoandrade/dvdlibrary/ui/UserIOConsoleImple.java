/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author  Roberto Andrade
 */
public class UserIOConsoleImple implements UserIO{
    final private Scanner console = new Scanner(System.in);

    @Override
    public void print(String msg) {
    System.out.println(msg);    
    }

    @Override
    public double readDouble(String prompt) {
        boolean invalidInput = true;
           double num = 0;  
           while(invalidInput){
               try{
                   String sValue = this.readString(prompt);
                   num = Double.parseDouble(sValue);
               }catch (NumberFormatException e){
                   this.print("Input ERROR , Please enter a new value");
               }
           }
           return num; 
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
      double result;
      do{
           result = this.readDouble(prompt);
      } while( result < min || result > max);
      return result;
    }

    @Override
    public float readFloat(String prompt) {
           boolean invalidInput = true;
           float num = 0;  
           while(invalidInput){
               try{
                   String sValue = this.readString(prompt);
                   num = Float.parseFloat(sValue);
               }catch (NumberFormatException e){
                   this.print("Input ERROR , Please enter a new value");
               }
           }
           return num;     }

    @Override
    public float readFloat(String prompt, float min, float max) {
    float result;
      do{
           result = this.readFloat(prompt);
      } while( result < min || result > max);
      return result;    }

    @Override
    public int readInt(String prompt) {
           boolean invalidInput = true;
           int num = 0;  
           while(invalidInput){
               try{
                   String sValue = this.readString(prompt);
                   num = Integer.parseInt(sValue);
                   invalidInput = false;
               }catch (NumberFormatException e){
                   this.print("Input ERROR , Please enter a new value");
               }
           }

           return num;

 }

    @Override
    public int readInt(String prompt, int min, int max) {
      int result;
      do{
           result = this.readInt(prompt);
      } while( result < min || result > max);
      return result;    }

    @Override
    public long readLong(String prompt) {
           boolean invalidInput = true;
           long num = 0;  
           while(invalidInput){
               try{
                   String sValue = this.readString(prompt);
                   num = Long.parseLong(sValue);
               }catch (NumberFormatException e){
                   this.print("Input ERROR , Please enter a new value");
               }
           }
           return num;    }

    @Override
    public long readLong(String prompt, long min, long max) {
      long result;
      do{
           result = this.readLong(prompt);
      } while( result < min || result > max);
      return result;    }

    @Override
    public String readString(String prompt) {
      System.out.println(prompt); 
      return console.nextLine();
    }
}
