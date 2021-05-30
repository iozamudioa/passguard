package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class UserDto implements Serializable {

  private static final long serialVersionUID = 4752061078375667607L;

  private String idUser;
  private String username;
  private String password;

}
