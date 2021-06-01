package net.iozamudioa.passguard.util.enums;

import lombok.Getter;

public enum Roles {

  ROLE_ADMIN(1), ROLE_USER(2), ROLE_GUESS(3);

  @Getter
  public int idRole;

  Roles(int idRoleArg) {
    idRole = idRoleArg;
  }

}

