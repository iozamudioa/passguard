package net.iozamudioa.passguard.dao;

import java.util.Map;

public interface CredentialDao {

  public Map<String, Object> save(Object... objects);

}
