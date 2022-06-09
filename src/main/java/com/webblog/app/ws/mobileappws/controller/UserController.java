package com.webblog.app.ws.mobileappws.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.webblog.app.ws.mobileappws.ui.request.models.User;
import com.webblog.app.ws.mobileappws.ui.response.models.UserRest;

@RestController
@RequestMapping("/users")
public class UserController {
	Map<String,UserRest> map;
	
	//TODO: create Service Class and utility class for UUID generator
	
	@GetMapping(
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			path = {"/{userId}"})
	public ResponseEntity<UserRest> getUser(@PathVariable(required = true,value = "userId") String id) throws Exception
	{
		
		if(map!= null && map.containsKey(id)) {
			return new ResponseEntity<>(map.get(id), HttpStatus.OK);
		}
		throw new Exception("exception occured");
		//return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping()
	public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "limit", defaultValue = "1") int limit,
			@RequestParam(value = "sort", defaultValue = "desc") String sort)
	{
		return "page is "+page+" limit is "+ limit+" sort is "+sort;
	}
	
	@PostMapping(produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE	
			})
	public ResponseEntity<UserRest> createUser(@Valid @RequestBody User user) {
		String uuId = UUID.randomUUID().toString();
		UserRest userRest = new UserRest();
		userRest.setEmail(user.getEmail());
		userRest.setFirstName(user.getFirstName());
		userRest.setLastName(user.getLastName());
		userRest.setUserId(uuId);
		
		if(map == null) {
			map = new HashMap<>();
		}
		map.put(uuId, userRest);
		return new ResponseEntity<>(userRest, HttpStatus.OK);
	}
	
	
	//TODO: complete put endpoint
	@PatchMapping(produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE
			},
			consumes = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_VALUE	
			}, path = "/userId")
	public String updateUser(@PathVariable(value = "userId") String id) {
		if(map.containsKey(id)) {
			
		}
		return "user updated";
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") String id ) {
			map.remove(id);
		return ResponseEntity.noContent().build();
	}
}