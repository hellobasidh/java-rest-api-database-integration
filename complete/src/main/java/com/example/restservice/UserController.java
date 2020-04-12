package com.example.restservice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.entity.PersonData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "User Registration Service", description = "Rest Application used to register the user")
@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/user/{code}/save")
	@ApiOperation(value = "Save or Update the User Data of the user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User Data stored successfully"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })

	public ResponseEntity<PersonData> createOrUpdateHealthData(@PathVariable String code,
			@RequestBody @Valid PersonData data) throws RecordNotFoundException {
		PersonData updated = userService.createOrUpdateUser(data);
		return new ResponseEntity<PersonData>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/user/{code}/getAll")
	@ApiOperation(value = "Get All the User Data of the users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfuly sent the user data of all the users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })

	public ResponseEntity<List<PersonData>> getAllUsers() {
		List<PersonData> list = userService.getAllUsers();
		return new ResponseEntity<List<PersonData>>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/user/{code}/{id}")
	@ApiOperation(value = "Get the User Data of the requested user")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfuly sent the user data of the requested users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })

	public ResponseEntity<PersonData> getUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		PersonData entity = userService.getUserById(id);
		return new ResponseEntity<PersonData>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/user/{code}/{id}")
	@ApiOperation(value = "Delete User Data of the particular user")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfuly Deleted the user data of the requested users"),
			@ApiResponse(code = 400, message = "Bad Service Request - Invalid input request"),
			@ApiResponse(code = 401, message = "You are not authorized to use the service"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The service you were trying to reach is not found") })
	
	public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws RecordNotFoundException {
		userService.deleteUserById(id);
		return ResponseEntity.status(HttpStatus.OK).body("User has been deleted");
	}

}
