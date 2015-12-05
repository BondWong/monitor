<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form>
	<div class="form-group">
		<label for="progressName">Progress name</label> <input type="text"
			class="form-control" id="progressName" placeholder="Progress name"
			ng-model="progress.name" />
	</div>
	<div class="form-group">
		<label for="progressFile">Files</label>
		<div ng-repeat="file in progress.mediaFiles" id="progressFile">
			<p>
				<a role="button" ng-href="/{{file.url}}" target="_blank">{{file.name}}</a><a
					role="button" ng-click="showDescription(file.id)"
					class="btn btn-success col-lg-offset-1 col-md-offset-1"><span
					class="badge">description</span></a> <a role="button"
					ng-click="removeFile(file.id)"
					class="col-lg-offset-2 col-md-offset-2"><span class="badge">&times;</span></a>
			</p>
			<div class="modal fade"
				ng-attr-id="{{'progressMediaFileTextarea' + file.id}}"
				tabindex="-1" role="dialog"
				aria-labelledby="progressMediaFileTextareaLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="progressMediaFileTextareaLabel">Input
								MediaFile Description</h4>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<textarea rows="4" style="resize: none;" class="form-control"
										ng-attr-id="{{'progressMediaFileTextarea' + file.id}}"
										ng-model="file.description"></textarea>
								</div>
							</form>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary"
								data-dismiss="modal">Save</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		<p class="help-block">Uploaded files.</p>
	</div>
	<div class="form-group">
		<label for="progressDescription">Progress Description</label>
		<textarea class="form-control" rows="5"
			ng-model="progress.description"></textarea>
	</div>
	<button type="button" class="btn btn-default" ng-click="update()">Update</button>
	<button type="button" class="btn btn-default" ng-click="back()">Back</button>
</form>
