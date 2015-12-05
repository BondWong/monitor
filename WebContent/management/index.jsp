<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html ng-app="app">
<head>
<meta charset="UTF-8" />
<meta name="description" content="猎网工作室项目查看平台" />
<meta name="keywords" content="猎网工作室" />
<meta name="author" content="黃俊邦(WongChunbong)" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>HuntingWeb</title>
<link href="/assets/css/bootstrap/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<link href="/assets/css/bootstrap/bootstrap-slider.css" type="text/css"
	rel="stylesheet" />
<link href="/assets/css/datetimepicker/datetimepicker.css"
	type="text/css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse" ng-controller="navbarController">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar-content"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:void(0)">HuntingWeb
					Monitor</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse" id="navbar-content">
				<ul class="nav navbar-nav">
					<li><a ng-click="toProjects()" href="javascript:void(0)">Projects
					</a></li>
					<li><a ng-click="toClients()" href="javascript:void(0)">Clients</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/logout" class="btn btn-danger navbar-btn"><span
							class="glyphicon glyphicon-off
					"></span></a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>
	<div
		class="container-fluid col-lg-offset-1 col-md-offset-1 col-lg-10 col-md-10">
		<div class="col-lg-3 col-md-3" ui-view="master-view"></div>
		<div class="col-lg-7 col-md-7" ui-view="detail-view"></div>
	</div>

	<script src="/assets/js/jquery.js"></script>
	<script src="/assets/js/moment.js"></script>
	<script src="/assets/js/bootstrap.js"></script>
	<script src="/assets/js/angular.js"></script>

	<script src="/assets/js/ui-bootstrap-tpls.js"></script>
	<script src="/assets/js/bootstrap-slider.js"></script>
	<script src="/assets/js/angular-ui-router.js"></script>
	<script src="/assets/js/angular-resource.js"></script>
	<script src="/assets/js/angular-file-model.js"></script>
	<script src="/assets/js/focusIf.js"></script>
	<script src="/assets/js/datetimepicker.js"></script>
	<script src="/assets/js/slider.js"></script>

	<script src="app.js"></script>
	<script src="filters.js"></script>
	<script src="services.js"></script>
	<script src="controllers.js"></script>
	<script src="project/controllers.js"></script>
	<script src="project/detail/controllers.js"></script>
	<script src="client/controllers.js"></script>
	<script src="client/detail/controllers.js"></script>
	<script src="project/detail/content/controllers.js"></script>
</body>
</html>