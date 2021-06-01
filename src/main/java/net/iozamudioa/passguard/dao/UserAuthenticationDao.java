package net.iozamudioa.passguard.dao;

import java.util.List;
import java.util.Map;

public interface UserAuthenticationDao {

  public List<Map<String, Object>> getCredentialsByUsername(Object... objects);

}
