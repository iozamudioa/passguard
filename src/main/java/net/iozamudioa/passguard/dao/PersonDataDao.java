package net.iozamudioa.passguard.dao;

import java.util.List;
import java.util.Map;

public interface PersonDataDao {

  public List<Map<String, Object>> list(Object... objects);

  public Map<String, Object> get(Object... objects);

  public Map<String, Object> saveOrUpdate(Object... objects);

}
