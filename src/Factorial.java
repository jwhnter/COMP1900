/**
 * Joshua Hunter
 * Lab 8
 * Section 103
 */

import java.util.Scanner;

public class Factorial {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("n=");
    System.out.println(factorialize(input.nextInt()));
  }

  public static int factorialize(int n) {
    if (n > 1) {
      return (n * factorialize(n - 1));
    } else {
      return n;
    }
  }
}
