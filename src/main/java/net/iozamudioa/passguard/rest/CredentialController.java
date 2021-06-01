package net.iozamudioa.passguard.rest;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import net.iozamudioa.passguard.dto.CredentialDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.service.CredentialService;
import net.iozamudioa.passguard.util.UtilRequest;

@RestController
@RequestMapping("/credential")
@Api(tags = "Credentials", description = "Methods provided for management of credentials")
@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_ADMIN')")
public class CredentialController {

  @Autowired
  private CredentialService credentialService;

  @PutMapping(value = {"/{idUser}", ""})
  public ResponseDto<CredentialDto> update(HttpServletRequest request,
      @RequestBody CredentialDto credentialDto,
      @PathVariable(value = "idUser", required = false) Integer idUser) throws Exception {

    idUser = UtilRequest.getIdUser(request, idUser);

    credentialDto.setIdUser(idUser);
    ResponseDto<CredentialDto> response = new ResponseDto<>();
    response.setData(credentialService.save(credentialDto));
    response.setMessage("Updated Successfully.");

    return response;
  }

}
