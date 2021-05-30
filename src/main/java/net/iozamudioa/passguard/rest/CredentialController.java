package net.iozamudioa.passguard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import net.iozamudioa.passguard.dto.CredentialDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.service.CredentialService;

@RestController
@RequestMapping("/credential")
@Api(tags = "Credentials", description = "Methods provided for management of credentials")
public class CredentialController {

  @Autowired
  private CredentialService credentialService;

  @PutMapping("/{idUser}")
  public ResponseDto<CredentialDto> update(@RequestBody CredentialDto credentialDto,
      @RequestParam(value = "idUser") Integer idUser) throws Exception {

    if (idUser == 0) {
      throw new Exception("idUser not valid");
    }

    credentialDto.setIdUser(idUser);
    ResponseDto<CredentialDto> response = new ResponseDto<>();
    response.setData(credentialService.save(credentialDto));
    response.setMessage("Updated Successfully.");

    return response;
  }

}
