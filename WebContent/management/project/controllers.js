/**
 * 
 */

"use strict";

angular.module("app").controller("projectFunctionController",
		projectFunctionController);

projectFunctionController.$inject = [ "$scope", "$state", "projectService" ];

function projectFunctionController($scope, $state, projectService) {
	$scope.projectService = projectService;
	$scope.projects = $scope.projectService.projects;
	$scope.fetchProjects = projectService.fetchProjects;
	$scope.showProject = showProject;
	$scope.deleteProject = deleteProject;

	$scope.name = "";

	$scope.fetchProjects();

	$scope.$watch("projectService.projects", function(newVal, oldVal) {
		if (newVal != oldVal)
			$scope.projects = newVal;
	}, true);

	function showProject(id) {
		$state.go("project.content", {
			id : id
		});
		$state.go("project.content.data", {
			id : id
		});
	}

	function deleteProject(id) {
		projectService.deleteProject(id);
		if ($scope.projects.length <= 1)
			$state.go("project.form");
	}

}