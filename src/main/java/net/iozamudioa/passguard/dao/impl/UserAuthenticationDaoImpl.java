package net.iozamudioa.passguard.dao.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.iozamudioa.passguard.dao.UserAuthenticationDao;
import net.iozamudioa.passguard.util.StoredProcedures;
import net.iozamudioa.passguard.util.UtilDao;

@Repository
public class UserAuthenticationDaoImpl implements UserAuthenticationDao {

  @Autowired
  private UtilDao utilDao;

  @Override
  public Map<String, Object> getCredentialsByUsername(Object... params) {
    return utilDao.procedureForMap(StoredProcedures.GET_CREDENTIALS_BY_USERNAME, params);
  }

}
