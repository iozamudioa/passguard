package net.iozamudioa.passguard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.service.PersonDataService;

@RestController
@RequestMapping("/data-person")
@Api(tags = "Data Person", description = "Methods provided for management of personal information")
public class PersonDataController {

  @Autowired
  private PersonDataService personDataService;

  @PostMapping("/{idUser}")
  public ResponseDto<UserDto> saveOrUpdate(@RequestParam("idUser") Integer idUser,
      @RequestBody PersonDataDto personDataDto) throws Exception {
    if (idUser == 0) {
      throw new Exception("idUser not valid");
    }
    return new ResponseDto<UserDto>(personDataService.saveOrUpdate(idUser, personDataDto));
  }

}
