package net.iozamudioa.passguard.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.UserDto;

@UtilityClass
public class UtilServices {

  public UserDto getUserFromResultSet(Map<String, Object> map, ObjectMapper mapper) {
    UserDto userDto = mapper.convertValue(map, UserDto.class);
    if (map.get("idPersonData") != null) {
      userDto.setPersonData(mapper.convertValue(map, PersonDataDto.class));
    }
    return userDto;
  }

  public List<UserDto> getUserListFromResultSet(List<Map<String, Object>> resultset,
      ObjectMapper mapper) {
    List<UserDto> lst = new ArrayList<>();
    resultset.forEach(i -> {
      lst.add(UtilServices.getUserFromResultSet(i, mapper));
    });
    return lst;

  }

}
