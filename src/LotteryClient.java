/**
 * LotteryClient
 * Project 2
 * Joshua Hunter
 */

import java.util.Scanner;

public class LotteryClient {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    char    again;       // play again?
    int     userBonus;   // bonus number chosen by user
    int[]   userNumbers; // list of numbers chosen by the user

    do {
      Lottery powerball = new Lottery();
      powerball.setVars();

      // select numbers
      userNumbers = getNumbers(powerball.size, powerball.max);
      do {
        System.out.print("Bonus number (1-" + powerball.bonusMax + "): ");
        userBonus = input.nextInt();
      } while (userBonus < 1 || userBonus > powerball.bonusMax);

      powerball.play(userNumbers, userBonus);

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
  public static int[] getNumbers(int k, int n) {
    Scanner input = new Scanner(System.in);
    int[]   newNumbers = new int[k];

    System.out.print("Enter " + k + " numbers between 1 and " + n + ": ");
    for (int i = 0; i < k; i++) {
      int tmp = input.nextInt(); // hold on to the number so we can test it
      if (Lottery.find(tmp, newNumbers)) {
        System.out.println("Cannot use the same number twice");
        i--;
      } else if (tmp < 1 || tmp > n) {
        System.out.println("Number must be between 1 and " + n);
        i--;
      } else {
        newNumbers[i] = tmp;
      }
    }
    return newNumbers;
  }
}