package net.iozamudioa.passguard.exception;

public class PasswordInvalidException extends Exception {

  private static final long serialVersionUID = 1L;

  public PasswordInvalidException(String message) {
    super(message);
  }
}
