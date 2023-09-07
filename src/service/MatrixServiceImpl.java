package service;

import entity.Matrix;
import repository.FileReaderManager;
import repository.FileReaderManagerImpl;
import repository.FileWriteManager;
import repository.FileWriteManagerImpl;

import java.util.List;
import java.util.Scanner;

public class MatrixServiceImpl implements MatrixService {
    FileReaderManagerImpl reader = new FileReaderManager();
    FileWriteManagerImpl writer = new FileWriteManager();
    List<Matrix> list;

    public MatrixServiceImpl() {
        list = reader.readFile();
    }

    @Override
    public void addMatrix(Matrix matrix) {
        list.add(matrix);
        System.out.println("Them thanh cong!");
    }

    @Override
    public void deleteMatrix(int index) {
        list.remove(index - 1);
    }

    @Override
    public void showAllMatrices() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("Ma tran thu %d:\n", i + 1);
            Matrix matrix = list.get(i);
            showMatrix(matrix);
        }
    }

    @Override
    public void showMatrix(Matrix matrix) {
        int rows = matrix.getRows();
        int cols = matrix.getCols();
        for(int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++) {
                System.out.printf("%.2f\t", matrix.getElementMatrix(row, col));
            }
            System.out.println();
        }
    }


    @Override
    public boolean isAddMatrix(Matrix matrix) {
        Scanner scanner = new Scanner(System.in);
        showMatrix(matrix);
        System.out.println("Bạn có muốn lưu ma trận này không (Y/N)? ");
        String confirm = scanner.nextLine();
        if(confirm.equals("Y"))return true;
        return false;
    }

    @Override
    public Matrix getMatrix(int index) {
        return list.get(index - 1);
    }

    @Override
    public void showTwoMatricesLast() {
        int size = list.size();
        System.out.println("Ma trận thứ " + (list.size() - 1) + ":");
        showMatrix(list.get(size - 2));
        System.out.println("Ma trận thứ " + (list.size()) + ":");
        showMatrix(list.get(size - 1));
    }

    @Override
    public void saveData() {
        writer.writeFile(list);
    }
}
