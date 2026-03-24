package com.study.java_springboot.java.exception;

public class NetworkClient {
  private final String address;
  public boolean initError;
  public boolean connectError;
  public boolean sendError;

  public NetworkClient(String address) {
    this.address = address;
  }

  public void connect() throws NetworkClientException {
    if (connectError) {
      throw new NetworkClientException("connectError", address + "서버에 연결을 실패하였습니다.");
    }

    System.out.println(address + "서버에 연결이 되었습니다.");
  }

  public void send(String message) throws NetworkClientException {
    if (sendError) {
      throw new NetworkClientException("sendError", address + "서버에 메시지 전송을 실패하였습니다.");
    }

    System.out.println(address + "서버에 메시지: " + message + "가 전송되었습니다.");
  }

  public void initError(String data) throws NetworkClientException {
    if (data.contains("error")) {
      connectError = true;
    }

    System.out.println(address + "서버 초기화가 완료되었습니다. 데이터: " + data);
  }

  public void disconnent() {
    System.out.println(address + "서버와 연결이 끊어졌습니다.");
  }
}
