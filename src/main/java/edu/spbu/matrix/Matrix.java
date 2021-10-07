package edu.spbu.matrix;

/**
 *
 */
public interface Matrix {
  /**
   * однопоточное умнджение матриц должно поддерживаться для всех 4-х вариантов
   * 
   * @param matrix
   * @return
   */
  Matrix mul(Matrix matrix);

  /**
   * многопоточное умножение матриц
   * 
   * @param matrix
   * @return
   */
  Matrix dmul(Matrix matrix);

}
