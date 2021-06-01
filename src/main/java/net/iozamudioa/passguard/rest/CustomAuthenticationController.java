package net.iozamudioa.passguard.rest;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.iozamudioa.passguard.config.JwtTokenUtil;
import net.iozamudioa.passguard.dto.LoginResponseDto;
import net.iozamudioa.passguard.dto.RequestAuthenticateDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.secure.AuthUser;
import net.iozamudioa.passguard.util.Constant;

@RestController
@Api(tags = "Authenticate", description = "Methods provided for authenticate in api endpoints")
public class CustomAuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService customUserDetailsService;

  @PostMapping(value = "/authenticate")
  @ApiOperation(value = "Method for authentication.")
  public ResponseDto<LoginResponseDto> createAuthenticationToken(
      @RequestBody RequestAuthenticateDto requestAuthenticateDto) throws Exception {

    authenticate(requestAuthenticateDto.getUsername(), requestAuthenticateDto.getPassword());

    AuthUser authUser = (AuthUser) customUserDetailsService
        .loadUserByUsername(requestAuthenticateDto.getUsername());

    final String token = Constant.PREFIX_TOKEN + jwtTokenUtil.generateToken(authUser);

    return new ResponseDto<LoginResponseDto>(new LoginResponseDto(token));
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

  }

}
