/**
 * 
 */

"use strict";
angular.module("app").constant("projectTypes", [ {
	value : "APP",
	name : "APP"
}, {
	value : "Website",
	name : "Website"
}, {
	value : "Design",
	name : "Design"
}, {
	value : "Others",
	name : "Others"
} ]).factory("fileService", [ "$http", "$q", fileService ]).factory(
		"projectResource", [ "$resource", projectResource ]).factory(
		"projectService",
		[ "projectResource", "fileService", "fileResource", projectService ])
		.factory("fileResource", [ "$resource", fileResource ]).factory(
				"clientResource", [ "$resource", clientResource ]).factory(
				"clientService", [ "clientResource", clientService ]).factory(
				"progressResource", [ "$resource", progressResource ]).factory(
				"progressService",
				[ "progressResource", "$rootScope", "fileService",
						"fileResource", progressService ]).factory(
				"messageService",
				[ "$rootScope", "$resource", messageServiceFactory ]);

function messageServiceFactory($rootScope, $resource) {
	var resource = $resource(
			"/message/:publisher/:projectId/:page/:size/:messageId/", {}, {
				"fetch" : {
					method : "GET",
					isArray : true,
					cache : true
				},
				"create" : {
					method : "POST"
				},
				"update" : {
					method : "PUT"
				}
			});

	var service = {
		fetch : fetch,
		create : create,
		update : update
	}

	return service;

	function fetch(projectId, publisher, page, size) {
		resource.fetch({
			projectId : projectId,
			page : page,
			size : size,
			publisher : publisher
		}).$promise.then(function(response) {
			$rootScope.$broadcast("management-project-messages-fetched",
					response);
		});
	}

	function create(projectId, messageId, params) {
		resource.create({
			projectId : projectId
		}, params).$promise.then(function(id) {
			$rootScope.$broadcast("management-project-message-created",
					messageId);
		}, function(reason) {
			console.error(reason);
		});
	}

	function update(id, param) {
		resource.update({
			messageId : id
		}, param).$promise.then(function(response) {
			$rootScope.$broadcast("management-project-message-updated", id);
		}, function(reason) {
			console.error(reason);
		});
	}
}

function fileResource($resource) {
	return $resource("/file/:type/:ownerId/:fileId", {}, {});
}

function fileService($http, $q) {
	var service = {
		uploadFiles : uploadFiles
	};

	return service;

	function uploadFiles(files) {
		var deferred = $q.defer();
		var num = files.length;

		if (num <= 0)
			deferred.resolve([]);
		else {
			var fileReps = [];

			angular.forEach(files, function(file, index) {
				var fileReader = new FileReader();

				fileReader.id = file.id;
				fileReader.name = file.name;
				fileReader.type = file.type;

				fileReader.onload = onload;
				fileReader.onerror = onerror;
				fileReader.onprogress = onprogress;

				fileReader.readAsArrayBuffer(file);

			});
		}

		return deferred.promise;

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
				fileReps[fileReps.length] = fileRep;
				if (--num <= 0)
					deferred.resolve(fileReps);
			}).error(function(error) {
				deferred.reject(error);
			});
		}

		function onerror(error) {
			deferred.reject(error);
		}

		function onprogress(evt) {

		}
	}

}

function projectResource($resource) {
	return $resource("/project/:id/:page/:size", {}, {
		"fetch" : {
			method : "GET",
			isArray : true,
			cache : true
		},
		"create" : {
			method : "POST"
		},
		"update" : {
			method : "PUT"
		}
	});
}

function projectService(projectResource, fileService, fileResource) {
	var service = {
		projects : [],
		currentProject : {},
		createProject : createProject,
		getProject : getProject,
		fetchProjects : fetchProjects,
		updateProject : updateProject,
		deleteProject : deleteProject,
		removeFile : removeFile
	};
	return service;

	function createProject(param) {
		fileService.uploadFiles(param.files).then(function(data) {
			param.files = data;
			projectResource.create(param).$promise.then(function(data) {
				service.currentProject = data;
				service.projects.push(data);
			}), function(reason) {
				console.error(reason);
			};
		}, function(reason) {
			console.error(reason);
		});
	}

	function getProject(id) {
		projectResource.get({
			id : id
		}).$promise.then(function(data) {
			service.currentProject = data
		}, function(reason) {
			console.error(reason);
		});
	}

	function fetchProjects() {
		projectResource.fetch({
			page : 0,
			size : 50
		}).$promise.then(function(data) {
			service.projects = data;
		}, function(reason) {
			console.error(reason);
		});
	}

	function updateProject(param) {
		projectResource.update({
			id : param.id
		}, param).$promise.then(function(data) {
			angular.forEach(service.projects, function(element, index) {
				if (element.id == data.id) {
					service.projects[index] = data;
					return;
				}
			});
		}, function(reason) {
			console.error(reason);
		});
	}

	function deleteProject(id) {
		projectResource.remove({
			id : id
		}).$promise.then(function(response) {
			angular.forEach(service.projects, function(element, index) {
				if (element.id == id) {
					service.projects.splice(index, 1);
					return;
				}
			});
		}, function(reason) {
			console.error(reason);
		});
	}

	function removeFile(projectId, fileId) {
		fileResource.remove({
			id : id,
			fileId : fileId
		}).$promise.then(function(response) {
			angular.forEach(service.projects, function(element, index) {
				if (element.id == projectId) {
					service.projects[index] = response;
				}
			});
		}, function(error) {
			console.error(error);
		});
	}
}

function clientResource($resource) {
	return $resource("/client/:id/:page/:size", {}, {
		"update" : {
			method : "PUT"
		},
		"fetch" : {
			method : "GET",
			isArray : true,
			cache : true
		},
		"create" : {
			method : "POST"
		}
	});
}

function clientService(clientResource) {
	var service = {
		clients : [],
		currentClient : {},
		create : create,
		fetch : fetch,
		get : get,
		update : update,
		remove : remove
	};

	return service;

	function create(params) {
		clientResource.create(params).$promise.then(function(response) {
			service.clients.push(response);
			service.currentClient = response;
		});
	}

	function fetch(page, size) {
		clientResource.fetch({
			page : page,
			size : size
		}).$promise.then(function(response) {
			service.clients = response;
		});
	}

	function get(id) {
		clientResource.get({
			id : id
		}).$promise.then(function(response) {
			service.currentClient = response;
		});
	}

	function update(params) {
		clientResource.update({
			id : params.id
		}, params).$promise.then(function(response) {
			angular.forEach(service.clients, function(element, index) {
				if (element.id == params.id) {
					service.clients[index] = element;
				}
			});
		});
	}

	function remove(id) {
		clientResource.remove({
			id : id
		}).$promise.then(function(response) {
			angular.forEach(service.clients, function(element, index) {
				if (element.id == id) {
					service.clients.splice(index, 1);
					return;
				}
			});
		});
	}
}

function progressResource($resource) {
	return $resource("/progress/:projectId/:progressId/:page/:size", {}, {
		"update" : {
			method : "PUT"
		},
		"fetch" : {
			method : "GET",
			isArray : true
		},
		"create" : {
			method : "POST"
		}
	});
}

function progressService(progressResource, $rootScope, fileService,
		fileResource) {
	var service = {
		create : create,
		get : get,
		fetch : fetch,
		update : update,
		remove : remove,
		removeFile : removeFile
	};
	return service;

	function create(projectId, param) {
		fileService.uploadFiles(param.mediaFiles).then(function(data) {
			angular.forEach(data, function(element, index) {
				angular.forEach(param.mediaFiles, function(mf, i) {
					if (mf.id == element.id)
						element.description = mf.description;
				});
			});
			param.mediaFiles = data;
			progressResource.create({
				projectId : projectId
			}, param).$promise.then(function(response) {
				$rootScope.$broadcast("progress-created", {
					progress : response
				});
			}, function(reason) {
				console.error(reason);
			});
		}, function(reason) {
			console.error(reason);
		});
	}

	function get(progressId) {
		progressResource.get({
			progressId : progressId
		}).$promise.then(function(response) {
			$rootScope.$broadcast("progress-getted", {
				progress : response
			});
		}, function(reason) {
			console.log(reason);
		});
	}

	function fetch(projectId, page, size) {
		progressResource.fetch({
			projectId : projectId,
			page : page,
			size : size
		}).$promise.then(function(response) {
			$rootScope.$broadcast("progresses-fetched", response);
		}, function(reason) {
			console.error(reason);
		});
	}

	function update(progressId, param) {
		progressResource.update({
			progressId : progressId
		}, param).$promise.then(function(response) {

		}, function(reason) {
			console.error(reason);
		});
	}

	function remove(projectId, progressId) {
		progressResource.remove({
			projectId : projectId,
			progressId : progressId
		}).$promise.then(null, function(reason) {
			console.error(reason);
		});
	}

	function removeFile(progressId, fileId) {
		fileResource.remove({
			type : "progress",
			ownerId : progressId,
			fileId : fileId
		}).$promise.then(function(response) {
			$rootScope.$broadcast("progress-file-deleted", {
				fileId : fileId
			});
		}, function(reason) {
			console.log(reason);
		});
	}
}