<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<div class="well">
		<form class="form-group">
			<input placeholder="search" class="form-control" ng-model="name" />
		</form>
		<div class="btn-group btn-group-justified" role="group">
			<a role="button" ui-sref="client.form" class="btn btn-default">Create</a>
		</div>
	</div>
	<div class="col-lg-12 col-md-12">
		<p ng-repeat="client in clients | search:name">
			<a role="button" ng-click="showClient(client.id)">{{client.name}}</a>
			<a role="button" ng-click="deleteClient(client.id)"
				class="pull-right"><span class="badge">&times;</span></a>
		</p>
	</div>
</div>