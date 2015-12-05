<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<a class="btn btn-default" role="button" ng-click="toCreate()">Add</a>
</div>

<div ng-repeat="progress in progresses">
	<p>
		<a role="button" ng-click="toDetail(progress.id)">{{progress.name}}</a><a
			role="button" href="javascript:void(0)"><span
			class="glyphicon glyphicon-remove" aria-hidden="true"
			ng-click="remove(progress.id)"></span></a>
	</p>
</div>

<div>
	<a class="btn btn-default" role="button" ng-click="loadMore()">Load
		more</a>
</div>