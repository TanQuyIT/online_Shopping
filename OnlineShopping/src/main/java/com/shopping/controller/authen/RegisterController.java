package com.shopping.controller.authen;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.dao.UserDAO;
import com.shopping.model.Role;
import com.shopping.model.User;

@Controller
public class RegisterController {

	@Autowired
	private UserDAO userDAO;

	@GetMapping(value = "/register")
	public String register() {
		return "authen/register";
	}

	@PostMapping(value = "/register")
	public String register(HttpServletRequest request, @RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password, @RequestParam(name = "repassword") String repassword) {
		if (userDAO.findByEmail(email) != null) {
			User user = userDAO.findByEmail(email);
			if (user.isVerify() == true) {
				request.setAttribute("error", "The email address is already exist!");
				return "authen/register";
			} else {
				if (!password.equals(repassword)) {
					request.setAttribute("error", "The password do not match!");
					request.setAttribute("email", email);
					user.setPassword(repassword);
					user.setAvatar("1608484153089.png");
					userDAO.update(user);
					return "authen/register";
				} else {
					user.setPassword(new BCryptPasswordEncoder().encode(password));
					userDAO.update(user);
				}
			}
		} else {
			if (!password.equals(repassword)) {
				request.setAttribute("error", "The password do not match!");
				request.setAttribute("email", email);
				return "authen/register";
			} else {
				try {
					User user = new User();
					user.setEmail(email);
					user.setPassword(new BCryptPasswordEncoder().encode(password));
					user.setAvatar("1608484153089.png");
					Role role = new Role();
					role.setRoleId(3);
					user.setRole(role);
					user.setVerify(true);
					userDAO.insert(user);
				} catch (Exception e) {
					System.out.println(e.toString());
				}
			}
		}
		return "authen/login";
	}
}