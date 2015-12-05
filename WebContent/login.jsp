<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="猎网工作室项目查看平台" />
<meta name="keywords" content="猎网工作室" />
<meta name="author" content="黃俊邦(WongChunbong)" />
<meta name="viewport" content="width=device-width" />
<title>HuntingWeb</title>

<!-- CSS -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
<link rel="stylesheet" href="assets/css/bootstrap/bootstrap.min.css">
<link rel="stylesheet"
	href="assets/font-awesome/css/font-awesome.min.css">
<link rel="stylesheet" href="assets/css/login/form-elements.css">
<link rel="stylesheet" href="assets/css/login/style.css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
            <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

<!-- Favicon and touch icons -->
<!-- <link rel="shortcut icon" href="assets/ico/favicon.png"> 
<link rel="apple-touch-icon-precomposed" sizes="144x144"
	href="assets/ico/apple-touch-icon-144-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="114x114"
	href="assets/ico/apple-touch-icon-114-precomposed.png">
<link rel="apple-touch-icon-precomposed" sizes="72x72"
	href="assets/ico/apple-touch-icon-72-precomposed.png">
<link rel="apple-touch-icon-precomposed"
	href="assets/ico/apple-touch-icon-57-precomposed.png"> -->

</head>

<body>

	<!-- Top content -->
	<div class="top-content">

		<div class="inner-bg">
			<div class="container">
				<div class="row">
					<div class="col-sm-8 col-sm-offset-2 text">
						<h1>
							<strong>HuntingWeb</strong>项目查看平台
						</h1>
						<c:choose>
							<c:when test="${requestScope.error != null }">
								<h3 class="error">${requestScope.error }</h3>
							</c:when>
							<c:otherwise>
								<div class="description">
									<p>请使用我们提供的账号和密码登陆查看自己项目进度</p>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-6 col-sm-offset-3 form-box">
						<div class="form-top">
							<div class="form-top-left">
								<h3>登陆</h3>
								<p>输入账号和密码:</p>
							</div>
							<div class="form-top-right">
								<i class="fa fa-lock"></i>
							</div>
						</div>
						<div class="form-bottom">
							<form role="form" action="/login" method="post"
								class="login-form">
								<div class="form-group">
									<label class="sr-only" for="form-id">账号</label> <input
										type="text" name="id" placeholder="账号"
										class="form-username form-control" id="form-id">
								</div>
								<div class="form-group">
									<label class="sr-only" for="form-password">密码</label> <input
										type="password" name="password" placeholder="密码"
										class="form-password form-control" id="form-password">
								</div>
								<button type="submit" class="btn">登陆</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>


	<!-- Javascript -->
	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/jquery.backstretch.min.js"></script>
	<script src="assets/js/login.js"></script>

	<!--[if lt IE 10]>
            <script src="assets/js/placeholder.js"></script>
        <![endif]-->

</body>

</html>