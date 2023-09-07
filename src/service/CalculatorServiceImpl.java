package service;

import entity.Matrix;

public class CalculatorServiceImpl implements CalculatorService{
    private boolean isSquareMatrix(int rows, int cols){
        return rows == cols;
    }

    private boolean areMatricesSameSize(Matrix matrixA, Matrix matrixB){
        if (matrixA == null || matrixB == null) {
            return false;
        }
        int numRows1 = matrixA.getRows();
        int numCols1 = matrixA.getCols();
        int numRows2 = matrixB.getRows();
        int numCols2 = matrixB.getCols();

        return numRows1 == numRows2 && numCols1 == numCols2;
    }


    @Override
    public Matrix multiplyMatrix(Matrix matrixA, Matrix matrixB) {
        int rowsA = matrixA.getRows();
        int colsA = matrixA.getCols();
        int rowsB = matrixB.getRows();
        int colsB = matrixB.getCols();
        if(colsA != rowsB){
            throw new IllegalArgumentException("Kích thước ma trận không hợp lệ cho phép nhân ma trận.");
        }
        Matrix matrixC = new Matrix(rowsA, colsB);
        for (int row = 0; row < rowsA; row++) {
            for (int col = 0; col < colsB; col++) {
                for (int k = 0; k < rowsA; k++) {
                    matrixC.setElementMatrix(row, col, matrixC.getElementMatrix(row, col) + matrixA.getElementMatrix(row, k) * matrixB.getElementMatrix(k, col));
                }
            }
        }
        return matrixC;
    }


    @Override
    public Matrix addMatrices(Matrix matrixA, Matrix matrixB) {
        if(!areMatricesSameSize(matrixA,matrixB)){
            throw new IllegalArgumentException("Kích thước các ma trận không hợp lệ cho phép cộng ma trận.");
        }
        int rows = matrixA.getRows();
        int cols = matrixA.getCols();
        Matrix res = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                res.setElementMatrix(row, col, matrixA.getElementMatrix(row, col) + matrixB.getElementMatrix(row,col));
            }
        }
        return res;
    }

    @Override
    public Matrix minusMatrices(Matrix matrixA, Matrix matrixB) {
        if(areMatricesSameSize(matrixA,matrixB)){
            throw new IllegalArgumentException("Kích thước các ma trận không hợp lệ cho phép trừ ma trận.");
        }
        int rows = matrixA.getRows();
        int cols = matrixA.getCols();
        Matrix res =new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                res.setElementMatrix(row, col, matrixA.getElementMatrix(row, col) - matrixB.getElementMatrix(row,col));
            }
        }
        return res;
    }

    @Override
    public Matrix multiplyMatrixWithK(Matrix matrix, float k) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        Matrix res =new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                res.setElementMatrix(row, col, matrix.getElementMatrix(row, col) * k);
            }
        }
        return res;
    }
}
