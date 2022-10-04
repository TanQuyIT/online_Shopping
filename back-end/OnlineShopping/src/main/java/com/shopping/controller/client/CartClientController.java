package com.shopping.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shopping.model.Item;
import com.shopping.model.Product;
import com.shopping.service.ProductService;

@SuppressWarnings({ "unchecked", "unused" })
@Controller
@RequestMapping(value = "/client")
public class CartClientController {

	@Autowired
	private ProductService productService;
	
	@GetMapping(value = "/cart")
	public String cart(HttpServletRequest request, HttpSession session) {
		Object object = session.getAttribute("cart");
		int totalQuantity = 0;
		float subTotal = 0;
		float grandTotal = 0;
		if (object != null) {
			
			HashMap<Long, Item> mapItem = (HashMap<Long, Item>) object;
			for(Map.Entry<Long, Item> entry : mapItem.entrySet()) {
				Long key = entry.getKey();
			    Item value = entry.getValue();
			    totalQuantity += value.getQuantity();
			    subTotal += (value.getUnitPrice() * value.getQuantity());
			}
			grandTotal = subTotal + 5;
		}
		session.setAttribute("totalQuantity", totalQuantity);
		session.setAttribute("subTotal", subTotal);
		session.setAttribute("grandTotal", grandTotal);
		return "client/cart"; 
	}
	
	// Add product to cart
	
	@GetMapping(value = "/add-to-cart")
	public String addToCart(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "productId") long productId) {
		
		Product product = productService.findById(productId);
		float unitPrice = product.getPrice() - Math.round((product.getPrice() * product.getSale().getSalePercent() / 100));
		
		Object object = session.getAttribute("cart");
		
		if (object == null) {
			Item itemsDTO = new Item();
			itemsDTO.setProduct(product);
			itemsDTO.setUnitPrice(unitPrice);
			itemsDTO.setQuantity(1);
			Map<Long, Item> mapItem = new HashMap<Long, Item>();
			mapItem.put(productId, itemsDTO);
			session.setAttribute("cart", mapItem);
		} else {
			Map<Long, Item> mapItem = (Map<Long, Item>) object;
			if (mapItem.get(productId) == null) {
				Item itemsDTO = new Item();
				itemsDTO.setProduct(product);
				itemsDTO.setUnitPrice(unitPrice);
				itemsDTO.setQuantity(1);
				mapItem.put(productId, itemsDTO);
			} else {
				Item items = mapItem.get(productId);
				int quantity = items.getQuantity();
				if (quantity < 5) {
					items.setQuantity(quantity + 1);
				}
			}
			session.setAttribute("cart", mapItem);
		}
		return "redirect:../client/cart";
	}
	
	@PostMapping(value = "/add-to-cart")
	public String addToCart(HttpSession session, @RequestParam(name = "productId") long productId,
			@RequestParam(name = "quantity") int quantity) {
		Product productDTO = productService.findById(productId);
		float unitPrice = productDTO.getPrice() - Math.round((productDTO.getPrice() * productDTO.getSale().getSalePercent() / 100));
		
		Object object = session.getAttribute("cart");
		if (object == null) {
			Item itemsDTO = new Item();
			itemsDTO.setProduct(productDTO);
			itemsDTO.setQuantity(quantity);
			itemsDTO.setUnitPrice(unitPrice);
			Map<Long, Item> mapItem = new HashMap<Long, Item>();
			mapItem.put(productId, itemsDTO);
			session.setAttribute("cart", mapItem);
		} else {
			Map<Long, Item> mapItem = (Map<Long, Item>) object;
			if (mapItem.get(productId) == null) {
				Item itemsDTO = new Item();
				itemsDTO.setProduct(productDTO);
				itemsDTO.setQuantity(quantity);
				itemsDTO.setUnitPrice(unitPrice);
				mapItem.put(productId, itemsDTO);
			} else {
				Item itemsDTO = mapItem.get(productId);
				itemsDTO.setQuantity(quantity);
			}
			session.setAttribute("cart", mapItem);
		}

		return "redirect:../client/cart";
	}
	
	// Detele product from cart
	
	@GetMapping(value = "/delete-from-cart")
	public String deleteFromCart(HttpServletRequest request, HttpSession session,
			@RequestParam(name = "productId") long productId) {
		
		Object object = session.getAttribute("cart");
		Map<Long, Item> mapItem = (Map<Long, Item>) object;
		mapItem.remove(productId);
		session.setAttribute("cart", mapItem);
		return "redirect:../client/cart";
	}
}