package com.shopping.controller.client;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.dao.UserDAO;
import com.shopping.model.Role;
import com.shopping.model.User;
import com.shopping.model.UserPrincipal;

@Controller
@RequestMapping(value = "/client")
public class ProfileClientController {
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping(value = "/profile")
	public String profile() {
		return "client/profile";
	}
	
	@GetMapping(value = "/profile-from-cart")
	public String profileFromCart(HttpServletRequest request) {
		request.setAttribute("messageError", "Please update your delivery information.");
		return "client/profile";
	}
	
	@PostMapping(value = "/profile-update")
	public String profileUpdate(HttpServletRequest request,
			@RequestParam(name = "fullname", required = false) String fullname,
			@RequestParam(name = "phone") String phone,
			@RequestParam(name = "address") String address) {
		UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
		userPrincipal.setFullname(fullname);
		userPrincipal.setPhone(phone);
		userPrincipal.setAddress(address);
		Role role = new Role();
		role.setRoleId(userPrincipal.getRole().getRoleId());
		role.setRoleName(userPrincipal.getRole().getRoleName());
		
		User user = new User();
		user.setUserId(userPrincipal.getUserId());
		user.setEmail(userPrincipal.getEmail());
		user.setPhone(userPrincipal.getPhone());
		user.setAddress(userPrincipal.getAddress());
		user.setAvatar(userPrincipal.getAvatar());
		user.setFullname(userPrincipal.getFullname());
		user.setVerify(userPrincipal.isVerify());
		user.setGender(userPrincipal.isGender());
		user.setPassword(userPrincipal.getPassword());
		user.setRole(role);
		
		userDAO.update(user);
		request.setAttribute("messageSuccess", "Update delivery information successful.");
		return "client/profile";
	}
	
	@PostMapping(value = "/change-password")
	public String changePassword(HttpServletRequest request) {
		String oldpassword = request.getParameter("oldpassword");
		String newpassword= request.getParameter("newpassword");
		String repassword = request.getParameter("repassword");
		
		UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
		String passwordEncoder = new BCryptPasswordEncoder().encode(oldpassword);
		request.setAttribute("oldpassword", userPrincipal.getPassword());
		request.setAttribute("passwordEncoder", passwordEncoder);
		request.setAttribute("newpassword", newpassword);
		request.setAttribute("repassword", repassword);
		

		return "client/profile";
	}
}
