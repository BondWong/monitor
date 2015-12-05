<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<li role="presentation" class="active"><a ng-click="toData()"
			href="javascript:void(0)" aria-controls="data" role="tab"
			data-toggle="tab">Project data</a></li>
		<li role="presentation"><a ng-click="toProgresses()"
			href="javascript:void(0)" aria-controls="progress" role="tab"
			data-toggle="tab">Project progress</a></li>
		<li role="presentation"><a ng-click="toMessages()"
			href="javascript:void(0)" aria-controls="messages" role="tab"
			data-toggle="tab">Client messages</a></li>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<div role="tabpanel" class="tab-pane active" ui-view="content"></div>
	</div>

</div>
