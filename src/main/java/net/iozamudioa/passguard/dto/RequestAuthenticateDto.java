package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class RequestAuthenticateDto implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private String password;

}
