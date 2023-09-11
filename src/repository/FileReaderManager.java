package repository;

import entity.Matrix;
import entity.SquareMatrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderManager implements FileReaderManagerImpl{
    @Override
    public List<Matrix> readFile() {
        List<Matrix> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(url))) {
            String line;
            int numRows = 0;
            int numCols = 0;
            Matrix matrix = null;
            int countRows = 0;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Số hàng: ")) {
                    numRows = Integer.parseInt(line.substring(9));
                    countRows = 0;
                } else if (line.startsWith("Số cột: ")) {
                    numCols = Integer.parseInt(line.substring(8));
                    if(numCols != numRows){
                        matrix = new Matrix(numRows, numCols);
                    }else{
                        matrix = new SquareMatrix(numRows, numCols);
                    }
                    list.add(matrix);
                } else if (!line.isEmpty()) {
                    String[] values = line.split(" ");
                    for (int i = 0; i < numCols; i++) {
                        matrix.setElementMatrix(countRows, i, Float.parseFloat(values[i]));
                    }
                    countRows++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
