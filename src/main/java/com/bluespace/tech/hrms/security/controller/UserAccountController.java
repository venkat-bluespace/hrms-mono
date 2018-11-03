package com.bluespace.tech.hrms.security.controller;

import java.util.ArrayList;
import java.util.Collection;
//import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bluespace.tech.hrms.domain.general.Roles;
import com.bluespace.tech.hrms.security.constants.AccountTypeConstants;
//import com.bluespace.tech.hrms.security.constants.RoleConstants;
import com.bluespace.tech.hrms.security.domain.Credentials;
import com.bluespace.tech.hrms.security.domain.UserAccount;

@RestController
@RequestMapping("/")
public class UserAccountController extends AbstractBaseController {
	
	@GetMapping(value = { "/user" }, produces = { "application/json" })
	public ResponseEntity<UserAccount> user() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserAccount user = new UserAccount();
		user.setUserName(userDetails.getUsername());
		Collection<String> roles = new ArrayList<>();
		Collection<Roles> uiRoles = new ArrayList<>();
//		Map<String, String> grantPermissionByRole = RoleConstants.getRoles();
		for (GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {
			if (AccountTypeConstants.SUPER_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
				user.setUserType(AccountTypeConstants.SUPER_ADMIN.getAccountType());
			} else if (AccountTypeConstants.ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
				user.setUserType(AccountTypeConstants.ADMIN.getAccountType());
			} else if (AccountTypeConstants.EMPLOYEE.getAccountType().equals(grantedAuthority.getAuthority())) {
				user.setUserType(AccountTypeConstants.EMPLOYEE.getAccountType());
			} else if (AccountTypeConstants.USER.getAccountType().toString().equals(grantedAuthority.getAuthority())) {
				user.setUserType(AccountTypeConstants.USER.getAccountType());
			}
			roles.add(grantedAuthority.getAuthority());
/*			if (grantPermissionByRole.get(grantedAuthority.getAuthority().toUpperCase()) != null) {
				uiRoles.add(grantPermissionByRole.get(grantedAuthority.getAuthority().toUpperCase()));
			}*/
		}
		user.setRoles(uiRoles);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> login(@RequestBody Credentials credentials) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(path = "/logout", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
