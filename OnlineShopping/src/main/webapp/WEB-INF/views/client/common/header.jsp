<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="images/favicon.png">
<title>Welcome to Team1Shop</title>
</head>
<body id="home">
	<div class="header">
		<div class="container">
			<div class="row">
				<div class="col-md-2 col-sm-2">
					<div class="logo">
						<a href="../../index.jsp"><img
							src="../resource/client/images/logo.png" alt="Team1Shop"></a>
					</div>
				</div>
				<div class="col-md-10 col-sm-10">
					<div class="header_top">
						<div class="row">
							<div class="col-md-6"></div>

							<div class="col-md-6">
								<c:if test="${sessionScope.user == null}">
									<ul class="usermenu">
										<li><a href="../login" class="log">Login</a></li>
										<li><a href="../register" class="reg">Register</a></li>
									</ul>
								</c:if>

								<c:if test="${sessionScope.user != null}">
									<ul class="usermenu" style="display: flex;">
										<li><a href="profile"><img class="img-circle"
												src="../download?image=${sessionScope.user.avatar}"
												style="width: 26px; margin-top: -4px;"> </a><a
											href="profile" style="margin-left: -22px;"><span
												style="margin-left: 5px; color: white; font-size: 14px;">${sessionScope.user.fullname}</span></a></li>
										<c:if test="${sessionScope.user.role.roleId != 3}">
											<li style="margin-right: 56px;"><i
												style="font-size: 22px; margin-top: -2px; color: #5F9DF7;"
												class="fa">&#xf013;</i> <a
												style="position: absolute; margin-left: -18px; margin-right: 10px;"
												href="/admin/home">Admin Page</a></li>
										</c:if>
										<li><i
											style="font-size: 22px; margin-top: -2px; color: #F7544A;"
											class="fa">&#xf011;</i> <a
											style="position: absolute; margin-left: -18px;"
											href="../logout">Logout</a></li>
									</ul>
								</c:if>

							</div>
						</div>
					</div>
					<div class="clearfix"></div>
					<div class="header_bottom">
						<ul class="option">
							<li id="search" class="search">
								<form action="search" method="get">
									<input class="search-submit" type="submit" value=""><input
										class="search-input" placeholder="Enter your search term..."
										type="text" value="" name="text">
								</form>
							</li>

							<li class="option-cart""><a href="cart" class="cart-icon">cart
									<span class="cart_no">${sessionScope.totalQuantity}</span>
							</a></li>

							<!--                            <li class="option-cart" sec:authorize="isAuthenticated()">
                              <a th:href="@{/client/cart-load}" class="cart-icon">cart <span class="cart_no">02</span></a>
                           </li>
                            -->
						</ul>
						<div class="navbar-header">
							<button type="button" class="navbar-toggle"
								data-toggle="collapse" data-target=".navbar-collapse">
								<span class="sr-only">Toggle navigation</span><span
									class="icon-bar"></span><span class="icon-bar"></span><span
									class="icon-bar"></span>
							</button>
						</div>
						<div class="navbar-collapse">
							<ul class="nav">
								<li><a style="text-transform: none;" href="home">Home</a></li>
								<c:forEach items="${sessionScope.categories}" var="category">
									<li><a style="text-transform: none;"
										href="search?categoryId=${category.categoryId}">${category.categoryName}</a>
									</li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>