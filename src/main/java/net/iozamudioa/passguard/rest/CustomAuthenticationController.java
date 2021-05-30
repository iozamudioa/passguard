package net.iozamudioa.passguard.rest;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import net.iozamudioa.passguard.config.JwtTokenUtil;
import net.iozamudioa.passguard.dto.LoginResponseDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.util.Constant;

@RestController
@CrossOrigin
public class CustomAuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService customUserDetailsService;

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseDto<LoginResponseDto> createAuthenticationToken(
      @RequestBody UserDto authenticationRequest) throws Exception {

    authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

    final UserDetails userDetails =
        customUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

    final String token = Constant.PREFIX_TOKEN + jwtTokenUtil.generateToken(userDetails);

    return new ResponseDto<LoginResponseDto>(new LoginResponseDto(token));
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

  }

}
