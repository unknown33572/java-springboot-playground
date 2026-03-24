package com.study.java_springboot.java.exception;

public class NetworkClientException extends Exception {

  private String errorCode;

  public NetworkClientException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
  }

  public String getErrorCode() {
    return errorCode;
  }

}
