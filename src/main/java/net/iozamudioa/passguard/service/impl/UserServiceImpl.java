package net.iozamudioa.passguard.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.UserDao;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.exception.ResourceNotFoundException;
import net.iozamudioa.passguard.service.PersonDataService;
import net.iozamudioa.passguard.service.UserService;
import net.iozamudioa.passguard.util.UtilPassword;
import net.iozamudioa.passguard.util.UtilServices;
import net.iozamudioa.passguard.util.UtilUsername;
import net.iozamudioa.passguard.util.enums.Roles;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private UtilPassword utilPassword;

  @Autowired
  private UtilUsername utilUsername;

  @Autowired
  private PersonDataService personDataService;

  @Override
  @Transactional
  public UserDto save(UserDto userDto) throws PasswordInvalidException {

    if (!utilPassword.isValid(userDto.getPassword())) {
      throw new PasswordInvalidException("Password Invalid");
    }

    if (!utilUsername.isValid(userDto.getUsername())) {
      throw new PasswordInvalidException("Username Invalid");
    }

    UserDto user = mapper.convertValue(userDao.save(userDto.getUsername(),
        utilPassword.encode(userDto.getPassword()),
        userDto.getRole() == null ? Roles.ROLE_USER.getIdRole() : userDto.getRole().getIdRole()),
        UserDto.class);

    return userDto.getPersonData() != null
        ? personDataService.saveOrUpdate(user.getIdUser(), userDto.getPersonData())
        : user;

  }

  @Override
  public List<UserDto> list() {
    return UtilServices.getUserListFromResultSet(userDao.list(new Object[] {null}), mapper);
  }

  @Override
  public UserDto getByUsername(String username) throws ResourceNotFoundException {
    List<Map<String, Object>> lst = userDao.list(username);

    if (lst.isEmpty()) {
      throw new ResourceNotFoundException("username not found");
    }


    return UtilServices.getUserFromResultSet(lst.get(0), mapper);
  }

}
