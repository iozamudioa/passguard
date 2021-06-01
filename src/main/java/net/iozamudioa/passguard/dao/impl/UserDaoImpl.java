package net.iozamudioa.passguard.dao.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import net.iozamudioa.passguard.dao.UserDao;
import net.iozamudioa.passguard.util.StoredProcedures;
import net.iozamudioa.passguard.util.UtilDao;

@Repository
public class UserDaoImpl implements UserDao {

  @Autowired
  private UtilDao utilDao;

  @Override
  @Transactional
  public Map<String, Object> save(Object... objects) {
    return utilDao.procedureForMap(StoredProcedures.SAVE_USER, objects);
  }

  @Override
  public List<Map<String, Object>> list(Object... objects) {
    return utilDao.procedureForList(StoredProcedures.LIST_USERS, objects);
  }

}
