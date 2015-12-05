/**
 * 
 */

"use strict";

angular.module("app", [ "ui.bootstrap-slider", "file-model", "ngResource",
		"ui.bootstrap.datetimepicker", "ui.router" ]);

angular.module("app").config([ "$stateProvider", function($stateProvider) {
	$stateProvider.state("project", {
		url : "/project",
		views : {
			"detail-view" : {
				templateUrl : "management/project-detail"
			},
			"master-view" : {
				templateUrl : "management/project-function",
				controller : "projectFunctionController"
			}
		}
	}).state("client", {
		url : "/client",
		views : {
			"detail-view" : {
				templateUrl : "management/client-detail"
			},
			"master-view" : {
				templateUrl : "management/client-function",
				controller : "clientFunctionController"
			}
		}
	}).state("project.form", {
		url : "/form",
		views : {
			"detail-content" : {
				templateUrl : "management/project-detail-form",
				controller : "projectFormController"
			}
		}
	}).state("client.form", {
		url : "/form",
		views : {
			"detail-content" : {
				templateUrl : "management/client-detail-form",
				controller : "clientFormController"
			}
		}
	}).state("project.content", {
		url : "/content/:id",
		views : {
			"detail-content" : {
				templateUrl : "management/project-detail-content",
				controller : "projectContentController"
			}
		}
	}).state("project.content.data", {
		url : "/data",
		views : {
			"content" : {
				templateUrl : "management/project-detail-content-data",
				controller : "projectDataController"
			}
		}
	}).state("project.content.progresses", {
		url : "/progresses",
		views : {
			"content" : {
				templateUrl : "management/project-detail-content-progresses",
				controller : "projectProgressesController"
			}
		}
	}).state("project.content.messages", {
		url : "/messages",
		views : {
			"content" : {
				templateUrl : "management/project-detail-content-messages",
				controller : "projectMessagesController"
			}
		}
	}).state("project.content.progressForm", {
		url : "/form",
		views : {
			"content" : {
				templateUrl : "management/project-detail-content-progressForm",
				controller : "projectProgressFormController"
			}
		}
	}).state("project.content.progress", {
		url : "/progress/:progressId",
		views : {
			"content" : {
				templateUrl : "management/project-detail-content-progress",
				controller : "projectProgressController"
			}
		}
	}).state("client.data", {
		url : "/detail/:id",
		views : {
			"detail-content" : {
				templateUrl : "management/client-detail-data",
				controller : "clientDataController"
			}
		}
	});
} ]);