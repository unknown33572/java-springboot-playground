package com.study.java_springboot.java.exception;

public class NetworkService {
  public void sendMessage(String data) throws NetworkClientException {
    String address = "http://localhost:8080";
    NetworkClient networkClient = new NetworkClient(address);

    networkClient.initError(data);
    networkClient.connect();
    networkClient.send(data);
    networkClient.disconnent();
  }
}
