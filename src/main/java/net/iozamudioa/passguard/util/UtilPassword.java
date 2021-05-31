package net.iozamudioa.passguard.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UtilPassword {

  private static final String REGEX_PASSWORD =
      "^(?=(?:.*\\d){2})(?=(?:.*[A-Z]){1})(?=(?:.*[@$?¡\\-_]){1})\\S{8,}$";



  @Autowired
  private PasswordEncoder passwordEncoder;

  public String encode(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }

  public Boolean isValid(String rawPassword) {
    return rawPassword.matches(REGEX_PASSWORD);
  }

  public Boolean areEqual(String password, String passowrdRepeat) {
    return password.equals(passowrdRepeat);
  }

}
