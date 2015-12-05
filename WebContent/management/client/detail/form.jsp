<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form>
	<div class="form-group">
		<label for="clientName">Client name</label> <input type="text"
			class="form-control" id="clientName" placeholder="Client name"
			ng-model="data.name" />
	</div>
	<div class="form-group">
		<label for="clientPassword">Client password</label> <input
			type="password" id="clientPassword" class="form-control"
			ng-model="data.password" placeholder="Client password" />
	</div>
	<div class="form-group">
		<label for="clientProject">Client project</label> <select
			id="clientProject" class="form-control" ng-model="data.project"
			ng-options="project.id as project.name for project in projects"></select>
	</div>
	<div class="form-group">
		<label for="clientPhone">Client Phone</label> <input type="text"
			class="form-control" id="clientPhone" ng-model="data.phone"
			placeholder="Client phone" /> <label for="addressStreet">Street</label>
		<input type="text" class="form-control" id="addressStreet"
			ng-model="data.address.street" placeholder="Address street" /> <label
			for="addressCity">City</label> <input type="text"
			class="form-control" id="addressCity" ng-model="data.address.city"
			placeholder="Address city" /> <label for="addressState">State</label>
		<input type="text" class="form-control" id="addressState"
			ng-model="data.address.state" placeholder="Address state" /> <label
			for="addressZip">Zip</label> <input type="text" class="form-control"
			id="addressZip" ng-model="data.address.zip" placeholder="Address zip" />
		<label for="addressCountry">Country</label> <input type="text"
			class="form-control" id="addressCountry"
			ng-model="data.address.country" placeholder="Address country" />
	</div>
	<button type="button" class="btn btn-default"
		ng-click="createClient(data)">Submit</button>
</form>