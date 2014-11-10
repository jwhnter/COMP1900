/**
 * Factorial
 * Joshua Hunter
 * Lab 8
 * Section 103
 */

import java.util.Scanner;

public class Factorial {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    System.out.print("n=");
    System.out.println(factorial(input.nextInt()));
  }

  public static int factorial(int n) {
    if (n == 0) {
      return 1; // 0! == 1 by definition
    } else if (n > 1) {
      return (n * factorial(n - 1));
    } else {  // if n == 1
      return n;
    }
  }
}
