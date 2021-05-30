package net.iozamudioa.passguard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.UserDao;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.service.UserService;
import net.iozamudioa.passguard.util.UtilPassword;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private UtilPassword utilPassword;

  @Override
  @Transactional
  public UserDto save(UserDto userDto) throws PasswordInvalidException {

    if (!utilPassword.isValid(userDto.getPassword())) {
      throw new PasswordInvalidException("Password Invalid");
    }

    return mapper.convertValue(
        userDao.save(userDto.getUsername(), utilPassword.encode(userDto.getPassword())),
        UserDto.class);
  }

}
