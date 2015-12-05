/**
 * 
 */

"use strcit";

angular.module("app").factory("dataService", dataServiceFactory).factory(
		"messageService", messageServiceFactory).factory("clientService",
		clientServiceFactory).factory("fileService",
		[ "$http", "$q", "$rootScope", fileServiceFactory ]);

dataServiceFactory.$inject = [ "$resource", "$rootScope" ];
messageServiceFactory.$inject = [ "$resource", "$rootScope" ];
clientServiceFactory.$inject = [ "$resource", "$rootScope" ];

function fileServiceFactory($http, $q, $rootScope) {
	var service = {
		uploadFile : uploadFile
	};

	return service;

	function uploadFile(file) {
		var fileReader = new FileReader();

		fileReader.id = file.id;
		fileReader.name = file.name;
		fileReader.type = file.type;

		fileReader.onload = onload;
		fileReader.onerror = onerror;
		fileReader.onprogress = onprogress;

		fileReader.readAsArrayBuffer(file);
		return;

		function onload(evt) {
			var fileReader = evt.target;
			var result = new Int8Array(evt.target.result);
			var bytes = [];

			angular.forEach(result, function(element, index) {
				bytes[index] = result[index];
			});

			$http.post("/file", {
				id : fileReader.id,
				name : fileReader.name,
				type : fileReader.type,
				bytes : bytes
			}).success(function(fileRep) {
				$rootScope.$broadcast("monitor-fileupload-success", fileRep);
			}).error(function(error) {
				$rootScope.$broadcast("monitor-fileupload-fail", error);
			});
		}

		function onerror(error) {
			$rootScope.$broadcast("monitor-fileupload-fail", error);
		}

		function onprogress(evt) {

		}
	}

}

function clientServiceFactory($resource, $rootScope) {
	var resource = $resource("/client/:id", {}, {
		"get" : {
			method : "GET",
			cache : true
		}
	});

	var service = {
		get : get
	};
	return service;

	function get(id) {
		resource.get({
			id : id
		}).$promise.then(function(response) {
			$rootScope.$broadcast("monitor-client-getted", response);
		}, function(reason) {
			console.error(reason);
		});
	}
}

function dataServiceFactory($resource, $rootScope) {
	var projectResource = $resource("/project/:id", {}, {
		"get" : {
			method : "GET",
			cache : true
		}
	});
	var progressResource = $resource("/progress/:projectId/:page/:size", {}, {
		"fetch" : {
			method : "GET",
			isArray : true,
			cache : true
		}
	});
	var service = {
		getProject : getProject,
		fetchProgresses : fetchProgresses
	}

	return service;

	function getProject(id) {
		projectResource.get({
			id : id
		}).$promise.then(function(response) {
			$rootScope.$broadcast("monitor-project-getted", {
				project : response
			});
		}, function(reaseon) {
			console.error(reason);
		});
	}

	function fetchProgresses(projectId, page, size) {
		progressResource.fetch({
			projectId : projectId,
			page : page,
			size : size
		}).$promise.then(function(response) {
			$rootScope.$broadcast("monitor-progresses-fetched", {
				progresses : response
			});
		}, function(reason) {
			console.error(reason);
		});
	}

}

function messageServiceFactory($resource, $rootScope) {
	var resource = $resource("/message/:projectId/:page/:size", {}, {
		"create" : {
			method : "POST"
		},
		"fetch" : {
			method : "GET",
			isArray : true,
			cache : true
		},
		"createFileMessage" : {
			method : "POST"
		}
	});

	var service = {
		create : create,
		fetch : fetch
	};

	return service;

	function create(projectId, params) {
		resource.create({
			projectId : projectId
		}, params).$promise.then(function(response) {
			$rootScope.$broadcast("monitor-message-created", response);
		}, function(reason) {
			console.error(reason);
		});
	}

	function fetch(projectId, page, size) {
		resource.fetch({
			projectId : projectId,
			page : page,
			size : size
		}).$promise.then(function(response) {
			$rootScope.$broadcast("monitor-messages-fetched", response);
		}, function(reason) {
			console.error(reason);
		});
	}
}