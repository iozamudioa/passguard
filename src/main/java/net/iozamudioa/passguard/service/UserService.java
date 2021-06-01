package net.iozamudioa.passguard.service;

import java.util.List;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.exception.ResourceNotFoundException;

public interface UserService {

  public UserDto save(UserDto userDto) throws PasswordInvalidException;

  public List<UserDto> list();

  public UserDto getByUsername(String username) throws ResourceNotFoundException;

}
