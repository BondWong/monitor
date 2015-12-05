<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form>
	<div class="form-group">
		<label for="projectName">Project name</label> <input type="text"
			class="form-control" id="projectName" placeholder="Project name"
			ng-model="project.name" />
	</div>
	<div class="form-group">
		<label for="projectValue">Project Value</label>
		<div class="input-group">
			<div class="input-group-addon">¥</div>
			<input id="projectValue" class="form-control"
				ng-model="project.value" placeholder="Project value" />
		</div>
	</div>
	<div class="form-group">
		<label for="projectType">Project Type</label> <select id="projectType"
			class="form-control" ng-model="project.type"
			ng-options="type.value as type.name for type in types"></select>
	</div>
	<div class="form-group">
		<label for="projectDeadline">Project Deadline</label>
		<div class="dropdown">
			<a class="dropdown-toggle" id="datepicker-dropdown" role="button"
				data-toggle="dropdown" data-target="#" href="javascript:void(0)">
				<div class="input-group">
					<input id="projectDeadline" type="text" class="form-control"
						data-ng-model="project.deadline"><span
						class="input-group-addon"><i
						class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
				<datetimepicker data-ng-model="project.deadline"
					data-datetimepicker-config="{ dropdownSelector: '#datepicker-dropdown',startView:'day',minView:'day'}" />
			</ul>
		</div>
	</div>
	<div class="form-group">
		<label for="projectProgress">Project Progress</label>
		<slider id="projectProgress" ng-model="project.progress" min="0"
			step="1" max="100" value="1"></slider>
	</div>
	<div class="form-group">
		<label for="projectFiles">Project files</label>
		<p ng-repeat="file in project.files" id="projectFiles">
			<a role="button" ng-href="/{{file.url}}" target="_blank">{{file.name}}</a>
			<a role="button" class="col-lg-offset-3 col-md-offset-3"></a>
		</p>
	</div>
	<button type="button" class="btn btn-default"
		ng-click="updateProject()">Update</button>
</form>