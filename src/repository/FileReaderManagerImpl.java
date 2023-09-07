package repository;

import entity.Matrix;

import java.util.List;

public interface FileReaderManagerImpl extends FileManager{
    List<Matrix> readFile();
}
