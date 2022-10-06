<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard</title>
<meta charset="utf-8">
</head>

<body>
	<div th:fragment="header">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="home"> <img
					src="../resource/admin/assets/img/logo-dark.png"
					alt="Klorofil Logo" class="img-responsive logo">
				</a>
			</div>
			<div class="container-fluid">
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="home"> <i class="fa fa-home"
								style="font-size: 18px;"></i><span> Home</span>
						</a></li>
						<li><a href="#"> <img
								src="../download?image=${sessionScope.user.avatar}"
								class="img-circle" alt="Avatar"> <span>${sessionScope.user.fullname}</span>
						</a></li>
						<li><a href="../logout"> <i class="material-icons"
								style="color: red;">&#xe8ac;</i><span>Logout</span>
						</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
	<!-- END NAVBAR -->
</body>
</html>