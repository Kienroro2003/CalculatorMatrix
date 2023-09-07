package repository;

import entity.Matrix;

import java.util.List;

public interface FileWriteManagerImpl extends FileManager{
    void writeFile(List<Matrix> list);
}
