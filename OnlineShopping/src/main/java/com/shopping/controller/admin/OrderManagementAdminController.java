package com.shopping.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.dao.ItemDAO;
import com.shopping.dao.OrderDAO;
import com.shopping.model.Order;


@Controller
@RequestMapping(value = "/admin")
public class OrderManagementAdminController {

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@GetMapping(value = "/order-list")
	public String findAll(HttpServletRequest request) {
		int pageIndex = 0;
		int pageSize = 10;
		int totalPage = 0;
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		int count = orderDAO.count();
		if (count % 10 == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("orders", orderDAO.findAll(pageIndex, pageSize));
		return "admin/order/order_list";
	}
	
	@GetMapping(value = "order-updateHome")
	public String orderUpdateHome(HttpServletRequest request) {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		Order order = orderDAO.findById(orderId);
		order.setStatus("SUCCESS");
		orderDAO.update(order);
		return "redirect:/admin/home";
	}

	
	@GetMapping(value = "order-update")
	public String orderUpdate(HttpServletRequest request) {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		Order order = orderDAO.find(orderId);
		order.setStatus("SUCCESS");
		orderDAO.update(order);
		return "redirect:/admin/order-list";
	}
	
	@GetMapping(value = "order-details")
	public String orderDetails(HttpServletRequest request) {
		long orderId = Long.parseLong(request.getParameter("orderId"));
		request.setAttribute("items", itemDAO.findByOrderId(orderId));
		request.setAttribute("order", orderDAO.findById(orderId));
		return "admin/order/order_details";
	}
	
}