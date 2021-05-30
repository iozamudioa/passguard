package net.iozamudioa.passguard.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.iozamudioa.passguard.dao.PersonDataDao;
import net.iozamudioa.passguard.util.StoredProcedures;
import net.iozamudioa.passguard.util.UtilDao;

@Repository
public class PersonDataDaoImpl implements PersonDataDao {

  @Autowired
  private UtilDao utilDao;

  @Override
  public List<Map<String, Object>> list(Object... objects) {
    return utilDao.procedureForList(StoredProcedures.LIST_PERSON_DATA, objects);
  }

  @Override
  public Map<String, Object> get(Object... objects) {
    return utilDao.procedureForMap(StoredProcedures.LIST_PERSON_DATA, objects);
  }

  @Override
  public Map<String, Object> saveOrUpdate(Object... objects) {
    return utilDao.procedureForMap(StoredProcedures.SAVE_UPDATE_PERSON_DATA, objects);
  }

}
