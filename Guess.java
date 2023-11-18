import java.util.Scanner;
import java.util.Random;
public class Guess{


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            int maxtrials =3;
            int attempt;
            for(attempt = 1; attempt <= maxtrials; attempt++) {
System.out.println("enter a number");
int number = scanner.nextInt();


    Random random = new Random();
    
   int  i = random.nextInt(10);
    System.out.println("random number " + i);
    if (number == i) 
        System.out.println("you win");
    
else if(number!=i)
                System.out.println("incorrect number you have" + (maxtrials - attempt)+ "attempts left");
          else if(attempt  == maxtrials){
                                          System.out.println("random number " + i);

                    break;
  
}
            }
    }

} }

    