package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class UserDto implements Serializable {

  private static final long serialVersionUID = 4752061078375667607L;

  private Integer idUser;
  private String username;
  private String password;

  private PersonDataDto personData;

}
