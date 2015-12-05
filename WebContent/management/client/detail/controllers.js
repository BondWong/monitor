/**
 * 
 */

"use strict";

angular.module("app").controller("clientDataController", clientDataController)
		.controller("clientFormController", clientFormController);

clientDataController.$inject = [ "$scope", "$stateParams", "clientService" ];
clientFormController.$inject = [ "$scope", "$state", "clientService",
		"projectService" ];

function clientDataController($scope, $stateParams, clientService) {
	$scope.service = clientService;
	$scope.client = $scope.service.currentClient;

	$scope.getClient = getClient;
	$scope.updateClient = updateClient;

	function getClient(id) {
		$scope.service.get(id);
	}

	function updateClient() {
		$scope.service.update($scope.client);
	}

	$scope.$watch("service.currentClient", function(newVal, oldVal) {
		if (newVal != oldVal) {
			$scope.client = newVal;
		}
	});

	$scope.getClient($stateParams.id);
}

function clientFormController($scope, $state, clientService, projectService) {
	$scope.data = {
		id : "" + moment().valueOf(),
		name : "",
		password : "",
		project : "",
		phone : "",
		address : {
			street : "",
			city : "",
			state : "",
			zip : "",
			country : ""
		},
		attributes : {}
	};
	$scope.clientService = clientService;
	$scope.projectService = projectService;
	$scope.projects = projectService.projects;

	$scope.createClient = createClient;

	$scope.$watch("projectService.projects", function(newVal, oldVal) {
		if (newVal != oldVal)
			$scope.projects = newVal
	}, true);

	$scope.$watch("clientService.clients", function(newVal, oldVal) {
		if (newVal != oldVal) {
			$scope.data = {
				id : "" + moment().valueOf(),
				name : "",
				password : "",
				project : "",
				phone : "",
				address : {
					street : "",
					city : "",
					state : "",
					zip : "",
					country : ""
				},
				attributes : {}
			};
		}
	}, true);
	
	projectService.fetchProjects();
	
	function createClient() {
		$scope.clientService.create($scope.data);
		$state.go("client.data", {
			id : $scope.data.id
		});
	}

}
