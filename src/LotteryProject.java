/*
 * LotteryProject
 * Hold a virtual lottery drawing, proving how futile the lottery is in the process!
 * Joshua Hunter
 * 10/23/2014
 *
 */

import java.util.Scanner;

/**
 * @author Joshua Hunter
 * @since  10-23-2014
 */
public class LotteryProject {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    int     size;           // k - how many distinct numbers
    int     max;            // n - the range for the lottery numbers
    int     bonusMax;       // m - the range for the bonus number
    int     bonus;          // bonus number chosen by user
    int     winningBonus;   // randomly drawn bonus number
    int[]   myNumbers;      // numbers entered by the user
    int[]   winningNumbers; // randomly drawn winners

    // get the lottery attributes from the user
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
      bonusMax = input.nextInt();
    } while (bonusMax < 1);

    // select numbers
    myNumbers = enterNumbers(size, max);
    do {
      System.out.print("Bonus number (1-" + bonusMax + "): ");
      bonus = input.nextInt();
    } while (bonus < 1 || bonus > bonusMax);

    // draw the random numbers
    winningNumbers = drawNumbers(size, max);
    winningBonus = (int)(Math.random() * bonusMax + 1);

    // display the user's numbers and the winning numbers
    System.out.print("Your numbers:    ");
    for (int i = 0; i < myNumbers.length; i++) {
      System.out.print(myNumbers[i] + " ");
    }
    System.out.println(bonus);

    System.out.print("Winning numbers: ");
    for (int i = 0; i < winningNumbers.length; i++) {
      System.out.print(winningNumbers[i] + " ");
    }
    System.out.println(winningBonus);

    if (containSameElements(myNumbers, winningNumbers)
        && bonus == winningBonus) {
      System.out.println("\nCongratulations, you won!");
    } else {
      System.out.println("\nBetter luck next time!");
    }

    System.out.println("You had a " + jackpotChance(size, max, bonusMax)
        + "% chance of winning.");
  }

  /**
   * Odds of winning the lottery.
   * @param k how many numbers will be drawn
   * @param n max value for those numbers
   * @param m max value for a separate, bonus number
   * @return  odds of winning as a percentage.
   */
  public static double jackpotChance(int k, int n, int m) {
  /*
   * The number of possible tickets for a given jackpot is
   *
   * | max * (max - 1) * (max - 2) * ... * (max - size + 1)
   * |----------------------------------------------------- * bonus
   * | size * (size - 1) * (size - 2) * ... 3 * 2 * 1
   */
    double numerator = 1;   // the numerator of the fraction above
    double denominator = 1; // the denominator of the fraction above

    for (int i = n; i >= n - k + 1; i--) {
      numerator = numerator * i;
    }
    for (int i = k; i > 0; i--) {
      denominator = denominator * i;
    }
    // return the inverse of the number of tickets to get the probability of
    // selecting a winning ticket
    return 1 / ((numerator / denominator) * m);
  }

  /**
   * Get user lottery numbers.
   * @param k how many numbers to select
   * @param n max value for those numbers
   * @return  the array of numbers chosen
   */
  public static int[] enterNumbers(int k, int n) {
    Scanner input = new Scanner(System.in);
    int[] numbers = new int[k]; //contains the chosen numbers
    int number;

    System.out.print("Enter " + k + " numbers between 1 and " + n + ": ");
    for (int i = 0; i < k; i++) {
      number = input.nextInt();
      if (linearSearch(number, numbers)) {
        System.out.println("Cannot use the same number twice");
        i--;
      }else if (number < 1 || number > n) {
        System.out.println("Number must be between 1 and " + n);
        i--;
      } else {
        numbers[i] = number;
      }
    }
    return numbers;
  }

  /**
   * Generate random winning numbers.
   * @param k how many random numbers to draw
   * @param n max value for those numbers
   * @return  the array of random numbers
   */
  public static int[] drawNumbers (int k, int n){
    int[] numbers = new int[k];

    // assign a random number from 1 to n for all elements in the array
    for (int i = 0; i < k; i++) {
      numbers[i] = (int)(Math.random() * n + 1);
    }
    return numbers;
  }

  /**
   * Checks whether two arrays contain the same elements.
   * @param refArray    the reference array
   * @param searchArray an array to be compared with the reference array
   * @return            false if any element in a is missing in b, otherwise true
   */
  public static boolean containSameElements(int[] refArray, int[] searchArray) {
    for (int i = 0; i < refArray.length; i++) {
      if (!linearSearch(refArray[i], searchArray)) {
        return false;
      }
    }
    return true;
  }

  /**
   * Search for an integer within an array.
   * @param key  the search term
   * @param list the array to be searched
   * @return     true if the key is found, otherwise false
   */
  public static boolean linearSearch(int key, int[] list) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == key) {
        return true;
      }
    }
    return false;
  }
}