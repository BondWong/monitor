<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<security:authentication property="principal.username" var="clientId"
	scope="session" />
<!DOCTYPE html>
<html ng-app="app" ng-init="clientId=${clientId }">
<head>
<meta charset="UTF-8" />
<meta name="description" content="猎网工作室项目查看平台" />
<meta name="keywords" content="猎网工作室" />
<meta name="author" content="黃俊邦(WongChunbong)" />
<meta name="viewport" content="width=device-width" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title>HuntingWeb</title>
<link href="assets/css/bootstrap/bootstrap.min.css" type="text/css"
	rel="stylesheet" />
<link href="assets/css/angular/angular-timeline.css" type="text/css"
	rel="stylesheet" />
<link href="assets/css/chat/style.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-inverse">
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
			<div class="collapse navbar-collapse" id="navbar-content"
				ng-controller="navbarController">
				<p class="navbar-text">欢迎使用HuntingWeb项目查看平台</p>
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">项目状态 <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0)">价格：{{project.value |
									currency}}</a></li>
							<li><a href="javascript:void(0)">进度：{{project.progress}}%</a></li>
							<li><a href="javascript:void(0)">提交次数：{{project.submitNum}}</a></li>
						</ul></li>
				</ul>
				<ul class="nav navbar-nav">
					<li><a href="javascript:void(0)" ng-click="toggleFeedback()">联系我们</a></li>
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

	<div ng-controller="monitorController" class="container">
		<timeline> <timeline-event ng-repeat="event in events">
		<timeline-badge class="{{event.badgeClass}}"> <i
			class="{{event.badgeIconClass}}"> </i> </timeline-badge> <timeline-panel
			class="{{event.badgeClass}}"> <timeline-heading>
		<h4>{{event.progress.name}}</h4>
		</timeline-heading> <timeline-content event="event"></timeline-content> <small>{{event.progress.dateTime
			| friendly}}</small>
		<hr />
		<timeline-footer> </timeline-footer> </timeline-panel> </timeline-event> <timeline-event>
		<timeline-badge class="success" ng-click="loadMore()">
		<i class="glyphicon glyphicon-refresh"></i></timeline-badge></timeline-event> </timeline>
	</div>
	<feedback ng-if="$root.feedbackShow"></feedback>

	<script src="assets/js/jquery.js"></script>
	<script src="assets/js/moment.js"></script>
	<script src="assets/js/bootstrap.js"></script>
	<script src="assets/js/angular.js"></script>

	<script src="assets/js/angular-ui-router.js"></script>
	<script src="assets/js/angular-resource.js"></script>
	<script src="assets/js/angular-file-model.js"></script>
	<script src="assets/js/angular-timeline.js"></script>

	<script src="monitor/app.js"></script>
	<script src="monitor/controllers.js"></script>
	<script src="monitor/services.js"></script>
	<script src="monitor/filters.js"></script>
	<script src="monitor/directives.js"></script>
</body>
</html>