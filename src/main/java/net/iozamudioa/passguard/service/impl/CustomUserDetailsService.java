package net.iozamudioa.passguard.service.impl;

import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import net.iozamudioa.passguard.dao.UserAuthenticationDao;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserAuthenticationDao userAuthenticationDao;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Map<String, Object> userMap = userAuthenticationDao.getCredentialsByUsername(username);

    if (userMap.isEmpty()) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    return new User((String) userMap.get("username"), (String) userMap.get("password"),
        new ArrayList<>());


  }

}
