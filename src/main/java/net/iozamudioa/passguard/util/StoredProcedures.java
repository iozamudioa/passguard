package net.iozamudioa.passguard.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StoredProcedures {

  public final String GET_CREDENTIALS_BY_USERNAME = "getCredentialsByUsername";
  public final String SAVE_USER = "saveUser";
  public final String LIST_USERS = "listUsers";

  public final String SAVE_CREDENTIAL = "saveCredential";

  public final String SAVE_UPDATE_PERSON_DATA = "updatePersonData";
  public final String LIST_PERSON_DATA = "listPersonData";

}
