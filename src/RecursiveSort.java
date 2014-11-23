/**
 * RecursiveSort
 * Joshua Hunter
 * Lab 8 Homework
 * Section 103
 */
public class RecursiveSort {
  public static void main(String[] args) {
    int[] array = new int[10];

    for (int i = 0; i < array.length; i++) {
      array[i] = (int)(Math.random() * 1000);
    } // fill the array with random data

    System.out.print("Unsorted: ");
    print(array);

    maxSort(array, array.length - 1);

    System.out.print("Sorted: ");
    print(array);
  }

  public static void print(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void maxSort(int[] array, int last) {
    if (last > 0) {
      int max = 0;

      // find the largest element
      for (int i = 0; i <= last; i++) {
        if (array[i] > array[max]) {
          max = i;
        }
      }

      // swap the largest element with the last unsorted element
      int tmp = array[max];
      array[max] = array[last];
      array[last] = tmp;

      // recurse through the array
      maxSort(array, last - 1);
    }
  }
}