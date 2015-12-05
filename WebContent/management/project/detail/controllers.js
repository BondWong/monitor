/**
 * 
 */

"use strict";

angular.module("app")
		.controller("projectFormController", projectFormController).controller(
				"projectContentController", projectContentController);

projectFormController.$inject = [ "$scope", "$state", "projectService",
		"projectTypes" ];
projectContentController.$inject = [ "$scope", "$state", "$stateParams" ];

function projectContentController($scope, $state, $stateParams) {
	$scope.toData = toData;
	$scope.toProgresses = toProgresses;
	$scope.toMessages = toMessages;

	function toData() {
		$state.go("project.content.data", {
			id : $stateParams.id
		});
	}

	function toProgresses() {
		$state.go("project.content.progresses", {
			id : $stateParams.id
		});
	}

	function toMessages() {
		$state.go("project.content.messages", {
			id : $stateParams.id
		});
	}

}

function projectFormController($scope, $state, projectService, projectTypes) {
	$scope.types = projectTypes;
	$scope.file = {};
	$scope.initialData = {
		id : moment().valueOf() + "",
		name : "",
		value : 5000,
		type : $scope.types[0].value,
		deadline : moment(),
		files : [],
		attributes : {}
	};
	$scope.data = {
		id : moment().valueOf() + "",
		name : "",
		value : 5000,
		type : $scope.types[0].value,
		deadline : moment(),
		files : [],
		attributes : {}
	};
	$scope.service = projectService;
	$scope.createProject = createProject;
	$scope.showFileDialog = showFileDialog;
	$scope.removeFile = removeFile;

	function createProject() {
		var data = {};
		angular.copy($scope.data, data);
		data.deadline = data.deadline.valueOf();
		data.value = parseInt(data.value);
		$scope.service.createProject(data);
	}

	function showFileDialog() {
		angular.element("#projectFile").click();
	}

	function removeFile(id) {
		angular.forEach($scope.data.files, function(element, index) {
			if (element.id == id) {
				$scope.data.files.splice(index, 1);
				return;
			}
		})
	}

	$scope.$watch("service.currentProject", function(newVal, oldVal) {
		if (newVal != oldVal) {
			$scope.data = $scope.initialData;
			$state.go("project.content", {
				id : newVal.id
			});
			$state.go("project.content.data", {
				id : newVal.id
			});
		}
	});

	$scope.$watch("file", function(newVal, oldVal) {
		if (newVal != oldVal) {
			newVal.id = moment().valueOf() + "";
			$scope.data.files.push(newVal);
		}
	});
}