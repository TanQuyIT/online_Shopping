package com.shopping.controller.authen;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.dao.UserDAO;
import com.shopping.model.UserPrincipal;

@Controller
public class LoginController {

	@Autowired
	private UserDAO userDAO;

	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "err", required = false) String err) {
		request.setAttribute("err", err);
		return "authen/login";
	}

	@GetMapping(value = "/home") // chuyen den home khi dang nhap thanh cong
	public String loginSuccess(HttpServletRequest request, HttpSession session) {
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		session.setAttribute("user", userPrincipal);
		String username = userPrincipal.getEmail();
		session.setAttribute("username", username.split("@")[0]);
		if (request.isUserInRole("ADMIN")) { // neu role la admin thi chuyen den trang admin
			return "redirect:/admin/home";
		} else {
			return "redirect:/client/home"; // khong phai admin chuyen den trang client
		}
	}

	@GetMapping(value = "/logout")
	public String logout() {
		return "redirect:/login";
	}

}