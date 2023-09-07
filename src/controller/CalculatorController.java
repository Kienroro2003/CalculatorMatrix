package controller;

import entity.Matrix;
import service.CalculatorService;
import service.CalculatorServiceImpl;
import service.MatrixService;
import service.MatrixServiceImpl;

import java.util.Scanner;

public class CalculatorController {
    private MatrixService matrixService = new MatrixServiceImpl();
    private CalculatorService calculatorService = new CalculatorServiceImpl();
    public void menuMain(){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("**********CÁC CHỨC NĂNG CHÍNH**********");
            System.out.println("1. Thao tác với danh sách ma trận.");
            System.out.println("2. Thực hiện các phép tính.");
            System.out.println("3. Thoát.");
            System.out.print("Nhập sự lựa chọn của bạn: ");
            int choose = sc.nextInt();
            switch (choose){
                case 1:{
                    menuMatrix(sc);
                    break;
                }
                case 2:{
                    menuCalculator(sc);
                    break;
                }
                case 3:{
                    matrixService.saveData();
                    System.exit(0);
                    break;
                }
            }
        }
    }

    private void menuMatrix(Scanner sc) {
        while(true){
            System.out.println("**********CÁC CHỨC NĂNG THAO TÁC MA TRẬN**********");
            System.out.println("1. Thêm ma trận.");
            System.out.println("2. Xoá ma trận.");
            System.out.println("3. Hiển thị tất cả ma trận.");
            System.out.println("4. Hiển thi ma trận.");
            System.out.println("5. Thoát.");
            System.out.print("Nhập sự lựa chọn của bạn: ");
            int choose = sc.nextInt();
            switch (choose){
                case 1:{
                    Matrix matrix = new Matrix();
                    if(matrixService.isAddMatrix(matrix)){
                        matrixService.addMatrix(matrix);
                    }
                    break;
                }
                case 2:{
                    System.out.print("Nhập vị trí ma trận cần xoá: ");
                    int index = sc.nextInt();
                    matrixService.deleteMatrix(index);
                    break;
                }
                case 3:{
                    matrixService.showAllMatrices();
                    break;
                }
                case 4:{
                    System.out.print("Nhập vị trí ma trận cần hiển thị: ");
                    int index = sc.nextInt();
                    System.out.println("Chưa thật sự cần thiết");
                    break;
                }
                case 5:{
                    return;
                }
            }
        }
    }

    private void menuCalculator(Scanner sc) {
        while(true){
            System.out.println("**********CÁC CHỨC NĂNG TÍNH TOÁN MA TRẬN**********");
            System.out.println("1. Cộng 2 ma trận.");
            System.out.println("2. Trừ 2 ma trận.");
            System.out.println("3. Tích 2 ma trận.");
            System.out.println("4. Ma trận chuyển vị.");
            System.out.println("5. Nhân ma trận với K.");
            System.out.println("6. Thoát.");
            System.out.print("Nhập sự lựa chọn của bạn: ");
            int choose = sc.nextInt();
            matrixService.showTwoMatricesLast();
            switch (choose){
                case 1:{
                    System.out.print("Nhập vị trí ma trận thứ nhất: ");
                    int indexA = sc.nextInt();
                    Matrix matrixA = matrixService.getMatrix(indexA);
                    System.out.print("Nhập vị trí ma trận thứ hai: ");
                    int indexB = sc.nextInt();
                    Matrix matrixB = matrixService.getMatrix(indexB);
                    Matrix res = calculatorService.addMatrices(matrixA, matrixB);
                    if(matrixService.isAddMatrix(res)){
                        matrixService.addMatrix(res);
                    }
                    break;
                }
                case 2:{
                    System.out.print("Nhập vị trí ma trận thứ nhất: ");
                    int indexA = sc.nextInt();
                    Matrix matrixA = matrixService.getMatrix(indexA);
                    System.out.print("Nhập vị trí ma trận thứ hai: ");
                    int indexB = sc.nextInt();
                    Matrix matrixB = matrixService.getMatrix(indexB);
                    Matrix res = calculatorService.minusMatrices(matrixA, matrixB);
                    if(matrixService.isAddMatrix(res)){
                        matrixService.addMatrix(res);
                    }
                    break;
                }
                case 3:{
                    System.out.print("Nhập vị trí ma trận thứ nhất: ");
                    int indexA = sc.nextInt();
                    Matrix matrixA = matrixService.getMatrix(indexA);
                    System.out.print("Nhập vị trí ma trận thứ hai: ");
                    int indexB = sc.nextInt();
                    Matrix matrixB = matrixService.getMatrix(indexB);
                    Matrix res = calculatorService.multiplyMatrix(matrixA, matrixB);
                    if(matrixService.isAddMatrix(res)){
                        matrixService.addMatrix(res);
                    }
                    break;
                }
                case 4:{
                    System.out.print("Nhập vị trí ma trận thứ nhất: ");
                    int indexA = sc.nextInt();
                    Matrix matrixA = matrixService.getMatrix(indexA);
                    Matrix res = matrixA.transposeMatrix();
                    if(matrixService.isAddMatrix(res)){
                        matrixService.addMatrix(res);
                    }
                    break;
                }
                case 5:{
                    System.out.print("Nhập vị trí ma trận thứ nhất: ");
                    int indexA = sc.nextInt();
                    Matrix matrixA = matrixService.getMatrix(indexA);
                    System.out.print("Nhập hằng số K: ");
                    int k = sc.nextInt();
                    Matrix res = calculatorService.multiplyMatrixWithK(matrixA, k);
                    if(matrixService.isAddMatrix(res)){
                        matrixService.addMatrix(res);
                    }
                    break;
                }
                case 6:{
                    return;
                }
            }
        }
    }
}
