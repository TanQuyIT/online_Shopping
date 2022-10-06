package com.shopping.controller.authen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.UserPrincipal;

@Controller
public class LoginController {

	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "err", required = false) String err) {
		request.setAttribute("err", err);
		return "authen/login";
	}

	@GetMapping(value = "/home")
	public String loginSuccess(HttpServletRequest request, HttpSession session) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		session.setAttribute("user", userPrincipal);
		String username = userPrincipal.getEmail();
		session.setAttribute("username", username.split("@")[0]);
		if (request.isUserInRole("ADMIN")) {
			return "redirect:/admin/home";
		} else {
			return "redirect:/client/home";
		}
	}

	@GetMapping(value = "/logout")
	public String logout() {
		return "redirect:/login";
	}

}