package net.iozamudioa.passguard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.PersonDataDao;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.service.PersonDataService;

@Service
public class PersonDataServiceImpl implements PersonDataService {

  @Autowired
  private PersonDataDao personDataDao;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public List<UserDto> list() throws JsonMappingException, JsonProcessingException {
    return getListFromResultSet(personDataDao.list(new Object[] {null}));
  }

  @Override
  public UserDto get(Integer idUser) {
    return getFromResultSet(personDataDao.get(idUser));
  }

  @Override
  public UserDto saveOrUpdate(Integer idUser, PersonDataDto personDataDto) {
    return getFromResultSet(
        personDataDao.saveOrUpdate(personDataDto.getName(), personDataDto.getLastname(),
            personDataDto.getEmail(), personDataDto.getBirthDate(), idUser));
  }


  private List<UserDto> getListFromResultSet(List<Map<String, Object>> resultset) {
    List<UserDto> lst = new ArrayList<>();
    resultset.forEach(i -> {
      lst.add(getFromResultSet(i));
    });
    return lst;

  }

  private UserDto getFromResultSet(Map<String, Object> map) {
    UserDto userDto = mapper.convertValue(map, UserDto.class);
    userDto.setPersonData(mapper.convertValue(map, PersonDataDto.class));
    return userDto;
  }

}
