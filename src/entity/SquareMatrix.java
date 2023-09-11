package entity;

import exception.MatrixNotInvertibleException;
import service.CalculatorService;
import service.CalculatorServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class SquareMatrix extends Matrix {

    public SquareMatrix(int rows, int cols) {
        super(rows, cols);
    }

    public SquareMatrix(float[][] matrix) {
        super(matrix);
    }

    public float[] getMainDiagonal() {
        float[] arr = new float[this.getRows()];
        for (int i = 0; i < this.getRows(); i++) {
            arr[i] = this.getElementMatrix(i, i);
        }
        return arr;
    }

    protected float getMultiplyMain(){
        float res = 1;
        for (int i = 0; i < this.getMainDiagonal().length; i++) {
            res *= this.getMainDiagonal()[i];
        }
        return res;
    }

    public float calculateMainDiagonalProduct() {
        float res = 1;
        for (int i = 0; i < this.getMainDiagonal().length; i++) {
            res *= this.getMainDiagonal()[i];
        }
        return res;
    }

    public float[] getSubMainDiagonal() {
        float[] arr = new float[this.getRows()];
        for (int i = 0; i < this.getRows(); i++) {
            arr[i] = this.getElementMatrix(this.getRows() - i - 1, i);
        }
        return arr;
    }

    private Matrix getSubMatrix(Matrix matrix, int row, int col) {
        int n = matrix.getRows() - 1;
        Matrix newMatrix = new Matrix(n, n);
        int currentCol = 0;
        int currentRow = 0;
        for (int i = 0; i < matrix.getRows(); i++) {
            if (i == row) continue;
            for (int j = 0; j < matrix.getRows(); j++) {
                if (j == col) continue;
                newMatrix.setElementMatrix(currentRow, currentCol++, matrix.getElementMatrix(i, j));
                if (currentCol == n) {
                    currentCol = 0;
                    currentRow++;
                }
            }
        }
        return newMatrix;
    }

    private SquareMatrix getAdjMatrix() {
        SquareMatrix resMatrix = new SquareMatrix(this.getRows(), this.getRows());
        for (int row = 0; row < this.getRows(); row++) {
            for (int col = 0; col < this.getRows(); col++) {
                resMatrix.setElementMatrix(row, col, (int) Math.pow(-1, row + col) * getDet(getSubMatrix(this, row, col), 0, 0));
            }

        }
        return resMatrix;
    }

    public Matrix getInvertibleMatrix() throws MatrixNotInvertibleException {
        float detMatrix = this.getDet(this, 0,  new ArrayList<>(), new ArrayList<>());
        if (detMatrix == 0) {
            throw new MatrixNotInvertibleException("Vì định thức là 0 nên ma trận này không khả nghịch");
        }
        SquareMatrix adj = this.getAdjMatrix();
        return CalculatorServiceImpl.getInstance().multiplyMatrixWithK(adj.transposeMatrix(), 1 / detMatrix);

    }

    private float getDet(Matrix matrix, int res, int row) {
        if (matrix.getRows() == 2) {
            return matrix.getElementMatrix(0, 0) * matrix.getElementMatrix(1, 1) - matrix.getElementMatrix(1, 0) * matrix.getElementMatrix(0, 1);
        } else if (matrix.getRows() == 1) {
            return matrix.getElementMatrix(0, 0);
        }
//        for (int row = 0; row < matrix.getRows(); row++) {
        for (int i = 0; i < matrix.getCols(); i++) {
            float detARowCol = matrix.getElementMatrix(0, i);
            Matrix subMatrix = getSubMatrix(matrix, 0, i);
            float detMRowCol = getDet(subMatrix, res, row + 1);
            res += ((row + i) % 2 == 0 ? 1 : -1) * detARowCol * detMRowCol;
        }
//        }
        return res;
    }


    public int convertToRowEchelonMatrixSquare() throws CloneNotSupportedException {
        SquareMatrix matrix = (SquareMatrix) this.clone();
        matrix.setElementMatrix(0,0,10);
        matrix.showMatrix();
        this.showMatrix();
        return 0;
    }

    private float getDet(Matrix matrix, int res, List<Integer> listCol, List<Integer> listRow) {
        if (matrix.getRows() - listCol.size() == 2) {
            float main = 1;
            float sub = 1;
            int count = 0;
            for (int i = 0; i < matrix.getRows(); i++) {
                if(listRow.contains(i))continue;
                for (int col = 0; col < matrix.getCols(); col++) {
//                    for (; k < listCol.size(); k++) {
//                        if(listCol.get(k) == col){
//                            isContinue = true;
//                            k++;
//                            break;
//                        };
//                    }
                    if(listCol.contains(col)){
                        continue;
                    }
                    float elementMatrix = matrix.getElementMatrix(i, col);
                    switch (count) {
                        case 0:
                        case 3: {
                            main *= elementMatrix;
                            break;
                        }
                        case 1:
                        case 2: {
                            sub *= elementMatrix;
                            break;
                        }
                    }
                    count++;
                }
            }
            return main - sub;
        } else {
            for (int i = 0; i < matrix.getRows(); i++) {
                if(listRow.contains(i)){
                    continue;
                }
                listRow.add(i);
                for (int j = 0; j < matrix.getCols(); j++) {
                    boolean isContinue = false;
                    if(listCol.contains(j)){
                        continue;
                    }
                    listCol.add(j);
                    float detARowCol = matrix.getElementMatrix(i, j);
                    float detMRowCol = getDet(matrix, 0, listCol, listRow);
                    listCol.remove(listCol.size() - 1);
                    res += ((i + j) % 2 == 0 ? 1 : -1) * detARowCol * detMRowCol;
                }
                listRow.remove(listRow.size() - 1);
            }

            return res;
        }
    }

    public static void main(String[] args) {
//        float[][] arrMatrix1 = {
//                {1,2,3,9},
//                {2,10,4,7},
//                {4,5,6,11},
//                {6,9,0,4}
//        };
//        float[][] arrMatrix2 = {
//                {1,2,3,9},
//                {2,10,4,7},
//                {4,5,6,11},
//        };
//        List<Matrix> list = new ArrayList<>();
//        Matrix matrix1 = new Matrix(arrMatrix2);
//        Matrix matrix2 = new SquareMatrix(arrMatrix1);
//        list.add(matrix1);
//        list.add(matrix2);
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getClass().getSimpleName());
//            if(list.get(i).getClass().getSimpleName().equals("SquareMatrix")){
//                SquareMatrix squareMatrix = (SquareMatrix) list.get(i);
//                squareMatrix.getAdjMatrix().showMatrix();
//            }
//        }
//        float[][] arr = {
//                {1, 4, 7},
//                {2, 5, 8},
//                {3, 6, 9}
//        };
//        float[][] arr = {
//                {4, 5, 0, 0, 1},
//                {3, 0, 0, 0, 0},
//                {5, 1, 2, 0, 1},
//                {2, 6, 0, -1, 1},
//                {-6, 3, 1, 0, 1}
//        };
        float[][] arr = {
                {1, 1, 2, 2},
                {-3, 1, 5, 1},
                {-2, 0, 0, 1},
                {2, 1, 3, -1},
        };
        try {
//            Matrix matrix = new SquareMatrix(arr).getInvertibleMatrix();
//            matrix.showMatrix();
            SquareMatrix matrix = new SquareMatrix(arr);
            matrix.convertToRowEchelonMatrixSquare();
        } catch (MatrixNotInvertibleException e) {
            System.out.println(e.getMessage());
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
