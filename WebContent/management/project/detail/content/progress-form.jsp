<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form>
	<div class="form-group">
		<label for="progressName">Progress name</label> <input type="text"
			class="form-control" id="progressName" placeholder="Progress name"
			ng-model="data.name" />
	</div>
	<div class="form-group">
		<label for="progressFile">File input</label> <input type="file"
			id="progressFile" style="visibility: hidden;" file-model="file" />
		<div ng-repeat="file in data.mediaFiles">
			<p>
				<a role="button" href="javascript:void(0)"
					ng-click="toggleTextarea(file.id)">{{file.name}}</a> <a
					role="button" ng-click="removeFile(file.id)"
					class="col-lg-offset-3 col-md-offset-3"><span class="badge">&times;</span></a>
			</p>
			<div class="modal fade"
				ng-attr-id="{{'mediaFileTextarea' + file.id}}" tabindex="-1"
				role="dialog" aria-labelledby="mediaFileTextareaLabel">
				<div class="modal-dialog" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title" id="mediaFileTextareaLabel">Input
								MediaFile Description</h4>
						</div>
						<div class="modal-body">
							<form>
								<div class="form-group">
									<textarea rows="4" style="resize: none;" class="form-control"
										ng-attr-id="{{'mediaFileTextarea' + file.id}}"
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
		<a class="btn btn-default" role="button"
			ng-click="showFileDialog('progressFile')">Add <span
			class="glyphicon glyphicon-open-file" aria-hidden="true"></span>
		</a>
		<p class="help-block">Upload files.</p>
	</div>
	<div class="form-group">
		<label for="progressDescription">Progress Description</label>
		<textarea class="form-control" rows="5" ng-model="data.description"></textarea>
	</div>
	<button type="button" class="btn btn-default" ng-click="create()">Submit</button>
</form>
