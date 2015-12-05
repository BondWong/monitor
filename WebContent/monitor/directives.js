/**
 * 
 */

"use strict";

angular
		.module("app")
		.directive(
				"timelineContent",
				function() {
					return {
						restrict : "E",
						templateUrl : "monitor-content",
						replace : true,
						scope : {
							event : "="
						},
						link : function(scope, element, attribute) {
							scope.isBrief = scope.event.progress.description.length > 140 ? true
									: false;
							var paragraphs = scope.event.progress.description
									.split("\n");
							scope.description = "";
							angular
									.forEach(
											paragraphs,
											function(element, index) {
												if (scope.description.length < 140)
													scope.description += element
															+ "\n";
												else {
													scope.description += "...";
													return;
												}
											});
							scope.tips = "展开";
							scope.temp = "";
							scope.mediaFiles = scope.event.progress.mediaFiles;
							scope.showComplete = showComplete;
							scope.collapse = collapse;

							function showComplete() {
								if (scope.tips == "展开") {
									scope.tips = "收起";
									scope.temp = scope.description;
									scope.description = scope.event.progress.description;
								} else {
									scope.tips = "展开";
									scope.description = scope.temp;
								}
							}

							function collapse(id) {
								angular.element("#collapse" + id).collapse(
										'toggle');
							}
						}
					};
				})
		.directive('draggable', function($document) {
			return function(scope, element, attr) {
				var startX = 0, startY = 0;
				var y = element.css("top"), x = element.css("left");
				y = parseInt(y.substr(0, y.indexOf("px")));
				x = parseInt(x.substr(0, x.indexOf("px")));
				element.on('mousedown', function(event) {
					// Prevent default dragging of selected content
					event.preventDefault();
					startX = event.screenX - x;
					startY = event.screenY - y;
					$document.on('mousemove', mousemove);
					$document.on('mouseup', mouseup);
				});

				function mousemove(event) {
					y = event.screenY - startY;
					x = event.screenX - startX;
					element.css({
						top : y + 'px',
						left : x + 'px'
					});
				}

				function mouseup() {
					$document.off('mousemove', mousemove);
					$document.off('mouseup', mouseup);
				}
			};
		})
		.directive(
				"feedback",
				[
						"$rootScope",
						"messageService",
						"$filter",
						"clientService",
						"fileService",
						function($rootScope, messageService, $filter,
								clientService, fileService) {
							return {
								restrcit : "E",
								replace : true,
								templateUrl : "monitor-feedback",
								scope : {},
								link : function(scope, element, attribute) {
									scope.clientService = clientService;
									scope.service = messageService;
									scope.friendlyDateTime = $filter("friendly");
									scope.messageToSend = "";
									scope.chatHistory = angular
											.element('.chat-history');
									scope.textarea = angular
											.element('#message-to-send');
									scope.chatHistoryList = scope.chatHistory
											.find('ul');
									scope.fileInput = angular
											.element("#feedbackFile");
									scope.send = send;
									scope.fetch = scope.service.fetch;
									scope.scrollToBottom = scrollToBottom;
									scope.focus = focus;
									scope.showFileDialog = showFileDialog;
									scope.file = {};
									scope.client = {};

									// javascript way
									scope.fileInput
											.change(function() {
												scope.file = scope.fileInput[0].files[0];
												scope.file.id = moment()
														.valueOf()
														+ "";
												fileService
														.uploadFile(scope.file);
											});

									scope
											.$on(
													"monitor-fileupload-success",
													function(event, fileRep) {
														scope.service
																.create(
																		scope.client.projectId,
																		{
																			content : fileRep.name,
																			publisher : "client",
																			id : moment()
																					.valueOf()
																					+ "",
																			dateTime : moment()
																					.valueOf(),
																			attributes : {
																				bootstrapStatus : "danger",
																			},
																			file : fileRep
																		});
													});
									scope.$on("monitor-fileupload-fail",
											function(event, error) {
												console.error(error);
											});
									scope.$on("monitor-fileupload-progress",
											function(event, progress) {
												console.log(progress);
											});

									scope.$on("monitor-message-created",
											function(event, data) {
												render(data, "append");
											});

									scope.$on("monitor-messages-fetched",
											function(event, data) {
												angular.forEach(data, function(
														element, index) {
													render(element, "prepend");
												});
											});

									scope.$on("monitor-client-getted",
											function(event, data) {
												scope.client = data;
												scope.fetch(data.projectId, 0,
														20);
											});

									scope.clientService
											.get($rootScope.clientId);

									function send() {
										scope.scrollToBottom();
										if (scope.messageToSend.trim() !== '') {
											var message = {
												content : scope.messageToSend,
												publisher : "client",
												id : moment().valueOf() + "",
												dateTime : moment().valueOf(),
												attributes : {
													bootstrapStatus : "danger",
												}
											};
											scope.service.create(
													scope.client.projectId,
													message);
											scope.messageToSend = "";
										}
									}

									function render(message, means) {
										if (message.content.trim() == '')
											return;
										var dateTime = scope
												.friendlyDateTime(message.dateTime);
										var wrapper = "";
										if (message.publisher == "client") {
											if (message.file == null) {
												wrapper = '<li class="clearfix"><div class="message-data align-right"><span class="message-data-time" >'
														+ dateTime
														+ '</span> &nbsp; &nbsp;<span class="message-data-name" >You</span> <i class="fa fa-circle me"></i></div><div class="message other-message float-right" style="white-space: pre-line; word-wrap: break-word;"">'
														+ message.content
														+ '</div></li>'
											} else {
												console.log(message.file.url);
												wrapper = '<li class="clearfix"><div class="message-data align-right"><span class="message-data-time" >'
														+ dateTime
														+ '</span> &nbsp; &nbsp;<span class="message-data-name" >You</span> <i class="fa fa-circle me"></i></div><div class="message other-message float-right" style="white-space: pre-line; word-wrap: break-word;""><a href="/'
														+ message.file.url
														+ '" target="_blank">'
														+ message.content
														+ '</a></div></li>'
											}
										} else {
											wrapper = '<li><div class="message-data"><span class="message-data-name"> HuntingWeb</span><span class="message-data-time" >'
													+ dateTime
													+ '</span> &nbsp; &nbsp;</div><div class="message my-message">'
													+ message.content
													+ '</div></li>'
										}
										if (means == "prepend")
											scope.chatHistoryList
													.prepend(wrapper);
										else
											scope.chatHistoryList
													.append(wrapper);
										scope.scrollToBottom();
									}

									function scrollToBottom() {
										scope.chatHistory
												.scrollTop(scope.chatHistory[0].scrollHeight);
									}

									function focus() {
										scope.textarea.focus();
									}

									function showFileDialog() {
										scope.fileInput.click();
									}

								}
							}
						} ]);