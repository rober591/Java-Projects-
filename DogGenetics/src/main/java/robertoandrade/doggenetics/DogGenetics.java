/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package robertoandrade.doggenetics;
import java.util.Scanner ;
import java.util.Random;


/**
 *
 * @author Roberto Andrade
 */
public class DogGenetics {

    public static void main(String[] args) {
        String name;
        int a,b,c,d,e;
        int x;
        Random random =new Random();
        Scanner input = new Scanner(System.in);
        System.out.println("What is your Dog's name ?");
        name = input.nextLine();
        String[] DogBreeds = {"American Pit Bull Terrier","American Staffordshire Terrier", "Border Collie", "Bull Terrier", "Labrador" , "Chihuahua" , " Shih Tzu" , "Bulldog" , "German Shepherd" , "Dobermann" , "Spanish Mastiff", "Border Terrier" , " Golden Retriever"};
        System.out.println("----------------------------------------");
        System.out.println(name + " is: ");
        // variables a,b,c,d,e are the percentage per breed
        a = random.nextInt(59)+1;
        b = 60 - a;
        c = random.nextInt(29)+1;
        d = 30 - c + random.nextInt(9)+1;
        e = 100 - a - b -c -d;
        // breeds array is to make sure that breeds are not duplicated 
        int[] breeds = {-1 , -1 , -1 , -1 , -1};
        int k=0;
        int z;
        breeds[k] = random.nextInt(13);
        k++;
        while(breeds[4] == -1){
            z = random.nextInt(13);
            if(z != breeds[0] && z != breeds[1] && z != breeds[2] && z != breeds[3] && z != breeds[4]){
                    breeds[k] = z;
                    k ++;

            }
        }
        System.out.println(a+"%  " + DogBreeds[breeds[0]]);
        System.out.println(b+"%  " + DogBreeds[breeds[1]]);
        System.out.println(c+"%  " + DogBreeds[breeds[2]]);
        System.out.println(d+"%  " + DogBreeds[breeds[3]]);
        System.out.println(e+"%  " + DogBreeds[breeds[4]]);
        System.out.println("----------------------------------------");



   
    }
}
