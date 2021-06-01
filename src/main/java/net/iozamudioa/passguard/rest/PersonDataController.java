package net.iozamudioa.passguard.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import net.iozamudioa.passguard.dto.PersonDataDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.service.PersonDataService;
import net.iozamudioa.passguard.util.UtilRequest;

@RestController
@RequestMapping("/data-person")
@Api(tags = "Data Person", description = "Methods provided for management of personal information")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class PersonDataController {

  @Autowired
  private PersonDataService personDataService;

  @PostMapping("/{idUser}")
  public ResponseDto<UserDto> saveOrUpdate(HttpServletRequest request,
      @PathVariable("idUser") Integer idUser, @PathVariable PersonDataDto personDataDto)
      throws Exception {
    if (idUser == 0) {
      throw new Exception("idUser not valid");
    }

    idUser = UtilRequest.getIdUser(request, idUser);

    return new ResponseDto<UserDto>(personDataService.saveOrUpdate(idUser, personDataDto));
  }

}
