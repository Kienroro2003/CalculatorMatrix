package repository;

import entity.Matrix;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriteManager implements FileWriteManagerImpl{
    @Override
    public void writeFile(List<Matrix> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(url))) {
            for (int i = 0; i < list.size(); i++) {
                Matrix matrix = list.get(i);
                int rows = matrix.getRows();
                int cols = matrix.getCols();
                writer.write("Số hàng: " + rows);
                writer.newLine();
                writer.write("Số cột: " + cols);
                writer.newLine();
                for(int row = 0; row < rows; row++){
                    for (int col = 0; col < cols; col++) {
                        writer.write(matrix.getElementMatrix(row, col) + " ");
                    }
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
