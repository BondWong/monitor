<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<div class="well">
		<form class="form-group">
			<input placeholder="search" class="form-control" ng-model="name" />
		</form>
		<div class="btn-group btn-group-justified" role="group">
			<a role="button" ui-sref="project.form" class="btn btn-default">Create</a>
		</div>
	</div>
	<div class="col-lg-offset-1 col-md-offset-1 col-lg-10 col-md-10">
		<p ng-repeat="project in projects | search:name">
			<a role="button" ng-click="showProject(project.id)">{{project.name}}<label
				role="button" class="col-lg-offset-1 col-md-offset-1"
				ng-show="project.unreadMessageNum>0"><span
					class="label label-pill label-warning">{{project.unreadMessageNum}}</span></label></a>
			<a role="button" ng-click="deleteProject(project.id)"
				class="pull-right"><span class="badge">&times;</span></a>
		</p>
	</div>
</div>