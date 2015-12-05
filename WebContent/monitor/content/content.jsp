<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div>
	<p style='white-space: pre-line; word-wrap: break-word;'>{{description}}</p>
	<small ng-show='isBrief'><a href='javascript:void(0)'
		ng-click='showComplete()'>{{tips}}</a></small>
	<hr />
	<div class="panel-group" id="accordion" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-default" ng-repeat="mediaFile in mediaFiles">
			<div class="panel-heading" role="tab"
				id="heading{{mediaFile.id}}">
				<h4 class="panel-title">
					<a role="button" data-toggle="collapse" data-parent="#accordion"
						href="javascript:void(0)" aria-expanded="true"
						aria-controls="collapse{{mediaFile.id}}"
						ng-click="collapse(mediaFile.id)">
						{{mediaFile.name}} </a>
				</h4>
			</div>
			<div id="collapse{{mediaFile.id}}"
				class="panel-collapse collapse" role="tabpanel"
				aria-labelledby="heading{{mediaFile.id}}">
				<div class="panel-body">
					<div class="thumbnail" ng-show="{{mediaFile.type | isImage}}">
						<img ng-src="{{mediaFile.url}}" alt="{{mediaFile.name}}">
						<div class="caption">
							<p style='white-space: pre-line; word-wrap: break-word;'>{{mediaFile.description}}</p>
						</div>
					</div>
					<div ng-show="{{mediaFile.type | isVideo}}">
						<video class="col-lg-12 col-md-12" controls
							ng-src="{{mediaFile.url | trustUrl}}"></video>
						<div class="caption">
							<p style='white-space: pre-line; word-wrap: break-word;'>{{mediaFile.description}}</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>