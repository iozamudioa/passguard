package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@Data
@JsonInclude(value = Include.NON_NULL)
public class CredentialDto implements Serializable {

  private static final long serialVersionUID = 7221173309043976877L;

  private Integer idCredential;
  private Integer idUser;
  private String password;
  private Date createdAt;
  private Date disabledAt;
  private Boolean enabled;

}
