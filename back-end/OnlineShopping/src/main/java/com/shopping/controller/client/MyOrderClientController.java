package com.shopping.controller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.dao.ItemDAO;
import com.shopping.dao.OrderDAO;
import com.shopping.model.Item;
import com.shopping.model.UserPrincipal;


@Controller
@RequestMapping(value = "/client")
public class MyOrderClientController {

	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
	@GetMapping(value = "/my-order")
	public String myOrder(HttpServletRequest request) {
		UserPrincipal userPrincipal = (UserPrincipal) request.getSession().getAttribute("user");
		long userId = (long) userPrincipal.getUserId();
		request.setAttribute("orders", orderDAO.findByBuyer(userId));
		return "client/my_order";
	}
	
	@GetMapping(value = "order-details")
	public String orderDetails(HttpServletRequest request, @RequestParam(name = "orderId") long orderId) {
		List<Item> items = itemDAO.findByOrderId(orderId);
		float subTotal = 0;
		for (Item item : items) {
			subTotal += (item.getUnitPrice() * item.getQuantity());
		}
		float grandTotal = subTotal + 5;
		request.setAttribute("subTotal", subTotal);
		request.setAttribute("grandTotal", grandTotal);
		request.setAttribute("items", items);
		return "client/order_details";  
	}
}
