package net.iozamudioa.passguard.rest;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.iozamudioa.passguard.config.JwtTokenUtil;
import net.iozamudioa.passguard.dto.LoginResponseDto;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.util.Constant;

@RestController
@Api(tags = "Authentication", description = "jhlkadjshflkdja")
public class CustomAuthenticationController {

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Autowired
  private UserDetailsService customUserDetailsService;

  @PostMapping(value = "/authenticate")
  @ApiOperation(value = "Method for authentication.")
  public ResponseDto<LoginResponseDto> createAuthenticationToken(@RequestBody UserDto userDto)
      throws Exception {

    authenticate(userDto.getUsername(), userDto.getPassword());

    final UserDetails userDetails =
        customUserDetailsService.loadUserByUsername(userDto.getUsername());

    final String token = Constant.PREFIX_TOKEN + jwtTokenUtil.generateToken(userDetails);

    return new ResponseDto<LoginResponseDto>(new LoginResponseDto(token));
  }

  @GetMapping("/")
  public ModelAndView redirectWithUsingRedirectPrefix(ModelMap model) {
    return new ModelAndView("redirect:/swagger-ui/", model);
  }

  private void authenticate(String username, String password) {
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);

    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

  }

}
