package org.tof.example;

public class JNIExample {

  public static void main(String... args) {
      final String returnedValue = new DoTheJob().doTheJobNative();
      System.out.println(returnedValue);
  }
}
