/**
 * LotteryProject
 * Hold a virtual lottery drawing, proving how futile the lottery is in the process!
 * Joshua Hunter
 * 10/23/2014
 *
 */

import java.util.Scanner;

public class LotteryProject {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int size;             // k - how many distinct numbers
    int max;              // n - the range for the lottery numbers
    int bonus;            // m - the range for the bonus number
    int[] myNumbers;      // numbers entered by the user
    int[] winningNumbers; // randomly drawn winners

    do {
      System.out.print("k=");
      size = input.nextInt();
    } while (size < 1);
    do {
      System.out.print("n=");
      max = input.nextInt();
    } while (max < 1);
    do {
      System.out.print("m=");
      bonus = input.nextInt();
    } while (bonus < 1);

    myNumbers = enterNumbers(size, max);
    winningNumbers = drawNumbers(size, max);

    System.out.print("Your numbers:    ");
    for (int i = 0; i < myNumbers.length; i++) {
      System.out.print(myNumbers[i] + " ");
    }
    System.out.print("\nWinning numbers: ");
    for (int i = 0; i < winningNumbers.length; i++) {
      System.out.print(winningNumbers[i] + " ");
    }

    if (containSameElements(myNumbers, winningNumbers)) {
      System.out.println("\nCongratulations, you won!");
    } else {
      System.out.println("\nBetter luck next time!");
    }

    System.out.println("You had a " + jackpotChance(size, max, bonus)
        + "% chance of winning.");
  }
  public static double jackpotChance(int size, int max, int bonus) {
  /*
   * The number of possible tickets for a given jackpot is
   *
   * | max * (max - 1) * (max - 2) * ... * (max - size + 1)
   * |----------------------------------------------------- * bonus
   * | size * (size - 1) * (size - 2) * ... 3 * 2 * 1
   */
    double numerator = 1;   // the numerator of the fraction above
    double denominator = 1; // the denominator of the fraction above

    for (int i = max; i >= max - size + 1; i--) {
      numerator = numerator * i;
    }
    for (int i = size; i > 0; i--) {
      denominator = denominator * i;
    }
    // return the inverse of the number of tickets to get the probability of
    // selecting a winning ticket
    return 1 / ((numerator / denominator) * bonus);
  }

  public static int[] enterNumbers(int size, int max) {
    Scanner input = new Scanner(System.in);
    int[] numbers = new int[size]; //contains the chosen numbers
    int number;

    for (int i = 0; i < size; i++) {
      System.out.print("Enter a number between 1 and " + max + ": ");
      number = input.nextInt();
      if (linearSearch(number, numbers)) {
        System.out.println("Cannot use the same number twice");
        i--;
      }else if (number < 1 || number > max) {
        System.out.println("Number must be between 1 and " + max);
        i--;
      } else {
        numbers[i] = number;
      }
    }
    return numbers;
  }

  public static int[] drawNumbers (int k, int n){
    int[] numbers = new int[k];

    for (int i = 0; i < k; i++) {
      numbers[i] = (int)(Math.random() * n + 1);
    }
    return numbers;
  }

  public static boolean containSameElements(int[] a, int[] b) {
    for (int i = 0; i < a.length; i++) {
      if (!linearSearch(a[i], b)) {
        return false;
      }
    }
    return true;
  }

  public static boolean linearSearch(int key, int[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == key) {
        return true;
      }
    }
    return false;
  }
}