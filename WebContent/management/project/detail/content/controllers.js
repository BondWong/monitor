/**
 * 
 */

"use strict";

angular.module("app")
		.controller("projectDataController", projectDataController).controller(
				"projectProgressesController", projectProgressesController)
		.controller("projectProgressFormController",
				projectProgressFormController).controller(
				"projectProgressController", projectProgressController)
		.controller("projectMessagesController", projectMessagesController);

projectDataController.$inject = [ "$scope", "$stateParams", "projectService",
		"projectTypes" ];
projectProgressesController.$inject = [ "$scope", "$state", "$stateParams",
		"progressService" ];
projectProgressController.$inject = [ "$scope", "$state", "$stateParams",
		"progressService" ];
projectMessagesController.$inject = [ "$scope", "$stateParams",
		"messageService" ];

function projectMessagesController($scope, $stateParams, messageService) {
	$scope.service = messageService;
	$scope.fetch = messageService.fetch;
	$scope.toggle = toggle;
	$scope.reply = reply;
	$scope.update = messageService.update;
	$scope.markAsRead = markAsRead;

	$scope.$on("management-project-messages-fetched", function(event, data) {
		$scope.messages = data;
	});

	$scope.$on("management-project-message-created", function(event, id) {
		$scope.update(id, {
			attributes : {
				bootstrapStatus : "default"
			}
		});
	});

	$scope.$on("management-project-message-updated", function(event, id) {
		angular.forEach($scope.messages, function(element, index) {
			if (element.id == id) {
				$scope.messages[index].attributes.bootstrapStatus = "default";
			}
		});
	});

	$scope.fetch($stateParams.id, "client", 0, 20);

	function toggle(id) {
		angular.element("#" + id).collapse("toggle");
	}

	function reply(messageId) {
		var content = angular.element("#reply-textarea" + messageId).val();
		var params = {
			content : content,
			publisher : "admin",
			id : moment().valueOf() + "",
			dateTime : moment().valueOf(),
			attributes : {}
		};
		$scope.service.create($stateParams.id, messageId, params);
	}

	function markAsRead(messageId) {
		$scope.update(messageId, {
			attributes : {
				bootstrapStatus : "default"
			}
		});
	}

}

function projectDataController($scope, $stateParams, projectService,
		projectTypes) {
	$scope.types = projectTypes;
	$scope.service = projectService;
	$scope.project = $scope.service.currentProject;

	$scope.getProject = $scope.service.getProject;
	$scope.updateProject = updateProject;
	$scope.removeFile = removeFile;

	function updateProject() {
		var project = {};
		angular.copy($scope.project, project);
		project.deadline = project.deadline.valueOf();
		project.value = parseInt(project.value);
		$scope.service.updateProject(project);
	}

	function removeFile(id) {
		angular.forEach($scope.project.files, function(element, index) {
			if (element.id == id) {
				$scope.project.files.splice(index, 1);
				$scope.service.removeFile($scope.project.id, id);
			}
		});
	}

	$scope.$watch("service.currentProject", function(newVal, oldVal) {
		if (newVal != oldVal) {
			$scope.project = newVal;
			$scope.project.deadline = moment($scope.project.deadline);
		}
	});

	$scope.getProject($stateParams.id);
}

function projectProgressesController($scope, $state, $stateParams,
		progressService) {
	$scope.service = progressService;
	$scope.progresses = [];
	$scope.page = 0;
	$scope.size = 20;
	$scope.toCreate = toCreate;
	$scope.toDetail = toDetail;
	$scope.loadMore = loadMore;
	$scope.remove = remove;

	function toCreate() {
		$state.go("project.content.progressForm");
	}

	function toDetail(id) {
		$state.go("project.content.progress", {
			progressId : id
		});
	}

	function loadMore() {
		$scope.service.fetch($stateParams.id, $scope.page, $scope.size);
	}

	function remove(progressId) {
		$scope.service.remove($stateParams.id, progressId);
		angular.forEach($scope.progresses, function(element, index) {
			if (element.id == progressId) {
				$scope.progresses.splice(index, 1);
				return;
			}
		})
	}

	$scope.$on("progresses-fetched", function(event, data) {
		if (data == null || data.length == 0)
			return;
		$scope.page++;
		$scope.progresses = data;
	});

	$scope.service.fetch($stateParams.id, $scope.page, $scope.size);
}

function projectProgressFormController($scope, $state, $stateParams,
		progressService) {
	$scope.service = progressService;
	$scope.create = create;
	$scope.removeFile = removeFile;
	$scope.showFileDialog = showFileDialog;
	$scope.toggleTextarea = toggleTextarea;
	$scope.data = {
		id : moment().valueOf() + "",
		name : "",
		mediaFiles : [],
		description : ""
	};
	$scope.file = {};

	function create() {
		$scope.service.create($stateParams.id, $scope.data);
	}

	function removeFile(id) {
		angular.forEach($scope.data.mediaFiles, function(element, index) {
			if (element.id == id) {
				$scope.data.mediaFiles.splice(index, 1);
				return;
			}
		});
	}

	function showFileDialog(id) {
		angular.element("#" + id).click();
	}

	function toggleTextarea(id) {
		angular.element("#mediaFileTextarea" + id).modal("toggle");
	}

	$scope.$watch("file", function(newVal, oldVal) {
		if (newVal != oldVal) {
			newVal.id = moment().valueOf() + "";
			$scope.data.mediaFiles.push(newVal);
		}
	});

	$scope.$on("progress-created", function(event, data) {
		$scope.data = {
			id : moment().valueOf() + "",
			name : "",
			mediaFiles : [],
			description : ""
		};
		$state.go("project.content.progresses");
	});
}

function projectProgressController($scope, $state, $stateParams,
		progressService) {
	$scope.service = progressService;
	$scope.progress = {};
	$scope.get = $scope.service.get;
	$scope.update = update;
	$scope.back = back;
	$scope.removeFile = removeFile;
	$scope.showFileDialog = showFileDialog;
	$scope.showDescription = showDescription;

	$scope.get($stateParams.progressId);

	function update() {
		$scope.service.update($stateParams.progressId, $scope.progress);
	}

	function back() {
		$state.go("project.content.progresses");
	}

	function removeFile(id) {
		$scope.service.removeFile($scope.progress.id, id);
	}

	function showFileDialog(domId) {
		angular.element("#" + domId).clic();
	}

	function showDescription(id) {
		console.log(id);
		angular.element("#progressMediaFileTextarea" + id).modal("toggle");
	}

	$scope.$on("progress-getted", function(event, data) {
		$scope.progress = data.progress;
	});

	$scope.$on("progress-file-deleted", function(event, data) {
		var id = data.fileId;
		angular.forEach($scope.progress.mediaFiles, function(element, index) {
			if (element.id == id) {
				$scope.progress.mediaFiles.splice(index, 1);
			}
		});
	});
}
