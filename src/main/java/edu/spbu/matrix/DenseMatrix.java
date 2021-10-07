package edu.spbu.matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Плотная матрица
 */
public class DenseMatrix implements Matrix {

  public static class IllegalMatrixException extends Exception {
    public IllegalMatrixException(String message) {
      super(message);
    }
  }

  private int[][] matrix;
  private int width;
  private int height;

  public DenseMatrix(int width, int height) {
    assert width > 0 : "Matrix width must be a positive integer";
    assert height > 0 : "Matrix height must be a positive integer";

    this.width = width;
    this.height = height;
    matrix = new int[height][width];
  }

  /**
   * Создаёт матрицу из компонент
   */
  public DenseMatrix(int[][] matrix) {
    assert matrix.length > 0 : "Matrix must have at least one row";

    height = matrix.length;
    width = matrix[0].length;

    assert width > 0 : "Matrix must have at least one column";
    for (int[] row : matrix) {
      assert row.length == width : "All rows of the matrix must have the same width";
    }

    this.matrix = matrix.clone();
  }

  /**
   * Загружает матрицу из файла
   */
  public DenseMatrix(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line = br.readLine();

      if (line == null) {
        throw new RuntimeException("File is empty");
      }

      ArrayList<int[]> matrix = new ArrayList<>();

      int[] initialRow = readInitialRowFromLine(line);
      matrix.add(initialRow);

      width = initialRow.length;

      while ((line = br.readLine()) != null) {
        matrix.add(readRowFromLine(line, width, matrix.size() + 1));
      }

      height = matrix.size();
      this.matrix = new int[height][width];
      matrix.toArray(this.matrix);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private int[] readInitialRowFromLine(String line) {
    ArrayList<Integer> list = new ArrayList<>();

    try (Scanner scanner = new Scanner(line)) {
      while (scanner.hasNextInt()) {
        list.add(scanner.nextInt());
      }

      if (scanner.hasNext()) {
        try {
          scanner.skip(scanner.delimiter());
        } catch (NoSuchElementException e) {
        }

        String rest = scanner.nextLine();
        throw new RuntimeException(
            "Unable to read matrix: encountered token that cannot be interpreted as an integer on line 1\n    "
                + rest.substring(0, Math.min(30, rest.length()))
                + "\n    ^\nMake sure the file contains integers separated by spaces");
      }
    }

    return convertToArray(list);
  }

  private int[] convertToArray(ArrayList<Integer> list) {
    int size = list.size();
    int[] array = new int[size];
    for (int i = 0; i < size; ++i) {
      array[i] = list.get(i).intValue();
    }
    return array;
  }

  private int[] readRowFromLine(String line, int width, int lineNumber) {
    int[] array = new int[width];

    try (Scanner scanner = new Scanner(line)) {
      int i;
      for (i = 0; i < width && scanner.hasNextInt(); ++i) {
        if (scanner.hasNextInt()) {
          array[i] = scanner.nextInt();
        }
      }

      if (i < width) {
        throw new RuntimeException("Expected line " + lineNumber + " to contain " + width
            + " integer values for components, but less were found");
      }
      if (scanner.hasNextInt()) {
        throw new RuntimeException("Expected line " + lineNumber + " to contain " + width
            + " integer values for components, but more were found");
      }
      if (scanner.hasNext()) {
        try {
          scanner.skip(scanner.delimiter());
        } catch (NoSuchElementException e) {
        }

        String rest = scanner.nextLine();
        System.out.println(rest.length());
        throw new RuntimeException(
            "Unable to read matrix: encountered token that cannot be interpreted as an integer on line " + lineNumber
                + "\n    " + rest.substring(0, Math.min(30, rest.length()))
                + "\n    ^\nMake sure the file contains integers separated by spaces");
      }
    }

    return array;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();

    for (int[] row : this.matrix) {
      for (int value : row) {
        sb.append(value);
        sb.append(" ");
      }
      sb.append("\n");
    }
    return sb.toString();
  }

  public static void main(String[] args) {
    DenseMatrix m1 = new DenseMatrix("./matrix.txt");
    DenseMatrix m2 = new DenseMatrix("./matrix2.txt");
    System.out.println(m1.mul(m2));
  }

  public DenseMatrix mul(DenseMatrix matrix) {
    assert this.width == matrix.height : "Impossible to multiply matrices width such dimensions";

    DenseMatrix result = new DenseMatrix(matrix.width, this.height);
    for (int i = 0; i < this.height; ++i) {
      for (int j = 0; j < matrix.width; ++j) {
        int element = 0;
        for (int k = 0; k < this.width; ++k) {
          element += this.matrix[i][k] * matrix.matrix[k][j];
        }
        result.matrix[i][j] = element;
      }
    }

    return result;
  }

  /**
   * однопоточное умножение матриц должно поддерживаться для всех 4-х вариантов
   *
   * @param o
   * @return
   */
  @Override
  public Matrix mul(Matrix o) {
    return null;
  }

  /**
   * многопоточное умножение матриц
   *
   * @param o
   * @return
   */
  @Override
  public Matrix dmul(Matrix o) {
    return null;
  }

  /**
   * спавнивает с обоими вариантами
   * 
   * @param o
   * @return
   */
  @Override
  public boolean equals(Object o) {
    if (o instanceof DenseMatrix) {
      return false;
    }
    return false;
  }

}
