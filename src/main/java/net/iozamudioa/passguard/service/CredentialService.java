package net.iozamudioa.passguard.service;

import net.iozamudioa.passguard.dto.CredentialDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;

public interface CredentialService {

  public CredentialDto save(CredentialDto credentialDto) throws PasswordInvalidException;

}
