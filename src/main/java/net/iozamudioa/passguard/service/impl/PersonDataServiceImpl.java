package net.iozamudioa.passguard.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.PersonDataDao;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.service.PersonDataService;
import net.iozamudioa.passguard.util.UtilServices;

@Service
public class PersonDataServiceImpl implements PersonDataService {

  @Autowired
  private PersonDataDao personDataDao;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public List<UserDto> list() throws JsonMappingException, JsonProcessingException {
    return UtilServices.getUserListFromResultSet(personDataDao.list(new Object[] {null}), mapper);
  }

  @Override
  public UserDto get(Integer idUser) {
    return UtilServices.getUserFromResultSet(personDataDao.get(idUser), mapper);
  }

  @Override
  public UserDto saveOrUpdate(Integer idUser, PersonDataDto personDataDto) {
    return UtilServices.getUserFromResultSet(
        personDataDao.saveOrUpdate(personDataDto.getName(), personDataDto.getLastname(),
            personDataDto.getEmail(), personDataDto.getBirthDate(), idUser),
        mapper);
  }



}
