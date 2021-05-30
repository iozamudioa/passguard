package net.iozamudioa.passguard.dao.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import net.iozamudioa.passguard.dao.CredentialDao;
import net.iozamudioa.passguard.util.StoredProcedures;
import net.iozamudioa.passguard.util.UtilDao;

@Repository
public class CredentialDaoImpl implements CredentialDao {

  @Autowired
  private UtilDao utilDao;

  @Override
  public Map<String, Object> save(Object... objects) {
    return utilDao.procedureForMap(StoredProcedures.SAVE_CREDENTIAL, objects);
  }

}
