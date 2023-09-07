package entity;

import java.util.Scanner;

public class Matrix {
    private float[][] matrix;
    private int rows;
    private int cols;

    public Matrix() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số hàng: ");
        this.rows = scanner.nextInt();
        System.out.print("Nhập số cột: ");
        this.cols = scanner.nextInt();
        this.matrix = new float[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.printf("Nhập phần tử hàng %d cột %d: ", row + 1, col + 1);
                matrix[row][col] = scanner.nextFloat();
            }
        }
    }

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.matrix = new float[rows][cols];
    }

    public Matrix(float[][] matrix, int rows, int cols) {
        this.matrix = matrix;
        this.rows = rows;
        this.cols = cols;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void showMatrix(){
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.printf("%.2f\t", matrix[row][col]);
            }
            System.out.println();
        }
    }

    public float getElementMatrix(int row, int col){
        return matrix[row][col];
    }

    public void setElementMatrix(int row, int col, float value){
        this.matrix[row][col] = value;
    }

    public Matrix transposeMatrix() {
        int rows = this.getRows();
        int cols = this.getCols();
        Matrix res = new Matrix(rows, cols);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                res.setElementMatrix(col, row, this.getElementMatrix(row, col));
            }
        }
        return res;
    }

    protected float[] getRow(int row){
        return matrix[row];
    }

}
