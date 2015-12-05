<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form>
	<div class="form-group">
		<label for="projectName">Project name</label> <input type="text"
			class="form-control" id="projectName" placeholder="Project name"
			ng-model="data.name" />
	</div>
	<div class="form-group">
		<label for="projectValue">Project Value</label>
		<div class="input-group">
			<div class="input-group-addon">¥</div>
			<input id="projectValue" class="form-control" ng-model="data.value"
				placeholder="Project value" />
		</div>
	</div>
	<div class="form-group">
		<label for="projectType">Project Type</label> <select id="projectType"
			class="form-control" ng-model="data.type"
			ng-options="type.value as type.name for type in types"></select>
	</div>
	<div class="form-group">
		<label for="projectDeadline">Project Deadline</label>
		<div class="dropdown">
			<a class="dropdown-toggle" id="datepicker-dropdown" role="button"
				data-toggle="dropdown" data-target="#" href="javascript:void(0)">
				<div class="input-group">
					<input id="projectDeadline" type="text" class="form-control"
						data-ng-model="data.deadline"><span
						class="input-group-addon"><i
						class="glyphicon glyphicon-calendar"></i></span>
				</div>
			</a>
			<ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
				<datetimepicker data-ng-model="data.deadline"
					data-datetimepicker-config="{ dropdownSelector: '#datepicker-dropdown',startView:'day',minView:'day'}" />
			</ul>
		</div>
	</div>
	<div class="form-group">
		<label for="projectFile">File input</label> <input type="file"
			id="projectFile" style="visibility: hidden;" file-model="file" />
		<p ng-repeat="file in data.files">
			<a role="button" src="javascript:void(0)">{{file.name}}</a> <a
				role="button" ng-click="removeFile(file.id)"
				class="col-lg-offset-3 col-md-offset-3"><span class="badge">&times;</span></a>
		</p>
		<a class="btn btn-default" role="button" ng-click="showFileDialog()">Add
			<span class="glyphicon glyphicon-open-file" aria-hidden="true"></span>
		</a>
		<p class="help-block">Upload requirement files.</p>
	</div>
	<button type="button" class="btn btn-default"
		ng-click="createProject()">Submit</button>
</form>