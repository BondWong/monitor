/**
 * 
 */

"use strict";

angular.module("app").controller("monitorController", monitorController)
		.controller("navbarController", navbarController);

monitorController.$inject = [ "$rootScope", "$scope", "dataService",
		"clientService" ];
navbarController.$inject = [ "$rootScope", "$scope", "dataService" ];

function navbarController($rootScope, $scope, dataService) {
	$scope.project = {};
	$rootScope.feedbackShow = false;
	$scope.toggleFeedback = toggleFeedback;

	function toggleFeedback() {
		$rootScope.feedbackShow = !$rootScope.feedbackShow;
	}

	$scope.$on("monitor-project-getted", function(event, data) {
		$scope.project = data.project;
	});
}

function monitorController($rootScope, $scope, dataService, clientService) {
	$scope.events = [];
	$scope.getProject = dataService.getProject;
	$scope.fetchProgresses = dataService.fetchProgresses;
	$scope.getClient = clientService.get;
	$scope.client = {};
	$scope.project = {};
	$scope.progress = [];
	$scope.showFileDialog = showFileDialog;
	$scope.loadMore = loadMore;
	$scope.page = 0;
	$scope.size = 20;

	$scope.$on("monitor-project-getted", function(event, data) {
		$scope.project = data.project;
	});

	$scope.$on("monitor-progresses-fetched", function(event, data) {
		console.log(data);
		if (data == null || data.length == 0)
			return;
		$scope.page++;
		$scope.progresses = data.progresses;
		angular.forEach($scope.progresses, function(element, index) {
			if (index != 0)
				$scope.events.push({
					progress : element,
					badgeClass : "info",
					badgeIconClass : "glyphicon glyphicon-ok"
				});
			else
				$scope.events.push({
					progress : element,
					badgeClass : "warning",
					badgeIconClass : "glyphicon glyphicon-console"
				});
		});

	});

	$scope.$on("monitor-client-getted", function(event, data) {
		$scope.client = data;
		$scope.getProject(data.projectId);
		$scope.fetchProgresses(data.projectId, $scope.page, $scope.size);
	});

	$scope.$watch("feedbackFile", function(newVal, oldVal) {
		if (newVal != oldVal) {
			newVal.id = moment().valudOf() + "";
			$scope.newFeedbackFiles.push(newVal);
		}
	});

	$scope.getClient($rootScope.clientId);

	function showFileDialog(id) {
		angular.element(id).click();
	}

	function loadMore() {
		$scope.fetchProgresses($scope.client.projectId, $scope.page,
				$scope.size);
	}
}