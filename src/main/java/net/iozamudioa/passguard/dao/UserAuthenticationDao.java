package net.iozamudioa.passguard.dao;

import java.util.Map;

public interface UserAuthenticationDao {

  public Map<String, Object> getCredentialsByUsername(Object... objects);

}
