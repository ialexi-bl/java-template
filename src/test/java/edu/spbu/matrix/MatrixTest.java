package edu.spbu.matrix;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatrixTest {
  /**
   * ожидается 4 таких теста
   */
  @Test
  public void mulDD() {
    try {
      Matrix m1 = new DenseMatrix("./src/test/java/edut/spbu/matrix/test-data/mul1-1.txt");
      Matrix m2 = new DenseMatrix("./src/test/java/edut/spbu/matrix/test-data/mul1-2.txt");
      Matrix expected = new DenseMatrix("./src/test/java/edut/spbu/matrix/test-data/mul1-result.txt");
      assertEquals(expected, m1.mul(m2));
    } catch (Exception e) {
    }
  }
}
