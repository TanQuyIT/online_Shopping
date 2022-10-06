<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body id="home">
	<div class="hot-products">
		<h3 class="title">
			<strong>Hot</strong> Products
		</h3>
		<div class="control">
			<a id="prev_hot" class="prev" href="#">&lt;</a><a id="next_hot"
				class="next" href="#">&gt;</a>
		</div>
		<ul id="hot">
			<li>
				<div class="row">
					<c:forEach items="${hotOne}" var="product">
						<div class="col-md-3 col-sm-6">
							<div class="products">
								<div class="offer">-${product.sale.salePercent}%</div>
								<div class="thumbnail">
									<a href="product-details?productId=${product.productId}"><img
										style="width: 90%;" src="../download?image=${product.image}"
										alt="${product.productName}"></a>
								</div>
								<div class="productname">${product.productName}</div>

								<c:if test="${product.sale.salePercent == 0}">
									<span class="price"
										style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">.</span>
									<span class="price">$${product.price - (product.price *
										product.sale.salePercent / 100)}0</span>
								</c:if>

								<c:if test="${product.sale.salePercent != 0}">
									<span class="price"
										style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">$${product.price}0</span>
									<span class="price">$${product.price - (product.price *
										product.sale.salePercent / 100)}0</span>
								</c:if>
								<div class="button_group">
									<a class="button add-cart" type="button"
										href="add-to-cart?productId=${product.productId}">Add To
										Cart</a>

									<button class="button compare" type="button">
										<i class="fa fa-exchange"></i>
									</button>
									<button class="button wishlist" type="button">
										<i class="fa fa-heart-o"></i>
									</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</li>
			<li>
				<div class="row">
					<c:forEach items="${hotTwo}" var="product">
						<div class="col-md-3 col-sm-6">
							<div class="products">
								<div class="offer">-${product.sale.salePercent}%</div>
								<div class="thumbnail">
									<a href="product-details?productId=${product.productId}"><img
										style="width: 90%;" src="../download?image=${product.image}"
										alt="Product Name"></a>
								</div>
								<div class="productname">${product.productName}</div>

								<c:if test="${product.sale.salePercent == 0}">
									<span class="price"
										style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">.</span>
									<span class="price">$${product.price - (product.price *
										product.sale.salePercent / 100)}0</span>
								</c:if>

								<c:if test="${product.sale.salePercent != 0}">
									<span class="price"
										style="font-size: 15px; color: black; text-decoration: line-through; margin-bottom: 0px; margin-top: -5px;">$${product.price}0</span>
									<span class="price">$${product.price - (product.price *
										product.sale.salePercent / 100)}0</span>
								</c:if>

								<div class="button_group">
									<a class="button add-cart" type="button"
										href="add-to-cart?productId=${product.productId}">Add To
										Cart</a>

									<button class="button compare" type="button">
										<i class="fa fa-exchange"></i>
									</button>
									<button class="button wishlist" type="button">
										<i class="fa fa-heart-o"></i>
									</button>
								</div>
							</div>
						</div>
					</c:forEach>
				</div>
			</li>
		</ul>
	</div>
	<!-- Bootstrap core JavaScript==================================================-->
	<!-- 	<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/jquery.easing.1.3.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/bootstrap.min.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/jquery.sequence-min.js"></script> -->
	<!-- 	<script type="text/javascript" -->
	<!-- 		src="js/jquery.carouFredSel-6.2.1-packed.js"></script> -->
	<!-- 	<script defer src="js/jquery.flexslider.js"></script> -->
	<!-- 	<script type="text/javascript" src="js/script.min.js"></script> -->
</body>

</html>