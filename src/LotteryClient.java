import java.util.Scanner;

public class LotteryClient {
  public static void main(String[] args) {
    Scanner input   = new Scanner(System.in);
    char    again;          // play again?
    int     myBonus;        // bonus number chosen by user
    int[]   myNumbers;      // list of numbers chosen by the user

    do {
      Lottery powerball = new Lottery();
      powerball.setVars();

      // select numbers
      myNumbers = enterNumbers(powerball.size, powerball.max);
      do {
        System.out.print("Bonus number (1-" + powerball.bonusMax + "): ");
        myBonus = input.nextInt();
      } while (myBonus < 1 || myBonus > powerball.bonusMax);

      powerball.play(myNumbers, myBonus);

      System.out.print("Play again? (y/n): ");
      again = input.next().charAt(0);
    } while(again == 'y' || again == 'Y');
  }

  /**
   * Get user lottery numbers.
   * @param k how many numbers to select
   * @param n max value for those numbers
   * @return  the array of numbers chosen
   */
  public static int[] enterNumbers(int k, int n) {
    Scanner input = new Scanner(System.in);
    int[] numbers = new int[k];
    int tmp; // holds the most recently entered number so it can be tested

    System.out.print("Enter " + k + " numbers between 1 and " + n + ": ");
    for (int i = 0; i < k; i++) {
      tmp = input.nextInt();
      if (Lottery.find(tmp, numbers)) {
        System.out.println("Cannot use the same number twice");
        i--;
      }else if (tmp < 1 || tmp > n) {
        System.out.println("Number must be between 1 and " + n);
        i--;
      } else {
        numbers[i] = tmp;
      }
    }
    return numbers;
  }
}
