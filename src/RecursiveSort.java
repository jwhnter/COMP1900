/**
 * RecursiveSort
 * Joshua Hunter
 * Lab 8 Homework
 * Section 103
 */
public class RecursiveSort {
  public static void main(String[] args) {
    int[] array = new int[1000];

    randomize(array); // fill the array with random data
    maxSort(array, 0, array.length - 1);
  }

  public static void randomize(int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = (int)(Math.random() * 1000);
    }
  }

  public static void maxSort(int[] array, int first, int last) {
    if (first < last) {
      int max = array[first];
      int maxIndex = first;

      // find the largest element
      for (int i = first; i < last; i++) {
        if (array[i] > max) {
          max = array[i];
          maxIndex = i;
        }
      }

      // swap the largest element with the last unsorted element
      int tmp = array[maxIndex];
      array[maxIndex] = array[last];
      array[last] = tmp;

      maxSort(array, first + 1, last - 1);
    }
  }
}