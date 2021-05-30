package net.iozamudioa.passguard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.CredentialDao;
import net.iozamudioa.passguard.dto.CredentialDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.service.CredentialService;
import net.iozamudioa.passguard.util.UtilPassword;

@Service
public class CredentialServiceImpl implements CredentialService {

  @Autowired
  private CredentialDao credentialDao;

  @Autowired
  private UtilPassword utilPassword;

  @Autowired
  private ObjectMapper mapper;

  @Override
  public CredentialDto save(CredentialDto credentialDto) throws PasswordInvalidException {

    if (!utilPassword.isValid(credentialDto.getPassword())) {
      throw new PasswordInvalidException("Password Invalid");
    }

    if (!utilPassword.areEqual(credentialDto.getPassword(), credentialDto.getPasswordRepeat())) {
      throw new PasswordInvalidException("It is not the same password.");
    }

    return mapper.convertValue(credentialDao.save(credentialDto.getIdUser(),
        utilPassword.encode(credentialDto.getPassword())), CredentialDto.class);
  }

}
