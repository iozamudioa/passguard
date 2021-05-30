package net.iozamudioa.passguard.service;

import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;

public interface UserService {

  public UserDto save(UserDto userDto) throws PasswordInvalidException;

}
