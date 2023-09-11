/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package entity;

import java.util.Scanner;

public class Matrix implements Cloneable {
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

    public Matrix(float[][] matrix) {
        this.matrix = matrix;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
    }

    public float[][] getMatrix() {
        return matrix;
    }

    public int getRows() {
        return matrix.length;
    }

    public int getCols() {
        return matrix[0].length;
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

    private static float gcd(float a, float b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Hàm tính bội chung nhỏ nhất (LCM) dựa trên GCD
    private static float lcm(float a, float b) {
        return (a * b) / gcd(a, b);
    }


    public Matrix convertToRowEchelonMatrix() {
        Matrix matrix = new Matrix(this.getMatrix().clone(), this.getRows(), this.getCols());
        if(this.getRows() == this.getCols() && this.getRows() == 2)return matrix;
        else{
            for (int row = 1; row < this.getRows(); row++) {
                int colTarget = findPivot(matrix.getRow(row - 1));
                if(colTarget != this.getCols()){
                    matrix.showMatrix();
                    float pivotAbove = this.getElementMatrix(row - 1, colTarget);
                    System.out.println("-----------------------------------");
                    for(int k = row; k < this.getRows(); k++){
                        float pivotFocus = this.getElementMatrix(k, colTarget);
                        float lcm = lcm(pivotAbove, pivotFocus);
                        float num1 = lcm / pivotAbove;
                        float num2 = lcm / pivotFocus;

                        System.out.printf("%.2fh%d + %.2fh%d -> h%d\n", num2, k, num1, row - 1, k);
                        for (int col = colTarget; col < this.getCols(); col++) {
                            matrix.setElementMatrix(k, col, getElementMatrix(row - 1, col) * -num1 + getElementMatrix(k, col) * num2);
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private int findPivot(float[] row){
        int cols = row.length;
        int col = 0;
        for (; col < cols; col++) {
            if(row[col] != 0)return col;
        }
        return col;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
