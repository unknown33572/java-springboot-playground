package com.study.java_springboot.java.exception;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws NetworkClientException {
    NetworkService networkService = new NetworkService();

    Scanner sc = new Scanner(System.in);

    while (true) {
      System.out.println("데이터를 입력하세요: ");
      String inputData = sc.nextLine();

      if (inputData.equals("exit")) {
        break;
      }
      networkService.sendMessage(inputData);
      System.out.println();
    }
        System.out.println("프로그램을 종료합니다.");
  }
}
