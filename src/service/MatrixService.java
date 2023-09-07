package service;

import entity.Matrix;

public interface MatrixService {
    void addMatrix(Matrix matrix);
    void deleteMatrix(int index);

    void showAllMatrices();

    void showMatrix(Matrix matrix);

    boolean isAddMatrix(Matrix matrix);

    Matrix getMatrix(int index);

    void showTwoMatricesLast();

    void saveData();
}
