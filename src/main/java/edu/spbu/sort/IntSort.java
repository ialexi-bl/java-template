package edu.spbu.sort;

import java.util.Collections;
import java.util.List;

/**
 * Created by artemaliev on 07/09/15.
 */
public class IntSort {
  private static void merge(int[] array, int start, int middle, int end, int[] buffer) {
    int p1 = start;
    int p2 = middle;
    int p = 0;
    while (p1 < middle && p2 < end) {
      if (array[p1] < array[p2]) {
        buffer[p++] = array[p1++];
      } else {
        buffer[p++] = array[p2++];
      }
    }
    while (p1 < middle) {
      buffer[p++] = array[p1++];
    }
    while (p2 < end) {
      buffer[p++] = array[p2++];
    }

    for (int i = 0, m = end - start; i < m; i++) {
      array[start + i] = buffer[i];
    }
  }

  private static void sort(int[] array, int start, int end, int buffer[]) {
    if (end - start < 2) {
      return;
    }

    int middle = start + (end - start) / 2;
    sort(array, start, middle, buffer);
    sort(array, middle, end, buffer);

    merge(array, start, middle, end, buffer);
  }

  public static void sort(int[] array) {
    int[] buffer = new int[array.length];
    sort(array, 0, array.length, buffer);
  }

  public static void sort(List<Integer> list) {
    Collections.sort(list);
  }
}
