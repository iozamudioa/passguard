package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;

@JsonInclude(value = Include.NON_NULL)
@Data
public class PersonDataDto implements Serializable {

  private static final long serialVersionUID = -8806839217966845724L;

  private String name;
  private String lastname;
  private String email;
  private Date birthDate;
  private Date createdAt;
  private Date updatedAt;

}
