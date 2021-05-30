package net.iozamudioa.passguard.service;

import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.UserDto;

public interface PersonDataService {

  public List<UserDto> list() throws JsonMappingException, JsonProcessingException;

  public UserDto get(Integer idUser);

  public UserDto saveOrUpdate(Integer idUser, PersonDataDto personDataDto);


}
