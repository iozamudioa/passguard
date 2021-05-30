package net.iozamudioa.passguard.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.service.UserService;

@RestController(value = "/users")
@Api(tags = "Users")
public class UserController {

  @Autowired
  private UserService usersService;

  @PostMapping
  @ApiOperation(value = "This method is used to save user.")
  public ResponseDto<UserDto> save(@RequestBody UserDto userDto) throws PasswordInvalidException {

    ResponseDto<UserDto> response = new ResponseDto<UserDto>();
    response.setData(usersService.save(userDto));
    response.setMessage("Created Successfully.");
    return response;
  }


}
