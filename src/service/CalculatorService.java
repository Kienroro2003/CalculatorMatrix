package service;

import entity.Matrix;

public interface CalculatorService{
    Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB);

    Matrix addMatrices(Matrix matrixA, Matrix matrixB);

    Matrix minusMatrices(Matrix matrixA, Matrix matrixB);

    Matrix multiplyMatrixWithK(Matrix matrix, float k);

    Matrix getInvertibleMatrix(Matrix matrix);


}
