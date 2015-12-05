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
				templateUrl : "project-detail"
			},
			"master-view" : {
				templateUrl : "project-function",
				controller : "projectFunctionController"
			}
		}
	}).state("client", {
		url : "/client",
		views : {
			"detail-view" : {
				templateUrl : "client-detail"
			},
			"master-view" : {
				templateUrl : "client-function",
				controller : "clientFunctionController"
			}
		}
	}).state("project.form", {
		url : "/form",
		views : {
			"detail-content" : {
				templateUrl : "project-detail-form",
				controller : "projectFormController"
			}
		}
	}).state("client.form", {
		url : "/form",
		views : {
			"detail-content" : {
				templateUrl : "client-detail-form",
				controller : "clientFormController"
			}
		}
	}).state("project.content", {
		url : "/content/:id",
		views : {
			"detail-content" : {
				templateUrl : "project-detail-content",
				controller : "projectContentController"
			}
		}
	}).state("project.content.data", {
		url : "/data",
		views : {
			"content" : {
				templateUrl : "project-detail-content-data",
				controller : "projectDataController"
			}
		}
	}).state("project.content.progresses", {
		url : "/progresses",
		views : {
			"content" : {
				templateUrl : "project-detail-content-progresses",
				controller : "projectProgressesController"
			}
		}
	}).state("project.content.messages", {
		url : "/messages",
		views : {
			"content" : {
				templateUrl : "project-detail-content-messages",
				controller: "projectMessagesController"
			}
		}
	}).state("project.content.progressForm", {
		url : "/form",
		views : {
			"content" : {
				templateUrl : "project-detail-content-progressForm",
				controller : "projectProgressFormController"
			}
		}
	}).state("project.content.progress", {
		url : "/progress/:progressId",
		views : {
			"content" : {
				templateUrl : "project-detail-content-progress",
				controller : "projectProgressController"
			}
		}
	}).state("client.data", {
		url : "/detail/:id",
		views : {
			"detail-content" : {
				templateUrl : "client-detail-data",
				controller : "clientDataController"
			}
		}
	});
} ]);