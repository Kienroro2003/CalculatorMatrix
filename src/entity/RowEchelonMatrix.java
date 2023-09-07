package entity;

public class RowEchelonMatrix extends Matrix {

    public static float gcd(float a, float b) {
        if (b == 0) {
            return a;
        } else {
            return gcd(b, a % b);
        }
    }

    // Hàm tính bội chung nhỏ nhất (LCM) dựa trên GCD
    public static float lcm(float a, float b) {
        return (a * b) / gcd(a, b);
    }


    public Matrix convertToRowEchelonMatrix() {
        Matrix matrix = new Matrix(this.getMatrix(), this.getRows(), this.getCols());
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
            colTarget++;
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

    public static void main(String[] args) {
        float[][] arrMatrix = {
                {4, 3, 3, -1, 4},
                {3, -1, 3, -2,1},
                {3,1,-2,1,0},
                {5,4,-2,1,1}
        };
        RowEchelonMatrix matrix = new RowEchelonMatrix();
        Matrix matrix1 = matrix.convertToRowEchelonMatrix();
        matrix1.showMatrix();
    }
}

