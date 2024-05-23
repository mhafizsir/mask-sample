package id.mhafizsir;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
  public static void main(String[] args) {

    User user = new User();
    user.setEmail("aaaa@bbb.com");
    user.setMobilePhone("081234567890");

    System.out.println("Masked User using toString: " + user);
    log.info("Masked User using log: {}", user);
  }
}
