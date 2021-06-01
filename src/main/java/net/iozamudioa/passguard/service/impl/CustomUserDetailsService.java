package net.iozamudioa.passguard.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.iozamudioa.passguard.dao.UserAuthenticationDao;
import net.iozamudioa.passguard.dto.secure.AuthUser;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  @Autowired
  private UserAuthenticationDao userAuthenticationDao;

  @Autowired
  private ObjectMapper mapper;


  @Override
  public AuthUser loadUserByUsername(String username) throws UsernameNotFoundException {

    List<Map<String, Object>> userList = userAuthenticationDao.getCredentialsByUsername(username);

    if (userList.isEmpty()) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    AuthUser user = mapper.convertValue(userList.get(0), AuthUser.class);

    List<GrantedAuthority> authorities = new ArrayList<>();

    userList.forEach(i -> {
      authorities.add(new SimpleGrantedAuthority((String) i.get("role")));
    });

    user.setAuthorities(authorities);

    return user;


  }

}
