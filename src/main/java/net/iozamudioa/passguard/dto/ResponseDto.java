package net.iozamudioa.passguard.dto;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ResponseDto<T> implements Serializable {

  private static final long serialVersionUID = 1L;

  public T data;
  public Long timestamp = new Date().getTime();
  public String message;
  public String error;

  public ResponseDto(T t) {
    super();
    this.data = t;

  }



}
