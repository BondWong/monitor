/**
 * 
 */

"use strcit";

angular.module("app").controller("navbarController", navbarController);

navbarController.$inject = [ "$scope", "$state" ];

function navbarController($scope, $state) {
	$scope.toProjects = toProjects;
	$scope.toClients = toClients;

	function toProjects() {
		$state.go("project");
		$state.go("project.form");
	}

	function toClients() {
		$state.go("client");
		$state.go("client.form");
	}

	toProjects();
}