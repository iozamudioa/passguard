package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import lombok.Getter;

public class LoginResponseDto implements Serializable {

  private static final long serialVersionUID = -8091879091924046844L;

  @Getter
  private final String token;

  public LoginResponseDto(String token) {
    super();
    this.token = token;
  }

}
