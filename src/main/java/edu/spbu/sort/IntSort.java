package edu.spbu.sort;

import java.util.Collections;
import java.util.List;

/**
 * Created by artemaliev on 07/09/15.
 */
public class IntSort {
  private static int[] merge(int[] array, int start, int middle, int end) {
    int[] result = new int[end - start];

    int p1 = start, p2 = middle, p = 0;
    while (p1 < middle && p2 < end) {
      if (array[p1] < array[p2]) {
        result[p++] = array[p1++];
      } else {
        result[p++] = array[p2++];
      }
    }
    while (p1 < middle) {
      result[p++] = array[p1++];
    }
    while (p2 < end) {
      result[p++] = array[p2++];
    }

    return result;
  }

  private static void sort(int[] array, int start, int end) {
    if (end - start < 2) {
      return;
    }

    int middle = start + (end - start) / 2;
    sort(array, start, middle);
    sort(array, middle, end);

    int[] merged = merge(array, start, middle, end);
    for (int i = 0, m = merged.length; i < m; i++) {
      array[start + i] = merged[i];
    }
  }

  public static void sort(int[] array) {
    sort(array, 0, array.length);
  }

  public static void sort(List<Integer> list) {
    Collections.sort(list);
  }
}
