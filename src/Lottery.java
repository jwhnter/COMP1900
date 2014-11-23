import java.util.Scanner;

public class Lottery {
  public int size;
  public int max;
  public int bonusMax;
  private int bonus;
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
  }

  /**
   * Play a simulated lottery.
   * @param playerNumbers numbers chosen by the player
   * @param playerBonus   bonus number chosen by the player
   */
  public void play(int[] playerNumbers, int playerBonus) {
    draw(size, max);

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
      System.out.println("\nCongratulations, you won!");
    } else {
      System.out.println("\nBetter luck next time!");
    }

    System.out.println("You had a " + calcOdds(size, max, bonusMax)
        + "% chance of winning.");
  }

  /**
   * Generate random winning numbers.
   * @param k how many random numbers to draw
   * @param n max value for those numbers
   * @return  the array of random numbers
   */
  public void draw(int k, int n) {
    numbers = new int[size];

    // assign a random number from 1 to n for all elements in the array
    for (int i = 0; i < k; i++) {
      numbers[i] = (int)(Math.random() * n + 1);
    }

    bonus = (int)(Math.random() * n + 1);
  }

  /**
   * Calculate odds of winning the lottery.
   * @param k how many numbers will be drawn
   * @param n max value for those numbers
   * @param m max value for a separate, bonus number
   * @return  odds of winning as a percentage.
   */
  public static double calcOdds(int k, int n, int m) {
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
  public static boolean matchNumbers(int[] refArray, int[] searchArray) {
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
