package net.iozamudioa.passguard.rest;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.iozamudioa.passguard.dto.ResponseDto;
import net.iozamudioa.passguard.dto.UserDto;
import net.iozamudioa.passguard.exception.PasswordInvalidException;
import net.iozamudioa.passguard.exception.ResourceNotFoundException;
import net.iozamudioa.passguard.service.UserService;

@RestController
@RequestMapping("/users")
@Api(tags = "Users", description = "Methods provided for management of users")
@PreAuthorize("hasRole('ROLE_ADMIN')")
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


  @GetMapping
  @ApiOperation(value = "This method is used to get user by username.")
  public ResponseDto<UserDto> getByUsername(@RequestParam("username") String username)
      throws PasswordInvalidException, ResourceNotFoundException {
    ResponseDto<UserDto> response = new ResponseDto<UserDto>();
    response.setData(usersService.getByUsername(username));
    response.setMessage("Obtained Successfully.");
    return response;
  }

  @GetMapping("/list")
  @ApiOperation(value = "This method is used to list all users.")
  public ResponseDto<List<UserDto>> list()
      throws PasswordInvalidException, ResourceNotFoundException {
    ResponseDto<List<UserDto>> response = new ResponseDto<List<UserDto>>();
    response.setData(usersService.list());
    response.setMessage("Obtained Successfully.");
    return response;
  }



}
