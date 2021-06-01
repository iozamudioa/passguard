package net.iozamudioa.passguard.dao;

import java.util.List;
import java.util.Map;

public interface UserDao {

  public Map<String, Object> save(Object... objects);

  public List<Map<String, Object>> list(Object... objects);

}
