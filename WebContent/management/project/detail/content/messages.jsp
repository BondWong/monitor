<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="panel-group" id="accordion" role="tablist"
	aria-multiselectable="true" ng-if="messages">
	<div class="panel panel-{{message.attributes.bootstrapStatus}}"
		ng-repeat="message in messages">
		<div class="panel-heading" role="tab" id="message.id">
			<h4 class="panel-title">
				<a role="button" data-toggle="collapse" data-parent="#accordion"
					ng-click="toggle(message.id)" aria-expanded="true"
					aria-controls="{{message.id}}"> Message </a> <small
					class="col-lg-offset-1 col-lg-offset-1">{{message.dateTime
					| friendly}}</small> <small ng-click="markAsRead(message.id)"><a
					role="button" class="close pull-right" aria-label="Close"> <span
						aria-hidden="true">mark as read</span>
				</a></small>
			</h4>
		</div>
		<div id="{{message.id}}" class="panel-collapse collapse"
			role="tabpanel" aria-labelledby="{{message.id}}">
			<div class="panel-body">
				<a ng-if="message.file.url" ng-href="/{{message.file.url}}"
					target="_blank"> {{message.content}}</a>
				<p ng-if="!message.file.url">{{message.content}}</p>
				<form>
					<div class="form-group">
						<textarea class="form-control" rows="3" style="resize: none;"
							ng-attr-id="{{'reply-textarea' + message.id}}"></textarea>
					</div>
					<button type="submit" class="btn btn-success"
						ng-click="reply(message.id)">Reply</button>
				</form>
			</div>
		</div>
	</div>
</div>