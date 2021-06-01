package net.iozamudioa.passguard.util;

import javax.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;

@UtilityClass
@Log4j2
public class UtilRequest {

  public Integer getIdUser(HttpServletRequest request, Integer idUser) throws Exception {
    Integer idUserToken = (Integer) request.getAttribute("idUser");

    if (idUser != null) {
      idUser = idUserToken;
    }

    if (idUser == 0) {
      throw new Exception("idUser not valid");
    }

    if (!request.isUserInRole("ROLE_ADMIN")) {
      log.info("user dont is admin!");
      idUser = idUserToken;
    }

    return idUser;
  }

}
