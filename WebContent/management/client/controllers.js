/**
 * 
 */

"use strict";

angular.module("app").controller("clientFunctionController",
		clientFunctionController);

clientFunctionController.$inject = [ "$scope", "$state", "clientService" ];

function clientFunctionController($scope, $state, clientService) {
	$scope.service = clientService;
	$scope.clients = $scope.service.clients;
	$scope.deleteClient = deleteClient;
	$scope.showClient = showClient;
	$scope.fetchClients = $scope.service.fetch;

	$scope.name = "";

	function showClient(id) {
		$state.go("client.data", {
			id : id
		});
	}

	function deleteClient(id) {
		$scope.service.remove(id);
	}

	$scope.$watch("service.clients", function(newVal, oldVal) {
		if (newVal != oldVal)
			$scope.clients = newVal;
	}, true);

	$scope.fetchClients(0, 50);
}