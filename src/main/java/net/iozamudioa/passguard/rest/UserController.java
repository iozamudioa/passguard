package net.iozamudioa.passguard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService usersService;

  @PostMapping
  public ResponseDto<UserDto> save(@RequestBody UserDto userDto) throws PasswordInvalidException {

    ResponseDto<UserDto> response = new ResponseDto<UserDto>();
    response.setData(usersService.save(userDto));
    response.setMessage("Created Successfully.");
    return response;
  }


}
