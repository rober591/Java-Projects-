/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package robertoandrade.sumativesums;

/**
 *
 * @author Roberto Andrade
 */
public class SumativeSums {
    
    static int Sumativesums(int[] arr){
        int sum = 0;
        for(int k=0 ; k < arr.length ; k++){
            sum = sum + arr[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 90, -33, -55, 67, -16, 28, -55, 15 };
        int[] arr2 = { 999, -60, -77, 14, 160, 301 };
        int[] arr3 = { 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190, 200, -99};
        
        
        System.out.println("#1 Array Sum: " + Sumativesums(arr1));
        System.out.println("#2 Array Sum: " + Sumativesums(arr2));
        System.out.println("#3 Array Sum: " + Sumativesums(arr3));

        
    }
}
