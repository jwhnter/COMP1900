/**
 * PartitionBenchmark
 * Compare two different methods for partitioning arrays: a simple partition,
 * and a partition-in-place method.
 * Joshua Hunter
 * Lab Section 103
 * 10/27/2014
 */
public class PartitionBenchmark {
  public static void main(String[] args) {
    long start;
    long stop;
    long elapsedTime = 0;

    // First partition method
    for (int i = 0; i <= 1000; i++) {
      // create a new array of 100000 random numbers
      double[] randArray = new double[100000];
      for (int j = 0; j < randArray.length; j++) {
        randArray[j] = Math.random();
      }
      // start the stopwatch
      start = System.currentTimeMillis();
      // call a partition method on the array
      partition(randArray);
      // stop the stopwatch
      stop = System.currentTimeMillis();
      // compute the time elapsed
      elapsedTime += stop - start;
    }
    System.out.println("Average time (partition): " + (elapsedTime / 1000)
        + " ms");

    // reset the elapsed time clock
    elapsedTime = 0;

    // Second partition method: partition-in-place
    for (int i = 0; i <= 1000; i++) {
      // create a new array of 100000 random numbers
      double[] randArray = new double[100000];
      for (int j = 0; j < randArray.length; j++) {
        randArray[j] = Math.random();
      }
      // start the stopwatch
      start = System.currentTimeMillis();
      // call a partition method on the array
      partitionInPlace(randArray);
      // stop the stopwatch
      stop = System.currentTimeMillis();
      // compute the time elapsed
      elapsedTime += stop - start;
    }
    System.out.println("Average time (partition-in-place): "
        + (elapsedTime / 1000) + " ms");

  }

  public static double[] partition(double[] a) {
    double pivotValue = a[0]; // pivot element
    int j = 0;  // keeps track of the elements in the lesser array
    int k = 0;  // keeps track of the elements in the greater array
    double[] lesser = new double[a.length];   // values smaller than the pivot
    double[] greater = new double[a.length];  // values greater than the pivot
    double[] result = new double[a.length];   // the partitioned array

    for (int i = 1; i < a.length; i++) {
      // store elements smaller than the pivot value in the lesser array
      if (a[i] < pivotValue) {
        lesser[j] = a[i];
        j++;
        // store elements greater than or equal to the pivot value in the
        // greater array
      } else {
        greater[k] = a[i];
        k++;
      }
    }
    // Combine the two arrays with the pivot value in between to get the final,
    // partitioned array
    for (int i = 0; i < a.length; i++) {
      if (i < j) {
        result[i] = lesser[i];
      } else if (i == j) {
        result[i] = pivotValue;
      } else {
        result[i] = greater[i];
      }
    }
    return result;
  }

  public static double[] partitionInPlace(double[] a) {
    double pivotValue = a[0];
    int j = 0;  // j keeps track of which elements have already been placed

    // start by swapping the pivot value with the value at the rightmost index
    a[0] = a[a.length - 1];
    a[a.length - 1] = pivotValue;

    // go through the array (except the rightmost index) to find the elements
    // that are < pivotValue
    for (int i = 0; i < a.length - 1; i++) {
      // if a[i] is < pivotValue, then swap a[i] and a[j] and increment j
      if (a[i] < pivotValue) {
        double temp = a[i];
        a[i] = a[j];
        a[j] = temp;

        j++;
      }
    }

    // now move the pivot back from its position on the right
    double temp = a[j];
    a[j] = a[a.length - 1];
    a[a.length - 1] = temp;

    // return the partitioned array
    return a;
  }
}
