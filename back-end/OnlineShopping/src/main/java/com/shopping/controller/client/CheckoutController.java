package com.shopping.controller.client;

import java.sql.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.dao.ItemDAO;
import com.shopping.dao.OrderDAO;
import com.shopping.model.Item;
import com.shopping.model.Order;
import com.shopping.model.Product;
import com.shopping.model.User;
import com.shopping.model.UserPrincipal;


@Controller
@RequestMapping(value = "/client")
public class CheckoutController {

	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private ItemDAO itemDao;
	
	@SuppressWarnings("unchecked")
	@PostMapping(value = "/checkout")
	public String checkout(HttpSession session) {
		
		float subTotal = 0;
		float fee = 5;
		
		UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		User user = new User();
		user.setUserId(userPrincipal.getUserId());
		
        Date date = new Date(new java.util.Date().getTime());
        
		Object object = session.getAttribute("cart");
		Map<Long, Item> mapItem = (Map<Long, Item>) object;
		
		for(Map.Entry<Long, Item> entry : mapItem.entrySet()) {
		    Long key = entry.getKey();
		    Item value = entry.getValue();
		    
		    subTotal += (value.getUnitPrice() * value.getQuantity());
		}
		
		Order order = new Order();
		order.setBuyDate(date);
		order.setBuyer(user);
		order.setStatus("PENDING");
		order.setPriceTotal(subTotal + fee);
		
		orderDao.insert(order);
		
		for(Map.Entry<Long, Item> entry : mapItem.entrySet()) {
		    Long key = entry.getKey();
		    Item value = entry.getValue();
		    
		    Product product = new Product();
		    product.setProductId(entry.getValue().getProduct().getProductId());
		    
		    Item item = new Item();
		    item.setItemId(entry.getValue().getItemId());
		    item.setProduct(product);
		    item.setQuantity(entry.getValue().getQuantity());
		    item.setUnitPrice(entry.getValue().getUnitPrice());
		    item.setOrder(order);
		    
			itemDao.insert(item);
		}
	
		mapItem.clear();
		session.setAttribute("cart", mapItem);
		
		return "redirect:/client/my-order";
	}
}