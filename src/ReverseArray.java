/**
 * ReverseArray
 * Joshua Hunter
 * Lab 8
 * Section 103
 */
public class ReverseArray {
  public static void main(String[] args) {
    char[] array = {
        'Y','a','y',' ','r','e','c','u','r','s','i','o','n','!'
    };

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
    }
    System.out.println();

    reverse(array, 0, array.length - 1);

    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i]);
    }
  }

  public static void reverse(char[] array, int first, int last) {
    if (last > first) {
      char tmp = array[first];
      array[first] = array[last];
      array[last] = tmp;
      reverse(array, first + 1, last - 1);
    }
  }
}
