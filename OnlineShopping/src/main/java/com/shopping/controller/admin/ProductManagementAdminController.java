package com.shopping.controller.admin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shopping.dao.CategoryDAO;
import com.shopping.dao.ProductDAO;
import com.shopping.dao.SaleDAO;
import com.shopping.model.Category;
import com.shopping.model.Product;
import com.shopping.model.Sale;

// Product Manager

@Controller
@RequestMapping(value = "/admin")
public class ProductManagementAdminController {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private SaleDAO saleDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	// Show all product
	@GetMapping(value = "/product-list")
	public String findAll(HttpServletRequest request) {
		int pageIndex = 0;
		int pageSize = 5;
		
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		int totalPage = 0;
		int count = productDAO.count();
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		
		request.setAttribute("default", "default");
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("products", productDAO.findAll(pageIndex ,pageSize));
		return "admin/product/listProduct";
	}
	
	// Show all product by category
	
	@GetMapping(value = "/product-list-by-category")
	public String findAllByCategory(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId) {
		int pageIndex = 0;
		int pageSize = 5;
		
		if (request.getParameter("pageIndex") != null) {
			pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
		}
		int totalPage = 0;
		int count = productDAO.countByCategoryId(categoryId);
		if (count % pageSize == 0) {
			totalPage = count / pageSize;
		} else {
			totalPage = count / pageSize + 1;
		}
		
		request.setAttribute("categoryId", categoryId);
		request.setAttribute("pageIndex", pageIndex);
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("products", productDAO.findAllByCategoryId(categoryId, pageIndex, pageSize));
		return "admin/product/listProductByCategory";
	}
	
	// Create new product
	
	@GetMapping(value = "/product-create")
	public String insert(HttpServletRequest request) {
		request.setAttribute("categories", categoryDAO.findAll());
		request.setAttribute("sales", saleDAO.findAll());
		return "admin/product/createNewProduct";
	}
	
	@PostMapping(value = "/product-create")
	public String insertPost(HttpServletRequest request, @RequestParam(name = "categoryId") long categoryId,
			@RequestParam(name = "productName") String productName,
			@RequestParam(name = "description") String description,
			@RequestParam(name = "price") float price,
			@RequestParam(name = "quantity") int quantity,
			@RequestParam(name = "saleId") String saleId,
			@RequestParam(name = "imageFile") MultipartFile imageFile) {
		Category category = new Category();
		category.setCategoryId(categoryId);
		Sale sale = new Sale();
		sale.setSaleId(saleId);
		Product product = new Product();
		product.setCategory(category);
		product.setSale(sale);
		product.setProductName(productName);
		product.setDescription(description);
		product.setPrice(price);
		product.setQuantity(quantity);
		if (imageFile != null && imageFile.getSize() > 0) {
			String originalFilename = imageFile.getOriginalFilename();
			int lastIndex = originalFilename.lastIndexOf(".");
			String ext = originalFilename.substring(lastIndex);
			String avatarFilename = System.currentTimeMillis() + ext;
			File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(imageFile.getBytes());
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage(avatarFilename);
		}
		
		productDAO.insert(product);
		return "redirect:../admin/product-list";
	}
	
	
	// Update product
	
	@GetMapping(value = "/product-update")
	public String update(HttpServletRequest request, @RequestParam(name = "productId") long productId) {
		request.setAttribute("product", productDAO.findById(productId));
		request.setAttribute("sales", saleDAO.findAll());
		request.setAttribute("categories", categoryDAO.findAll());
		return "admin/product/updateProduct";
	}
	
	@PostMapping(value = "/product-update")
	public String update(HttpServletRequest request,
			@RequestParam(name = "newPrice", required = false) String newPrice,
			@RequestParam(name = "imageFile", required = false) MultipartFile imageFile) {
		long productId = Long.parseLong(request.getParameter("productId"));
		long categoryId = Long.parseLong(request.getParameter("categoryId")); 
		float oldprice = Float.parseFloat(request.getParameter("oldPrice"));
		String productName = request.getParameter("productName");
		String description = request.getParameter("description");
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String image = request.getParameter("image");
		String saleId = request.getParameter("saleId");
		
		Sale sale = new Sale();
		sale.setSaleId(saleId);
		Category category = new Category();
		category.setCategoryId(categoryId);
		Product product = new Product();
		product.setProductId(productId);
		product.setSale(sale);
		product.setCategory(category);
		product.setProductName(productName);
		product.setDescription(description);
		product.setQuantity(quantity);
		if (newPrice == null || newPrice.equals("")) {
			product.setPrice(oldprice);
		} else {
			product.setPrice(Float.parseFloat(newPrice));
		}
		if (imageFile != null && imageFile.getSize() > 0) {
			String originalFilename = imageFile.getOriginalFilename();
			int lastIndex = originalFilename.lastIndexOf(".");
			String ext = originalFilename.substring(lastIndex);
			String avatarFilename = System.currentTimeMillis() + ext;
			File newfile = new File("C:\\image_spring_boot\\" + avatarFilename);
			FileOutputStream fileOutputStream;
			try {
				fileOutputStream = new FileOutputStream(newfile);
				fileOutputStream.write(imageFile.getBytes());
				fileOutputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			product.setImage(avatarFilename);
		} else {
			product.setImage(image);
		}
		
		productDAO.update(product);
		return "redirect:/admin/product-list";
	}
	
	// Delete Product
	
	@GetMapping(value = "/product-delete")
	public String delete(HttpServletRequest request) {
		String[] productIds = request.getParameterValues("productId");
		for (String productId : productIds) {
			productDAO.delete(productDAO.findById(Long.parseLong(productId)));
		}
		return "redirect:../admin/product-list";
	}
}