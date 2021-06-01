package net.iozamudioa.passguard.rest.advice;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;

@RestControllerAdvice
public class CustomExcepcionHandler {


  @ExceptionHandler(value = BadCredentialsException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<?> badCredentialsException(BadCredentialsException ex) {
    ResponseDto<Object> resposne = new ResponseDto<Object>();
    resposne.setError(ex.getMessage());
    return resposne;
  }

  @ExceptionHandler(value = PasswordInvalidException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<Object> passwordInvalidException(PasswordInvalidException ex) {
    ex.printStackTrace();

    ResponseDto<Object> resposne = new ResponseDto<Object>();
    resposne.setError(ex.getMessage());

    return resposne;
  }

  @ExceptionHandler(value = DuplicateKeyException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<Object> exception(DuplicateKeyException ex) {
    ResponseDto<Object> resposne = new ResponseDto<Object>();

    if (ex.getMessage().contains("username_UNIQUE")) {
      resposne.setError("Username exists.");
    }

    ex.printStackTrace();
    return resposne;
  }


  @ExceptionHandler(value = AccessDeniedException.class)
  @ResponseStatus(value = HttpStatus.FORBIDDEN)
  public ResponseDto<Object> exception(AccessDeniedException ex) {
    ex.printStackTrace();
    ResponseDto<Object> resposne = new ResponseDto<Object>();
    resposne.setError(ex.getMessage());
    return resposne;
  }

  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseDto<Object> exception(Exception ex) {
    ex.printStackTrace();
    ResponseDto<Object> resposne = new ResponseDto<Object>();
    resposne.setError("An error has ocurred.");
    return resposne;
  }

}
