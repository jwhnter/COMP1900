/**
 * Lottery
 * Project 2
 * Joshua Hunter
 */

import java.util.Random;
import java.util.Scanner;

public class Lottery {
  public int    size;
  public int    max;
  public  int   bonusMax;
  private int   bonus;
  private int[] numbers;

  /**
   * Set the characteristics of the lottery.
   * k: size of list of numbers to be drawn
   * n: max value for those numbers
   * m: max value for the bonus number
   */
  public void setVars() {
    Scanner input = new Scanner(System.in);

    System.out.println("Enter the lottery variables");
    do {
      System.out.print("Draw how many numbers? k=");
      size = input.nextInt();
      if (size < 1) {
        System.out.println("You must draw at least 1 number");
      }
    } while (size < 1);

    do {
      System.out.print("Max value for those numbers? n=");
      max = input.nextInt();
      if (max < size) { // can't draw without repeating if max < size
        System.out.println("Not enough numbers to draw from");
      }
    } while (max < size);

    do {
      System.out.print("Max value for the bonus number? m=");
      bonusMax = input.nextInt();
      if (bonusMax < 1) {
        System.out.println("Bonus number cannot be less than 1");
      }
    } while (bonusMax < 1);
  }

  /**
   * Play a simulated lottery.
   * @param playerNumbers numbers chosen by the player
   * @param playerBonus   bonus number chosen by the player
   */
  public void play(int[] playerNumbers, int playerBonus) {
    drawNumbers();

    System.out.print("Your numbers:    ");
    for (int i = 0; i < playerNumbers.length; i++) {
      System.out.print(playerNumbers[i] + " ");
    }
    System.out.println(playerBonus);

    System.out.print("Winning numbers: ");
    for (int i = 0; i < numbers.length; i++) {
      System.out.print(numbers[i] + " ");
    }
    System.out.println(bonus);

    if (matchNumbers(playerNumbers, numbers)
        && bonus == playerBonus) {
      System.out.println("\nCongratulations, you won the jackpot!");
    } else {
      System.out.println("\nYou didn't win the jackpot. :(");
    }

    System.out.println("You had a " + (calcOdds(size, max, bonusMax) * 100)
        + "% chance of winning.");
  }

  /**
   * Generate random winning numbers.
   */
  private void drawNumbers() {
    Random rand = new Random();
    numbers = new int[size];

    for (int i = 0; i < numbers.length; i++) {
      int tmp = rand.nextInt(max) + 1;
      if (find(tmp, numbers)) {  // if the number has already been drawn
        i--;                     // try again
      } else {
        numbers[i] = tmp;
      }
    }
    bonus = rand.nextInt(bonusMax) + 1;
  }

  /**
   * Calculate odds of winning the lottery.
   * @param k how many numbers will be drawn
   * @param n max value for those numbers
   * @param m max value for a separate, bonus number
   * @return  odds of winning as a percentage.
   */
  private static double calcOdds(int k, int n, int m) {
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
    /*
     * return the inverse of number of tickets to get the probability of
     * selecting the winner
     */
    return 1 / ((numerator / denominator) * m);
  }

  /**
   * Checks whether two arrays contain the same elements.
   * @param refArray    the reference array
   * @param searchArray an array to be compared with the reference array
   * @return            true if both contain the same elements, otherwise false
   */
  private static boolean matchNumbers(int[] refArray, int[] searchArray) {
    for (int i = 0; i < refArray.length; i++) {
      if (!find(refArray[i], searchArray)) {
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
  public static boolean find(int key, int[] list) {
    for (int i = 0; i < list.length; i++) {
      if (list[i] == key) {
        return true;
      }
    }
    return false;
  }
}