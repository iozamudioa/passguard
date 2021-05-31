package net.iozamudioa.passguard.util;

import org.springframework.stereotype.Component;

@Component
public class UtilUsername {

  private static final String REGEX_USERNAME = "^.\\S{6}$";

  public Boolean isValid(String username) {
    return username.matches(REGEX_USERNAME);
  }

}
