package com.shopping.controller.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.dao.CategoryDAO;
import com.shopping.dao.ProductDAO;

@Controller
@RequestMapping(value = "/client")
public class HomeClientController {

	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@GetMapping(value = "/home")
	public String home(HttpServletRequest request, HttpSession session) {
		request.setAttribute("hotOne", productDAO.hotProducts(0, 4));
		request.setAttribute("hotTwo", productDAO.hotProducts(1, 4));
		request.setAttribute("featuredOne", productDAO.featuredProducts(0, 4));
		request.setAttribute("featuredTwo", productDAO.featuredProducts(1, 4));
		session.setAttribute("categories", categoryDAO.findAll());
		return "client/home";
	}
}
